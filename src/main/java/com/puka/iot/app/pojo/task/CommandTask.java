package com.puka.iot.app.pojo.task;

import com.puka.iot.app.pojo.basic.Cache;

public class CommandTask extends Cache{
	
	private String method;
	
	private String commandId;
	
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
