package com.github.zw201913.entity;

import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * @author zouwei
 * @className SearchOpts
 * @date: 2022/8/17 上午9:56
 * @description:
 */
@Data
abstract class BaseSearchOpts extends Opts {

	private final String key;

	private int cursor;

	private int limit;

	private String match;

	private List<WhereParam> where;

	private List<WhereInParam> whereIn;

	private List<WhereEvalParam> whereEval;

	private List<WhereEvalShaParam> whereEvalSha;

	private boolean nofields;

	private ResultType resultType;

	protected BaseSearchOpts(Builder builder) {
		this.key = builder.key;
		this.cursor = builder.cursor;
		this.limit = builder.limit;
		this.match = builder.match;
		this.where = builder.where;
		this.whereIn = builder.whereIn;
		this.whereEval = builder.whereEval;
		this.whereEvalSha = builder.whereEvalSha;
		this.nofields = builder.nofields;
		this.resultType = builder.resultType;
		init();
	}


	protected void init() {
		addFunction("key", (list) -> list.add(this.key));
		addFunction("CURSOR", (list) -> {
			if (getCursor() >= 0) {
				list.add("CURSOR");
				list.add(String.valueOf(getCursor()));
			}
		});
		addFunction("LIMIT", (list) -> {
			if (getLimit() > 0) {
				list.add("LIMIT");
				list.add(String.valueOf(getLimit()));
			}
		});
		addFunction("MATCH", (list) -> {
			if (StringUtils.isNotBlank(getMatch())) {
				list.add("MATCH");
				list.add(getMatch());
			}
		});
		addFunction("WHERE", (list) -> {
			if (CollectionUtils.isNotEmpty(getWhere())) {
				for (WhereParam whereParam : getWhere()) {
					whereParam.parseToList(list);
				}
			}
		});
		addFunction("WHEREIN", (list) -> {
			if (CollectionUtils.isNotEmpty(getWhereIn())) {
				for (WhereInParam whereInParam : getWhereIn()) {
					whereInParam.parseToList(list);
				}
			}
		});
		addFunction("WHEREEVAL", (list) -> {
			if (CollectionUtils.isNotEmpty(getWhereEval())) {
				for (WhereEvalParam whereEvalParam : getWhereEval()) {
					whereEvalParam.parseToList(list);
				}
			}
		});
		addFunction("WHEREEVALSHA", (list) -> {
			if (CollectionUtils.isNotEmpty(getWhereEvalSha())) {
				for (WhereEvalShaParam whereEvalShaParam : getWhereEvalSha()) {
					whereEvalShaParam.parseToList(list);
				}
			}
		});
		addFunction("NOFIELDS", (list) -> {
			if (isNofields()) {
				list.add("NOFIELDS");
			}
		});
		addFunction("resultType", (list) -> {
			if (Objects.nonNull(getResultType())) {
				getResultType().parseToList(list);
			}
		});
	}


	public abstract static class Builder {
		private String key;

		private int cursor = -1;

		private int limit;

		private String match;

		private List<WhereParam> where;

		private List<WhereInParam> whereIn;

		private List<WhereEvalParam> whereEval;

		private List<WhereEvalShaParam> whereEvalSha;

		private boolean nofields;

		private ResultType resultType;

		public Builder key(String key) {
			this.key = key;
			return this;
		}

		public Builder cursor(int start) {
			this.cursor = start;
			return this;
		}

		public Builder limit(int count) {
			this.limit = count;
			return this;
		}

		public Builder match(String pattern) {
			this.match = pattern;
			return this;
		}

		public Builder where(WhereParam whereParam) {
			if (CollectionUtils.isEmpty(this.where)) {
				this.where = new LinkedList<>();
			}
			this.where.add(whereParam);
			return this;
		}

		public Builder whereIn(WhereInParam whereInParam) {
			if (CollectionUtils.isEmpty(this.whereIn)) {
				this.whereIn = new LinkedList<>();
			}
			this.whereIn.add(whereInParam);
			return this;
		}

		public Builder whereEval(WhereEvalParam whereEvalParam) {
			if (CollectionUtils.isEmpty(this.whereEval)) {
				this.whereEval = new LinkedList<>();
			}
			this.whereEval.add(whereEvalParam);
			return this;
		}

		public Builder whereEvalSha(WhereEvalShaParam whereEvalShaParam) {
			if (CollectionUtils.isEmpty(this.whereEvalSha)) {
				this.whereEvalSha = new LinkedList<>();
			}
			this.whereEvalSha.add(whereEvalShaParam);
			return this;
		}

		public Builder nofields() {
			this.nofields = true;
			return this;
		}


		public Builder resultType(ResultType resultType) {
			this.resultType = resultType;
			return this;
		}

		public abstract BaseSearchOpts build();
	}

	public enum OrderType {
		ASC, DESC
	}
}
