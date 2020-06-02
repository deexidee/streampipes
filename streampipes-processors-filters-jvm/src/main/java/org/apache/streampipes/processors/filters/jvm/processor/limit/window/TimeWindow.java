/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package org.apache.streampipes.processors.filters.jvm.processor.limit.window;

import com.google.common.util.concurrent.AbstractScheduledService;
import org.apache.streampipes.model.runtime.Event;
import org.apache.streampipes.processors.filters.jvm.processor.limit.util.EventSelection;
import org.apache.streampipes.wrapper.routing.SpOutputCollector;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TimeWindow extends AbstractScheduledService implements Window {
    private Integer windowSize;
    private EventSelection eventSelection;
    private SpOutputCollector outputCollector;
    private List<Event> events;

    public TimeWindow(Integer windowSize,
                      EventSelection eventSelection,
                      SpOutputCollector outputCollector) {
        this.windowSize = windowSize;
        this.eventSelection = eventSelection;
        this.outputCollector = outputCollector;
        this.events = new ArrayList<>();
        startAsync();
    }

    @Override
    public void onEvent(Event event) {
        events.add(event);
    }

    @Override
    public void destroy() {
        events.clear();
        stopAsync();
    }

    @Override
    protected void runOneIteration() {
        process();
    }

    @Override
    protected Scheduler scheduler() {
        return Scheduler.newFixedRateSchedule(0, windowSize, TimeUnit.MILLISECONDS);
    }

    private void process() {
        if (!events.isEmpty()) {
            switch (eventSelection) {
                case FIRST:
                    emit(events.get(0));
                    break;
                case LAST:
                    emit(events.get(events.size() - 1));
                    break;
                case ALL:
                    events.forEach(this::emit);
                    break;
            }
            events.clear();
        }
    }

    private void emit(Event e) {
        outputCollector.collect(e);
    }
}
