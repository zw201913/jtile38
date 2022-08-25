package com.github.zw201913.response;

import lombok.Getter;
import lombok.ToString;

/**
 * @author zouwei
 * @className ScriptExistsResponse
 * @date: 2022/8/24 上午10:22
 * @description:
 */
@Getter
@ToString(callSuper = true)
public class ScriptExistsResponse extends BaseResponse {

	private int[] result;

}
