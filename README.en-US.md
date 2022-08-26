<div align="center">
<h3>Java Client for Tile38</h3>
<p><img width="250" alt="jtile38-removebg-preview" src="https://user-images.githubusercontent.com/16849315/186699764-7a4d43b9-4269-4c3b-88b0-cee4d49847f9.png"></p>
  <p>
    <a href="https://github.com/zw201913/jtile38/blob/main/README.md">简体中文</a>&nbsp;&nbsp;
    <a href="https://github.com/zw201913/jtile38/blob/main/README.en-US.md">English</a>
</p>
  <p><img alt="License" src="https://camo.githubusercontent.com/093fc33ad6230c9fc08a4ca2392f4cf7fbe51e746be4d1a09456d8b1e1101a40/68747470733a2f2f696d672e736869656c64732e696f2f6372617465732f6c2f73616c766f2e737667" /></p>
</div>

## 🎯 Feature

   - Provides the JTile38Client class to support all Tile38 commands
   - Provides JTile38Leader and JTile38Follower classes to support read-write separation mode

## ⚡️ Quick Start

   - First pull dependencies from the maven central repository：[JTile38](https://mvnrepository.com/artifact/com.github.zw201913/jtile38)
     ```xml
     <dependency>
         <groupId>com.github.zw201913</groupId>
         <artifactId>jtile38</artifactId>
         <version>1.0.3</version>
     </dependency>
     ```
   - Using JTile38Client
   
     ```java
     JTile38Client client = new JTile38Client(host, port, password);
     // Add Point data
     SetOpts opts = new SetOpts.Builder()
                .key("fleet")
                .id("truck1")
                .field("speed", 90.5)
                .field("height", 123.8)
                .build();
     String result = client.setPoint(opts, new Point(116.249113, 39.762651));
     // {"ok":true,"elapsed":"16.501µs"}
     System.out.println(result);

      // Query specified data
     String pointResult = client.get("fleet", "truck1");
     // {"ok":true,"object":{"type":"Point","coordinates":[39.762651,116.249113]},"elapsed":"17.223µs"}
     System.out.println(pointResult);
     
     // Query the list of data within 500 meters of the target area
     WithInOpts withInOpts = (WithInOpts) new WithInOpts.Builder()
                             .key("fleet")
     // set return type
                             .resultType(ResultType.hashes(5))
                             .build();
     String withInResult = client.within(withInOpts, new Circle(39.867514, 116.577703, 500));
     // {"ok":true,"fields":["age","height","speed"],"hashes":[{"id":"geojsonPolygon","hash":"wx4fu","fields":[0,120.5,90.5]},{"id":"geojsonMultiPolygon","hash":"wx4fu","fields":[0,120.5,90.5]}],"count":2,"cursor":0,"elapsed":"86.252µs"}
     System.out.println(withInResult);
     
     // close link
     client.close();
     ```
   - Using JTile38Follower

     JTile38Follower can only use the query function, and all the returned results have been encapsulated into entity classes;
     ```java
     JTile38Follower follower = new JTile38Follower("42.192.50.8", 9851, "123456");
     
     // Query the specified data `truck1`
     GetPointResponse getPointResponse = follower.getPoint("fleet", "truck1");
     // GetPointResponse(super=BaseResponse(ok=true, elapsed=10.47µs, err=null), fields=null, point=GetPointResponse.Point(lat=116.249113, lon=39.762651, z=0))
     System.out.println(getPointResponse);
     // Query all data under `fleet`
     ScanResponse scanResponse = follower.scan("fleet");
     // ScanResponse(super=BaseResponse(ok=true, elapsed=122.14µs, err=null), fields=[age, height, speed], ids=null, points=null, bounds=null, hashes=null, objects=[ScanResponse.Data(id=bounds1, object=ScanResponse.DataObject(raw=null, type=Polygon, coordinates=[[[39.867514, 116.577703], [39.868634, 116.577703], [39.868634, 116.578497], [39.867514, 116.578497], [39.867514, 116.577703]]], meta=null), fields=[0.0, 120.5, 90.5]), ScanResponse.Data(id=geojsonLineString, object=ScanResponse.DataObject(raw=null, type=LineString, coordinates=[[39.867514, 116.577703], [39.868634, 116.578497]], meta={name=zouwei, gender=7}), fields=[0.0, 120.5, 90.5]), ScanResponse.Data(id=geojsonMultiLineString, object=ScanResponse.DataObject(raw=null, type=MultiLineString, coordinates=[[[39.867514, 116.577703], [39.868634, 116.578497]], [[39.867314, 116.577503], [39.866634, 116.576497]]], meta={name=zouwei, gender=1}), fields=[0.0, 120.5, 90.5]), ScanResponse.Data(id=geojsonMultiPoint, object=ScanResponse.DataObject(raw=null, type=MultiPoint, coordinates=[[39.867514, 116.577703], [39.868634, 116.578497]], meta={name=zouwei, age=30}), fields=[0.0, 120.5, 90.5]), ScanResponse.Data(id=geojsonMultiPolygon, object=ScanResponse.DataObject(raw=null, type=MultiPolygon, coordinates=[[[[116.577703, 39.867514], [116.578497, 39.868634], [116.576497, 39.867634], [116.577703, 39.867514]], [[116.577703, 39.867514], [116.578497, 39.868634], [116.576497, 39.867634], [116.577703, 39.867514]]]], meta={name=zouwei, gender=7}), fields=[0.0, 120.5, 90.5]), ScanResponse.Data(id=geojsonPoint, object=ScanResponse.DataObject(raw=null, type=Point, coordinates=[39.867514, 116.577703], meta={name=zouwei, age=30}), fields=[0.0, 120.5, 90.5]), ScanResponse.Data(id=geojsonPolygon, object=ScanResponse.DataObject(raw=null, type=Polygon, coordinates=[[[116.577703, 39.867514], [116.578497, 39.868634], [116.576497, 39.867634], [116.577703, 39.867514]]], meta={name=zouwei, gender=7}), fields=[0.0, 120.5, 90.5]), ScanResponse.Data(id=geojsonpoint, object=ScanResponse.DataObject(raw=null, type=LineString, coordinates=[[39.867514, 116.577703], [39.868634, 116.578497]], meta={name=zouwei}), fields=[0.0, 120.5, 90.5]), ScanResponse.Data(id=hash, object=ScanResponse.DataObject(raw=rawString, type=RawString, coordinates=null, meta=null), fields=[0.0, 120.5, 90.5]), ScanResponse.Data(id=hello, object=ScanResponse.DataObject(raw=null, type=Point, coordinates=[33.462, -112.268], meta=null), fields=[30.0, 0.0, 0.0]), ScanResponse.Data(id=json, object=ScanResponse.DataObject(raw={"name":{"first":"zou"}}, type=RawString, coordinates=null, meta=null), fields=[0.0, 0.0, 0.0]), ScanResponse.Data(id=point1, object=ScanResponse.DataObject(raw=null, type=Point, coordinates=[39.867514, 116.577703, 1661309797349], meta=null), fields=[0.0, 120.5, 90.5]), ScanResponse.Data(id=truck1, object=ScanResponse.DataObject(raw=null, type=Point, coordinates=[39.762651, 116.249113], meta=null), fields=[0.0, 123.8, 90.5])], count=13, cursor=0)
     System.out.println(scanResponse);

     // close link
     follower.close();
     ```
   - Using JTile38Leader

     ```java
     JTile38Leader leader = new JTile38Leader("42.192.50.8", 9851, "123456");

	 SetOpts setOpts = new SetOpts.Builder()
				.key("fleet")
				.id("geojson")
				.build();
	 // Add polygon
	 GeoJson.BaseGeoJson polygon = new GeoJson.Builder()
				.polygon()
				.coordinate(116.249113, 39.762651)
				.coordinate(116.510038, 39.796424)
				.coordinate(116.023893, 40.072312)
				// 注意结尾的坐标一定要和第一个点的坐标一致
				.coordinate(116.249113, 39.762651)
				.meta("city", "beijing")
				.meta("country", "china")
				.build();
	 SetResponse setResponse = leader.setGeoJson(setOpts, polygon);
	 // SetResponse(super=BaseResponse(ok=true, elapsed=62.447µs, err=null))
	 System.out.println(setResponse);

	 // Query the specified data `truck1`
	 GetResponse getResponse = leader.getObject("fleet", "geojson");
	 // GetResponse(super=BaseResponse(ok=true, elapsed=12.784µs, err=null), fields=null, object=GetResponse.DataObject(raw=null, type=Polygon, coordinates=[[[39.762651, 116.249113], [39.796424, 116.510038], [40.072312, 116.023893], [39.762651, 116.249113]]], meta={city=beijing, country=china}))
	 System.out.println(getResponse);

	 // Query all data under `fleet`
	 ScanResponse scanResponse = leader.scan("fleet");
	 // ScanResponse(super=BaseResponse(ok=true, elapsed=161.744µs, err=null), fields=[age, height, speed], ids=null, points=null, bounds=null, hashes=null, objects=[ScanResponse.Data(id=bounds1, object=ScanResponse.DataObject(raw=null, type=Polygon, coordinates=[[[39.867514, 116.577703], [39.868634, 116.577703], [39.868634, 116.578497], [39.867514, 116.578497], [39.867514, 116.577703]]], meta=null), fields=[0.0, 120.5, 90.5]), ScanResponse.Data(id=geojson, object=ScanResponse.DataObject(raw=null, type=Polygon, coordinates=[[[39.762651, 116.249113], [39.796424, 116.510038], [40.072312, 116.023893], [39.762651, 116.249113]]], meta={city=beijing, country=china}), fields=[0.0, 0.0, 0.0]), ScanResponse.Data(id=geojsonLineString, object=ScanResponse.DataObject(raw=null, type=LineString, coordinates=[[39.867514, 116.577703], [39.868634, 116.578497]], meta={name=zouwei, gender=7}), fields=[0.0, 120.5, 90.5]), ScanResponse.Data(id=geojsonMultiLineString, object=ScanResponse.DataObject(raw=null, type=MultiLineString, coordinates=[[[39.867514, 116.577703], [39.868634, 116.578497]], [[39.867314, 116.577503], [39.866634, 116.576497]]], meta={name=zouwei, gender=1}), fields=[0.0, 120.5, 90.5]), ScanResponse.Data(id=geojsonMultiPoint, object=ScanResponse.DataObject(raw=null, type=MultiPoint, coordinates=[[39.867514, 116.577703], [39.868634, 116.578497]], meta={name=zouwei, age=30}), fields=[0.0, 120.5, 90.5]), ScanResponse.Data(id=geojsonMultiPolygon, object=ScanResponse.DataObject(raw=null, type=MultiPolygon, coordinates=[[[[116.577703, 39.867514], [116.578497, 39.868634], [116.576497, 39.867634], [116.577703, 39.867514]], [[116.577703, 39.867514], [116.578497, 39.868634], [116.576497, 39.867634], [116.577703, 39.867514]]]], meta={name=zouwei, gender=7}), fields=[0.0, 120.5, 90.5]), ScanResponse.Data(id=geojsonPoint, object=ScanResponse.DataObject(raw=null, type=Point, coordinates=[39.867514, 116.577703], meta={name=zouwei, age=30}), fields=[0.0, 120.5, 90.5]), ScanResponse.Data(id=geojsonPolygon, object=ScanResponse.DataObject(raw=null, type=Polygon, coordinates=[[[116.577703, 39.867514], [116.578497, 39.868634], [116.576497, 39.867634], [116.577703, 39.867514]]], meta={name=zouwei, gender=7}), fields=[0.0, 120.5, 90.5]), ScanResponse.Data(id=geojsonpoint, object=ScanResponse.DataObject(raw=null, type=LineString, coordinates=[[39.867514, 116.577703], [39.868634, 116.578497]], meta={name=zouwei}), fields=[0.0, 120.5, 90.5]), ScanResponse.Data(id=hash, object=ScanResponse.DataObject(raw=rawString, type=RawString, coordinates=null, meta=null), fields=[0.0, 120.5, 90.5]), ScanResponse.Data(id=hello, object=ScanResponse.DataObject(raw=null, type=Point, coordinates=[33.462, -112.268], meta=null), fields=[30.0, 0.0, 0.0]), ScanResponse.Data(id=json, object=ScanResponse.DataObject(raw={"name":{"first":"zou"}}, type=RawString, coordinates=null, meta=null), fields=[0.0, 0.0, 0.0]), ScanResponse.Data(id=point1, object=ScanResponse.DataObject(raw=null, type=Point, coordinates=[39.867514, 116.577703, 1661309797349], meta=null), fields=[0.0, 120.5, 90.5]), ScanResponse.Data(id=truck1, object=ScanResponse.DataObject(raw=null, type=Point, coordinates=[39.762651, 116.249113], meta=null), fields=[0.0, 123.8, 90.5])], count=14, cursor=0)
	 System.out.println(scanResponse);

     // close link
	 leader.close();
     ```

## ☕ Supporters

`JTile38` is an open source project. If you want to support Salvo, you can ☕ 



## ⚠️ License

JTile38 is licensed under either of
* Apache License, Version 2.0, ([LICENSE](LICENSE) or [http://www.apache.org/licenses/LICENSE-2.0](http://www.apache.org/licenses/LICENSE-2.0))
* MIT license ([LICENSE-MIT](LICENSE-MIT) or [http://opensource.org/licenses/MIT](http://opensource.org/licenses/MIT))