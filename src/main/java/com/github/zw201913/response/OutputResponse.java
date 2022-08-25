package com.github.zw201913.response;

import com.github.zw201913.enums.OutPutType;
import lombok.Getter;
import lombok.ToString;

/**
 * @author zouwei
 * @className OutputResponse
 * @date: 2022/8/23 下午8:11
 * @description:
 */
@Getter
@ToString(callSuper = true)
public class OutputResponse extends BaseResponse {

	private String output;

	private OutputResponse(OutPutType outPutType) {
		super(true);
		this.output = outPutType.getType();
	}

	public static OutputResponse newInstance(OutPutType outPutType, String elapsed) {
		OutputResponse response = new OutputResponse(outPutType);
		response.setElapsed(elapsed);
		return response;
	}
}
