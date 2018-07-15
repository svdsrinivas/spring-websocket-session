package com.mia.wskafka.config;

import java.util.Map;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

public class WebSocketInteceptor implements HandshakeInterceptor{

	@Override
	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse arg1, WebSocketHandler arg2, Exception arg3) {
		
	}

	@Override
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse arg1, WebSocketHandler arg2,
			Map<String, Object> attributes) throws Exception {
		String path = request.getURI().getPath();
        String auctionId = path.substring(path.lastIndexOf('/') + 1);
        attributes.put("stoken", auctionId);
        return true;
	}

}
