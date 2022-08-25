package com.github.zw201913;

import com.github.zw201913.entity.*;
import com.github.zw201913.enums.OutPutType;
import com.github.zw201913.exception.JTile38Exception;
import io.lettuce.core.ClientOptions;
import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import io.lettuce.core.codec.StringCodec;
import io.lettuce.core.output.ValueOutput;
import io.lettuce.core.protocol.CommandArgs;
import io.lettuce.core.protocol.ProtocolKeyword;
import io.lettuce.core.protocol.ProtocolVersion;
import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author zouwei
 * @className Client
 * @date: 2022/8/11 下午11:53
 * @description:
 */
@Data
public class Tile38Client implements AutoCloseable {

	private static final StringCodec STRING_CODEC = new StringCodec(StandardCharsets.UTF_8);

	private RedisClient redisClient;

	private StatefulRedisConnection<String, String> conn;

	private RedisCommands<String, String> commands;

	public Tile38Client(final String host, final int port) {
		this(host, port, StringUtils.EMPTY);
	}

	private static final String PASSWORD_HOST_PORT_URI = "redis://%s@%s:%s";

	public Tile38Client(final String host, final int port, final String password) {
		String uri = String.format(PASSWORD_HOST_PORT_URI, password, host, port);
		this.redisClient = RedisClient.create(uri);
		ClientOptions options = ClientOptions.builder().protocolVersion(ProtocolVersion.RESP2).build();
		this.redisClient.setOptions(options);
		this.conn = this.redisClient.connect();
		this.commands = this.conn.sync();
		output(OutPutType.JSON);
	}

	private String execute(Tile38Command command, List<String>... args) {
		CommandArgs<String, String> commandArgs = new CommandArgs<>(STRING_CODEC);
		if (ArrayUtils.isNotEmpty(args)) {
			for (List<String> argList : args) {
				for (String arg : argList) {
					commandArgs.add(arg);
				}
			}
		}
		return commands.dispatch(command, new ValueOutput<>(STRING_CODEC), commandArgs);
	}

	private String execute(Tile38Command command, String[]... args) {
		CommandArgs<String, String> commandArgs = new CommandArgs<>(STRING_CODEC);
		if (ArrayUtils.isNotEmpty(args)) {
			for (String[] argList : args) {
				for (String arg : argList) {
					commandArgs.add(arg);
				}
			}
		}
		return commands.dispatch(command, new ValueOutput<>(STRING_CODEC), commandArgs);
	}

	private String execute(Tile38Command command, List<String> args) {
		CommandArgs<String, String> commandArgs = new CommandArgs<>(STRING_CODEC);
		if (!CollectionUtils.isEmpty(args)) {
			for (String arg : args) {
				commandArgs.add(arg);
			}
		}
		return commands.dispatch(command, new ValueOutput<>(STRING_CODEC), commandArgs);
	}

	private String execute(Tile38Command command, String... args) {
		CommandArgs<String, String> commandArgs = new CommandArgs<>(STRING_CODEC);
		if (ArrayUtils.isNotEmpty(args)) {
			for (String arg : args) {
				commandArgs.add(arg);
			}
		}
		return commands.dispatch(command, new ValueOutput<>(STRING_CODEC), commandArgs);
	}

	private String execute(Tile38Command command) {
		return commands.dispatch(command, new ValueOutput<>(STRING_CODEC));
	}

	/**
	 * 从 pos 开始下载 AOF 并保持连接处于活动状态
	 *
	 * @param pos
	 * @return
	 */
	public String aof(int pos) {
		return execute(Tile38Command.AOF, String.valueOf(pos));
	}

	/**
	 * 对aof做校验和
	 *
	 * @param pos
	 * @param size
	 * @return
	 */
	public String aofMd5(int pos, int size) {
		return execute(Tile38Command.AOFMD5, String.valueOf(pos), String.valueOf(size));
	}

	/**
	 * 执行aof压缩
	 *
	 * @return
	 */
	public String aofShrink() {
		return execute(Tile38Command.AOFSHRINK);
	}


	/**
	 * 验证密码
	 *
	 * @param password
	 * @return
	 */
	public String auth(String password) {
		return execute(Tile38Command.AUTH, password);
	}

	/**
	 * 返回键中所有对象的最小边界矩形
	 *
	 * @param key
	 * @return
	 */
	public String bounds(String key) {
		return execute(Tile38Command.BOUNDS, key);
	}

