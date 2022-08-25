package com.github.zw201913.entity;

import java.util.List;

/**
 * @author zouwei
 * @className ScanOpts
 * @date: 2022/8/17 下午2:11
 * @description:
 */
public class ScanOpts extends BaseSearchOpts {

	private OrderType order;

	private ScanOpts(Builder builder) {
		super(builder);
		this.order = builder.order;
	}

	public static class Builder extends BaseSearchOpts.Builder {
		private OrderType order;

		public Builder order(OrderType type) {
			this.order = type;
			return this;
		}

		@Override
		public BaseSearchOpts build() {
			return new ScanOpts(this);
		}
	}

	@Override
	public List<String> commandLine() {
		return commandLine("key", "CURSOR", "LIMIT", "MATCH", "order", "WHERE", "WHEREIN", "WHEREEVAL", "WHEREEVALSHA", "NOFIELDS", "resultType");
	}
}
