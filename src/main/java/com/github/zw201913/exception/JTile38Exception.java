package com.github.zw201913.exception;

import lombok.Data;

/**
 * @author zouwei
 * @className JTile38Exception
 * @date: 2022/8/25 下午5:13
 * @description:
 */
@Data
public class JTile38Exception extends Exception {

	private int code;
	private String msg;


	public JTile38Exception(int code, String msg, Exception e) {
		super(e);
		this.code = code;
		this.msg = msg;
	}

	public JTile38Exception(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
}