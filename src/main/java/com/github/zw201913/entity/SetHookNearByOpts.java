package com.github.zw201913.entity;

/**
 * @author zouwei
 * @className SetHookNearByOpts
 * @date: 2022/8/18 下午12:51
 * @description:
 */
public class SetHookNearByOpts extends SetHookOpts {
	protected SetHookNearByOpts(Builder builder) {
		super(builder);
	}

	@Override
	protected String command() {
		return "NEARBY";
	}

	public static class Builder extends SetHookOpts.Builder<NearByOpts> {

		@Override
		public SetHookNearByOpts build() {
			return new SetHookNearByOpts(this);
		}
	}
}
