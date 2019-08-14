package com.lettuce.air.pojo.task;

import com.lettuce.air.pojo.basic.Cache;

/**
 * 命令任务类
 * @author Lando
 *
 */
public class CommandTask extends Cache{
	
	/**
	 * 命令内容
	 */
	private String method;
	
	/**
	 * 消息标识
	 */
	private String commandId;
	
	/**
	 * 状态
	 */
	private String status;

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getCommandId() {
		return commandId;
	}

	public void setCommandId(String commandId) {
		this.commandId = commandId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	

}
