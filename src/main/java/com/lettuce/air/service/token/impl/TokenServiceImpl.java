package com.lettuce.air.service.token.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lettuce.air.cache.MapCache;
import com.lettuce.air.pojo.token.AccessToken;
import com.lettuce.air.service.token.TokenService;

/**
 * token操作的接口实现（cache实现）
 * 
 * @author Lando
 *
 */
@Service
public class TokenServiceImpl implements TokenService {
	
	@Autowired
    private MapCache<String, AccessToken> mapCache;

	@Override
	public AccessToken saveToken(AccessToken token) {
		mapCache.put(token.getAppid(), token);
		return token;
	}

	@Override
	public AccessToken findToken(String appid) {
		return mapCache.get(appid);
	}

}
