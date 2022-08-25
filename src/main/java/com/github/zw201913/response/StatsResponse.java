package com.github.zw201913.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

/**
 * @author zouwei
 * @className StatsResponse
 * @date: 2022/8/24 下午9:56
 * @description:
 */
@Getter
@ToString(callSuper = true)
public class StatsResponse extends BaseResponse {

	private Stats[] stats;

	@Getter
	@ToString
	public static class Stats {
		@JsonProperty("in_memory_size")
		private long inMemorySize;

		@JsonProperty("num_objects")
		private long numObjects;

		@JsonProperty("num_points")
		private long numPoints;

		@JsonProperty("num_strings")
		private long numStrings;
	}
}
