package com.github.zw201913.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

/**
 * @author zouwei
 * @className ServerResponse
 * @date: 2022/8/24 上午10:35
 * @description:
 */
@Getter
@ToString(callSuper = true)
public class ServerResponse extends BaseResponse {

	private Stats stats;

	@Getter
	@ToString(callSuper = true)
	public static class Stats {
		@JsonProperty("aof_size")
		private long aofSize;
		@JsonProperty("avg_item_size")
		private long avgItemSize;
		private int cpus;
		@JsonProperty("heap_released")
		private long heapReleased;
		@JsonProperty("heap_size")
		private long heapSize;
		@JsonProperty("http_transport")
		private boolean httpTransport;
		private String id;
		@JsonProperty("in_memory_size")
		private long inMemorySize;
		@JsonProperty("max_heap_size")
		private long maxHeapSize;
		@JsonProperty("mem_alloc")
		private long memAlloc;
		@JsonProperty("num_collections")
		private long numCollections;
		@JsonProperty("num_hooks")
		private long numHooks;
		@JsonProperty("num_objects")
		private long numObjects;
		@JsonProperty("num_points")
		private long numPoints;
		@JsonProperty("num_strings")
		private long numStrings;
		private int pid;
		@JsonProperty("pointer_size")
		private int pointerSize;
		@JsonProperty("read_only")
		private boolean readOnly;
		private int threads;
		private String version;
	}
}
