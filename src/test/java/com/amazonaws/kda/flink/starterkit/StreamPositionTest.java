package com.amazonaws.kda.flink.starterkit;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import com.amazonaws.kda.flink.starterkit.SessionUtil;
import com.amazonaws.kda.flink.starterkit.StreamPosition;

class StreamPositionTest {
	
	@Test
	void test_latest_position() {
		String streamInitPosition = "LATEST";
		boolean isStreamInitPositionValid = Arrays.stream(StreamPosition.values()).anyMatch((t) -> t.name().equals(streamInitPosition));
		assertTrue(isStreamInitPositionValid);
	}
	
	@Test
	void test_trim_horizon_position() {
		String streamInitPosition = "TRIM_HORIZON";
		boolean isStreamInitPositionValid = Arrays.stream(StreamPosition.values()).anyMatch((t) -> t.name().equals(streamInitPosition));
		assertTrue(isStreamInitPositionValid);
	}
	
	@Test
	void test_at_timestamp_position() {
		String streamInitPosition = "AT_TIMESTAMP";
		boolean isStreamInitPositionValid = Arrays.stream(StreamPosition.values()).anyMatch((t) -> t.name().equals(streamInitPosition));
		assertTrue(isStreamInitPositionValid);
	}
	
	@Test
	void test_at_wrong_position() {
		String streamInitPosition = "none";
		boolean isStreamInitPositionValid = Arrays.stream(StreamPosition.values()).anyMatch((t) -> t.name().equals(streamInitPosition));
		assertTrue(!isStreamInitPositionValid);
	}
	
	@Test
	void test() {
		boolean isTimestampValid = false;
		String streamInitPosition = "AT_TIMESTAMP";
		String streaminitialTimeStamp = "2020-08-24T13:50:00.000-00:00";
		
		boolean isStreamInitPositionValid = Arrays.stream(StreamPosition.values()).anyMatch((t) -> t.name().equals(streamInitPosition));
		
		if(isStreamInitPositionValid && streamInitPosition.equalsIgnoreCase(StreamPosition.AT_TIMESTAMP.name())) {
			isTimestampValid = SessionUtil.validateDate(streaminitialTimeStamp);
		}
		assertTrue(isTimestampValid);
	}
	
	
	
	

}
