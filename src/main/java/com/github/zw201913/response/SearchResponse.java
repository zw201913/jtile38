package com.github.zw201913.response;

import lombok.Getter;
import lombok.ToString;

/**
 * @author zouwei
 * @className SearchResponse
 * @date: 2022/8/25 下午2:51
 * @description:
 */
@Getter
@ToString(callSuper = true)
public class SearchResponse extends BaseResponse {
	private String[] fields;

	private long count;

	private long cursor;

	private DataObject[] objects;

	private String[] ids;

	@Getter
	@ToString
	public static class DataObject {
		private String id;
		private String object;
		private double[] fields;
	}
}
