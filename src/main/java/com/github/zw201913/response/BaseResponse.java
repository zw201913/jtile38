package com.github.zw201913.response;

import lombok.Getter;
import lombok.ToString;

/**
 * @author zouwei
 * @className BaseResponse
 * @date: 2022/8/12 下午8:53
 * @description:
 */
@Getter
@ToString
abstract class BaseResponse {

	private boolean ok;

	private String elapsed;

	private String err;

	protected void setElapsed(String elapsed) {
		this.elapsed = elapsed;
	}

	protected BaseResponse(boolean ok) {
		this.ok = ok;
	}

	protected BaseResponse() {
	}
}
