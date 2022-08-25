package com.github.zw201913.entity;

import com.github.zw201913.exception.JTile38Exception;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * @author zouwei
 * @className EvalScriptOpts
 * @date: 2022/8/17 下午7:44
 * @description:
 */
public class EvalScriptOpts extends Opts {

	private String scriptOrSha;

	private List<String> keys;

	private List<String> args;

	private EvalScriptOpts(Builder builder) {
		this.scriptOrSha = builder.scriptOrSha;
		this.keys = builder.keys;
		this.args = builder.args;
	}

	@Override
	public List<String> commandLine() {
		List<String> result = new LinkedList<>();
		int numkeys = CollectionUtils.isEmpty(this.keys) ? 0 : this.keys.size();
		result.add(this.scriptOrSha);
		result.add(String.valueOf(numkeys));
		if (numkeys > 0) {
			for (String key : this.keys) {
				result.add(key);
			}
		}
		if (CollectionUtils.isNotEmpty(this.args)) {
			for (String arg : this.args) {
				result.add(arg);
			}
		}
		return result;
	}

	public static class Builder {

		private String scriptOrSha;

		private List<String> keys;

		private List<String> args;

		public Builder scripts(String script) {
			this.scriptOrSha = script;
			return this;
		}

		public Builder sha(String sha) {
			this.scriptOrSha = sha;
			return this;
		}

		public Builder key(String... keys) {
			if (CollectionUtils.isEmpty(this.keys)) {
				this.keys = new LinkedList<>();
			}
			for (String key : keys) {
				this.keys.add(key);
			}
			return this;
		}

		public Builder arg(String... args) {
			if (CollectionUtils.isEmpty(this.args)) {
				this.args = new LinkedList<>();
			}
			for (String arg : args) {
				this.args.add(arg);
			}
			return this;
		}

		public EvalScriptOpts build() throws JTile38Exception {
			if (StringUtils.isEmpty(this.scriptOrSha)) {
				throw new JTile38Exception(500, "script or sha can not be empty");
			}
			return new EvalScriptOpts(this);
		}

	}
}
