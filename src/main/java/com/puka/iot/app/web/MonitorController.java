package com.puka.iot.app.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.puka.iot.app.cache.MapCache;
import com.puka.iot.app.common.controllerUtil.GenericResponse;
import com.puka.iot.app.common.controllerUtil.ResponseFormat;
import com.puka.iot.app.pojo.basic.Cache;

@RestController
@RequestMapping("/monitor")
public class MonitorController {
	
	@Autowired
	private MapCache<String, Cache> mapCache;
	
	@GetMapping(value = "/getDeviceList", produces = { "application/json;charset=UTF-8" })
	public GenericResponse getDeviceList(){
		return ResponseFormat.retParam(200, mapCache.getDeviceList());
	}
	
	@GetMapping(value = "/getTaskList", produces = { "application/json;charset=UTF-8" })
	public GenericResponse getTaskList(){
		return ResponseFormat.retParam(200, mapCache.getTaskList());
	}

}
