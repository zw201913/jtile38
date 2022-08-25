package com.github.zw201913;


import com.github.zw201913.entity.*;
import com.github.zw201913.response.*;
import com.github.zw201913.util.JsonUtil;

import java.util.Map;

/**
 * @author zouwei
 * @className Leader
 * @date: 2022/8/12 下午5:53
 * @description:
 */
public class Leader extends Follower implements AutoCloseable {


	public Leader(final String host, final int port, final String password) {
		super(host, port, password);
	}

	public Leader(final String host, final int port) {
		super(host, port);
	}

	/**
	 * 持久化config set写入的配置信息
	 *
	 * @return
	 */
	public ConfigRewriteResponse configRewrite() {
		return commands(() -> client.configRewrite(), ConfigRewriteResponse.class);
	}

	/**
	 * 设置服务器配置参数
	 *
	 * @param parameter
	 * @param value
	 * @return
	 */
	public ConfigSetResponse configSet(String parameter, String... value) {
		return commands(() -> client.configSet(parameter, value), ConfigSetResponse.class);
	}

	/**
	 * 删除指定的数据对象
	 *
	 * @param key
	 * @param id
	 * @return
	 */
	public DelResponse del(String key, String id) {
		return commands(() -> client.del(key, id), DelResponse.class);
	}

	/**
	 * 删除指定通道
	 *
	 * @param name
	 * @return
	 */
	public DelResponse delChan(String name) {
		return commands(() -> client.delChan(name), DelResponse.class);
	}

	/**
	 * 删除指定hook
	 *
	 * @param name
	 * @return
	 */
	public DelResponse delHook(String name) {
		return commands(() -> client.delHook(name), DelResponse.class);
	}


	/**
	 * 删除指定数据组
	 *
	 * @param key
	 * @return
	 */
	public DropResponse drop(String key) {
		return commands(() -> client.drop(key), DropResponse.class);
	}

	/**
	 * 设置过期时间
	 *
	 * @param key
	 * @param id
	 * @param seconds
	 * @return
	 */
	public ExpireResponse expire(String key, String id, int seconds) {
		return commands(() -> client.expire(key, id, seconds), ExpireResponse.class);
	}

	/**
	 * 情况数据库
	 *
	 * @return
	 */
	public FlushDBResponse flushDB() {
		return commands(() -> client.flushDB(), FlushDBResponse.class);
	}

	/**
	 * 给数据对象设置字段
	 *
	 * @param key
	 * @param id
	 * @param fields
	 * @param isXx
	 * @return
	 */
	public SetResponse fset(String key, String id, Map<String, Double> fields, boolean isXx) {
		return commands(() -> client.fset(key, id, fields, isXx), SetResponse.class);
	}

	/**
	 * 给数据对象设置字段
	 *
	 * @param key
	 * @param id
	 * @param fields
	 * @return
	 */
	public SetResponse fset(String key, String id, Map<String, Double> fields) {
		return commands(() -> client.fset(key, id, fields), SetResponse.class);
	}

	/**
	 * 给数据对象设置字段
	 *
	 * @param key
	 * @param id
	 * @param field
	 * @param value
	 * @param isXx
	 * @return
	 */
	public SetResponse fset(String key, String id, String field, Double value, boolean isXx) {
		return commands(() -> client.fset(key, id, field, value, isXx), SetResponse.class);
	}

	/**
	 * 给数据对象设置字段
	 *
	 * @param key
	 * @param id
	 * @param field
	 * @param value
	 * @return
	 */
	public SetResponse fset(String key, String id, String field, Double value) {
		return commands(() -> client.fset(key, id, field, value), SetResponse.class);
	}

	/**
	 * @return
	 */
	public GCResponse gc() {
		return commands(() -> client.gc(), GCResponse.class);
	}

	/**
	 * 删除json文档字段
	 *
	 * @param key
	 * @param id
	 * @param path
	 * @return
	 */
	public DelResponse jdel(String key, String id, String path) {
		return commands(() -> client.jdel(key, id, path), DelResponse.class);
	}

	/**
	 * 设置json文档
	 *
	 * @param key
	 * @param id
	 * @param path
	 * @param value
	 * @return
	 */
	public SetResponse jset(String key, String id, String path, String value) {
		return commands(() -> client.jset(key, id, path, value), SetResponse.class);
	}

	/**
	 * 设置json文档
	 *
	 * @param key
	 * @param id
	 * @param path
	 * @param value
	 * @return
	 */
	public SetResponse jsetRaw(String key, String id, String path, String value) {
		return commands(() -> client.jsetRaw(key, id, path, value), SetResponse.class);
	}

	/**
	 * 设置json文档
	 *
	 * @param key
	 * @param id
	 * @param path
	 * @param value
	 * @return
	 */
	public SetResponse jsetStr(String key, String id, String path, String value) {
		return commands(() -> client.jsetStr(key, id, path, value), SetResponse.class);
	}

