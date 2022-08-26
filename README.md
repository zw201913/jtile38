<div align="center">
<h3>Tile38 Java客户端</h3>
<p><img width="250" alt="jtile38-removebg-preview" src="https://user-images.githubusercontent.com/16849315/186699764-7a4d43b9-4269-4c3b-88b0-cee4d49847f9.png"></p>
  <p>
    <a href="https://github.com/zw201913/jtile38/blob/main/README.md">简体中文</a>&nbsp;&nbsp;
    <a href="https://github.com/zw201913/jtile38/blob/main/README.en-US.md">English</a>
</p>
  <p><img alt="License" src="https://camo.githubusercontent.com/093fc33ad6230c9fc08a4ca2392f4cf7fbe51e746be4d1a09456d8b1e1101a40/68747470733a2f2f696d672e736869656c64732e696f2f6372617465732f6c2f73616c766f2e737667" /></p>
</div>

## 🎯 功能

   - 提供了JTile38Client类支持Tile38所有的命令
   - 提供了JTile38Leader和JTile38Follower类支持读写分离模式

## ⚡️ 快速开始

   - 先从maven中央仓库拉依赖：[JTile38](https://mvnrepository.com/artifact/com.github.zw201913/jtile38)
     ```xml
     <dependency>
         <groupId>com.github.zw201913</groupId>
         <artifactId>jtile38</artifactId>
         <version>1.0.3</version>
     </dependency>
     ```
   - 使用JTile38Client
   
     ```java
     JTile38Client client = new JTile38Client(host, port, password);
     // 添加Point数据
     SetOpts opts = new SetOpts.Builder()
                .key("fleet")
                .id("truck1")
                .field("speed", 90.5)
                .field("height", 123.8)
                .build();
     String result = client.setPoint(opts, new Point(116.249113, 39.762651));
     // {"ok":true,"elapsed":"16.501µs"}
     System.out.println(result);

      // 查询指定数据
     String pointResult = client.get("fleet", "truck1");
     // {"ok":true,"object":{"type":"Point","coordinates":[39.762651,116.249113]},"elapsed":"17.223µs"}
     System.out.println(pointResult);
     
     // 判断在目标区域500米范围内的数据列表
     WithInOpts withInOpts = (WithInOpts) new WithInOpts.Builder()
                             .key("fleet")
     // 设置返回结果
                             .resultType(ResultType.hashes(5))
                             .build();
     String withInResult = client.within(withInOpts, new Circle(39.867514, 116.577703, 500));
     // {"ok":true,"fields":["age","height","speed"],"hashes":[{"id":"geojsonPolygon","hash":"wx4fu","fields":[0,120.5,90.5]},{"id":"geojsonMultiPolygon","hash":"wx4fu","fields":[0,120.5,90.5]}],"count":2,"cursor":0,"elapsed":"86.252µs"}
        System.out.println(withInResult);
     
     //关闭链接
     client.close();
     ```
   - 使用JTile38Follower

     JTile38Follower只能使用查询功能，并且所有的返回结果都已经被封装成实体类；
     ```java
     JTile38Follower follower = new JTile38Follower("42.192.50.8", 9851, "123456");
     
     // 查询指定数据truck1
     GetPointResponse getPointResponse = follower.getPoint("fleet", "truck1");
     // GetPointResponse(super=BaseResponse(ok=true, elapsed=10.47µs, err=null), fields=null, point=GetPointResponse.Point(lat=116.249113, lon=39.762651, z=0))
     System.out.println(getPointResponse);
     // 查询fleet下所有数据
     ScanResponse scanResponse = follower.scan("fleet");
     // ScanResponse(super=BaseResponse(ok=true, elapsed=122.14µs, err=null), fields=[age, height, speed], ids=null, points=null, bounds=null, hashes=null, objects=[ScanResponse.Data(id=bounds1, object=ScanResponse.DataObject(raw=null, type=Polygon, coordinates=[[[39.867514, 116.577703], [39.868634, 116.577703], [39.868634, 116.578497], [39.867514, 116.578497], [39.867514, 116.577703]]], meta=null), fields=[0.0, 120.5, 90.5]), ScanResponse.Data(id=geojsonLineString, object=ScanResponse.DataObject(raw=null, type=LineString, coordinates=[[39.867514, 116.577703], [39.868634, 116.578497]], meta={name=zouwei, gender=7}), fields=[0.0, 120.5, 90.5]), ScanResponse.Data(id=geojsonMultiLineString, object=ScanResponse.DataObject(raw=null, type=MultiLineString, coordinates=[[[39.867514, 116.577703], [39.868634, 116.578497]], [[39.867314, 116.577503], [39.866634, 116.576497]]], meta={name=zouwei, gender=1}), fields=[0.0, 120.5, 90.5]), ScanResponse.Data(id=geojsonMultiPoint, object=ScanResponse.DataObject(raw=null, type=MultiPoint, coordinates=[[39.867514, 116.577703], [39.868634, 116.578497]], meta={name=zouwei, age=30}), fields=[0.0, 120.5, 90.5]), ScanResponse.Data(id=geojsonMultiPolygon, object=ScanResponse.DataObject(raw=null, type=MultiPolygon, coordinates=[[[[116.577703, 39.867514], [116.578497, 39.868634], [116.576497, 39.867634], [116.577703, 39.867514]], [[116.577703, 39.867514], [116.578497, 39.868634], [116.576497, 39.867634], [116.577703, 39.867514]]]], meta={name=zouwei, gender=7}), fields=[0.0, 120.5, 90.5]), ScanResponse.Data(id=geojsonPoint, object=ScanResponse.DataObject(raw=null, type=Point, coordinates=[39.867514, 116.577703], meta={name=zouwei, age=30}), fields=[0.0, 120.5, 90.5]), ScanResponse.Data(id=geojsonPolygon, object=ScanResponse.DataObject(raw=null, type=Polygon, coordinates=[[[116.577703, 39.867514], [116.578497, 39.868634], [116.576497, 39.867634], [116.577703, 39.867514]]], meta={name=zouwei, gender=7}), fields=[0.0, 120.5, 90.5]), ScanResponse.Data(id=geojsonpoint, object=ScanResponse.DataObject(raw=null, type=LineString, coordinates=[[39.867514, 116.577703], [39.868634, 116.578497]], meta={name=zouwei}), fields=[0.0, 120.5, 90.5]), ScanResponse.Data(id=hash, object=ScanResponse.DataObject(raw=rawString, type=RawString, coordinates=null, meta=null), fields=[0.0, 120.5, 90.5]), ScanResponse.Data(id=hello, object=ScanResponse.DataObject(raw=null, type=Point, coordinates=[33.462, -112.268], meta=null), fields=[30.0, 0.0, 0.0]), ScanResponse.Data(id=json, object=ScanResponse.DataObject(raw={"name":{"first":"zou"}}, type=RawString, coordinates=null, meta=null), fields=[0.0, 0.0, 0.0]), ScanResponse.Data(id=point1, object=ScanResponse.DataObject(raw=null, type=Point, coordinates=[39.867514, 116.577703, 1661309797349], meta=null), fields=[0.0, 120.5, 90.5]), ScanResponse.Data(id=truck1, object=ScanResponse.DataObject(raw=null, type=Point, coordinates=[39.762651, 116.249113], meta=null), fields=[0.0, 123.8, 90.5])], count=13, cursor=0)
     System.out.println(scanResponse);

     // 关闭链接
     follower.close();
     ```
   - 使用JTile38Leader

     ```java
     JTile38Leader leader = new JTile38Leader("42.192.50.8", 9851, "123456");

	 SetOpts setOpts = new SetOpts.Builder()
				.key("fleet")
				.id("geojson")
				.build();
	 // 多边形
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

	 // 查询指定数据truck1
	 GetResponse getResponse = leader.getObject("fleet", "geojson");
	 // GetResponse(super=BaseResponse(ok=true, elapsed=12.784µs, err=null), fields=null, object=GetResponse.DataObject(raw=null, type=Polygon, coordinates=[[[39.762651, 116.249113], [39.796424, 116.510038], [40.072312, 116.023893], [39.762651, 116.249113]]], meta={city=beijing, country=china}))
	 System.out.println(getResponse);

	 // 查询fleet下所有数据
	 ScanResponse scanResponse = leader.scan("fleet");
	 // ScanResponse(super=BaseResponse(ok=true, elapsed=161.744µs, err=null), fields=[age, height, speed], ids=null, points=null, bounds=null, hashes=null, objects=[ScanResponse.Data(id=bounds1, object=ScanResponse.DataObject(raw=null, type=Polygon, coordinates=[[[39.867514, 116.577703], [39.868634, 116.577703], [39.868634, 116.578497], [39.867514, 116.578497], [39.867514, 116.577703]]], meta=null), fields=[0.0, 120.5, 90.5]), ScanResponse.Data(id=geojson, object=ScanResponse.DataObject(raw=null, type=Polygon, coordinates=[[[39.762651, 116.249113], [39.796424, 116.510038], [40.072312, 116.023893], [39.762651, 116.249113]]], meta={city=beijing, country=china}), fields=[0.0, 0.0, 0.0]), ScanResponse.Data(id=geojsonLineString, object=ScanResponse.DataObject(raw=null, type=LineString, coordinates=[[39.867514, 116.577703], [39.868634, 116.578497]], meta={name=zouwei, gender=7}), fields=[0.0, 120.5, 90.5]), ScanResponse.Data(id=geojsonMultiLineString, object=ScanResponse.DataObject(raw=null, type=MultiLineString, coordinates=[[[39.867514, 116.577703], [39.868634, 116.578497]], [[39.867314, 116.577503], [39.866634, 116.576497]]], meta={name=zouwei, gender=1}), fields=[0.0, 120.5, 90.5]), ScanResponse.Data(id=geojsonMultiPoint, object=ScanResponse.DataObject(raw=null, type=MultiPoint, coordinates=[[39.867514, 116.577703], [39.868634, 116.578497]], meta={name=zouwei, age=30}), fields=[0.0, 120.5, 90.5]), ScanResponse.Data(id=geojsonMultiPolygon, object=ScanResponse.DataObject(raw=null, type=MultiPolygon, coordinates=[[[[116.577703, 39.867514], [116.578497, 39.868634], [116.576497, 39.867634], [116.577703, 39.867514]], [[116.577703, 39.867514], [116.578497, 39.868634], [116.576497, 39.867634], [116.577703, 39.867514]]]], meta={name=zouwei, gender=7}), fields=[0.0, 120.5, 90.5]), ScanResponse.Data(id=geojsonPoint, object=ScanResponse.DataObject(raw=null, type=Point, coordinates=[39.867514, 116.577703], meta={name=zouwei, age=30}), fields=[0.0, 120.5, 90.5]), ScanResponse.Data(id=geojsonPolygon, object=ScanResponse.DataObject(raw=null, type=Polygon, coordinates=[[[116.577703, 39.867514], [116.578497, 39.868634], [116.576497, 39.867634], [116.577703, 39.867514]]], meta={name=zouwei, gender=7}), fields=[0.0, 120.5, 90.5]), ScanResponse.Data(id=geojsonpoint, object=ScanResponse.DataObject(raw=null, type=LineString, coordinates=[[39.867514, 116.577703], [39.868634, 116.578497]], meta={name=zouwei}), fields=[0.0, 120.5, 90.5]), ScanResponse.Data(id=hash, object=ScanResponse.DataObject(raw=rawString, type=RawString, coordinates=null, meta=null), fields=[0.0, 120.5, 90.5]), ScanResponse.Data(id=hello, object=ScanResponse.DataObject(raw=null, type=Point, coordinates=[33.462, -112.268], meta=null), fields=[30.0, 0.0, 0.0]), ScanResponse.Data(id=json, object=ScanResponse.DataObject(raw={"name":{"first":"zou"}}, type=RawString, coordinates=null, meta=null), fields=[0.0, 0.0, 0.0]), ScanResponse.Data(id=point1, object=ScanResponse.DataObject(raw=null, type=Point, coordinates=[39.867514, 116.577703, 1661309797349], meta=null), fields=[0.0, 120.5, 90.5]), ScanResponse.Data(id=truck1, object=ScanResponse.DataObject(raw=null, type=Point, coordinates=[39.762651, 116.249113], meta=null), fields=[0.0, 123.8, 90.5])], count=14, cursor=0)
	 System.out.println(scanResponse);

     // 关闭链接
	 leader.close();
     ```

## ☕ 支持

`JTile38`是一个开源项目, 如果想支持本项目, 欢迎请我喝咖啡可以 ☕

<p><image width="180" src="https://user-images.githubusercontent.com/16849315/186829736-a2e66254-bcff-4827-bd05-37df0eb9f785.jpg">
<image width="180" src="https://user-images.githubusercontent.com/16849315/186829768-ab60f742-d215-4e96-81ba-1b6ef24db7bc.png"></p>

## ⚠️ 开源协议

JTile38 项目采用以下开源协议:
* Apache License, Version 2.0, ([LICENSE](LICENSE) or [http://www.apache.org/licenses/LICENSE-2.0](http://www.apache.org/licenses/LICENSE-2.0))
* MIT license ([LICENSE-MIT](LICENSE-MIT) or [http://opensource.org/licenses/MIT](http://opensource.org/licenses/MIT))
