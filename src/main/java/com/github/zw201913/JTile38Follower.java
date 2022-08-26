package com.github.zw201913;

import com.github.zw201913.entity.*;
import com.github.zw201913.enums.OutPutType;
import com.github.zw201913.response.*;
import com.github.zw201913.util.JsonUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author zouwei
 * @className Follower
 * @date: 2022/8/12 下午2:08
 * @description:
 */
public class JTile38Follower implements AutoCloseable {

	protected JTile38Client client;

	public JTile38Follower(final String host, final int port, final String password) {
		this.client = new JTile38Client(host, port, password);
	}

	public JTile38Follower(final String host, final int port) {
		this.client = new JTile38Client(host, port);
	}

	@Override
	public void close() {
		if (Objects.nonNull(client)) {
			client.close();
		}
	}

	/**
	 * 下载aof
	 *
	 * @param pos
	 * @return
	 */
	public AofResponse aof(final int pos) {
		return commands(() -> client.aof(pos), json -> {
			if (StringUtils.equals(json, "OK")) {
				return AofResponse.success();
			}
			return JsonUtil.string2Obj(json, AofResponse.class);
		});
	}

	/**
	 * @param pos
	 * @param size
	 * @return
	 */
	public AofMd5Response aofMd5(final int pos, final int size) {
		return commands(() -> client.aofMd5(pos, size), AofMd5Response.class);
	}


	/**
	 * aof压缩
	 *
	 * @return
	 */
	public AofShrinkResponse aofShrink() {
		return commands(() -> client.aofShrink(), AofShrinkResponse.class);
	}


	/**
	 * 验证密码
	 *
	 * @param password
	 * @return
	 */
	public AuthResponse auth(final String password) {
		return commands(() -> client.auth(password), AuthResponse.class);
	}

	/**
	 * 返回集合(key)中所有对象的最小边界矩形
	 *
	 * @param key
	 * @return
	 */
	public BoundsResponse bounds(final String key) {
		return commands(() -> client.bounds(key), BoundsResponse.class);
	}

	/**
	 * 返回所有匹配模式的通道
	 *
	 * @param pattern
	 * @return
	 */
	public ChansResponse chans(final String pattern) {
		return commands(() -> client.chans(pattern), ChansResponse.class);
	}

	/**
	 * 获取配置信息
	 *
	 * @param parameter
	 * @return
	 */
	public ConfigGetResponse configGet(final String parameter) {
		return commands(() -> client.configGet(parameter), ConfigGetResponse.class);
	}

	/**
	 * 查询指定数据对象
	 *
	 * @param key
	 * @param id
	 * @return
	 */
	public GetResponse get(String key, String id) {
		return commands(() -> client.get(key, id), GetResponse.class);
	}

	/**
	 * 查询指定数据对象
	 *
	 * @param key
	 * @param id
	 * @return
	 */
	public GetResponse getWithFields(String key, String id) {
		return commands(() -> client.getWithFields(key, id), GetResponse.class);
	}

	/**
	 * 查询指定数据对象
	 *
	 * @param key
	 * @param id
	 * @return
	 */
	public GetPointResponse getPointWithFields(String key, String id) {
		return commands(() -> client.getPoint(key, id, true), GetPointResponse.class);
	}

	/**
	 * 查询指定数据对象
	 *
	 * @param key
	 * @param id
	 * @return
	 */
	public GetPointResponse getPoint(String key, String id) {
		return commands(() -> client.getPoint(key, id, false), GetPointResponse.class);
	}

	/**
	 * 查询指定数据对象
	 *
	 * @param key
	 * @param id
	 * @return
	 */
	public GetBoundsResponse getBoundsWithFields(String key, String id) {
		return commands(() -> client.getBounds(key, id, true), GetBoundsResponse.class);
	}

