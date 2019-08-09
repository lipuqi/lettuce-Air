package com.lettuce.air.profile.command;

import com.lettuce.air.common.exception.BasicException;
import com.lettuce.air.common.exception.CustomException;
import com.lettuce.air.profile.ServiceConstant;

import net.sf.json.JSONObject;

public class SwitchBulb_ON_OFF extends DeviceCommand {

	private Integer toggleBulb;

	private Integer result;
	
	public SwitchBulb_ON_OFF() {
	}
	
	public SwitchBulb_ON_OFF(Integer toggleBulb) {
		super(ServiceConstant.SwitchBulb, ServiceConstant.SwitchBulb, ServiceConstant.ON_OFF);
		if(toggleBulb != 0 && toggleBulb != 1){
			throw new BasicException(10003, new CustomException(SwitchBulb_ON_OFF.class, "下发命令参数不正确"));
		}
		this.toggleBulb = toggleBulb;
	}

	public Integer getToggleBulb() {
		return toggleBulb;
	}

	public void setToggleBulb(Integer toggleBulb) {
		this.toggleBulb = toggleBulb;
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
		paras.put("toggleBulb", getToggleBulb());
		
		command.put("paras", paras);
		return command;
	}

}
