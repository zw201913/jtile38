package com.github.zw201913.response;

import lombok.Getter;
import lombok.ToString;

/**
 * @author zouwei
 * @className ScriptLoadResponse
 * @date: 2022/8/24 上午10:32
 * @description:
 */
@Getter
@ToString(callSuper = true)
public class ScriptLoadResponse extends BaseResponse {

	private String result;
}
