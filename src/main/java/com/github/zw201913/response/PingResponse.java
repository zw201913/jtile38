package com.github.zw201913.response;

import lombok.Getter;
import lombok.ToString;

/**
 * @author zouwei
 * @className PingResponse
 * @date: 2022/8/23 下午10:45
 * @description:
 */
@Getter
@ToString(callSuper = true)
public class PingResponse extends BaseResponse {

	private String ping;
}
