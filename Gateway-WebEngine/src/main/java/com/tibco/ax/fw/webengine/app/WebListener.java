package com.tibco.ax.fw.webengine.app;

import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

@Weave(type=MatchType.BaseClass)
public abstract class WebListener {

	@Trace
	public void getData(IWebRequest webRequest) {
		Weaver.callOriginal();
	}
}
