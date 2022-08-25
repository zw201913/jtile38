package com.github.zw201913.entity;

/**
 * @author zouwei
 * @className SetHookIntersectsOpts
 * @date: 2022/8/18 下午12:57
 * @description:
 */
public class SetHookIntersectsOpts extends SetHookOpts {
	protected SetHookIntersectsOpts(Builder builder) {
		super(builder);
	}

	@Override
	protected String command() {
		return "INTERSECTS";
	}

	public static class Builder extends SetHookOpts.Builder<IntersectsOpts> {

		@Override
		public SetHookIntersectsOpts build() {
			return new SetHookIntersectsOpts(this);
		}
	}
}
