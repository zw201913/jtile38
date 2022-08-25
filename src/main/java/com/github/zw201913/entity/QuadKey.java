package com.github.zw201913.entity;

import java.util.LinkedList;
import java.util.List;

/**
 * @author zouwei
 * @className QuadKey
 * @date: 2022/8/18 下午2:16
 * @description:
 */
public class QuadKey extends Element {

	private String quadkey;

	public QuadKey(String quadkey) {
		this.quadkey = quadkey;
	}

	@Override
	public List<String> commandArgs() {
		List<String> list = new LinkedList<>();
		list.add("QUADKEY");
		list.add(this.quadkey);
		return list;
	}
}
