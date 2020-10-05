package com.amazonaws.kda.flink.starterkit;

import org.apache.flink.api.common.functions.AggregateFunction;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.ObjectMapper;

/**
 * This class implements AggregateFunction. It aggregates multiple events into a
 * composite event and it is applied on top of Flink Session window.
 * 
 * @author Ravi Itha, Amazon Web Services, Inc.
 *
 */
public class Aggregator implements AggregateFunction<Event, String, String> {

	private static final long serialVersionUID = -8528772774907786176L;

	@Override
	public String createAccumulator() {
		return new String();
	}

	@Override
	public String add(Event value, String accumulator) {
		String newAccumulator = null;
		try {
			newAccumulator =  new String(accumulator).concat("$").concat(new ObjectMapper().writeValueAsString(value));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return newAccumulator;
	}

	@Override
	public String getResult(String accumulator) {
		return accumulator.toString();
	}

	@Override
	public String merge(String a, String b) {
		return new  String(a).concat("$").concat(b);
	}

}