	/**
	 * 查询通道
	 *
	 * @param pattern
	 * @return
	 */
	public String chans(String pattern) {
		return execute(Tile38Command.CHANS, pattern);
	}

	/**
	 * 获取配置属性
	 *
	 * @param parameter
	 * @return
	 */
	public String configGet(String parameter) {
		return execute(Tile38Command.CONFIG_GET, parameter);
	}

	/**
	 * 用于保持由 CONFIG SET 命令所做的更改
	 *
	 * @return
	 */
	public String configRewrite() {
		return execute(Tile38Command.CONFIG_REWRITE);
	}

	/**
	 * 设置特殊的配置属性，可以配合config rewrite一起使用
	 *
	 * @param parameter
	 * @param value
	 * @return
	 */
	public String configSet(String parameter, String... value) {
		int length = ArrayUtils.getLength(value);
		if (length > 0) {
			String[] destArray = new String[length + 1];
			destArray[0] = parameter;
			System.arraycopy(value, 0, destArray, 1, length);
			return execute(Tile38Command.CONFIG_SET, destArray);
		}
		return execute(Tile38Command.CONFIG_SET, parameter);
	}

	/**
	 * 删除数据对象
	 *
	 * @param key
	 * @param id
	 * @return
	 */
	public String del(String key, String id) {
		return execute(Tile38Command.DEL, key, id);
	}

	/**
	 * 删除通道
	 *
	 * @param name
	 * @return
	 */
	public String delChan(String name) {
		return execute(Tile38Command.DELCHAN, name);
	}

	/**
	 * 删除Hook
	 *
	 * @param name
	 * @return
	 */
	public String delHook(String name) {
		return execute(Tile38Command.DELHOOK, name);
	}

	/**
	 * 删除一组数据对象
	 *
	 * @param key
	 * @return
	 */
	public String drop(String key) {
		return execute(Tile38Command.DROP, key);
	}

	/**
	 * 执行lua脚本
	 *
	 * @param evalScriptOpts
	 * @return
	 */
	public String eval(EvalScriptOpts evalScriptOpts) {
		return execute(Tile38Command.EVAL, evalScriptOpts.commandLine());
	}

	/**
	 * 执行lua脚本
	 *
	 * @param evalScriptOpts
	 * @param timeout
	 * @return
	 */
	public String eval(EvalScriptOpts evalScriptOpts, double timeout) throws JTile38Exception {
		if (timeout < 0) {
			throw new JTile38Exception(500, "timeout must be greater than zero");
		}
		List<String> args = new LinkedList<>();
		args.add(String.valueOf(timeout));
		args.add(Tile38Command.EVAL.name());
		return execute(Tile38Command.TIMEOUT, args, evalScriptOpts.commandLine());
	}


	/**
	 * 执行lua脚本
	 *
	 * @param evalScriptOpts
	 * @return
	 */
	public String evalSha(EvalScriptOpts evalScriptOpts) {
		return execute(Tile38Command.EVALSHA, evalScriptOpts.commandLine());
	}

	/**
	 * 执行lua脚本
	 *
	 * @param evalScriptOpts
	 * @param timeout
	 * @return
	 * @throws JTile38Exception
	 */
	public String evalSha(EvalScriptOpts evalScriptOpts, double timeout) throws JTile38Exception {
		if (timeout < 0) {
			throw new JTile38Exception(500, "timeout must be greater than zero");
		}
		List<String> args = new LinkedList<>();
		args.add(String.valueOf(timeout));
		args.add(Tile38Command.EVALSHA.name());
		return execute(Tile38Command.TIMEOUT, evalScriptOpts.commandLine());
	}

	/**
	 * 非原子方式执行lua脚本
	 *
	 * @param evalScriptOpts
	 * @return
	 */
	public String evalNa(EvalScriptOpts evalScriptOpts) {
		return execute(Tile38Command.EVALNA, evalScriptOpts.commandLine());
	}

	/**
	 * 非原子方式执行lua脚本
	 *
	 * @param evalScriptOpts
	 * @param timeout
	 * @return
	 * @throws JTile38Exception
	 */
	public String evalNa(EvalScriptOpts evalScriptOpts, double timeout) throws JTile38Exception {
		if (timeout < 0) {
			throw new JTile38Exception(500, "timeout must be greater than zero");
		}
		List<String> args = new LinkedList<>();
		args.add(String.valueOf(timeout));
		args.add(Tile38Command.EVALNA.name());
		return execute(Tile38Command.TIMEOUT, evalScriptOpts.commandLine());
	}

