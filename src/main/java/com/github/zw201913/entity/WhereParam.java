package com.github.zw201913.entity;

import com.github.zw201913.enums.InfType;
import lombok.Data;

import java.util.List;

/**
 * @author zouwei
 * @className WhereParam
 * @date: 2022/8/17 上午10:13
 * @description:
 */
@Data
public class WhereParam {

	private String field;

	private double value;

	private InfType infType;


	private WhereParam(final String field, final double value, InfType infType) {
		this.field = field;
		this.value = value;
		this.infType = infType;
	}

	public static WhereParam createInstance(final String field, final double value, InfType infType) {
		return new WhereParam(field, value, infType);
	}

	public static WhereParam createGreaterThanParam(final String field, final double value) {
		return createInstance(field, value, InfType.GREATER_THAN);
	}

	public static WhereParam createLessThanParam(final String field, final double value) {
		return createInstance(field, value, InfType.LESS_THAN);
	}

	public void parseToList(List<String> list) {
		list.add("WHERE");
		if (InfType.GREATER_THAN == this.getInfType()) {
			list.add(this.field);
			list.add(String.valueOf(this.value));
			list.add(InfType.GREATER_THAN.getInf());
		} else {
			list.add(this.field);
			list.add(InfType.LESS_THAN.getInf());
			list.add(String.valueOf(this.value));
		}
	}
}
