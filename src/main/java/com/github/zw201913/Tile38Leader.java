package com.github.zw201913;


import com.github.zw201913.entity.*;
import com.github.zw201913.response.*;

import java.util.Map;

/**
 * @author zouwei
 * @className Leader
 * @date: 2022/8/12 下午5:53
 * @description:
 */
public class Tile38Leader extends Tile38Follower implements AutoCloseable {


	public Tile38Leader(final String host, final int port, final String password) {
		super(host, port, password);
	}

	public Tile38Leader(final String host, final int port) {
		super(host, port);
	}

	/**
	 * 持久化config set写入的配置信息
	 *
	 * @return
	 */
	public ConfigRewriteResponse configRewrite() {
		return commands(() -> tile38Client.configRewrite(), ConfigRewriteResponse.class);
	}

	/**
	 * 设置服务器配置参数
	 *
	 * @param parameter
	 * @param value
	 * @return
	 */
	public ConfigSetResponse configSet(String parameter, String... value) {
		return commands(() -> tile38Client.configSet(parameter, value), ConfigSetResponse.class);
	}

	/**
	 * 删除指定的数据对象
	 *
	 * @param key
	 * @param id
	 * @return
	 */
	public DelResponse del(String key, String id) {
		return commands(() -> tile38Client.del(key, id), DelResponse.class);
	}

	/**
	 * 删除指定通道
	 *
	 * @param name
	 * @return
	 */
	public DelResponse delChan(String name) {
		return commands(() -> tile38Client.delChan(name), DelResponse.class);
	}

	/**
	 * 删除指定hook
	 *
	 * @param name
	 * @return
	 */
	public DelResponse delHook(String name) {
		return commands(() -> tile38Client.delHook(name), DelResponse.class);
	}


	/**
	 * 删除指定数据组
	 *
	 * @param key
	 * @return
	 */
	public DropResponse drop(String key) {
		return commands(() -> tile38Client.drop(key), DropResponse.class);
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
		return commands(() -> tile38Client.expire(key, id, seconds), ExpireResponse.class);
	}

	/**
	 * 情况数据库
	 *
	 * @return
	 */
	public FlushDBResponse flushDB() {
		return commands(() -> tile38Client.flushDB(), FlushDBResponse.class);
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
		return commands(() -> tile38Client.fset(key, id, fields, isXx), SetResponse.class);
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
		return commands(() -> tile38Client.fset(key, id, fields), SetResponse.class);
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
		return commands(() -> tile38Client.fset(key, id, field, value, isXx), SetResponse.class);
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
		return commands(() -> tile38Client.fset(key, id, field, value), SetResponse.class);
	}

	/**
	 * @return
	 */
	public GCResponse gc() {
		return commands(() -> tile38Client.gc(), GCResponse.class);
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
		return commands(() -> tile38Client.jdel(key, id, path), DelResponse.class);
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
		return commands(() -> tile38Client.jset(key, id, path, value), SetResponse.class);
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
		return commands(() -> tile38Client.jsetRaw(key, id, path, value), SetResponse.class);
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
		return commands(() -> tile38Client.jsetStr(key, id, path, value), SetResponse.class);
	}

	/**
	 * 根据指定特征删除数据对象
	 *
	 * @param key
	 * @param pattern
	 * @return
	 */
	public DelResponse pdel(String key, String pattern) {
		return commands(() -> tile38Client.pdel(key, pattern), DelResponse.class);
	}

	/**
	 * 根据指定特征删除通道
	 *
	 * @param pattern
	 * @return
	 */
	public DelResponse pdelChan(String pattern) {
		return commands(() -> tile38Client.pdelChan(pattern), DelResponse.class);
	}

	/**
	 * 根据指定特征删除Hook
	 *
	 * @param pattern
	 * @return
	 */
	public DelResponse pdelHook(String pattern) {
		return commands(() -> tile38Client.pdelHook(pattern), DelResponse.class);
	}

	/**
	 * 删除过期时间
	 *
	 * @param key
	 * @param id
	 * @return
	 */
	public DelResponse persist(String key, String id) {
		return commands(() -> tile38Client.persist(key, id), DelResponse.class);
	}

