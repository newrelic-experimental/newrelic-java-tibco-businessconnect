package com.tibco.ax.fw.runtime.impl;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.tibco.ax.fw.runtime.impl.ErrorReply;

@Weave(type=MatchType.BaseClass)
public abstract class AbstractService {

	@Trace(dispatcher=true)
	public final void processor() {
		NewRelic.addCustomParameter("Service ID", getServiceID());
		Weaver.callOriginal();
	}
	
	@Trace
	public final void preProcessor() {
		Weaver.callOriginal();
	}
	
	public abstract String getServiceID();

	@Trace
	protected abstract int preProcessMsg(String paramString, int paramInt);
	
	@Trace
	protected abstract int processMsg(String paramString, int paramInt);
	
	@Trace
	protected abstract int postProcessMsg(String paramString, int paramInt);
	
	@Trace
	protected ErrorReply processError(String paramString, ServiceException paramServiceException) {
		NewRelic.noticeError(paramServiceException);
		return Weaver.callOriginal();
	}
}
