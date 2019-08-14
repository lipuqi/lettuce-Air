package com.lettuce.air.iot.app.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lettuce.air.cache.MapCache;
import com.lettuce.air.common.controllerUtil.GenericResponse;
import com.lettuce.air.common.controllerUtil.ResponseFormat;
import com.lettuce.air.pojo.basic.Cache;

/**
 * 前端监控接口
 * @author Lando
 *
 */
@RestController
@RequestMapping("/monitor")
public class MonitorController {
	
	@Autowired
	private MapCache<String, Cache> mapCache;
	
	/**
	 * 获取设备列表
	 * @return
	 */
	@GetMapping(value = "/getDeviceList", produces = { "application/json;charset=UTF-8" })
	public GenericResponse getDeviceList(){
		return ResponseFormat.retParam(200, mapCache.getDeviceList());
	}
	
	/**
	 * 获取任务列表
	 * @return
	 */
	@GetMapping(value = "/getTaskList", produces = { "application/json;charset=UTF-8" })
	public GenericResponse getTaskList(){
		return ResponseFormat.retParam(200, mapCache.getTaskList());
	}

}
