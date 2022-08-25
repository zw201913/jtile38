package com.github.zw201913.entity;

/**
 * @author zouwei
 * @className SetChanWithNearByOpts
 * @date: 2022/8/18 上午11:24
 * @description:
 */
public class SetChanNearByOpts extends SetChanOpts {
	protected SetChanNearByOpts(Builder builder) {
		super(builder);
	}

	@Override
	protected String command() {
		return "NEARBY";
	}

	public static class Builder extends SetChanOpts.Builder<NearByOpts> {

		@Override
		public SetChanNearByOpts build() {
			return new SetChanNearByOpts(this);
		}
	}
}
