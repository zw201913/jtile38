package com.github.zw201913.enums;

/**
 * @author zouwei
 * @className InfType
 * @date: 2022/8/17 上午10:14
 * @description:
 */
public enum InfType {

	GREATER_THAN("+inf"), LESS_THAN("-inf");

	private final String inf;

	InfType(final String inf) {
		this.inf = inf;
	}

	public String getInf() {
		return this.inf;
	}
}
