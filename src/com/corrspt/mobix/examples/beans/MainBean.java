package com.corrspt.mobix.examples.beans;

import netgest.bo.xwc.framework.components.XUIViewRoot;
import netgest.bo.xwc.xeo.beans.XEOBaseBean;

public class MainBean extends XEOBaseBean {
	
	public XUIViewRoot getMain(){
		return getRequestContext().getSessionContext().createView("actionButton.xvw");
	}

	
}
