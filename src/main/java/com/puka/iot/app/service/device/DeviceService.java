package com.puka.iot.app.service.device;

import net.sf.json.JSONObject;

/**
 * 设备操作的接口
 * @author Lando
 *
 */
public interface DeviceService {

	/**
	 * 获取设备数据变化
	 * @param result
	 */
	public void getDeviceData(JSONObject result);
	
	
	/**
	 * 获取命令状态
	 * @param result
	 */
	public void getCommandStatus(JSONObject result);
	
	/**
	 * 下发命令
	 * @param method
	 * @param value
	 * @throws Exception
	 */
	public void sendCommand(String method, Integer value)  throws Exception;
	
	/**
	 * 获取灯的状态
	 * @return
	 */
	public Integer getBulbStatus();
	
	/**
	 * 获取系统状态
	 * @return
	 */
	public Integer getOperationPiStatus();
	
	/**
	 * 初始化设备
	 * @return
	 */
	public void initDevice();
	
	
}
