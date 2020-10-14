package com.tibco.ax.fw.webengine.client;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.tibco.ax.fw.runtime.impl.SyncReply;

@Weave
public class WebClient {

	@Trace
	public SyncReply perform() {
		
		SyncReply reply =  Weaver.callOriginal();
		String tmp = reply.getConfirmationID();
		if(tmp != null && !tmp.isEmpty()) {
			NewRelic.addCustomParameter("Confirmation ID", tmp);
		}
		tmp = reply.getJobID();
		if(tmp != null && !tmp.isEmpty()) {
			NewRelic.addCustomParameter("Job ID", tmp);
		}
		tmp = reply.getStatusCode();
		if(tmp != null && !tmp.isEmpty()) {
			NewRelic.addCustomParameter("Status Code", tmp);
		}
		tmp = reply.getStatusMsg();
		if(tmp != null && !tmp.isEmpty()) {
			NewRelic.addCustomParameter("Status Msg", tmp);
		}
		return reply;
	}
}
