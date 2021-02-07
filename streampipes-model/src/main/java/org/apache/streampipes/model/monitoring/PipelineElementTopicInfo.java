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
package org.apache.streampipes.model.monitoring;

public class PipelineElementTopicInfo {

  private String topicName;

  private Long currentOffset;
  private Long latestOffset;
  private Long offsetAtPipelineStart;

  public PipelineElementTopicInfo() {

  }

  public String getTopicName() {
    return topicName;
  }

  public void setTopicName(String topicName) {
    this.topicName = topicName;
  }

  public Long getCurrentOffset() {
    return currentOffset;
  }

  public void setCurrentOffset(Long currentOffset) {
    this.currentOffset = currentOffset;
  }

  public Long getLatestOffset() {
    return latestOffset;
  }

  public void setLatestOffset(Long latestOffset) {
    this.latestOffset = latestOffset;
  }

  public Long getOffsetAtPipelineStart() {
    return offsetAtPipelineStart;
  }

  public void setOffsetAtPipelineStart(Long offsetAtPipelineStart) {
    this.offsetAtPipelineStart = offsetAtPipelineStart;
  }
}