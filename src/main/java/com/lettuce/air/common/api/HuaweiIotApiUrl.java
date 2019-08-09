package com.lettuce.air.common.api;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.lettuce.air.common.config.HuaweiIotProperties;
import com.lettuce.air.utils.https.HttpsUtil;
import com.lettuce.air.utils.https.StreamClosedHttpResponse;

import net.sf.json.JSONObject;

@Component
@ConfigurationProperties(prefix="huaweiIot.api")
public class HuaweiIotApiUrl {
	
	//获取token
	private String tokenUrl;
	
	//刷新token
	private String refreshTokenUrl;
	
	//创建设备命令
	private String deviceCommandsUrl;
	
	
	public void setTokenUrl(String tokenUrl) {
		this.tokenUrl = tokenUrl;
	}

	public void setRefreshTokenUrl(String refreshTokenUrl) {
		this.refreshTokenUrl = refreshTokenUrl;
	}

	public void setDeviceCommandsUrl(String deviceCommandsUrl) {
		this.deviceCommandsUrl = deviceCommandsUrl;
	}

	public String getToken(HuaweiIotProperties huaweiIotProperties) throws Exception {
		HttpsUtil httpsUtil = new HttpsUtil();
		httpsUtil.initSSLConfigForTwoWay();
		
		Map<String, String> paramLogin = new HashMap<>();
        paramLogin.put("appId", huaweiIotProperties.getAppID());
        paramLogin.put("secret", huaweiIotProperties.getAppSecret());

        StreamClosedHttpResponse responseLogin = httpsUtil.doPostFormUrlEncodedGetStatusLine(tokenUrl, paramLogin);
        return responseLogin.getContent();
	}
	
	public String getToken(HuaweiIotProperties huaweiIotProperties, String refreshToken) throws Exception {
		HttpsUtil httpsUtil = new HttpsUtil();
		httpsUtil.initSSLConfigForTwoWay();
		
		Map<String, String> paramRefresh = new HashMap<>();
		paramRefresh.put("appId", huaweiIotProperties.getAppID());
		paramRefresh.put("secret", huaweiIotProperties.getAppSecret());
		paramRefresh.put("refreshToken", refreshToken);

        StreamClosedHttpResponse responseLogin = httpsUtil.doPostFormUrlEncodedGetStatusLine(refreshTokenUrl, paramRefresh);
        return responseLogin.getContent();
	}

	public String getDeviceCommandsUrl(String appID, String token, JSONObject paramCreateDeviceCommand) throws Exception {
        HttpsUtil httpsUtil = new HttpsUtil();
        httpsUtil.initSSLConfigForTwoWay();
        
        Map<String, String> header = new HashMap<>();
        header.put("app_key", appID);
        header.put("Authorization", "Bearer" + " " + token);
        
        HttpResponse responseCreateDeviceCommand = httpsUtil.doPostJson(deviceCommandsUrl, header, paramCreateDeviceCommand.toString());
		
		return httpsUtil.getHttpResponseBody(responseCreateDeviceCommand);
	}

}
