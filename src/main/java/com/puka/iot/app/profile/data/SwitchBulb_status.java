package com.puka.iot.app.profile.data;

import com.puka.iot.app.common.exception.CustomException;

import net.sf.json.JSONObject;

public class SwitchBulb_status extends DeviceData {

	private Integer status;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public void packaging(JSONObject service) {
		try {
			if (service.containsKey("serviceId"))
				setServiceId(service.getString("serviceId"));
			if (service.containsKey("serviceType"))
				setServiceType(service.getString("serviceType"));
			if (service.containsKey("data")) {
				JSONObject serviceData = service.getJSONObject("data");
				if (serviceData.containsKey("status"))
					setStatus(serviceData.getInt("status"));
			}
		} catch (Exception e) {
			throw new CustomException(SwitchBulb_status.class, "SwitchBulb-status上传数据解析服务出现问题", e);
		}
	}

}
