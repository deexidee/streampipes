package de.fzi.cep.sepa.flink.samples;

import java.util.Arrays;

import de.fzi.cep.sepa.desc.ModelSubmitter;
import de.fzi.cep.sepa.flink.samples.elasticsearch.ElasticSearchController;
import de.fzi.cep.sepa.flink.samples.wordcount.WordCountController;

public class Init {

	public static void main(String[] args)
	{
		ModelSubmitter.submitMixed(Arrays.asList(new WordCountController(), new ElasticSearchController()), 8094);
		
	}
}
