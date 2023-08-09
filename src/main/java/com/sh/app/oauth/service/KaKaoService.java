package com.sh.app.oauth.service;

import java.util.Map;

public interface KaKaoService {

	String getAuthorizeUri();

	Map<String, Object> getTokens(String code);

	Map<String, Object> getProfile(String accessToken);

}
