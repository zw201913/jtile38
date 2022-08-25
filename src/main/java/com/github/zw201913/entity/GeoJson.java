package com.github.zw201913.entity;

import com.github.zw201913.util.JsonUtil;
import lombok.Data;
import lombok.ToString;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author zouwei
 * @className GeoJson
 * @date: 2022/8/12 下午10:36
 * @description:
 */
@Data
@ToString(callSuper = true)
public class GeoJson {

	public static class Builder {

		public Point.Builder point() {
			return new Point.Builder();
		}

		public MultiPoint.Builder multPoint() {
			return new MultiPoint.Builder();
		}

		public LineString.Builder lineString() {
			return new LineString.Builder();
		}

		public MultiLineString.Builder multiLineString() {
			return new MultiLineString.Builder();
		}

		public Polygon.Builder polygon() {
			return new Polygon.Builder();
		}

		public MultiPolygon.Builder multiPolygon() {
			return new MultiPolygon.Builder();
		}
	}


	public static class Point extends BaseGeoJson {

		Point(Builder builder) {
			super(builder);
			this.type = GeoJsonType.Point;
			this.coordinates = builder.coordinates;
		}

		public static class Builder extends BaseGeoJson.Builder {

			private double[] coordinates;

			public Builder coordinate(double lon, double lat) {
				coordinates = new double[]{lat, lon};
				return this;
			}

			public Point build() {
				return new Point(this);
			}
		}
	}

	public static class MultiPoint extends BaseGeoJson {

		MultiPoint(Builder builder) {
			super(builder);
			this.type = GeoJsonType.MultiPoint;
			this.coordinates = builder.convert2Array();
		}

		public static class Builder extends BaseGeoJson.Builder {

			private List<Coordinate> coordinates;

			public Builder coordinate(double lon, double lat) {
				if (CollectionUtils.isEmpty(this.coordinates)) {
					this.coordinates = new LinkedList<>();
				}
				this.coordinates.add(new Coordinate(lat, lon));
				return this;
			}

			protected double[][] convert2Array() {
				int length = this.coordinates.size();
				double[][] result = new double[length][];
				for (int i = 0; i < length; i++) {
					result[i] = this.coordinates.get(i).convertToArray();
				}
				return result;
			}

			@Override
			public MultiPoint build() {
				return new MultiPoint(this);
			}
		}
	}

	public static class LineString extends MultiPoint {

		LineString(Builder builder) {
			super(builder);
			this.type = GeoJsonType.LineString;
		}

		public static class Builder extends MultiPoint.Builder {

			@Override
			public LineString build() {
				return new LineString(this);
			}
		}
	}

	public static class MultiLineString extends BaseGeoJson {

		MultiLineString(Builder builder) {
			super(builder);
			this.type = GeoJsonType.MultiLineString;
			this.coordinates = builder.convertToArray();
		}

		public static class Builder extends BaseGeoJson.Builder {

			private List<Line> lines = new LinkedList<>();

			public Line line() {
				return new Line(this);
			}

			void addLine(Line line) {
				lines.add(line);
			}

			double[][][] convertToArray() {
				int length = this.lines.size();
				double[][][] result = new double[length][][];
				for (int i = 0; i < length; i++) {
					Line line = this.lines.get(i);
					result[i] = line.convert2Array();
				}
				return result;
			}

			@Override
			public BaseGeoJson build() {
				return new MultiLineString(this);
			}
		}

		public static class Line {
			private List<Coordinate> coordinates;

			private Builder builder;

			Line(Builder builder) {
				this.builder = builder;
				this.builder.addLine(this);
			}

			private double[][] convert2Array() {
				int length = this.coordinates.size();
				double[][] result = new double[length][];
				for (int i = 0; i < length; i++) {
					result[i] = this.coordinates.get(i).convertToArray();
				}
				return result;
			}

