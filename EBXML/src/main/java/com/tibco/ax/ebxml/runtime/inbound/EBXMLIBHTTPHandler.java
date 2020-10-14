package com.tibco.ax.ebxml.runtime.inbound;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.tibco.ax.fw.runtime.inbound.impl.IBMIMEHandler;
import com.tibco.ax.fw.runtime.inbound.impl.IBMSHService;
import com.tibco.ax.fw.util.BCTrace;

@SuppressWarnings("serial")
@Weave
public abstract class EBXMLIBHTTPHandler extends IBMIMEHandler {

	public EBXMLIBHTTPHandler(IBMSHService arg0) {
		super(arg0);
	}

	@Trace
	public boolean validateMessage(String execStateID, int prevHints, BCTrace trace) {
		 EBXMLIBMsgContext messageContext = (EBXMLIBMsgContext)this.mMessageContext;
		 if(messageContext != null) {
			 String tmp = messageContext.getConversationID();
			 if(tmp != null && !tmp.isEmpty()) {
				 NewRelic.addCustomParameter("Converstation ID", tmp);
			 }
			 tmp = messageContext.getCorrelationID();
			 if(tmp != null && !tmp.isEmpty()) {
				 NewRelic.addCustomParameter("Correlation ID", tmp);
			 }
			 tmp = messageContext.getService();
			 if(tmp != null && !tmp.isEmpty()) {
				 NewRelic.addCustomParameter("Service", tmp);
			 }
			 tmp = messageContext.getCorrelationID();
			 if(tmp != null && !tmp.isEmpty()) {
				 NewRelic.addCustomParameter("Correlation ID", tmp);
			 }
			 tmp = messageContext.getMessageId();
			 if(tmp != null && !tmp.isEmpty()) {
				 NewRelic.addCustomParameter("Message ID", tmp);
			 }
			 tmp = messageContext.getPartnerID();
			 if(tmp != null && !tmp.isEmpty()) {
				 NewRelic.addCustomParameter("Partner ID", tmp);
			 }
			 tmp = messageContext.getAction();
			 if(tmp != null && !tmp.isEmpty()) {
				 NewRelic.addCustomParameter("Action", tmp);
			 }
			 tmp = messageContext.getBusinessProcess();
			 if(tmp != null && !tmp.isEmpty()) {
				 NewRelic.addCustomParameter("Business Process", tmp);
			 }
			 tmp = messageContext.getFromID();
			 if(tmp != null && !tmp.isEmpty()) {
				 NewRelic.addCustomParameter("From ID", tmp);
			 }
			 tmp = messageContext.getToID();
			 if(tmp != null && !tmp.isEmpty()) {
				 NewRelic.addCustomParameter("To ID", tmp);
			 }


		 }
		return Weaver.callOriginal();
	}
}
