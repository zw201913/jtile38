package com.github.zw201913.response;

import com.github.zw201913.util.JsonUtil;
import lombok.Getter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

/**
 * @author zouwei
 * @className JGetResponse
 * @date: 2022/8/23 下午7:54
 * @description:
 */
@Getter
@ToString(callSuper = true)
public class JGetResponse extends BaseResponse {

	private String value;

	public <T> T toObject(Class<T> clazz) {
		if (StringUtils.isEmpty(this.value)) {
			return null;
		}
		return JsonUtil.string2Obj(this.value, clazz);
	}
}
