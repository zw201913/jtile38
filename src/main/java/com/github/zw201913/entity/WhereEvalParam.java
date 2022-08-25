package com.github.zw201913.entity;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * @author zouwei
 * @className WhereEvalParam
 * @date: 2022/8/17 上午10:28
 * @description:
 */
public class WhereEvalParam {

	private String script;

	private List<String> values;

	private WhereEvalParam(final String script, String... values) {
		this.script = script;
		this.values = new LinkedList<>();
		if (ArrayUtils.isNotEmpty(values)) {
			for (String value : values) {
				this.values.add(value);
			}
		}
	}

	private WhereEvalParam(final String script, List<String> values) {
		this.script = script;
		this.values = new LinkedList<>();
		if (CollectionUtils.isNotEmpty(values)) {
			for (String value : values) {
				this.values.add(value);
			}
		}
	}

	public static WhereEvalParam createInstance(final String script, String... values) {
		return new WhereEvalParam(script, values);
	}

	public static WhereEvalParam createInstance(final String script, List<String> values) {
		return new WhereEvalParam(script, values);
	}

	public void parseToList(List<String> list) {
		list.add("WHEREEVAL");
		list.add(this.script);
		int size = this.values.size();
		list.add(String.valueOf(size));
		for (String value : this.values) {
			list.add(value);
		}
	}
}
