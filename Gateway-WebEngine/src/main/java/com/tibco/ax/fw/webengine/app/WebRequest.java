package com.tibco.ax.fw.webengine.app;

import java.io.InputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.nr.instrumentation.fit.tomcat.RequestWrapper;
import com.nr.instrumentation.fit.tomcat.ResponseWrapper;

@Weave(type=MatchType.BaseClass)
public abstract class WebRequest {
	
	public abstract HttpServletRequest getReq();
	
	public abstract HttpServletResponse getResp();

	protected void sendReply(int code, String msg, Map<String, String> headers, String strBody, InputStream streamBody, String cntType) {
		RequestWrapper reqWrapper = new RequestWrapper(getReq());
		ResponseWrapper respWrapper = new ResponseWrapper(getResp());
		NewRelic.getAgent().getTransaction().setWebRequest(reqWrapper);
		NewRelic.getAgent().getTransaction().setWebResponse(respWrapper);
		
		NewRelic.getAgent().getTransaction().addOutboundResponseHeaders();
		
		Weaver.callOriginal();
		
	}
}
