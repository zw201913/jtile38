package com.github.zw201913.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zouwei
 * @className Point
 * @date: 2022/8/12 下午5:37
 * @description:
 */
@Data
public class Point extends Element implements Serializable {
	// 经度
	private double lng;
	// 维度
	private double lat;
	// 额外的数据
	private double z;

	public Point(double lng, double lat, double z) {
		this.lat = lat;
		this.lng = lng;
		this.z = z;
	}

	public Point(double lng, double lat) {
		this(lng, lat, Integer.MIN_VALUE);
	}

	@Override
	public List<String> commandArgs() {
		List<String> result = new LinkedList<>();
		result.add("POINT");
		result.add(String.valueOf(this.lng));
		result.add(String.valueOf(this.lat));
		if (this.z != Integer.MIN_VALUE) {
			result.add(String.valueOf(this.z));
		}
		return result;
	}
}
