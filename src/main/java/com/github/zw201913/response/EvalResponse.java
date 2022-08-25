package com.github.zw201913.response;

import lombok.Getter;
import lombok.ToString;

/**
 * @author zouwei
 * @className EvalResponse
 * @date: 2022/8/25 下午4:06
 * @description:
 */
@Getter
@ToString(callSuper = true)
public class EvalResponse extends BaseResponse {

	private Object result;
}
