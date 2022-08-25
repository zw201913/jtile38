package com.github.zw201913.response;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.Getter;
import lombok.ToString;
import org.apache.commons.collections4.MapUtils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author zouwei
 * @className GetResponse
 * @date: 2022/8/23 下午6:19
 * @description:
 */
@Getter
@ToString(callSuper = true)
public class GetResponse extends BaseResponse {
	
	private Map<String, Double> fields;

	private DataObject object;

	@Getter
	@ToString
	public static class DataObject {
		private DataObject() {
		}

		private DataObject(String rawString) {
			this.type = "RawString";
			this.raw = rawString;
		}

		private String raw;

		private String type;

		private Object coordinates;

		private Map<String, String> meta;

		@JsonAnySetter
		public void handleUnknown(String key, Object value) {
			if (MapUtils.isEmpty(this.meta)) {
				this.meta = new LinkedHashMap<>();
			}
			this.meta.put(key, String.valueOf(value));
		}
	}
}