	/**
	 * 根据指定特征删除数据对象
	 *
	 * @param key
	 * @param pattern
	 * @return
	 */
	public DelResponse pdel(String key, String pattern) {
		return commands(() -> client.pdel(key, pattern), DelResponse.class);
	}

	/**
	 * 根据指定特征删除通道
	 *
	 * @param pattern
	 * @return
	 */
	public DelResponse pdelChan(String pattern) {
		return commands(() -> client.pdelChan(pattern), DelResponse.class);
	}

	/**
	 * 根据指定特征删除Hook
	 *
	 * @param pattern
	 * @return
	 */
	public DelResponse pdelHook(String pattern) {
		return commands(() -> client.pdelHook(pattern), DelResponse.class);
	}

	/**
	 * 删除过期时间
	 *
	 * @param key
	 * @param id
	 * @return
	 */
	public DelResponse persist(String key, String id) {
		return commands(() -> client.persist(key, id), DelResponse.class);
	}

	/**
	 * 根据特征订阅通道
	 *
	 * @param pattern
	 * @param patterns
	 * @return
	 */
	public PSubscribeResponse psubscribe(String pattern, String... patterns) {
		return commands(() -> client.psubscribe(pattern, patterns), PSubscribeResponse.class);
	}

	/**
	 * 设置是否只读模式
	 *
	 * @param readOnly
	 * @return
	 */
	public SetResponse readOnly(boolean readOnly) {
		return commands(() -> client.readOnly(readOnly), SetResponse.class);
	}

	/**
	 * @param key
	 * @param newkey
	 * @return
	 */
	public RenameResponse rename(String key, String newkey) {
		return commands(() -> client.rename(key, newkey), RenameResponse.class);
	}

	/**
	 * @param key
	 * @param newkey
	 * @return
	 */
	public RenameResponse renameNx(String key, String newkey) {
		return commands(() -> client.renameNx(key, newkey), RenameResponse.class);
	}

	/**
	 * 清除lua脚本缓存
	 *
	 * @return
	 */
	public ScriptFlushResponse scriptFlush() {
		return commands(() -> client.scriptFlush(), ScriptFlushResponse.class);
	}

	/**
	 * 加载lua脚本
	 *
	 * @param script
	 * @return
	 */
	public ScriptLoadResponse scriptLoad(String script) {
		return commands(() -> client.scriptLoad(script), ScriptLoadResponse.class);
	}

	/**
	 * 设置Point类型
	 *
	 * @param setOpts
	 * @param point
	 * @return
	 */
	public SetResponse setPoint(final SetOpts setOpts, final Point point) {
		return commands(() -> client.setPoint(setOpts, point), SetResponse.class);
	}


	/**
	 * 设置一个Bound矩形
	 *
	 * @param setOpts
	 * @param bounds
	 * @return
	 */
	public SetResponse setBounds(final SetOpts setOpts, Bounds bounds) {
		return commands(() -> client.setBounds(setOpts, bounds), SetResponse.class);
	}

	/**
	 * 创建一个geohash
	 *
	 * @param setOpts
	 * @param geohash
	 * @return
	 */
	public SetResponse setGeohash(final SetOpts setOpts, final Geohash geohash) {
		return commands(() -> client.setGeohash(setOpts, geohash), SetResponse.class);
	}

	/**
	 * 创建一个geohash
	 *
	 * @param setOpts
	 * @param geoJson
	 * @return
	 */
	public SetResponse setGeohash(final SetOpts setOpts, final String geoJson) {
		return commands(() -> client.setGeohash(setOpts, geoJson), SetResponse.class);
	}

	/**
	 * 创建一个GeoJson
	 *
	 * @param setOpts
	 * @param geoJson
	 * @return
	 */
	public SetResponse setGeoJson(final SetOpts setOpts, final GeoJson.BaseGeoJson geoJson) {
		return commands(() -> client.setObject(setOpts, geoJson), SetResponse.class);
	}

	/**
	 * 创建一个raw string
	 *
	 * @param setOpts
	 * @param rawString
	 * @return
	 */
	public SetResponse setRawString(final SetOpts setOpts, final RawString rawString) {
		return commands(() -> client.setString(setOpts, rawString), SetResponse.class);
	}

	/**
	 * 创建一个raw string
	 *
	 * @param setOpts
	 * @param rawString
	 * @return
	 */
	public SetResponse setRawString(final SetOpts setOpts, final String rawString) {
		return commands(() -> client.setString(setOpts, rawString), SetResponse.class);
	}

	/**
	 * 创建通道
	 *
	 * @param setChanNearByOpts
	 * @param point
	 * @return
	 * @throws Exception
	 */
	public SetResponse setChanNearBy(SetChanNearByOpts setChanNearByOpts, Point point) throws Exception {
		return commandsThrowException(() -> client.setChanNearBy(setChanNearByOpts, point), SetResponse.class);
	}