	/**
	 * 根据特征订阅通道
	 *
	 * @param pattern
	 * @param patterns
	 * @return
	 */
	public PSubscribeResponse psubscribe(String pattern, String... patterns) {
		return commands(() -> tile38Client.psubscribe(pattern, patterns), PSubscribeResponse.class);
	}

	/**
	 * 设置是否只读模式
	 *
	 * @param readOnly
	 * @return
	 */
	public SetResponse readOnly(boolean readOnly) {
		return commands(() -> tile38Client.readOnly(readOnly), SetResponse.class);
	}

	/**
	 * @param key
	 * @param newkey
	 * @return
	 */
	public RenameResponse rename(String key, String newkey) {
		return commands(() -> tile38Client.rename(key, newkey), RenameResponse.class);
	}

	/**
	 * @param key
	 * @param newkey
	 * @return
	 */
	public RenameResponse renameNx(String key, String newkey) {
		return commands(() -> tile38Client.renameNx(key, newkey), RenameResponse.class);
	}

	/**
	 * 清除lua脚本缓存
	 *
	 * @return
	 */
	public ScriptFlushResponse scriptFlush() {
		return commands(() -> tile38Client.scriptFlush(), ScriptFlushResponse.class);
	}

	/**
	 * 加载lua脚本
	 *
	 * @param script
	 * @return
	 */
	public ScriptLoadResponse scriptLoad(String script) {
		return commands(() -> tile38Client.scriptLoad(script), ScriptLoadResponse.class);
	}

	/**
	 * 设置Point类型
	 *
	 * @param setOpts
	 * @param point
	 * @return
	 */
	public SetResponse setPoint(final SetOpts setOpts, final Point point) {
		return commands(() -> tile38Client.setPoint(setOpts, point), SetResponse.class);
	}


	/**
	 * 设置一个Bound矩形
	 *
	 * @param setOpts
	 * @param bounds
	 * @return
	 */
	public SetResponse setBounds(final SetOpts setOpts, Bounds bounds) {
		return commands(() -> tile38Client.setBounds(setOpts, bounds), SetResponse.class);
	}

	/**
	 * 创建一个geohash
	 *
	 * @param setOpts
	 * @param geohash
	 * @return
	 */
	public SetResponse setGeohash(final SetOpts setOpts, final Geohash geohash) {
		return commands(() -> tile38Client.setGeohash(setOpts, geohash), SetResponse.class);
	}

	/**
	 * 创建一个geohash
	 *
	 * @param setOpts
	 * @param geoJson
	 * @return
	 */
	public SetResponse setGeohash(final SetOpts setOpts, final String geoJson) {
		return commands(() -> tile38Client.setGeohash(setOpts, geoJson), SetResponse.class);
	}

	/**
	 * 创建一个GeoJson
	 *
	 * @param setOpts
	 * @param geoJson
	 * @return
	 */
	public SetResponse setGeoJson(final SetOpts setOpts, final GeoJson.BaseGeoJson geoJson) {
		return commands(() -> tile38Client.setObject(setOpts, geoJson), SetResponse.class);
	}

	/**
	 * 创建一个raw string
	 *
	 * @param setOpts
	 * @param rawString
	 * @return
	 */
	public SetResponse setRawString(final SetOpts setOpts, final RawString rawString) {
		return commands(() -> tile38Client.setString(setOpts, rawString), SetResponse.class);
	}

	/**
	 * 创建一个raw string
	 *
	 * @param setOpts
	 * @param rawString
	 * @return
	 */
	public SetResponse setRawString(final SetOpts setOpts, final String rawString) {
		return commands(() -> tile38Client.setString(setOpts, rawString), SetResponse.class);
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
		return commandsThrowException(() -> tile38Client.setChanNearBy(setChanNearByOpts, point), SetResponse.class);
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
		return commands(() -> tile38Client.setChanNearBy(setChanNearByOpts, roam), SetResponse.class);
	}

