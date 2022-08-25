package com.github.zw201913.response;

import lombok.Getter;
import lombok.ToString;

/**
 * @author zouwei
 * @className SubscribeResponse
 * @date: 2022/8/24 下午10:03
 * @description:
 */
@Getter
@ToString(callSuper = true)
public class SubscribeResponse extends BaseResponse {
	private String command;

	private String channel;

	private int num;
}
