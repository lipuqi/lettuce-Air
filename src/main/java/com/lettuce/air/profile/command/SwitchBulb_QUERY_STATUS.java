package com.lettuce.air.profile.command;

import com.lettuce.air.common.exception.BasicException;
import com.lettuce.air.common.exception.CustomException;
import com.lettuce.air.profile.ServiceConstant;

import net.sf.json.JSONObject;

public class SwitchBulb_QUERY_STATUS extends DeviceCommand {

	private Integer issue;

	private Integer result;
	
	public SwitchBulb_QUERY_STATUS() {
	}
	
	public SwitchBulb_QUERY_STATUS(Integer issue) {
		super(ServiceConstant.SwitchBulb, ServiceConstant.SwitchBulb, ServiceConstant.QUERY_STATUS);
		if(issue != 1){
			throw new BasicException(10003, new CustomException(SwitchBulb_QUERY_STATUS.class, "下发命令参数不正确"));
		}
		this.issue = issue;
	}

	public Integer getIssue() {
		return issue;
	}

	public void setIssue(Integer issue) {
		this.issue = issue;
	}

	public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}

	@Override
	public JSONObject unpack() {
		JSONObject command = new JSONObject();
		command.put("serviceId", getServiceId());
		command.put("method", getMethod());
		
		JSONObject paras = new JSONObject();
		paras.put("issue", getIssue());
		
		command.put("paras", paras);
		return command;
	}

}
