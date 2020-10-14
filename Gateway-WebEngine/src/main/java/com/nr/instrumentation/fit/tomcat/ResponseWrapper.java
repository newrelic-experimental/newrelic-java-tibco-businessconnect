package com.nr.instrumentation.fit.tomcat;

import javax.servlet.http.HttpServletResponse;

import com.newrelic.api.agent.ExtendedResponse;
import com.newrelic.api.agent.HeaderType;

public class ResponseWrapper extends ExtendedResponse {
	
	private HttpServletResponse response = null;
	
	public ResponseWrapper(HttpServletResponse resp) {
		response = resp;
	}

	@Override
	public int getStatus() throws Exception {
		return response.getStatus();
	}

	@Override
	public String getStatusMessage() throws Exception {
		return null;
	}

	@Override
	public String getContentType() {
		return null;
	}

	@Override
	public HeaderType getHeaderType() {
		return HeaderType.HTTP;
	}

	@Override
	public void setHeader(String name, String value) {
		response.setHeader(name, value);
	}

	@Override
	public long getContentLength() {
		return response.getBufferSize();
	}

}
