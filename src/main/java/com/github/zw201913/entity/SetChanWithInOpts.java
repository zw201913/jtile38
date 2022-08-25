package com.github.zw201913.entity;

/**
 * @author zouwei
 * @className SetChanWithInOpts
 * @date: 2022/8/18 下午12:45
 * @description:
 */
public class SetChanWithInOpts extends SetChanOpts {
	protected SetChanWithInOpts(Builder builder) {
		super(builder);
	}

	@Override
	protected String command() {
		return "WITHIN";
	}

	public static class Builder extends SetChanOpts.Builder<WithInOpts> {

		@Override
		public SetChanOpts build() {
			return new SetChanWithInOpts(this);
		}
	}
}