	/**
	 * 非原子方式执行lua脚本
	 *
	 * @param evalScriptOpts
	 * @return
	 */
	public String evalNaSha(EvalScriptOpts evalScriptOpts) {
		return execute(Tile38Command.EVALNASHA, evalScriptOpts.commandLine());
	}

	/**
	 * 非原子方式执行lua脚本
	 *
	 * @param evalScriptOpts
	 * @param timeout
	 * @return
	 * @throws JTile38Exception
	 */
	public String evalNaSha(EvalScriptOpts evalScriptOpts, double timeout) throws JTile38Exception {
		if (timeout < 0) {
			throw new JTile38Exception(500, "timeout must be greater than zero");
		}
		List<String> args = new LinkedList<>();
		args.add(String.valueOf(timeout));
		args.add(Tile38Command.EVALNASHA.name());
		return execute(Tile38Command.TIMEOUT, evalScriptOpts.commandLine());
	}

	/**
	 * 只读方式执行lua脚本
	 *
	 * @param evalScriptOpts
	 * @return
	 */
	public String evalRo(EvalScriptOpts evalScriptOpts) {
		return execute(Tile38Command.EVALRO, evalScriptOpts.commandLine());
	}

	/**
	 * 只读方式执行lua脚本
	 *
	 * @param evalScriptOpts
	 * @param timeout
	 * @return
	 * @throws JTile38Exception
	 */
	public String evalRo(EvalScriptOpts evalScriptOpts, double timeout) throws JTile38Exception {
		if (timeout < 0) {
			throw new JTile38Exception(500, "timeout must be greater than zero");
		}
		List<String> args = new LinkedList<>();
		args.add(String.valueOf(timeout));
		args.add(Tile38Command.EVALRO.name());
		return execute(Tile38Command.TIMEOUT, evalScriptOpts.commandLine());
	}

	/**
	 * 只读方式执行lua脚本
	 *
	 * @param evalScriptOpts
	 * @return
	 */
	public String evalRoSha(EvalScriptOpts evalScriptOpts) {
		return execute(Tile38Command.EVALROSHA, evalScriptOpts.commandLine());
	}

	/**
	 * 只读方式执行lua脚本
	 *
	 * @param evalScriptOpts
	 * @param timeout
	 * @return
	 * @throws JTile38Exception
	 */
	public String evalRoSha(EvalScriptOpts evalScriptOpts, double timeout) throws JTile38Exception {
		if (timeout < 0) {
			throw new JTile38Exception(500, "timeout must be greater than zero");
		}
		List<String> args = new LinkedList<>();
		args.add(String.valueOf(timeout));
		args.add(Tile38Command.EVALROSHA.name());
		return execute(Tile38Command.TIMEOUT, evalScriptOpts.commandLine());
	}

	/**
	 * 设置过期时间
	 *
	 * @param key
	 * @param id
	 * @param seconds
	 * @return
	 */
	public String expire(String key, String id, int seconds) {
		return execute(Tile38Command.EXPIRE, key, id, String.valueOf(seconds));
	}

	/**
	 * 清空数据库
	 *
	 * @return
	 */
	public String flushDB() {
		return execute(Tile38Command.FLUSHDB);
	}

	/**
	 * 设置字段
	 *
	 * @param key
	 * @param id
	 * @param fields
	 * @param isXx
	 * @return
	 */
	public String fset(String key, String id, Map<String, Double> fields, boolean isXx) {
		List<String> args = new LinkedList<>();
		args.add(key);
		args.add(id);
		if (isXx) {
			args.add("XX");
		}
		for (Map.Entry<String, Double> entry : fields.entrySet()) {
			args.add(entry.getKey());
			args.add(String.valueOf(entry.getValue()));
		}
		return execute(Tile38Command.FSET, args);
	}

	/**
	 * 设置字段
	 *
	 * @param key
	 * @param id
	 * @param fields
	 * @return
	 */
	public String fset(String key, String id, Map<String, Double> fields) {
		return fset(key, id, fields, false);
	}

	/**
	 * 设置字段
	 *
	 * @param key
	 * @param id
	 * @param field
	 * @param value
	 * @param isXx
	 * @return
	 */
	public String fset(String key, String id, String field, Double value, boolean isXx) {
		if (isXx) {
			return execute(Tile38Command.FSET, key, id, "XX", field, String.valueOf(value));
		}
		return execute(Tile38Command.FSET, key, id, field, String.valueOf(value));
	}

