package com.puka.iot.app.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.puka.iot.app.common.controllerUtil.GenericResponse;
import com.puka.iot.app.common.controllerUtil.ResponseFormat;
import com.puka.iot.app.common.exception.BasicException;
import com.puka.iot.app.common.exception.CustomException;
import com.puka.iot.app.service.device.DeviceService;

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
