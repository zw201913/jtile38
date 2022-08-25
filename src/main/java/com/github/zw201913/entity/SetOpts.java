package com.github.zw201913.entity;

import com.github.zw201913.exception.JTile38Exception;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * @author zouwei
 * @className SetOpts
 * @date: 2022/8/17 下午12:16
 * @description:
 */
public class SetOpts extends Opts {

	private String key;

	private String id;
	//字段值必须是双精度浮点型
	private Map<String, Double> fields;
	// 单位秒
	private int ex;
	// 创建方式：
	// NX 不存在的时候创建
	// XX 存在的时候更新
	private NxXx nxXx;

	private SetOpts(Builder builder) {
		this.key = builder.key;
		this.id = builder.id;
		this.fields = builder.fields;
		this.ex = builder.ex;
		this.nxXx = builder.nxXx;
	}

	@Override
	public List<String> commandLine() {
		List<String> result = new LinkedList<>();
		result.add(this.key);
		result.add(this.id);
		if (MapUtils.isNotEmpty(this.fields)) {
			for (Map.Entry<String, Double> entry : this.fields.entrySet()) {
				result.add("FIELD");
				result.add(entry.getKey());
				result.add(entry.getValue().toString());
			}
		}
		if (this.ex >= 0) {
			result.add("EX");
			result.add(String.valueOf(this.ex));
		}
		if (Objects.nonNull(this.nxXx)) {
			result.add(this.nxXx.name());
		}
		return result;
	}

	public enum NxXx {
		NX,
		XX
	}

	public static class Builder {
		private String key;

		private String id;
		//字段值必须是双精度浮点型
		private Map<String, Double> fields;
		// 单位秒
		private int ex = -1;
		// 创建方式：
		// NX 不存在的时候创建
		// XX 存在的时候更新
		private NxXx nxXx;

		public Builder key(String key) {
			this.key = key;
			return this;
		}

		public Builder id(String id) {
			this.id = id;
			return this;
		}


		public Builder field(String field, double value) {
			if (Objects.isNull(this.fields)) {
				this.fields = new LinkedHashMap<>();
			}
			this.fields.put(field, value);
			return this;
		}

		public Builder ex(int seconds) {
			this.ex = seconds;
			return this;
		}

		public Builder nxXx(NxXx nxXx) {
			this.nxXx = nxXx;
			return this;
		}

		public SetOpts build() throws JTile38Exception {
			if (StringUtils.isEmpty(this.key)) {
				throw new JTile38Exception(500, "key is empty");
			}
			if (StringUtils.isEmpty(this.id)) {
				throw new JTile38Exception(500, "id is empty");
			}
			return new SetOpts(this);
		}
	}
}
