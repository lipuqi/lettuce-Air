package com.puka.iot.app.utils;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.puka.iot.app.common.api.HuaweiIotApiUrl;
import com.puka.iot.app.common.config.HuaweiIotProperties;
import com.puka.iot.app.common.exception.CustomException;
import com.puka.iot.app.pojo.token.AccessToken;
import com.puka.iot.app.service.token.TokenService;

/**
 * Token工具
 * 
 * @author Lando
 *
 */
@Component
public class TokenUtil {

	@Autowired
	private HuaweiIotProperties huaweiIotProperties;

	@Autowired
	private HuaweiIotApiUrl huaweiIotApiUrl;

	@Autowired
	private TokenService tokenService;

	/**
	 * 获取访问凭证
	 * 
	 * @param appType
	 * @return
	 */
	public String getToken() {
		String appId = huaweiIotProperties.getAppID();
		AccessToken token = tokenService.findToken(appId);
		try {
			if (token == null) {
				token = AccessToken.packaging(appId, huaweiIotApiUrl.getToken(huaweiIotProperties));
				tokenService.saveToken(token);
			} else if (System.currentTimeMillis() >= token.getExpiresIn()) {
				token = AccessToken.packaging(appId,
						huaweiIotApiUrl.getToken(huaweiIotProperties, token.getRefreshToken()));
				tokenService.saveToken(token);
			}
		} catch (ClientProtocolException e) {
			throw new CustomException(TokenUtil.class, "token http请求出现异常ClientProtocolException", e);
		} catch (IOException e) {
			throw new CustomException(TokenUtil.class, "token http请求出现异常IOException", e);
		} catch (Exception e) {
			throw new CustomException(TokenUtil.class, "token http请求出现异常Exception", e);
		}
		return token.getToken();
	}

}