package com.github.zw201913.entity;

import lombok.Getter;

import java.util.LinkedList;
import java.util.List;

/**
 * @author zouwei
 * @className Circle
 * @date: 2022/8/18 下午2:04
 * @description:
 */
@Getter
public class Circle extends Element {

	private double lat;
	private double lon;
	private double meters;

	public Circle(double lat, double lon, double meters) {
		this.lat = lat;
		this.lon = lon;
		this.meters = meters;
	}

	@Override
	public List<String> commandArgs() {
		List<String> list = new LinkedList<>();
		list.add("CIRCLE");
		list.add(String.valueOf(this.lat));
		list.add(String.valueOf(this.lon));
		list.add(String.valueOf(this.meters));
		return list;
	}
}
