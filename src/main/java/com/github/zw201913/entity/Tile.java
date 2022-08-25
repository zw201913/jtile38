package com.github.zw201913.entity;

import java.util.LinkedList;
import java.util.List;

/**
 * @author zouwei
 * @className Tile
 * @date: 2022/8/18 下午2:10
 * @description:
 */
public class Tile extends Element {

	private int x;

	private int y;

	private int z;

	public Tile(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public List<String> commandArgs() {
		List<String> list = new LinkedList<>();
		list.add("TILE");
		list.add(String.valueOf(this.x));
		list.add(String.valueOf(this.y));
		list.add(String.valueOf(this.z));
		return list;
	}
}