	/**
	 * 设置字段
	 *
	 * @param key
	 * @param id
	 * @param field
	 * @param value
	 * @return
	 */
	public String fset(String key, String id, String field, Double value) {
		return execute(Tile38Command.FSET, key, id, field, String.valueOf(value));
	}


	/**
	 * 跟随领导者并复制领导者的数据
	 *
	 * @param host
	 * @param port
	 * @return
	 */
	public String follow(String host, int port) {
		return execute(Tile38Command.FOLLOW, host, String.valueOf(port));
	}

	/**
	 * 解除跟随
	 *
	 * @return
	 */
	public String noFollow() {
		return execute(Tile38Command.FOLLOW, "no", "one");
	}

	/**
	 * 垃圾回收
	 *
	 * @return
	 */
	public String gc() {
		return execute(Tile38Command.GC);
	}

	/**
	 * 查询数据对象
	 *
	 * @param key
	 * @param id
	 * @return
	 */
	public String get(String key, String id) {
		return execute(Tile38Command.GET, key, id);
	}

	/**
	 * 查询数据对象
	 *
	 * @param key
	 * @param id
	 * @return
	 */
	public String getWithFields(String key, String id) {
		return execute(Tile38Command.GET, key, id, "WITHFIELDS");
	}

	/**
	 * 查询数据对象
	 *
	 * @param key
	 * @param id
	 * @param withFields
	 * @return
	 */
	public String getObject(String key, String id, boolean withFields) {
		if (withFields) {
			return execute(Tile38Command.GET, key, id, "WITHFIELDS", "OBJECT");
		}
		return execute(Tile38Command.GET, key, id, "OBJECT");
	}

	/**
	 * 查询数据对象
	 *
	 * @param key
	 * @param id
	 * @param withFields
	 * @return
	 */
	public String getPoint(String key, String id, boolean withFields) {
		if (withFields) {
			return execute(Tile38Command.GET, key, id, "WITHFIELDS", "POINT");
		}
		return execute(Tile38Command.GET, key, id, "POINT");
	}

	/**
	 * 查询数据对象
	 *
	 * @param key
	 * @param id
	 * @param withFields
	 * @return
	 */
	public String getBounds(String key, String id, boolean withFields) {
		if (withFields) {
			return execute(Tile38Command.GET, key, id, "WITHFIELDS", "BOUNDS");
		}
		return execute(Tile38Command.GET, key, id, "BOUNDS");
	}

	/**
	 * 查询数据对象
	 *
	 * @param key
	 * @param id
	 * @param precision
	 * @param withFields
	 * @return
	 */
	public String getGeohash(String key, String id, int precision, boolean withFields) {
		if (withFields) {
			return execute(Tile38Command.GET, key, id, "WITHFIELDS", "HASH", String.valueOf(precision));
		}
		return execute(Tile38Command.GET, key, id, "HASH", String.valueOf(precision));
	}

	/**
	 * 正则表达式查找hook
	 *
	 * @param pattern
	 * @return
	 */
	public String hooks(String pattern) {
		return execute(Tile38Command.HOOKS, pattern);
	}

	/**
	 * 搜索相交数据对象
	 *
	 * @param intersectsOpts
	 * @param element
	 * @return
	 */
	public String intersects(IntersectsOpts intersectsOpts, Element element) {
		return execute(Tile38Command.INTERSECTS, intersectsOpts.commandLine(), element.commandArgs());
	}

	/**
	 * 搜索相交数据对象
	 *
	 * @param intersectsOpts
	 * @param element
	 * @param timeout
	 * @return
	 * @throws JTile38Exception
	 */
	public String intersects(IntersectsOpts intersectsOpts, Element element, double timeout) throws JTile38Exception {
		if (timeout < 0) {
			throw new JTile38Exception(500, "timeout must be greater than zero");
		}
		List<String> args = new LinkedList<>();
		args.add(String.valueOf(timeout));
		args.add(Tile38Command.INTERSECTS.name());
		return execute(Tile38Command.TIMEOUT, intersectsOpts.commandLine(), element.commandArgs());
	}

	/**
	 * 删除json文档字段
	 *
	 * @param key
	 * @param id
	 * @param path
	 * @return
	 */
	public String jdel(String key, String id, String path) {
		return execute(Tile38Command.JDEL, key, id, path);
	}