	/**
	 * 查询指定数据对象
	 *
	 * @param key
	 * @param id
	 * @return
	 */
	public GetBoundsResponse getBounds(String key, String id) {
		return commands(() -> client.getBounds(key, id, false), GetBoundsResponse.class);
	}

	/**
	 * 查询指定数据对象
	 *
	 * @param key
	 * @param id
	 * @param precision
	 * @return
	 */
	public GetGeohashResponse getGeohashWithFields(String key, String id, int precision) {
		return commands(() -> client.getGeohash(key, id, precision, true), GetGeohashResponse.class);
	}

	/**
	 * 查询指定数据对象
	 *
	 * @param key
	 * @param id
	 * @param precision
	 * @return
	 */
	public GetGeohashResponse getGeohash(String key, String id, int precision) {
		return commands(() -> client.getGeohash(key, id, precision, false), GetGeohashResponse.class);
	}

	/**
	 * 查询指定数据对象
	 *
	 * @param key
	 * @param id
	 * @return
	 */
	public GetResponse getObjectWithFields(String key, String id) {
		return commands(() -> client.getObject(key, id, true), GetResponse.class);
	}

	/**
	 * 查询指定数据对象
	 *
	 * @param key
	 * @param id
	 * @return
	 */
	public GetResponse getObject(String key, String id) {
		return commands(() -> client.getObject(key, id, false), GetResponse.class);
	}

	/**
	 * @param intersectsOpts
	 * @param element
	 * @return
	 */
	public IntersectsResponse intersects(IntersectsOpts intersectsOpts, Element element) {
		return commands(() -> client.intersects(intersectsOpts, element), IntersectsResponse.class);
	}

	/**
	 * @param intersectsOpts
	 * @param element
	 * @param timeout
	 * @return
	 * @throws Exception
	 */
	public IntersectsResponse intersects(IntersectsOpts intersectsOpts, Element element, double timeout) throws Exception {
		return commandsThrowException(() -> client.intersects(intersectsOpts, element, timeout), IntersectsResponse.class);
	}

	/**
	 * 查询json文档
	 *
	 * @param key
	 * @param id
	 * @param path
	 * @param isRaw
	 * @return
	 */
	public JGetResponse jget(String key, String id, String path, boolean isRaw) {
		return commands(() -> client.jget(key, id, path, isRaw), JGetResponse.class);
	}

	/**
	 * 查询json文档
	 *
	 * @param key
	 * @param id
	 * @param path
	 * @return
	 */
	public JGetResponse jget(String key, String id, String path) {
		return commands(() -> client.jget(key, id, path), JGetResponse.class);
	}

	/**
	 * 查询匹配的key
	 *
	 * @param pattern
	 * @return
	 */
	public KeysResponse keys(String pattern) {
		return commands(() -> client.keys(pattern), KeysResponse.class);
	}


	/**
	 * 查询指定特征hook
	 *
	 * @param pattern
	 * @return
	 */
	public HooksResponse hooks(String pattern) {
		return commands(() -> client.hooks(pattern), HooksResponse.class);
	}

	/**
	 * 查询数据输出格式
	 *
	 * @return
	 */
	public OutputResponse output() {
		return commands(() -> client.output(), s -> {
//			{"ok":true,"output":"json","elapsed":130ns}
			String elapsed = StringUtils.substringBetween(s, "\"elapsed\":", "}");
			if (StringUtils.containsAny(elapsed, "\'", "\"")) {
				elapsed = StringUtils.replace(elapsed, "\'", "");
				elapsed = StringUtils.replace(elapsed, "\"", "");
			}
			if (StringUtils.contains(s, "\"json\"")) {
				return OutputResponse.newInstance(OutPutType.JSON, elapsed);
			} else {
				return OutputResponse.newInstance(OutPutType.RESP, elapsed);
			}
		});
	}

	/**
	 * ping服务
	 *
	 * @return
	 */
	public PingResponse ping() {
		return commands(() -> client.ping(), PingResponse.class);
	}

