package com.github.zw201913.entity;

import java.util.List;

/**
 * @author zouwei
 * @className SetHookOpts
 * @date: 2022/8/18 上午10:47
 * @description:
 */
abstract class SetHookOpts extends SetChanOpts {

	private String endpoint;

	protected SetHookOpts(Builder builder) {
		super(builder);
		this.endpoint = builder.endpoint;
	}

	protected void init() {
		super.init();
		addFunction("endpoint", list -> list.add(this.endpoint));
	}

	@Override
	public List<String> commandLine() {
		return commandLine("name", "endpoint", "META", "EX", "COMMAND", "searchOpts");
	}

	public abstract static class Builder<T extends BaseSearchOpts> extends SetChanOpts.Builder<T> {

		private String endpoint;

		public Builder endpoint(String endpoint) {
			this.endpoint = endpoint;
			return this;
		}
	}
}
