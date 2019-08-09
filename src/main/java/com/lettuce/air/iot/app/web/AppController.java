package com.lettuce.air.iot.app.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lettuce.air.common.controllerUtil.GenericResponse;
import com.lettuce.air.common.controllerUtil.ResponseFormat;
import com.lettuce.air.common.exception.BasicException;
import com.lettuce.air.common.exception.CustomException;
import com.lettuce.air.service.device.DeviceService;

@RestController
@RequestMapping("/app")
public class AppController {
	
	@Autowired
	private DeviceService deviceService;
	
	@GetMapping(value = "/getBulbStatus", produces = { "application/json;charset=UTF-8" })
	public GenericResponse getBulbStatus(){
		return ResponseFormat.retParam(200, deviceService.getBulbStatus());
	}
	
	@GetMapping(value = "/getOperationPiStatus", produces = { "application/json;charset=UTF-8" })
	public GenericResponse getOperationPiStatus(){
		return ResponseFormat.retParam(200, deviceService.getOperationPiStatus());
	}
	
	@GetMapping("/sendCommand")
	public GenericResponse sendCommand(String method, Integer value){
		try {
			deviceService.sendCommand(method, value);
		} catch (CustomException ex) {
			throw ex;
		} catch (BasicException exc) {
			throw exc;
		} catch (Exception e) {
			throw new BasicException(1000, e);
		}
		return ResponseFormat.retParam(200, null);
	}
	

}
