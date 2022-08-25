package com.github.zw201913.entity;

/**
 * @author zouwei
 * @className SetChanIntersectsOpts
 * @date: 2022/8/18 下午12:47
 * @description:
 */
public class SetChanIntersectsOpts extends SetChanOpts {


	protected SetChanIntersectsOpts(Builder builder) {
		super(builder);
	}

	@Override
	protected String command() {
		return "INTERSECTS";
	}

	public static class Builder extends SetChanOpts.Builder<IntersectsOpts> {

		@Override
		public SetChanOpts build() {
			return new SetChanIntersectsOpts(this);
		}
	}
}
