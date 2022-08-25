package com.github.zw201913.entity;

import java.util.List;
import java.util.Objects;

/**
 * @author zouwei
 * @className SearchOpts
 * @date: 2022/8/17 下午1:17
 * @description:
 */
public class SearchOpts extends BaseSearchOpts {

	private OrderType order;

	private SearchOpts(Builder builder) {
		super(builder);
		this.order = builder.order;
	}

	@Override
	protected void init() {
		super.init();
		addFunction("order", list -> {
			if (Objects.nonNull(this.order)) {
				list.add(this.order.name());
			}
		});
	}

	public static class Builder extends BaseSearchOpts.Builder {
		private OrderType order;

		public Builder order(OrderType type) {
			this.order = type;
			return this;
		}


		@Override
		public Builder resultType(ResultType resultType) {
			if (resultType.isCount() || resultType.isIds()) {
				super.resultType(resultType);
			}
			return this;
		}

		@Override
		public BaseSearchOpts build() {
			return new SearchOpts(this);
		}
	}


	@Override
	public List<String> commandLine() {
		return commandLine("key", "CURSOR", "LIMIT", "MATCH", "order", "WHERE", "WHEREIN", "WHEREEVAL", "WHEREEVALSHA", "NOFIELDS", "resultType");
	}
}