			public Line coordinate(double lon, double lat) {
				if (CollectionUtils.isEmpty(this.coordinates)) {
					this.coordinates = new LinkedList<>();
				}
				this.coordinates.add(new Coordinate(lat, lon));
				return this;
			}

			public Line nextLine() {
				return new Line(this.builder);
			}

			public Builder end() {
				return this.builder;
			}
		}
	}

	public static class Polygon extends MultiPoint {

		Polygon(Builder builder) {
			super(builder);
			this.type = GeoJsonType.Polygon;
			this.coordinates = new double[][][]{builder.convert2Array()};
		}

		public static class Builder extends MultiPoint.Builder {

			@Override
			public Polygon build() {
				return new Polygon(this);
			}
		}
	}

	public static class MultiPolygon extends BaseGeoJson {

		MultiPolygon(Builder builder) {
			super(builder);
			this.type = GeoJsonType.MultiPolygon;
			this.coordinates = new double[][][][]{builder.convert2Array()};
		}

		public static class Builder extends BaseGeoJson.Builder {

			private List<Polygon> polygons = new LinkedList<>();

			@Override
			public BaseGeoJson build() {
				return new MultiPolygon(this);
			}

			void addPolygon(Polygon polygon) {
				polygons.add(polygon);
			}

			public Polygon polygon() {
				return new Polygon(this);
			}

			private double[][][] convert2Array() {
				int length = this.polygons.size();
				double[][][] result = new double[length][][];
				for (int i = 0; i < length; i++) {
					result[i] = this.polygons.get(i).convert2Array();
				}
				return result;
			}
		}

		public static class Polygon {
			private List<Coordinate> coordinates;

			private Builder builder;

			Polygon(Builder builder) {
				this.builder = builder;
				this.builder.addPolygon(this);
			}

			private double[][] convert2Array() {
				int length = this.coordinates.size();
				double[][] result = new double[length][];
				for (int i = 0; i < length; i++) {
					result[i] = this.coordinates.get(i).convertToArray();
				}
				return result;
			}

			public Polygon coordinate(double lon, double lat) {
				if (CollectionUtils.isEmpty(this.coordinates)) {
					this.coordinates = new LinkedList<>();
				}
				this.coordinates.add(new Coordinate(lat, lon));
				return this;
			}

			public Polygon nextPolygon() {
				return new Polygon(this.builder);
			}

			public Builder end() {
				return this.builder;
			}
		}
	}

	public abstract static class BaseGeoJson extends Element {

		protected GeoJsonType type;

		private Map<String, String> metadata;

		protected Object coordinates;

		BaseGeoJson(Builder builder) {
			this.metadata = builder.metadata;
		}

		protected Object coordinates() {
			return this.coordinates;
		}

		@Override
		public List<String> commandArgs() {
			List<String> result = new LinkedList<>();
			result.add("OBJECT");
			result.add(toJson());
			return result;
		}

		protected String toJson() {
			Map<String, Object> map = new LinkedHashMap<>();
			map.put("type", this.type);
			map.put("coordinates", coordinates());
			if (!MapUtils.isEmpty(this.metadata)) {
				for (Map.Entry<String, String> entry : this.metadata.entrySet()) {
					map.put(entry.getKey(), entry.getValue());
				}
			}
			return JsonUtil.obj2String(map);
		}

		public abstract static class Builder {
			private Map<String, String> metadata;

			public Builder meta(String key, String value) {
				if (MapUtils.isEmpty(this.metadata)) {
					this.metadata = new LinkedHashMap<>();
				}
				this.metadata.put(key, value);
				return this;
			}

			public abstract <T extends BaseGeoJson> T build();
		}

		static class Coordinate {
			private double lat;
			private double lon;

			Coordinate(double lat, double lon) {
				this.lat = lat;
				this.lon = lon;
			}

			public double[] convertToArray() {
				return new double[]{this.lat, this.lon};
			}
		}

		enum GeoJsonType {
			Point,
			LineString,
			Polygon,
			MultiPoint,
			MultiLineString,
			MultiPolygon
		}
	}

}
