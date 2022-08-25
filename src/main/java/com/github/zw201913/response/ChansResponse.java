package com.github.zw201913.response;

import lombok.Getter;
import lombok.ToString;

import java.util.Map;

/**
 * @author zouwei
 * @className ChansResponse
 * @date: 2022/8/23 下午4:36
 * @description:
 */
@Getter
@ToString(callSuper = true)
public class ChansResponse extends BaseResponse {

	private Chan[] chans;

	@Getter
	@ToString
	public static class Chan {
		private String name;
		private String key;
		private int ttl;
		private String[] command;
		private Map<String, String> meta;
	}
}
