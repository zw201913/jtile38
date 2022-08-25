package com.github.zw201913.entity;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zouwei
 * @className SetChanOpts
 * @date: 2022/8/17 下午10:13
 * @description:
 */
public abstract class SetChanOpts extends Opts {

	private String name;

	private Map<String, String> meta;

	private int ex;

	private BaseSearchOpts searchOpts;

	protected SetChanOpts(Builder builder) {
		this.name = builder.name;
		this.meta = builder.meta;
		this.ex = builder.ex;
		this.searchOpts = builder.searchOpts;
		init();
	}

	protected void init() {
		addFunction("name", list -> list.add(this.name));
		addFunction("META", list -> {
			if (MapUtils.isNotEmpty(this.meta)) {
				for (Map.Entry<String, String> entry : this.meta.entrySet()) {
					list.add("META");
					list.add(entry.getKey());
					list.add(entry.getValue());
				}
			}
		});
		addFunction("EX", list -> {
			if (this.ex > 0) {
				list.add("EX");
				list.add(String.valueOf(this.ex));
			}
		});
		addFunction("COMMAND", list -> list.add(command()));
		addFunction("searchOpts", list -> {
			List<String> commandLine = this.searchOpts.commandLine();
			if (CollectionUtils.isNotEmpty(commandLine)) {
				for (String c : commandLine) {
					list.add(c);
				}
			}
		});
	}

	@Override
	public List<String> commandLine() {
		return commandLine("name", "META", "EX", "COMMAND", "searchOpts");
	}

	protected abstract String command();


	public abstract static class Builder<T extends BaseSearchOpts> {
		private String name;

		private Map<String, String> meta;

		private int ex = -1;

		private T searchOpts;

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder meta(String name, String value) {
			if (MapUtils.isEmpty(this.meta)) {
				this.meta = new HashMap<>();
			}
			this.meta.put(name, value);
			return this;
		}

		public Builder ex(int seconds) {
			this.ex = seconds;
			return this;
		}

		public Builder searchOpts(T searchOpts) {
			this.searchOpts = searchOpts;
			return this;
		}

		public abstract <R extends SetChanOpts> R build();
	}
}
