package com.github.zw201913.response;

import lombok.Getter;
import lombok.ToString;

/**
 * @author zouwei
 * @className AofMd5Response
 * @date: 2022/8/22 下午5:29
 * @description:
 */
@Getter
@ToString(callSuper = true)
public class AofMd5Response extends BaseResponse {

	private String md5;
}
