package com.github.zw201913.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.LinkedList;
import java.util.List;

/**
 * @author zouwei
 * @className Raw
 * @date: 2022/8/14 上午1:11
 * @description:
 */
@Data
@AllArgsConstructor
@ToString(callSuper = true)
public class RawString extends Element {

	private String raw;

	@Override
	public List<String> commandArgs() {
		List<String> result = new LinkedList<>();
		result.add("STRING");
		result.add(this.raw);
		return result;
	}
}
