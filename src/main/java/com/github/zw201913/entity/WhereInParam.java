package com.github.zw201913.entity;

import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * @author zouwei
 * @className WhereInParam
 * @date: 2022/8/17 上午10:21
 * @description:
 */
@Data
public class WhereInParam {

	private String field;

	private List<Double> values;

	private WhereInParam(final String field, Double... values) {
		this.field = field;
		this.values = new LinkedList<>();
		if (ArrayUtils.isNotEmpty(values)) {
			for (Double value : values) {
				this.values.add(value);
			}
		}
	}

	private WhereInParam(final String field, List<Double> values) {
		this.field = field;
		this.values = new LinkedList<>();
		if (CollectionUtils.isNotEmpty(values)) {
			for (Double value : values) {
				this.values.add(value);
			}
		}
	}

	public static WhereInParam createInstance(final String field, Double... values) {
		return new WhereInParam(field, values);
	}

	public static WhereInParam createInstance(final String field, List<Double> values) {
		return new WhereInParam(field, values);
	}

	public void parseToList(List<String> list) {
		list.add("WHEREIN");
		list.add(this.field);
		for (Double value : this.values) {
			list.add(value.toString());
		}
	}
}
