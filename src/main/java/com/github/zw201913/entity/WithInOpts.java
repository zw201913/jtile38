package com.github.zw201913.entity;

import com.github.zw201913.enums.CommandType;
import com.github.zw201913.enums.DetectType;
import lombok.Getter;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * @author zouwei
 * @className WithInOpts
 * @date: 2022/8/17 下午12:43
 * @description:
 */
@Getter
public class WithInOpts extends BaseSearchOpts {

	private boolean fence;

	private List<DetectType> detect;

	private List<CommandType> commands;


	protected WithInOpts(Builder builder) {
		super(builder);
		this.fence = builder.fence;
		this.detect = builder.detect;
		this.commands = builder.commands;
	}


	@Override
	protected void init() {
		super.init();
		addFunction("FENCE", list -> {
			if (this.fence) {
				list.add("FENCE");
			}
		});
		addFunction("DETECT", list -> {
			if (CollectionUtils.isNotEmpty(this.detect)) {
				list.add("DETECT");
				for (DetectType detectType : this.detect) {
					list.add(StringUtils.lowerCase(detectType.name()));
				}
			}
		});
		addFunction("COMMANDS", list -> {
			if (CollectionUtils.isNotEmpty(this.commands)) {
				list.add("COMMANDS");
				for (CommandType commandType : this.commands) {
					list.add(StringUtils.lowerCase(commandType.name()));
				}
			}
		});
	}

	@Override
	public List<String> commandLine() {
		return commandLine("key", "CURSOR", "LIMIT", "MATCH", "WHERE", "WHEREIN", "WHEREEVAL", "WHEREEVALSHA", "NOFIELDS", "FENCE", "DETECT", "COMMANDS", "resultType");
	}

	public static class Builder extends BaseSearchOpts.Builder {
		private boolean fence;

		private List<DetectType> detect;

		private List<CommandType> commands;

		public Builder fence() {
			this.fence = true;
			return this;
		}

		public Builder detect(DetectType detectType) {
			if (CollectionUtils.isEmpty(this.detect)) {
				this.detect = new LinkedList<>();
			}
			this.detect.add(detectType);
			return this;
		}

		public Builder commands(CommandType commandType) {
			if (CollectionUtils.isEmpty(this.commands)) {
				this.commands = new LinkedList<>();
			}
			this.commands.add(commandType);
			return this;
		}

		public WithInOpts build() {
			return new WithInOpts(this);
		}
	}
}