	/**
	 * 创建通道
	 *
	 * @param setChanIntersectsOpts
	 * @param element
	 * @return
	 */
	public SetResponse setChanIntersects(SetChanIntersectsOpts setChanIntersectsOpts, Element element) {
		return commands(() -> tile38Client.setChanIntersects(setChanIntersectsOpts, element), SetResponse.class);
	}

	/**
	 * 创建通道
	 *
	 * @param setChanWithInOpts
	 * @param element
	 * @return
	 */
	public SetResponse setChanWithIn(SetChanWithInOpts setChanWithInOpts, Element element) {
		return commands(() -> tile38Client.setChanWithIn(setChanWithInOpts, element), SetResponse.class);
	}

	/**
	 * 创建hook
	 *
	 * @param setHookNearByOpts
	 * @param point
	 * @return
	 */
	public SetResponse setHookNearBy(SetHookNearByOpts setHookNearByOpts, Point point) throws Exception {
		return commandsThrowException(() -> tile38Client.setHookNearBy(setHookNearByOpts, point), SetResponse.class);
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
		return commands(() -> tile38Client.setHookNearBy(setHookNearByOpts, roam), SetResponse.class);
	}

	/**
	 * 创建hook
	 *
	 * @param setHookIntersectsOpts
	 * @param element
	 * @return
	 */
	public SetResponse setHookIntersects(SetHookIntersectsOpts setHookIntersectsOpts, Element element) {
		return commands(() -> tile38Client.setHookIntersects(setHookIntersectsOpts, element), SetResponse.class);
	}

	/**
	 * 创建hook
	 *
	 * @param setHookWithInOpts
	 * @param element
	 * @return
	 */
	public SetResponse setHookWithIn(SetHookWithInOpts setHookWithInOpts, Element element) {
		return commands(() -> tile38Client.setHookWithIn(setHookWithInOpts, element), SetResponse.class);
	}

	/**
	 * 订阅通道
	 *
	 * @param channel
	 * @param channels
	 * @return
	 */
	public SubscribeResponse subscribe(String channel, String... channels) {
		return commands(() -> tile38Client.subscribe(channel, channels), SubscribeResponse.class);
	}

	/**
	 * @param opts
	 * @return
	 */
	public EvalResponse eval(EvalScriptOpts opts) {
		return commands(() -> tile38Client.eval(opts), EvalResponse.class);
	}

	/**
	 * @param opts
	 * @return
	 */
	public EvalResponse evalNa(EvalScriptOpts opts) {
		return commands(() -> tile38Client.evalNa(opts), EvalResponse.class);
	}

	/**
	 * @param opts
	 * @return
	 */
	public EvalResponse evalNaSha(EvalScriptOpts opts) {
		return commands(() -> tile38Client.evalNaSha(opts), EvalResponse.class);
	}

	/**
	 * @param opts
	 * @return
	 */
	public EvalResponse evalSha(EvalScriptOpts opts) {
		return commands(() -> tile38Client.evalSha(opts), EvalResponse.class);
	}

	/**
	 * @param opts
	 * @param timeout
	 * @return
	 * @throws Exception
	 */
	public EvalResponse eval(EvalScriptOpts opts, double timeout) throws Exception {
		return commandsThrowException(() -> tile38Client.eval(opts, timeout), EvalResponse.class);
	}

	/**
	 * @param opts
	 * @param timeout
	 * @return
	 * @throws Exception
	 */
	public EvalResponse evalNa(EvalScriptOpts opts, double timeout) throws Exception {
		return commandsThrowException(() -> tile38Client.evalNa(opts, timeout), EvalResponse.class);
	}

	/**
	 * @param opts
	 * @param timeout
	 * @return
	 * @throws Exception
	 */
	public EvalResponse evalNaSha(EvalScriptOpts opts, double timeout) throws Exception {
		return commandsThrowException(() -> tile38Client.evalNaSha(opts, timeout), EvalResponse.class);
	}

	/**
	 * @param opts
	 * @param timeout
	 * @return
	 * @throws Exception
	 */
	public EvalResponse evalSha(EvalScriptOpts opts, double timeout) throws Exception {
		return commandsThrowException(() -> tile38Client.evalSha(opts, timeout), EvalResponse.class);
	}
}
