package com.github.zw201913.entity;

import java.util.List;

/**
 * @author zouwei
 * @className IntersectsOpts
 * @date: 2022/8/17 下午12:43
 * @description:
 */
public class IntersectsOpts extends WithInOpts {

	private double buffer;

	private boolean clip;

	private IntersectsOpts(Builder builder) {
		super(builder);
		this.buffer = builder.buffer;
		this.clip = builder.clip;
	}

	@Override
	protected void init() {
		super.init();
		addFunction("BUFFER", list -> {
			if (this.buffer > 0) {
				list.add("BUFFER");
				list.add(String.valueOf(this.buffer));
			}
		});

		addFunction("CLIP", list -> {
			if (this.clip) {
				list.add("CLIP");
			}
		});
	}


	public static class Builder extends WithInOpts.Builder {

		private double buffer;

		private boolean clip;

		public Builder buffer(double meters) {
			this.buffer = meters;
			return this;
		}

		public Builder clip() {
			this.clip = true;
			return this;
		}

		public IntersectsOpts build() {
			return new IntersectsOpts(this);
		}
	}

	@Override
	public List<String> commandLine() {
		return commandLine("key", "CURSOR", "BUFFER", "LIMIT", "MATCH", "WHERE", "WHEREIN", "WHEREEVAL", "WHEREEVALSHA", "CLIP", "NOFIELDS", "FENCE", "DETECT", "COMMANDS", "resultType");
	}
}
