package com.github.zw201913.entity;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * @author zouwei
 * @className WhereEvalShaParam
 * @date: 2022/8/17 上午10:30
 * @description:
 */
public class WhereEvalShaParam {

	private String sha;

	private List<String> values;

	private WhereEvalShaParam(final String sha, String... values) {
		this.sha = sha;
		this.values = new LinkedList<>();
		if (ArrayUtils.isNotEmpty(values)) {
			for (String value : values) {
				this.values.add(value);
			}
		}
	}

	private WhereEvalShaParam(final String script, List<String> values) {
		this.sha = sha;
		this.values = new LinkedList<>();
		if (CollectionUtils.isNotEmpty(values)) {
			for (String value : values) {
				this.values.add(value);
			}
		}
	}

	public static WhereEvalShaParam createInstance(final String script, String... values) {
		return new WhereEvalShaParam(script, values);
	}

	public static WhereEvalShaParam createInstance(final String script, List<String> values) {
		return new WhereEvalShaParam(script, values);
	}

	public void parseToList(List<String> list) {
		list.add("WHEREEVALSHA");
		list.add(this.sha);
		int size = this.values.size();
		list.add(String.valueOf(size));
		for (String value : this.values) {
			list.add(value);
		}
	}
}
