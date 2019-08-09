package com.lettuce.air.iot.app.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lettuce.air.common.controllerUtil.GenericResponse;
import com.lettuce.air.common.controllerUtil.ResponseFormat;
import com.lettuce.air.common.exception.BasicException;
import com.lettuce.air.common.exception.CustomException;
import com.lettuce.air.service.device.DeviceService;

import net.sf.json.JSONObject;

@RestController
@RequestMapping("/device")
public class DeviceController {
	
	@Autowired
	private DeviceService deviceService;
	
	@PostMapping(value = "/deviceDataChanged", produces = { "application/json;charset=UTF-8" })
	public GenericResponse deviceDataChanged(@RequestBody JSONObject result){
		try {
			deviceService.getDeviceData(result);
		} catch (CustomException ex) {
			throw ex;
		} catch (Exception e) {
			throw new BasicException(1000, e);
		}
		return ResponseFormat.retParam(200, "OK");
	}
	
	@PostMapping(value = "/commandStatus", produces = { "application/json;charset=UTF-8" })
	public GenericResponse commandStatus(@RequestBody JSONObject result){
		try {
			deviceService.getCommandStatus(result);
		} catch (CustomException ex) {
			throw ex;
		} catch (Exception e) {
			throw new BasicException(1000, e);
		}
		return ResponseFormat.retParam(200, "OK");
	}

}