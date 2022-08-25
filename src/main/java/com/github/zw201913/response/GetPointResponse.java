package com.github.zw201913.response;

import lombok.Getter;
import lombok.ToString;

import java.util.Map;

/**
 * @author zouwei
 * @className GetPointResponse
 * @date: 2022/8/25 下午2:23
 * @description:
 */
@Getter
@ToString(callSuper = true)
public class GetPointResponse extends BaseResponse {
	private Map<String, Double> fields;

	private Point point;

	@Getter
	@ToString
	public static class Point {
		private double lat;
		private double lon;
		private long z;
	}
}
