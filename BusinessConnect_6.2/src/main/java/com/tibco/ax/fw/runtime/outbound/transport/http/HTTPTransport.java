package com.tibco.ax.fw.runtime.outbound.transport.http;

import java.net.Socket;

import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.tibco.ax.fw.runtime.impl.SyncReply;
import com.tibco.ax.fw.util.BCTrace;

@Weave(type=MatchType.BaseClass)
public class HTTPTransport {

	@Trace(dispatcher=true)
	private Socket sendRequest(BCTrace trace, SyncReply[] syncReplies) {
		return Weaver.callOriginal();
	}
}