	/**
	 * 退出
	 *
	 * @return
	 */
	public boolean quit() {
		return commands(() -> client.quit(), s -> StringUtils.equals(s, "OK"));
	}

	/**
	 * 判断脚本是否存在
	 *
	 * @param sha1
	 * @param sha1s
	 * @return
	 */
	public boolean scriptExists(String sha1, String... sha1s) {
		return commands(() -> client.scriptExists(sha1, sha1s), s -> true);
	}

	/**
	 * @return
	 */
	public ServerResponse server() {
		return commands(() -> client.server(), ServerResponse.class);
	}

	/**
	 * @param key
	 * @param keys
	 * @return
	 */
	public StatsResponse stats(String key, String... keys) {
		return commands(() -> client.stats(key, keys), StatsResponse.class);
	}

	/**
	 * 扫描数据对象
	 *
	 * @param scanOpts
	 * @return
	 */
	public ScanResponse scan(ScanOpts scanOpts) {
		return commands(() -> client.scan(scanOpts), ScanResponse.class);
	}

	/**
	 * @param scanOpts
	 * @param timeout
	 * @return
	 * @throws Exception
	 */
	public ScanResponse scan(ScanOpts scanOpts, double timeout) throws Exception {
		return commandsThrowException(() -> client.scan(scanOpts, timeout), ScanResponse.class);
	}

	/**
	 * 扫描数据对象
	 *
	 * @param key
	 * @return
	 */
	public ScanResponse scan(String key) {
		ScanOpts scanOpts = (ScanOpts) new ScanOpts.Builder()
				.key(key)
				.build();
		return commands(() -> client.scan(scanOpts), ScanResponse.class);
	}

	/**
	 * @param key
	 * @param timeout
	 * @return
	 * @throws Exception
	 */
	public ScanResponse scan(String key, double timeout) throws Exception {
		ScanOpts scanOpts = (ScanOpts) new ScanOpts.Builder()
				.key(key)
				.build();
		return commandsThrowException(() -> client.scan(scanOpts, timeout), ScanResponse.class);
	}

	/**
	 * 查询过期时间
	 *
	 * @param key
	 * @param id
	 * @return
	 */
	public TtlResponse ttl(String key, String id) {
		return commands(() -> client.ttl(key, id), TtlResponse.class);
	}

	/**
	 * 查询数据对象
	 *
	 * @param searchOpts
	 * @return
	 */
	public SearchResponse search(SearchOpts searchOpts) {
		return commands(() -> client.search(searchOpts), SearchResponse.class);
	}

	/**
	 * @param searchOpts
	 * @param timeout
	 * @return
	 * @throws Exception
	 */
	public SearchResponse search(SearchOpts searchOpts, double timeout) throws Exception {
		return commandsThrowException(() -> client.search(searchOpts, timeout), SearchResponse.class);
	}

	/**
	 * 查询点范围内的坐标
	 *
	 * @param nearByOpts
	 * @param point
	 * @return
	 */
	public NearByResponse nearbyPoint(NearByOpts nearByOpts, Point point) {
		return commands(() -> client.nearbyPoint(nearByOpts, point), NearByResponse.class);
	}

	/**
	 * @param nearByOpts
	 * @param point
	 * @param timeout
	 * @return
	 * @throws Exception
	 */
	public NearByResponse nearbyPoint(NearByOpts nearByOpts, Point point, double timeout) throws Exception {
		return commandsThrowException(() -> client.nearbyPoint(nearByOpts, point, timeout), NearByResponse.class);
	}

	/**
	 * @param nearByOpts
	 * @param roam
	 * @return
	 */
	public NearByResponse nearbyRoam(NearByOpts nearByOpts, Roam roam) {
		return commands(() -> client.nearbyRoam(nearByOpts, roam), NearByResponse.class);
	}