	/**
	 * 创建通道
	 *
	 * @param setChanNearByOpts
	 * @param roam
	 * @return
	 * @throws Exception
	 */
	public SetResponse setChanNearBy(SetChanNearByOpts setChanNearByOpts, Roam roam) {
		return commands(() -> client.setChanNearBy(setChanNearByOpts, roam), SetResponse.class);
	}

	/**
	 * 创建通道
	 *
	 * @param setChanIntersectsOpts
	 * @param element
	 * @return
	 */
	public SetResponse setChanIntersects(SetChanIntersectsOpts setChanIntersectsOpts, Element element) {
		return commands(() -> client.setChanIntersects(setChanIntersectsOpts, element), SetResponse.class);
	}

	/**
	 * 创建通道
	 *
	 * @param setChanWithInOpts
	 * @param element
	 * @return
	 */
	public SetResponse setChanWithIn(SetChanWithInOpts setChanWithInOpts, Element element) {
		return commands(() -> client.setChanWithIn(setChanWithInOpts, element), SetResponse.class);
	}

	/**
	 * 创建hook
	 *
	 * @param setHookNearByOpts
	 * @param point
	 * @return
	 */
	public SetResponse setHookNearBy(SetHookNearByOpts setHookNearByOpts, Point point) throws Exception {
		return commandsThrowException(() -> client.setHookNearBy(setHookNearByOpts, point), SetResponse.class);
	}

	/**
	 * 创建hook
	 *
	 * @param setHookNearByOpts
	 * @param roam
	 * @return
	 * @throws Exception
	 */
	public SetResponse setHookNearBy(SetHookNearByOpts setHookNearByOpts, Roam roam) {
		return commands(() -> client.setHookNearBy(setHookNearByOpts, roam), SetResponse.class);
	}

	/**
	 * 创建hook
	 *
	 * @param setHookIntersectsOpts
	 * @param element
	 * @return
	 */
	public SetResponse setHookIntersects(SetHookIntersectsOpts setHookIntersectsOpts, Element element) {
		return commands(() -> client.setHookIntersects(setHookIntersectsOpts, element), SetResponse.class);
	}

	/**
	 * 创建hook
	 *
	 * @param setHookWithInOpts
	 * @param element
	 * @return
	 */
	public SetResponse setHookWithIn(SetHookWithInOpts setHookWithInOpts, Element element) {
		return commands(() -> client.setHookWithIn(setHookWithInOpts, element), SetResponse.class);
	}

	/**
	 * 订阅通道
	 *
	 * @param channel
	 * @param channels
	 * @return
	 */
	public SubscribeResponse subscribe(String channel, String... channels) {
		return commands(() -> client.subscribe(channel, channels), SubscribeResponse.class);
	}

	/**
	 * @param opts
	 * @return
	 */
	public EvalResponse eval(EvalScriptOpts opts) {
		return commands(() -> client.eval(opts), EvalResponse.class);
	}

	/**
	 * @param opts
	 * @return
	 */
	public EvalResponse evalNa(EvalScriptOpts opts) {
		return commands(() -> client.evalNa(opts), EvalResponse.class);
	}

	/**
	 * @param opts
	 * @return
	 */
	public EvalResponse evalNaSha(EvalScriptOpts opts) {
		return commands(() -> client.evalNaSha(opts), EvalResponse.class);
	}

	/**
	 * @param opts
	 * @return
	 */
	public EvalResponse evalSha(EvalScriptOpts opts) {
		return commands(() -> client.evalSha(opts), EvalResponse.class);
	}

	/**
	 * @param opts
	 * @param timeout
	 * @return
	 * @throws Exception
	 */
	public EvalResponse eval(EvalScriptOpts opts, double timeout) throws Exception {
		return commandsThrowException(() -> client.eval(opts, timeout), EvalResponse.class);
	}

	/**
	 * @param opts
	 * @param timeout
	 * @return
	 * @throws Exception
	 */
	public EvalResponse evalNa(EvalScriptOpts opts, double timeout) throws Exception {
		return commandsThrowException(() -> client.evalNa(opts, timeout), EvalResponse.class);
	}

	/**
	 * @param opts
	 * @param timeout
	 * @return
	 * @throws Exception
	 */
	public EvalResponse evalNaSha(EvalScriptOpts opts, double timeout) throws Exception {
		return commandsThrowException(() -> client.evalNaSha(opts, timeout), EvalResponse.class);
	}

	/**
	 * @param opts
	 * @param timeout
	 * @return
	 * @throws Exception
	 */
	public EvalResponse evalSha(EvalScriptOpts opts, double timeout) throws Exception {
		return commandsThrowException(() -> client.evalSha(opts, timeout), EvalResponse.class);
	}
}
