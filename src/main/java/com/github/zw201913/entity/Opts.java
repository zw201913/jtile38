package com.github.zw201913.entity;

import org.apache.commons.lang3.ArrayUtils;

import java.util.*;
import java.util.function.Consumer;

/**
 * @author zouwei
 * @className Opts
 * @date: 2022/8/17 下午7:52
 * @description:
 */
public abstract class Opts {

	private Map<String, Consumer<List<String>>> functionMap = new HashMap<>();

	public abstract List<String> commandLine();

	protected void addFunction(String opt, Consumer<List<String>> consumer) {
		functionMap.put(opt, consumer);
	}


	protected List<String> commandLine(String... options) {
		List<String> result = new LinkedList<>();
		if (ArrayUtils.isNotEmpty(options)) {
			for (String opt : options) {
				Consumer<List<String>> consumer = functionMap.get(opt);
				if (Objects.nonNull(consumer)) {
					consumer.accept(result);
				}
			}
		}
		return result;
	}
}
