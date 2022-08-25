package com.github.zw201913.entity;

import java.util.List;

/**
 * @author zouwei
 * @className ResultType
 * @date: 2022/8/17 上午10:44
 * @description:
 */
public class ResultType {

	private Type type;

	private int precision;

	enum Type {
		COUNT, IDS, OBJECTS, POINTS, BOUNDS, HASHES;
	}

	private ResultType(Type type, int precision) {
		this.type = type;
		if (Type.HASHES == this.type) {
			this.precision = precision;
		}
	}

	private static ResultType createInstance(Type type, int precision) {
		return new ResultType(type, precision);
	}

	public static ResultType ids() {
		return createInstance(Type.IDS, 0);
	}

	public static ResultType point() {
		return createInstance(Type.POINTS, 0);
	}

	public static ResultType count() {
		return createInstance(Type.COUNT, 0);
	}

	public static ResultType objects() {
		return createInstance(Type.OBJECTS, 0);
	}

	public static ResultType bounds() {
		return createInstance(Type.BOUNDS, 0);
	}

	public static ResultType hashes(int precision) {
		return createInstance(Type.HASHES, precision);
	}

	public void parseToList(List<String> list) {
		list.add(this.type.name());
		if (Type.HASHES == this.type) {
			list.add(String.valueOf(this.precision));
		}
	}

	public boolean isCount() {
		return this.type == Type.COUNT;
	}

	public boolean isBounds() {
		return this.type == Type.BOUNDS;
	}

	public boolean isObjects() {
		return this.type == Type.OBJECTS;
	}

	public boolean isIds() {
		return this.type == Type.IDS;
	}

	public boolean isPoints() {
		return this.type == Type.POINTS;
	}

	public boolean isHashes() {
		return this.type == Type.HASHES;
	}
}