	/**
	 * 获取json文档字段
	 *
	 * @param key
	 * @param id
	 * @param path
	 * @param isRaw
	 * @return
	 */
	public String jget(String key, String id, String path, boolean isRaw) {
		if (isRaw) {
			return execute(Tile38Command.JGET, key, id, path, "RAW");
		}
		return execute(Tile38Command.JGET, key, id, path);
	}

	/**
	 * 获取json文档字段
	 *
	 * @param key
	 * @param id
	 * @param path
	 * @return
	 */
	public String jget(String key, String id, String path) {
		return execute(Tile38Command.JGET, key, id, path);
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
	public String jset(String key, String id, String path, String value) {
		return execute(Tile38Command.JSET, key, id, path, value);
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
	public String jsetRaw(String key, String id, String path, String value) {
		return execute(Tile38Command.JSET, key, id, path, value, "RAW");
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
	public String jsetStr(String key, String id, String path, String value) {
		return execute(Tile38Command.JSET, key, id, path, value, "STR");
	}

	/**
	 * 正则表达式查询key
	 *
	 * @param pattern
	 * @return
	 */
	public String keys(String pattern) {
		return execute(Tile38Command.KEYS, pattern);
	}

	/**
	 * 搜索附近的数据对象
	 *
	 * @param nearByOpts
	 * @param element
	 * @return
	 */
	private String nearby(NearByOpts nearByOpts, Element element) {
		List<String> args1 = nearByOpts.commandLine();
		return execute(Tile38Command.NEARBY, args1, element.commandArgs());
	}

	/**
	 * @param nearByOpts
	 * @param point
	 * @return
	 */
	public String nearbyPoint(NearByOpts nearByOpts, Point point) {
		return nearby(nearByOpts, point);
	}

	/**
	 * @param nearByOpts
	 * @param roam
	 * @return
	 */
	public String nearbyRoam(NearByOpts nearByOpts, Roam roam) {
		return nearby(nearByOpts, roam);
	}

	/**
	 * 搜索附近的数据对象
	 *
	 * @param nearByOpts
	 * @param element
	 * @param timeout
	 * @return
	 * @throws JTile38Exception
	 */
	private String nearby(NearByOpts nearByOpts, Element element, double timeout) throws JTile38Exception {
		if (timeout < 0) {
			throw new JTile38Exception(500, "timeout must be greater than zero");
		}
		List<String> args = new LinkedList<>();
		args.add(String.valueOf(timeout));
		args.add(Tile38Command.NEARBY.name());
		return execute(Tile38Command.TIMEOUT, args, nearByOpts.commandLine(), element.commandArgs());
	}

	/**
	 * @param nearByOpts
	 * @param point
	 * @param timeout
	 * @return
	 * @throws JTile38Exception
	 */
	public String nearbyPoint(NearByOpts nearByOpts, Point point, double timeout) throws JTile38Exception {
		return nearby(nearByOpts, point, timeout);
	}

	/**
	 * @param nearByOpts
	 * @param roam
	 * @param timeout
	 * @return
	 * @throws JTile38Exception
	 */
	public String nearbyRoam(NearByOpts nearByOpts, Roam roam, double timeout) throws JTile38Exception {
		return nearby(nearByOpts, roam, timeout);
	}

	/**
	 * 设置数据输出格式
	 *
	 * @param type
	 * @return
	 */
	public String output(OutPutType type) {
		return execute(Tile38Command.OUTPUT, type.getType());
	}

	/**
	 * 查询数据输出格式
	 *
	 * @return
	 */
	public String output() {
		return execute(Tile38Command.OUTPUT);
	}

	/**
	 * 根据正则表达式删除数据对象
	 *
	 * @param key
	 * @param pattern
	 * @return
	 */
	public String pdel(String key, String pattern) {
		return execute(Tile38Command.PDEL, key, pattern);
	}

	/**
	 * 根据正则表达式删除通道
	 *
	 * @param pattern
	 * @return
	 */
	public String pdelChan(String pattern) {
		return execute(Tile38Command.PDELCHAN, pattern);
	}

	/**
	 * 根据正则表达式删除Hook
	 *
	 * @param pattern
	 * @return
	 */
	public String pdelHook(String pattern) {
		return execute(Tile38Command.PDELHOOK, pattern);
	}

	/**
	 * 删除过期时间
	 *
	 * @param key
	 * @param id
	 * @return
	 */
	public String persist(String key, String id) {
		return execute(Tile38Command.PERSIST, key, id);
	}

	/**
	 * ping服务器
	 *
	 * @return
	 */
	public String ping() {
		return execute(Tile38Command.PING);
	}

	/**
	 * 批量订阅通道
	 *
	 * @param pattern
	 * @param patterns
	 * @return
	 */
	public String psubscribe(String pattern, String... patterns) {
		return execute(Tile38Command.PSUBSCRIBE, new String[]{pattern}, patterns);
	}

	/**
	 * 关闭链接
	 *
	 * @return
	 */
	public String quit() {
		return execute(Tile38Command.QUIT);
	}

	/**
	 * 是否设置只读模式
	 *
	 * @param readOnly
	 * @return
	 */
	public String readOnly(boolean readOnly) {
		if (readOnly) {
			return execute(Tile38Command.READONLY, "yes");
		}
		return execute(Tile38Command.READONLY, "no");
	}

	/**
	 * 重命名
	 *
	 * @param key
	 * @param newkey
	 * @return
	 */
	public String rename(String key, String newkey) {
		return execute(Tile38Command.RENAME, key, newkey);
	}

	/**
	 * 重命名
	 *
	 * @param key
	 * @param newkey
	 * @return
	 */
	public String renameNx(String key, String newkey) {
		return execute(Tile38Command.RENAMENX, key, newkey);
	}

	/**
	 * 检查脚本是否存在
	 *
	 * @param sha1
	 * @param sha1s
	 * @return
	 */
	public String scriptExists(String sha1, String... sha1s) {
		return execute(Tile38Command.SCRIPT_EXISTS, new String[]{sha1}, sha1s);
	}

	/**
	 * 载入lua脚本
	 *
	 * @param script
	 * @return
	 */
	public String scriptLoad(String script) {
		return execute(Tile38Command.SCRIPT_LOAD, script);
	}

	/**
	 * 清除服务器中lua脚本缓存
	 *
	 * @return
	 */
	public String scriptFlush() {
		return execute(Tile38Command.SCRIPT_FLUSH);
	}

	/**
	 * 查看服务器信息
	 *
	 * @return
	 */
	public String server() {
		return execute(Tile38Command.SERVER);
	}

	/**
	 * 查询key的状态
	 *
	 * @param keys
	 * @return
	 * @throws JTile38Exception
	 */
	public String stats(String key, String... keys) {
		return execute(Tile38Command.STATS, new String[]{key}, keys);
	}

	/**
	 * 订阅通道
	 *
	 * @param channel
	 * @param channels
	 * @return
	 */
	public String subscribe(String channel, String... channels) {
		return execute(Tile38Command.SUBSCRIBE, new String[]{channel}, channels);
	}

	/**
	 * @param e1
	 * @param e2
	 * @return
	 */
	public String testWithIn(Element e1, Element e2) {
		List<String> commandArgs1 = e1.commandArgs();
		commandArgs1.add("WITHIN");
		return execute(Tile38Command.TEST, commandArgs1, e2.commandArgs());
	}

	/**
	 * @param e1
	 * @param e2
	 * @return
	 */
	public String testIntersects(Element e1, Element e2, boolean clip) {
		List<String> commandArgs1 = e1.commandArgs();
		commandArgs1.add("INTERSECTS");
		if (clip) {
			commandArgs1.add("CLIP");
		}
		return execute(Tile38Command.TEST, commandArgs1, e2.commandArgs());
	}

	/**
	 * 查询过期时间
	 *
	 * @param key
	 * @param id
	 * @return
	 */
	public String ttl(String key, String id) {
		return execute(Tile38Command.TTL, key, id);
	}

	/**
	 * 设置数据对象
	 *
	 * @param setOpts
	 * @param element
	 * @return
	 */
	private String setElement(SetOpts setOpts, Element element) {
		List<String> args1 = setOpts.commandLine();
		List<String> commandArgs = element.commandArgs();
		return execute(Tile38Command.SET, args1, commandArgs);
	}

	/**
	 * 设置点位
	 *
	 * @param setOpts
	 * @param point
	 * @return
	 */
	public String setPoint(SetOpts setOpts, Point point) {
		return setElement(setOpts, point);
	}

	/**
	 * 设置对象
	 *
	 * @param setOpts
	 * @param geoJson
	 * @return
	 */
	public String setObject(SetOpts setOpts, GeoJson.BaseGeoJson geoJson) {
		return setElement(setOpts, geoJson);
	}

	/**
	 * 设置矩形边界
	 *
	 * @param setOpts
	 * @param bounds
	 * @return
	 */
	public String setBounds(SetOpts setOpts, Bounds bounds) {
		return setElement(setOpts, bounds);
	}

	/**
	 * 设置geohash
	 *
	 * @param setOpts
	 * @param geohash
	 * @return
	 */
	public String setGeohash(SetOpts setOpts, Geohash geohash) {
		return setElement(setOpts, geohash);
	}

	/**
	 * 设置geohash
	 *
	 * @param setOpts
	 * @param geohash
	 * @return
	 */
	public String setGeohash(SetOpts setOpts, String geohash) {
		return setElement(setOpts, new Geohash(geohash));
	}

	/**
	 * 设置String
	 *
	 * @param setOpts
	 * @param string
	 * @return
	 */
	public String setString(SetOpts setOpts, RawString string) {
		return setElement(setOpts, string);
	}

	/**
	 * 设置String
	 *
	 * @param setOpts
	 * @param str
	 * @return
	 */
	public String setString(SetOpts setOpts, String str) {
		return setElement(setOpts, new RawString(str));
	}

	/**
	 * 搜索包含数据对象
	 *
	 * @param withInOpts
	 * @param element
	 * @return
	 */
	public String within(WithInOpts withInOpts, Element element) {
		return execute(Tile38Command.WITHIN, withInOpts.commandLine(), element.commandArgs());
	}

	/**
	 * 搜索包含数据对象
	 *
	 * @param withInOpts
	 * @param element
	 * @param timeout
	 * @return
	 * @throws JTile38Exception
	 */
	public String within(WithInOpts withInOpts, Element element, double timeout) throws JTile38Exception {
		if (timeout < 0) {
			throw new JTile38Exception(500, "timeout must be greater than zero");
		}
		List<String> args = new LinkedList<>();
		args.add(String.valueOf(timeout));
		args.add(Tile38Command.WITHIN.name());
		return execute(Tile38Command.TIMEOUT, args, withInOpts.commandLine(), element.commandArgs());
	}

	/**
	 * 查询数据对象
	 *
	 * @param searchOpts
	 * @return
	 */
	public String search(SearchOpts searchOpts) {
		return execute(Tile38Command.SEARCH, searchOpts.commandLine());
	}

	/**
	 * 查询数据对象
	 *
	 * @param searchOpts
	 * @param timeout
	 * @return
	 * @throws JTile38Exception
	 */
	public String search(SearchOpts searchOpts, double timeout) throws JTile38Exception {
		if (timeout < 0) {
			throw new JTile38Exception(500, "timeout must be greater than zero");
		}
		List<String> args = new LinkedList<>();
		args.add(String.valueOf(timeout));
		args.add(Tile38Command.SEARCH.name());
		return execute(Tile38Command.TIMEOUT, args, searchOpts.commandLine());
	}

	/**
	 * 扫描数据对象
	 *
	 * @param scanOpts
	 * @return
	 */
	public String scan(ScanOpts scanOpts) {
		return execute(Tile38Command.SCAN, scanOpts.commandLine());
	}

	/**
	 * 扫描数据对象
	 *
	 * @param scanOpts
	 * @param timeout
	 * @return
	 * @throws JTile38Exception
	 */
	public String scan(ScanOpts scanOpts, double timeout) throws JTile38Exception {
		if (timeout < 0) {
			throw new JTile38Exception(500, "timeout must be greater than zero");
		}
		List<String> args = new LinkedList<>();
		args.add(String.valueOf(timeout));
		args.add(Tile38Command.SCAN.name());
		return execute(Tile38Command.TIMEOUT, args, scanOpts.commandLine());
	}

	/**
	 * 设置通道
	 *
	 * @param setChanNearByOpts
	 * @param point
	 * @return
	 * @throws JTile38Exception
	 */
	public String setChanNearBy(SetChanNearByOpts setChanNearByOpts, Point point) throws JTile38Exception {
		if (point.getZ() < 0) {
			throw new JTile38Exception(500, "please set parameter `z` greater than zero");
		}
		return execute(Tile38Command.SETCHAN, setChanNearByOpts.commandLine(), point.commandArgs());
	}

	/**
	 * 设置通道
	 *
	 * @param setChanNearByOpts
	 * @param roam
	 * @return
	 */
	public String setChanNearBy(SetChanNearByOpts setChanNearByOpts, Roam roam) {
		return execute(Tile38Command.SETCHAN, setChanNearByOpts.commandLine(), roam.commandArgs());
	}

	/**
	 * 设置通道
	 *
	 * @param setChanIntersectsOpts
	 * @param element
	 * @return
	 * @throws JTile38Exception
	 */
	public String setChanIntersects(SetChanIntersectsOpts setChanIntersectsOpts, Element element) {
		return execute(Tile38Command.SETCHAN, setChanIntersectsOpts.commandLine(), element.commandArgs());
	}

	/**
	 * 设置通道
	 *
	 * @param setChanWithInOpts
	 * @param element
	 * @return
	 */
	public String setChanWithIn(SetChanWithInOpts setChanWithInOpts, Element element) {
		return execute(Tile38Command.SETCHAN, setChanWithInOpts.commandLine(), element.commandArgs());
	}

	/**
	 * 设置回调
	 *
	 * @param setHookNearByOpts
	 * @param point
	 * @return
	 * @throws JTile38Exception
	 */
	public String setHookNearBy(SetHookNearByOpts setHookNearByOpts, Point point) throws JTile38Exception {
		if (point.getZ() < 0) {
			throw new JTile38Exception(500, "please set parameter `z` greater than zero");
		}
		return execute(Tile38Command.SETHOOK, setHookNearByOpts.commandLine(), point.commandArgs());
	}

	/**
	 * 设置回调
	 *
	 * @param setHookNearByOpts
	 * @param roam
	 * @return
	 * @throws JTile38Exception
	 */
	public String setHookNearBy(SetHookNearByOpts setHookNearByOpts, Roam roam) {
		return execute(Tile38Command.SETHOOK, setHookNearByOpts.commandLine(), roam.commandArgs());
	}

	/**
	 * 设置回调
	 *
	 * @param setHookIntersectsOpts
	 * @param element
	 * @return
	 */
	public String setHookIntersects(SetHookIntersectsOpts setHookIntersectsOpts, Element element) {
		return execute(Tile38Command.SETHOOK, setHookIntersectsOpts.commandLine(), element.commandArgs());
	}

	/**
	 * 设置回调
	 *
	 * @param setHookWithInOpts
	 * @param element
	 * @return
	 */
	public String setHookWithIn(SetHookWithInOpts setHookWithInOpts, Element element) {
		return execute(Tile38Command.SETHOOK, setHookWithInOpts.commandLine(), element.commandArgs());
	}

	enum Tile38Command implements ProtocolKeyword {
		AUTH,
		BOUNDS,
		CHANS,
		CONFIG_GET,
		CONFIG_REWRITE,
		CONFIG_SET,
		DEL,
		DELCHAN,
		DELHOOK,
		DROP,
		EVAL,
		EVALNA,
		EVALNASHA,
		EVALRO,
		EVALROSHA,
		EVALSHA,
		EXPIRE,
		FLUSHDB,
		FSET,
		GC,
		GET,
		HOOKS,
		OUTPUT,
		SET,
		INTERSECTS,
		JDEL,
		JGET,
		JSET,
		KEYS,
		PDEL,
		PDELCHAN,
		PDELHOOK,
		PERSIST,
		PING,
		PSUBSCRIBE,
		QUIT,
		READONLY,
		RENAME,
		RENAMENX,
		SCRIPT_EXISTS,
		SCRIPT_FLUSH,
		SCRIPT_LOAD,
		SERVER,
		STATS,
		SUBSCRIBE,
		TEST,
		TIMEOUT,
		TTL,
		WITHIN,
		NEARBY,
		SEARCH,
		SCAN,
		SETCHAN,
		SETHOOK,
		AOF,
		AOFMD5,
		AOFSHRINK,
		FOLLOW;

		public final byte[] bytes;

		static final String UNDERSCORE = "_";
		static final String SPACE = " ";

		Tile38Command() {
			String name = StringUtils.replace(this.name(), UNDERSCORE, SPACE);
			this.bytes = name.getBytes(StandardCharsets.US_ASCII);
		}

		@Override
		public byte[] getBytes() {
			return this.bytes;
		}
	}


	@Override
	public void close() {
		if (Objects.nonNull(this.commands)) {
			this.commands = null;
		}
		if (Objects.nonNull(this.conn)) {
			this.conn.close();
		}
		if (Objects.nonNull(redisClient)) {
			redisClient.shutdown();
		}
	}
}
