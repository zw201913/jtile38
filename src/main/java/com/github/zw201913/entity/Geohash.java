package com.github.zw201913.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.LinkedList;
import java.util.List;

/**
 * @author zouwei
 * @className Geohash
 * @date: 2022/8/14 上午12:51
 * @description:
 */
@Data
@AllArgsConstructor
@ToString(callSuper = true)
public class Geohash extends Element {

	private String hash;


	@Override
	public List<String> commandArgs() {
		List<String> result = new LinkedList<>();
		result.add("HASH");
		result.add(this.hash);
		return result;
	}
}
