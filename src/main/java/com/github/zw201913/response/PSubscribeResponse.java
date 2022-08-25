package com.github.zw201913.response;

import lombok.Getter;
import lombok.ToString;

/**
 * @author zouwei
 * @className PSubscribeResponse
 * @date: 2022/8/23 下午10:48
 * @description:
 */
@Getter
@ToString(callSuper = true)
public class PSubscribeResponse extends BaseResponse {

	private String command;

	private String channel;

	private int num;
}
