package com.corrspt.mobix.components.renderers;

import static netgest.bo.xwc.components.HTMLAttr.CLASS;
import static netgest.bo.xwc.components.HTMLAttr.ID;
import static netgest.bo.xwc.components.HTMLAttr.ONCLICK;
import static netgest.bo.xwc.components.HTMLTag.BUTTON;
import static netgest.bo.xwc.components.HTMLTag.DIV;

import java.io.IOException;

import netgest.bo.xwc.components.classic.ActionButton;
import netgest.bo.xwc.components.classic.scripts.XVWScripts;
import netgest.bo.xwc.framework.XUIRenderer;
import netgest.bo.xwc.framework.XUIResponseWriter;
import netgest.bo.xwc.framework.components.XUIComponentBase;

public class ActionButtonRenderer extends XUIRenderer {
	
	@Override
	public void encodeBegin(XUIComponentBase component) throws IOException {
		
		XUIResponseWriter w = getResponseWriter();
		ActionButton button = (ActionButton) component;
		
		w.startElement(DIV);
			w.writeAttribute(ID, component.getClientId());
			
			w.startElement(BUTTON);
				w.writeAttribute(CLASS, "ui-btn");
				String script = XVWScripts.getAjaxCommandScript(button, XVWScripts.WAIT_DIALOG) + ";return false;";
				w.writeAttribute(ONCLICK, script);
				w.write(button.getLabel());
			w.endElement(BUTTON);
		w.endElement(DIV);
		
	}

}
