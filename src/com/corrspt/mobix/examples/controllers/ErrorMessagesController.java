package com.corrspt.mobix.examples.controllers;

import netgest.bo.xwc.framework.controllers.Controller;
import netgest.bo.xwc.framework.messages.XUIMessageSender;

public class ErrorMessagesController extends Controller {

	@Override
	public void init() {
		

	}

	@Override
	public boolean canClose(CloseOption option) {
		return true;
	}
	
	public void message(){
		XUIMessageSender.alertCritical("Title", "Message");
	}

}
