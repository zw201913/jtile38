package com.github.zw201913.response;

import lombok.Getter;
import lombok.ToString;
import org.apache.commons.collections4.MapUtils;

import java.util.Map;

/**
 * @author zouwei
 * @className ConfigGetResponse
 * @date: 2022/8/23 下午4:54
 * @description:
 */
@Getter
@ToString(callSuper = true)
public class ConfigGetResponse extends BaseResponse {

	private Map<String, String> properties;

	public String get(String key) {
		if (MapUtils.isEmpty(this.properties)) {
			return null;
		}
		return this.properties.get(key);
	}
}
