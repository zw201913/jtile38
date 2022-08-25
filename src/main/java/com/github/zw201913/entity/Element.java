package com.github.zw201913.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author zouwei
 * @className Element
 * @date: 2022/8/12 下午5:36
 * @description:
 */
@Data
public abstract class Element implements Serializable {

	public abstract List<String> commandArgs();

}
