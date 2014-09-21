package com.corrspt.mobix.components.renderers;

import static netgest.bo.xwc.components.HTMLAttr.CLASS;
import static netgest.bo.xwc.components.HTMLAttr.ID;
import static netgest.bo.xwc.components.HTMLAttr.ONCLICK;
import static netgest.bo.xwc.components.HTMLTag.A;
import static netgest.bo.xwc.components.HTMLTag.DIV;

import java.io.IOException;

import javax.faces.component.UIComponent;

import netgest.bo.xwc.components.classic.scripts.XVWScripts;
import netgest.bo.xwc.components.model.Menu;
import netgest.bo.xwc.framework.XUIRenderer;
import netgest.bo.xwc.framework.XUIResponseWriter;
import netgest.bo.xwc.framework.components.XUIComponentBase;
import netgest.utils.StringUtils;

public class ToolbarRenderer extends XUIRenderer {
	
	@Override
	public void encodeBegin(XUIComponentBase component) throws IOException {
		
		XUIResponseWriter w = getResponseWriter();
		
		w.startElement(DIV);
			w.writeAttribute(CLASS, "ui-controlgroup ui-controlgroup-horizontal ui-corner-all ");
			w.writeAttribute(ID, component.getClientId());
			w.writeAttribute("data-role", "controlgroup");
			w.writeAttribute("data-type", "horizontal");
			
			w.startElement(DIV);
			w.writeAttribute(CLASS, "ui-controlgroup-controls ");
			
			for (UIComponent child : component.getChildren()){
				Menu m = (Menu) child;
				w.startElement(A);
					String script = XVWScripts.getAjaxCommandScript(m, XVWScripts.WAIT_DIALOG) + ";return false;";
					w.writeAttribute(ONCLICK, script);
					String cssClass = "ui-btn ui-corner-all";
					if (StringUtils.hasValue(m.getCssClass())){
						cssClass += " " + m.getCssClass();
					}
					if (StringUtils.hasValue(m.getIconCls())){
						cssClass += " ui-btn-icon-left " + m.getIconCls();
						if (StringUtils.isEmpty(m.getText())){
							cssClass += " notext";
						}
					}
					w.writeAttribute(CLASS, cssClass);
					if (StringUtils.hasValue(m.getText())){
						w.write(m.getText());
					}
					
				w.endElement(A);
			}
			
		w.endElement(DIV);
		w.endElement(DIV); //control-group
		
		
	}
	
	@Override
	public boolean getRendersChildren() {
		return true;
	}
	
	@Override
	public void encodeChildren(XUIComponentBase component) throws IOException {
		
	}
	
	

}
