package com.github.zw201913.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

/**
 * @author zouwei
 * @className Bound
 * @date: 2022/8/12 下午9:33
 * @description:
 */
@Data
@AllArgsConstructor
public class Bounds extends Element {
	private double[] coordinate1;
	private double[] coordinate2;

	@Override
	public List<String> commandArgs() {
		List<String> result = new LinkedList<>();
		result.add("BOUNDS");
		result.add(String.valueOf(coordinate1[0]));
		result.add(String.valueOf(coordinate1[1]));
		result.add(String.valueOf(coordinate2[0]));
		result.add(String.valueOf(coordinate2[1]));
		return result;
	}
}
