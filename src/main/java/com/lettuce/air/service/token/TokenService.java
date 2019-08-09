package com.lettuce.air.service.token;

import com.lettuce.air.pojo.token.AccessToken;

/**
 * token操作的接口
 * @author Lando
 *
 */
public interface TokenService {

	/**
	 * 存入token
	 * @param token
	 * @return
	 */
	public AccessToken saveToken(AccessToken token);
	
	/**
	 * 查找token
	 * @param appid
	 * @return
	 */
	public AccessToken findToken(String appid);
}
