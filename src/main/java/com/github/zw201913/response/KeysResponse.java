package com.github.zw201913.response;

import lombok.Getter;
import lombok.ToString;

/**
 * @author zouwei
 * @className KeysResponse
 * @date: 2022/8/23 下午8:07
 * @description:
 */
@Getter
@ToString(callSuper = true)
public class KeysResponse extends BaseResponse {

	private String[] keys;
}
