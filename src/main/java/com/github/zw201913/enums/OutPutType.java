package com.github.zw201913.enums;

/**
 * @author zouwei
 * @className OutPutType
 * @date: 2022/8/12 下午5:58
 * @description:
 */
public enum OutPutType {
	JSON("json"),
	RESP("resp");

	OutPutType(final String type) {
		this.type = type;
	}

	private final String type;

	public String getType() {
		return type;
	}
}
