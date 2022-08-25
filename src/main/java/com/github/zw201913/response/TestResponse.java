package com.github.zw201913.response;

import lombok.Getter;
import lombok.ToString;

/**
 * @author zouwei
 * @className TestResponse
 * @date: 2022/8/25 下午4:23
 * @description:
 */
@Getter
@ToString(callSuper = true)
public class TestResponse extends BaseResponse {
	private boolean result;
}
