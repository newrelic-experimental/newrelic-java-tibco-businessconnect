package com.tibco.ax.fw.webengine.app;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

@Weave(type=MatchType.BaseClass)
public abstract class AppBase {
	
	public abstract String getAppName();

	@Trace
	protected final void processGet(HttpServletRequest req, HttpServletResponse resp) {
		NewRelic.getAgent().getTracedMethod().setMetricName(new String[] {"Custom","AppBase",getClass().getSimpleName(),"processGet",getAppName()});
		Weaver.callOriginal();
	}
	
	@Trace
	protected final void processPost(HttpServletRequest req, HttpServletResponse resp) {
		NewRelic.getAgent().getTracedMethod().setMetricName(new String[] {"Custom","AppBase",getClass().getSimpleName(),"processPost",getAppName()});
		Weaver.callOriginal();
	}
}
