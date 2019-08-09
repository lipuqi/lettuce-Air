package com.lettuce.air.profile.command;

import com.lettuce.air.common.exception.BasicException;
import com.lettuce.air.common.exception.CustomException;
import com.lettuce.air.profile.ServiceConstant;

import net.sf.json.JSONObject;

public class OperationPi_QUIT_PYTHON extends DeviceCommand {

	private Integer executeQuit;

	private Integer result;
	

	public Integer getExecuteQuit() {
		return executeQuit;
	}

	public void setExecuteQuit(Integer executeQuit) {
		this.executeQuit = executeQuit;
	}

	public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}
	
	public OperationPi_QUIT_PYTHON() {
	}

	public OperationPi_QUIT_PYTHON(Integer executeQuit) {
		super(ServiceConstant.OperationPi, ServiceConstant.OperationPi, ServiceConstant.QUIT_PYTHON);
		if(executeQuit != 1){
			throw new BasicException(10003, new CustomException(OperationPi_QUIT_PYTHON.class, "下发命令参数不正确"));
		}
		this.executeQuit = executeQuit;
	}

	@Override
	public JSONObject unpack() {
		JSONObject command = new JSONObject();
		command.put("serviceId", getServiceId());
		command.put("method", getMethod());
		
		JSONObject paras = new JSONObject();
		paras.put("executeQuit", getExecuteQuit());
		
		command.put("paras", paras);
		return command;
	}

}
