package com.tibco.ax.fw.webengine.app;

import java.net.InetAddress;
import java.net.Socket;
import java.util.HashMap;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

@Weave(type=MatchType.Interface)
public abstract class ISocketProcessor {

	@SuppressWarnings("rawtypes")
	@Trace
	public void processData(Socket socket, HashMap map) {
		if(socket != null) {
			InetAddress address = socket.getInetAddress();
			if(address != null) {
				String hostAddr = address.getHostAddress();
				if(hostAddr != null && !hostAddr.isEmpty()) {
					NewRelic.getAgent().getTracedMethod().setMetricName(new String[] {"Custom","ISocketProcessor",getClass().getSimpleName(),"processData",hostAddr});
				}
			}
		}
		Weaver.callOriginal();
	}
}
