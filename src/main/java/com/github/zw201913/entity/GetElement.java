package com.github.zw201913.entity;

import java.util.LinkedList;
import java.util.List;

/**
 * @author zouwei
 * @className GetElement
 * @date: 2022/8/18 下午1:57
 * @description:
 */
public class GetElement extends Element {

	private String key;

	private String id;

	public GetElement(String key, String id) {
		this.key = key;
		this.id = id;
	}

	@Override
	public List<String> commandArgs() {
		List<String> list = new LinkedList<>();
		list.add("GET");
		list.add(this.key);
		list.add(this.id);
		return list;
	}
}
