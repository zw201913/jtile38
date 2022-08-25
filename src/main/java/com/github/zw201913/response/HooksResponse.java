package com.github.zw201913.response;

import lombok.Getter;
import lombok.ToString;

import java.util.Map;

/**
 * @author zouwei
 * @className HooksResponse
 * @date: 2022/8/23 下午5:35
 * @description:
 */
@Getter
@ToString(callSuper = true)
public class HooksResponse extends BaseResponse {
	private Hook[] hooks;

	@Getter
	@ToString
	public static class Hook {
		private String name;
		private String key;
		private int ttl;
		private String[] endpoints;
		private String[] command;
		private Map<String, String> meta;
	}
}
