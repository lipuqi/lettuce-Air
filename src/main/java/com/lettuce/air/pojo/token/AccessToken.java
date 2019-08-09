package com.lettuce.air.pojo.token;

import com.lettuce.air.common.exception.CustomException;
import com.lettuce.air.pojo.basic.Cache;

import net.sf.json.JSONObject;

public class AccessToken extends Cache {

	// appId
	private String appid;

	// 凭证内容
	private String token;

	// 刷新凭证内容
	private String refreshToken;

	public AccessToken() {
	}

	public AccessToken(String appid, String token, String refreshToken, Long expiresIn) {
		super();
		this.appid = appid;
		this.token = token;
		this.refreshToken = refreshToken;
		super.setExpiresIn(expiresIn);
	}

	public String getAppid() {
		return appid;
	}

	public String getToken() {
		return token;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public static AccessToken packaging(String appid, String result) {
		AccessToken accessToken = null;
		try {
			JSONObject json = JSONObject.fromObject(result);
			if (json.containsKey("accessToken")) {
				accessToken = new AccessToken(appid, json.getString("accessToken"), json.getString("refreshToken"),
						System.currentTimeMillis() + json.getInt("expiresIn") * 1000);
			} else {
				throw new Exception("解析AccessToken时没有发现token值" + result);
			}
		} catch (Exception e) {
			throw new CustomException(AccessToken.class, "解析AccessToken出现问题", e);
		}
		return accessToken;
	}

}
