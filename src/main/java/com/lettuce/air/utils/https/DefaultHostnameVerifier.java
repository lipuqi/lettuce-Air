package com.lettuce.air.utils.https;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

class DefaultHostnameVerifier implements HostnameVerifier {

	@Override
	public boolean verify(String hostname, SSLSession session) {
		return true;
	}
}
