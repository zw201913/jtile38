package com.github.zw201913.entity;

import java.util.List;

/**
 * @author zouwei
 * @className NearByOpts
 * @date: 2022/8/17 下午2:16
 * @description:
 */
public class NearByOpts extends WithInOpts {

	private boolean distance;

	protected NearByOpts(Builder builder) {
		super(builder);
	}

	@Override
	protected void init() {
		super.init();
		addFunction("DISTANCE", list -> {
			if (this.distance) {
				list.add("DISTANCE");
			}
		});
	}

	public static class Builder extends WithInOpts.Builder {
		private boolean distance;

		public Builder distance() {
			this.distance = true;
			return this;
		}

		@Override
		public NearByOpts build() {
			return new NearByOpts(this);
		}
	}


	@Override
	public List<String> commandLine() {
		return commandLine("key", "CURSOR", "LIMIT", "MATCH", "DISTANCE", "WHERE", "WHEREIN", "WHEREEVAL", "WHEREEVALSHA", "NOFIELDS", "FENCE", "DETECT", "COMMANDS", "resultType");
	}
}