	/**
	 * @param nearByOpts
	 * @param roam
	 * @param timeout
	 * @return
	 * @throws Exception
	 */
	public NearByResponse nearbyRoam(NearByOpts nearByOpts, Roam roam, double timeout) throws Exception {
		return commandsThrowException(() -> client.nearbyRoam(nearByOpts, roam, timeout), NearByResponse.class);
	}

	/**
	 * @param withInOpts
	 * @param element
	 * @return
	 */
	public WithInResponse within(WithInOpts withInOpts, Element element) {
		return commands(() -> client.within(withInOpts, element), WithInResponse.class);
	}

	/**
	 * @param withInOpts
	 * @param element
	 * @param timeout
	 * @return
	 * @throws Exception
	 */
	public WithInResponse within(WithInOpts withInOpts, Element element, double timeout) throws Exception {
		return commandsThrowException(() -> client.within(withInOpts, element, timeout), WithInResponse.class);
	}

	/**
	 * @param host
	 * @param port
	 * @return
	 */
	public SetResponse follow(String host, int port) {
		return commands(() -> client.follow(host, port), SetResponse.class);
	}

	/**
	 * @return
	 */
	public SetResponse noFollow() {
		return commands(() -> client.noFollow(), SetResponse.class);
	}

	/**
	 * @param opts
	 * @return
	 */
	public EvalResponse evalRo(EvalScriptOpts opts) {
		return commands(() -> client.evalRo(opts), EvalResponse.class);
	}

	/**
	 * @param opts
	 * @return
	 */
	public EvalResponse evalRoSha(EvalScriptOpts opts) {
		return commands(() -> client.evalRoSha(opts), EvalResponse.class);
	}

	/**
	 * @param opts
	 * @param timeout
	 * @return
	 * @throws Exception
	 */
	public EvalResponse evalRo(EvalScriptOpts opts, double timeout) throws Exception {
		return commandsThrowException(() -> client.evalRo(opts, timeout), EvalResponse.class);
	}

	/**
	 * @param opts
	 * @param timeout
	 * @return
	 * @throws Exception
	 */
	public EvalResponse evalRoSha(EvalScriptOpts opts, double timeout) throws Exception {
		return commandsThrowException(() -> client.evalRoSha(opts, timeout), EvalResponse.class);
	}

	/**
	 * @param e1
	 * @param e2
	 * @return
	 */
	public TestResponse testWithIn(Element e1, Element e2) {
		return commands(() -> client.testWithIn(e1, e2), TestResponse.class);
	}

	/**
	 * @param e1
	 * @param e2
	 * @param clip
	 * @return
	 */
	public TestResponse testIntersects(Element e1, Element e2, boolean clip) {
		return commands(() -> client.testIntersects(e1, e2, clip), TestResponse.class);
	}

	/**
	 * @param action
	 * @param clazz
	 * @param <T>
	 * @return
	 */
	protected <T> T commands(Supplier<String> action, Class<T> clazz) {
		return commands(action, result -> JsonUtil.string2Obj(result, clazz));
	}

	/**
	 * @param action
	 * @param resultHandler
	 * @param <T>
	 * @return
	 */
	protected <T> T commands(Supplier<String> action, Function<String, T> resultHandler) {
		try {
			String result = action.get();
			System.out.println(result);
			return resultHandler.apply(result);
		} catch (Exception e) {
			throw e;
		}
	}

	protected <T> T commandsThrowException(ExceptionSupplier<String> action, Class<T> clazz) throws Exception {
		return commandsThrowException(action, result -> JsonUtil.string2Obj(result, clazz));
	}

	/**
	 * @param action
	 * @param resultHandler
	 * @param <T>
	 * @return
	 * @throws Exception
	 */
	protected <T> T commandsThrowException(ExceptionSupplier<String> action, Function<String, T> resultHandler) throws Exception {
		try {
			String result = action.get();
			return resultHandler.apply(result);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 *
	 * @param <T>
	 */
	protected interface ExceptionSupplier<T> {
		T get() throws Exception;
	}
}
