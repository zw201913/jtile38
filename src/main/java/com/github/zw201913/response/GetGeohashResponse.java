package com.github.zw201913.response;

import lombok.Getter;
import lombok.ToString;

import java.util.Map;

/**
 * @author zouwei
 * @className GetGeohashResponse
 * @date: 2022/8/25 下午2:35
 * @description:
 */
@Getter
@ToString(callSuper = true)
public class GetGeohashResponse extends BaseResponse {

	private Map<String, Double> fields;

	private String hash;
}
