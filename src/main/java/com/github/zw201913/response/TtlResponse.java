package com.github.zw201913.response;

import lombok.Getter;
import lombok.ToString;

/**
 * @author zouwei
 * @className TtlResponse
 * @date: 2022/8/24 下午10:06
 * @description:
 */
@Getter
@ToString(callSuper = true)
public class TtlResponse extends BaseResponse {

	private long ttl;
}
