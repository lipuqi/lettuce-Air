package com.puka.iot.app.service.device.impl;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.puka.iot.app.cache.MapCache;
import com.puka.iot.app.common.api.HuaweiIotApiUrl;
import com.puka.iot.app.common.config.HuaweiIotProperties;
import com.puka.iot.app.common.constant.PushStatus;
import com.puka.iot.app.common.exception.BasicException;
import com.puka.iot.app.common.exception.CustomException;
import com.puka.iot.app.pojo.basic.Cache;
import com.puka.iot.app.pojo.device.BasicDevice;
import com.puka.iot.app.pojo.device.Bulb;
import com.puka.iot.app.pojo.device.DeviceConstant;
import com.puka.iot.app.pojo.device.OperationPi;
import com.puka.iot.app.pojo.task.CommandTask;
import com.puka.iot.app.profile.ServiceConstant;
import com.puka.iot.app.profile.command.OperationPi_QUIT_PYTHON;
import com.puka.iot.app.profile.command.SwitchBulb_ON_OFF;
import com.puka.iot.app.profile.command.SwitchBulb_QUERY_STATUS;
import com.puka.iot.app.profile.data.OperationPi_status;
import com.puka.iot.app.profile.data.SwitchBulb_status;
import com.puka.iot.app.service.device.DeviceService;
import com.puka.iot.app.utils.TokenUtil;

import net.sf.json.JSONObject;

@Service
public class DeviceServiceImpl implements DeviceService {
	
	private static final Logger LOGGER = Logger.getLogger(DeviceServiceImpl.class);

	@Autowired
	private MapCache<String, Cache> mapCache;

	@Autowired
	private HuaweiIotProperties huaweiIotProperties;

	@Autowired
	private HuaweiIotApiUrl huaweiIotApiUrl;

	@Autowired
	private TokenUtil tokenUtil;

	@Override
	public void getDeviceData(JSONObject result) {
		
		BasicDevice device = null;
		if (result.containsKey("notifyType") && PushStatus.DEVICE_DATA_CHANGED.equals(result.getString("notifyType"))) {
			JSONObject service = result.getJSONObject("service");
			switch (service.getString("serviceId")) {
			case ServiceConstant.SwitchBulb:
				SwitchBulb_status switchBulb_status = new SwitchBulb_status();
				switchBulb_status.packaging(service);
				device = new Bulb();
				device.setStatus(switchBulb_status.getStatus());
				device.setUpdateTime();
				break;
			case ServiceConstant.OperationPi:
				OperationPi_status sperationPi_status = new OperationPi_status();
				sperationPi_status.packaging(service);
				device = new OperationPi();
				device.setStatus(sperationPi_status.getStatus());
				device.setUpdateTime();
				LOGGER.info("----------------------设备上报心跳----------------------");
				break;
			default:
				throw new CustomException(DeviceServiceImpl.class, "获取数据上报信息类型没有匹配");
			}
			mapCache.put(device.getDeviceKey(), device);
		}
	}

	@Override
	public void getCommandStatus(JSONObject result) {
		
		if (result.containsKey("commandId")) {
			CommandTask commandTask = (CommandTask) mapCache.get(result.getString("commandId"));
			if (commandTask == null) {
				throw new CustomException(DeviceServiceImpl.class, "获取命令状态时的命令标识不在序列中");
			}
			JSONObject commandResult = result.getJSONObject("result");
			String resultCode = commandResult.getString("resultCode");
			commandTask.setStatus(resultCode);
			if("SUCCESSFUL".equals(resultCode)){
				getCommandRsp(commandTask.getMethod(), commandResult.getJSONObject("resultDetail").getInt("result"));
			}
			mapCache.put(commandTask.getCommandId(), commandTask);
		}
	}
	
	private void getCommandRsp(String method, Integer value) {
		BasicDevice device = null;
		
		if (method != null && value != null) {
			switch (method) {
			case ServiceConstant.ON_OFF:
				device = new Bulb();
				device.setStatus(value);
				device.setUpdateTime();
				break;
			case ServiceConstant.QUERY_STATUS:
				device = new Bulb();
				device.setStatus(value);
				device.setUpdateTime();
				break;
			case ServiceConstant.QUIT_PYTHON:
				device = new OperationPi();
				device.setStatus(value);
				device.setUpdateTime();
				break;
			default:
				throw new CustomException(DeviceServiceImpl.class, "获取命令响应类型没有匹配");
			}
			mapCache.put(device.getDeviceKey(), device);
		}
	}

	@Override
	public void sendCommand(String method, Integer value) throws Exception {
		if(StringUtils.isEmpty(method) || value == null){
			throw new BasicException(10002, new CustomException(DeviceServiceImpl.class, "下发命令参数为空"));
		}
		
/*		if(getOperationPiStatus() == 0){
			throw new BasicException(30001, new CustomException(DeviceServiceImpl.class, "设备已下线"));
		}*/
		
		JSONObject commandData = new JSONObject();
		JSONObject command = null;

		switch (method) {
		case ServiceConstant.ON_OFF:
			if(getBulbStatus() == value){
				return;
			}
			command = new SwitchBulb_ON_OFF(value).unpack();
			break;
		case ServiceConstant.QUERY_STATUS:
			command = new SwitchBulb_QUERY_STATUS(value).unpack();
			break;
		case ServiceConstant.QUIT_PYTHON:
			command = new OperationPi_QUIT_PYTHON(value).unpack();
			break;
		default:
			throw new BasicException(10001, new CustomException(DeviceServiceImpl.class, "下发命令类型没有匹配"));
		}

		commandData.put("deviceId", huaweiIotProperties.getDeviceId());
		commandData.put("command", command);
		commandData.put("expireTime", 0);
		commandData.put("maxRetransmit", 3);
		commandData.put("callbackUrl", huaweiIotProperties.getCommandCallbackUrl());

		String result = huaweiIotApiUrl.getDeviceCommandsUrl(huaweiIotProperties.getAppID(), tokenUtil.getToken(),
				commandData);
		JSONObject resultJson = JSONObject.fromObject(result);

		CommandTask commandTask = new CommandTask();
		commandTask.setCommandId(resultJson.getString("commandId"));
		commandTask.setMethod(method);
		commandTask.setStatus(resultJson.getString("status"));
		commandTask.setExpiresIn(System.currentTimeMillis() + (huaweiIotProperties.getCommandExecuteTime() + 1) * 1000);
		
		mapCache.put(commandTask.getCommandId(), commandTask);
	}

	@Override
	public Integer getBulbStatus() {
		Bulb bulb = (Bulb) mapCache.get(DeviceConstant.Bulb);
		if(bulb != null){
			return bulb.getStatus();
		}
		return 0;
	}

	@Override
	public Integer getOperationPiStatus() {
		OperationPi operationPi = (OperationPi) mapCache.get(DeviceConstant.OperationPi);
		if(operationPi != null){
			return operationPi.getStatus();
		}
		return 0;
	}

	@Override
	public void initDevice() {
		BasicDevice device = new Bulb();
		device.setStatus(0);
		device.setUpdateTime();
		mapCache.put(device.getDeviceKey(), device);
		
		device = new OperationPi();
		device.setStatus(0);
		device.setUpdateTime();		
		mapCache.put(device.getDeviceKey(), device);
	}

}
