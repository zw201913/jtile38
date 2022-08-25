package com.github.zw201913.response;

import lombok.Getter;
import lombok.ToString;

import java.util.Map;

/**
 * @author zouwei
 * @className GetBoundsResponse
 * @date: 2022/8/25 下午2:30
 * @description:
 */
@Getter
@ToString(callSuper = true)
public class GetBoundsResponse extends BaseResponse {

	private Map<String, Double> fields;

	private Bound bounds;

	@Getter
	@ToString
	public static class Bound {
		private coordinate sw;
		private coordinate ne;
	}

	@Getter
	@ToString
	public static class coordinate {
		private double lat;
		private double lon;
	}
}