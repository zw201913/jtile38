package com.github.zw201913.entity;

/**
 * @author zouwei
 * @className SetHookWithInOpts
 * @date: 2022/8/18 下午12:55
 * @description:
 */
public class SetHookWithInOpts extends SetHookOpts {
	protected SetHookWithInOpts(Builder builder) {
		super(builder);
	}

	@Override
	protected String command() {
		return "WITHIN";
	}

	public static class Builder extends SetHookOpts.Builder<WithInOpts> {

		@Override
		public SetHookWithInOpts build() {
			return new SetHookWithInOpts(this);
		}
	}
}
