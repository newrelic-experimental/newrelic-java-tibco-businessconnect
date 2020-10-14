package com.tibco.ax.fw.runtime.outbound.transport;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Token;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.NewField;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.tibco.ax.fw.util.BCTrace;

@Weave(type=MatchType.BaseClass)
public abstract class TransportBase {
	
	@NewField
	protected Token token = null;

	public abstract String getExecutorName();
	
	protected abstract String getTransportName();
	
	@Trace(async=true)
	public void send(BCTrace trace) {
		if(token != null) {
			token.linkAndExpire();
			token = null;
		}
		NewRelic.getAgent().getTracedMethod().setMetricName(new String[] {"Custom","Transport",getClass().getSimpleName(),getExecutorName(),getTransportName(),"send"});
		Weaver.callOriginal();
	}
	
	@Trace
	public void schedule() {
		if(token == null) {
			token = NewRelic.getAgent().getTransaction().getToken();
		}
		Weaver.callOriginal();
	}
	
	@Trace(async=true)
	public void cancel() {
		if(token != null) {
			token.linkAndExpire();
			token = null;
		}
		Weaver.callOriginal();
	}
}
