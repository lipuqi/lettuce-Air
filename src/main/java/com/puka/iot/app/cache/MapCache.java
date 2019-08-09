package com.puka.iot.app.cache;

import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.puka.iot.app.common.config.HuaweiIotProperties;
import com.puka.iot.app.pojo.basic.Cache;
import com.puka.iot.app.pojo.device.Bulb;
import com.puka.iot.app.pojo.device.DeviceConstant;
import com.puka.iot.app.pojo.device.OperationPi;
import com.puka.iot.app.pojo.task.CommandTask;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Component
public class MapCache<K, V extends Cache> {

	@Autowired
	private HuaweiIotProperties huaweiIotProperties;

	private ConcurrentHashMap<K, V> concurrentHashMap = new ConcurrentHashMap<K, V>();

	public void put(K key, V value) {
		concurrentHashMap.put(key, value);
	}

	public V get(K key) {
		return concurrentHashMap.get(key);
	}

	public JSONArray getDeviceList() {
		JSONArray deviceList = new JSONArray();
		Bulb bulb = (Bulb) concurrentHashMap.get(DeviceConstant.Bulb);
		deviceList.add(JSONObject.fromObject(bulb));
		OperationPi operationPi = (OperationPi) concurrentHashMap.get(DeviceConstant.OperationPi);
		deviceList.add(JSONObject.fromObject(operationPi));
		return deviceList;
	}

	public JSONArray getTaskList() {
		JSONArray taskList = new JSONArray();
		for (Entry<K, V> entry : concurrentHashMap.entrySet()) {
			if (DeviceConstant.Bulb.equals(entry.getKey()) || DeviceConstant.OperationPi.equals(entry.getKey())
					|| huaweiIotProperties.getAppID().equals(entry.getKey())) {
				continue;
			}
			CommandTask commandTask = (CommandTask) entry.getValue();
			taskList.add(JSONObject.fromObject(commandTask));
		}
		return taskList;
	}

	public void cleanMap() {
		if (concurrentHashMap.size() != 0) {
			for (Entry<K, V> entry : concurrentHashMap.entrySet()) {
				Cache cache = (Cache) entry.getValue();
				if (cache.getExpiresIn() == null) {
					continue;
				}
				if (System.currentTimeMillis() >= cache.getExpiresIn()) {
					concurrentHashMap.remove(entry.getKey());
				}
			}
		}
	}

}
