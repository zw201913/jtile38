<div align="center">
<h3>Java Client for Til38</h3>
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
         <version>1.0.2</version>
     </dependency>
     ```
   - 使用JTile38Clien
   
     ```java
     Tile38Client client = new Tile38Client(host, port, password);
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
                             .resultType(ResultType.hashes(5))
                             .build();
     String withInResult = client.within(withInOpts, new Circle(39.867514, 116.577703, 500));
     // {"ok":true,"fields":["age","height","speed"],"hashes":[{"id":"geojsonPolygon","hash":"wx4fu","fields":[0,120.5,90.5]},{"id":"geojsonMultiPolygon","hash":"wx4fu","fields":[0,120.5,90.5]}],"count":2,"cursor":0,"elapsed":"86.252µs"}
	    System.out.println(withInResult);
     client.close();
     ```
   - 
