package com.github.zw201913.response;

import lombok.Getter;
import lombok.ToString;
import org.apache.commons.lang3.ArrayUtils;

/**
 * @author zouwei
 * @className BoundsResponse
 * @date: 2022/8/12 下午8:52
 * @description:
 */
@Getter
@ToString(callSuper = true)
public class BoundsResponse extends BaseResponse {

	private Polygon bounds;

	public double[][] getCoordinates() {
		if (this.bounds == null) {
			return null;
		}
		double[][][] coordinates = this.bounds.getCoordinates();
		if (ArrayUtils.isEmpty(coordinates)) {
			return null;
		}
		return coordinates[0];
	}

	@Getter
	@ToString
	public static class Polygon {
		private String type;
		private double[][][] coordinates;
	}
}

