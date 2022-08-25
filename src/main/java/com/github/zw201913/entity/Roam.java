package com.github.zw201913.entity;

import com.github.zw201913.exception.JTile38Exception;
import org.apache.commons.lang3.StringUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * @author zouwei
 * @className Roam
 * @date: 2022/8/18 下午1:32
 * @description:
 */
public class Roam extends Element {

	private boolean nodwell;

	private String key;

	private String pattern;

	private double meters;

	private Roam(Builder builder) {
		this.nodwell = builder.nodwell;
		this.key = builder.key;
		this.pattern = builder.pattern;
		this.meters = builder.meters;
	}


	public static class Builder {
		private boolean nodwell;

		private String key;

		private String pattern;

		private double meters;

		public Builder nodwell() {
			this.nodwell = true;
			return this;
		}

		public Builder key(String key) {
			this.key = key;
			return this;
		}

		public Builder pattern(String pattern) {
			this.pattern = pattern;
			return this;
		}

		public Builder meters(double meters) {
			this.meters = meters;
			return this;
		}

		public Roam build() throws JTile38Exception {
			if (StringUtils.isEmpty(this.key)) {
				throw new JTile38Exception(500, "the parameter `key` can not be empty");
			}
			if (StringUtils.isEmpty(this.pattern)) {
				throw new JTile38Exception(500, "the parameter `pattern` can not be empty");
			}
			if (this.meters <= 0) {
				throw new JTile38Exception(500, "the parameter `meters` must be greater than zero");
			}
			return new Roam(this);
		}
	}


	@Override
	public List<String> commandArgs() {
		List<String> list = new LinkedList<>();
		if (this.nodwell) {
			list.add("NODWELL");
		}
		list.add("ROAM");
		list.add(this.key);
		list.add(this.pattern);
		list.add(String.valueOf(this.meters));
		return list;
	}
}
