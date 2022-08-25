package com.github.zw201913.response;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.Getter;
import lombok.ToString;
import org.apache.commons.collections4.MapUtils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author zouwei
 * @className ScanResponse
 * @date: 2022/8/25 上午10:04
 * @description:
 */
@Getter
@ToString(callSuper = true)
public class ScanResponse extends BaseResponse {

	private String[] fields;

	private String[] ids;

	private PointType[] points;

	private BoundType[] bounds;

	private Hash[] hashes;

	private Data[] objects;

	private int count;

	private int cursor;

	@Getter
	@ToString
	public static class Data {
		private String id;

		private DataObject object;

		private double[] fields;
	}

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

	@Getter
	@ToString
	public static class BoundType {
		private String id;
		private Bound bounds;
		private double[] fields;
	}

	@Getter
	@ToString
	public static class Bound {
		private coordinate sw;
		private coordinate ne;
	}

	@Getter
	@ToString
	public static class coordinate {
		private double lat;
		private double lon;
	}

	@Getter
	@ToString
	public static class Hash {
		private String id;
		private String hash;
		private double[] fields;
	}

	@Getter
	@ToString
	public static class PointType {
		private String id;
		private Point point;
		private double[] fields;
	}

	@Getter
	@ToString
	public static class Point {
		private double lat;
		private double lon;
	}
}
