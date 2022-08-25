package com.github.zw201913.entity;

import java.util.LinkedList;
import java.util.List;

/**
 * @author zouwei
 * @className Sector
 * @date: 2022/8/18 下午2:18
 * @description:
 */
public class Sector extends Circle {
	/**
	 * 角度1
	 */
	private double bearing1;
	/**
	 * 角度2
	 */
	private double bearing2;

	public Sector(double lat, double lon, double meters, double bearing1, double bearing2) {
		super(lat, lon, meters);
		this.bearing1 = bearing1;
		this.bearing2 = bearing2;
	}

	public Sector(Circle circle, double bearing1, double bearing2) {
		super(circle.getLat(), circle.getLon(), circle.getMeters());
		this.bearing1 = bearing1;
		this.bearing2 = bearing2;
	}

	@Override
	public List<String> commandArgs() {
		List<String> list = new LinkedList<>();
		list.add("SECTOR");
		list.add(String.valueOf(getLat()));
		list.add(String.valueOf(getLon()));
		list.add(String.valueOf(getMeters()));
		list.add(String.valueOf(this.bearing1));
		list.add(String.valueOf(this.bearing2));
		return list;
	}
}
