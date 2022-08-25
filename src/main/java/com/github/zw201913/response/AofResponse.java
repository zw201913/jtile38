package com.github.zw201913.response;

import lombok.ToString;

/**
 * @author zouwei
 * @className AofResponse
 * @date: 2022/8/22 下午5:36
 * @description:
 */
@ToString(callSuper = true)
public class AofResponse extends BaseResponse {

	protected AofResponse(boolean ok) {
		super(ok);
	}

	public static AofResponse success() {
		return new AofResponse(Boolean.TRUE);
	}
}
