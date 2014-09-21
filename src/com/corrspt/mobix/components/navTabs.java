package com.corrspt.mobix.components;

import static netgest.bo.xwc.components.HTMLAttr.ID;
import static netgest.bo.xwc.components.HTMLAttr.ONCLICK;
import static netgest.bo.xwc.components.HTMLTag.A;
import static netgest.bo.xwc.components.HTMLTag.DIV;
import static netgest.bo.xwc.components.HTMLTag.LI;
import static netgest.bo.xwc.components.HTMLTag.UL;

import java.io.IOException;

import javax.faces.component.UIComponent;

import netgest.bo.xwc.components.classic.scripts.XVWScripts;
import netgest.bo.xwc.framework.XUIRenderer;
import netgest.bo.xwc.framework.XUIResponseWriter;
import netgest.bo.xwc.framework.components.XUIComponentBase;
import netgest.utils.StringUtils;


/**
 * 
 */
public class navTabs extends XUIComponentBase {

	
		
	@Override
	public void preRender() {
	}
	
	@Override
	public Object saveState() {				
		return super.saveState();
	}
	
	public static class XEOHTMLRenderer extends XUIRenderer {
			
		@Override
		public void encodeBegin(XUIComponentBase component) throws IOException {
			XUIResponseWriter w = getResponseWriter();
			navTabs oComp = (navTabs)component;
			
			w.startElement(DIV);
				w.writeAttribute(ID, oComp.getClientId());
				
				w.startElement(DIV);
					w.writeAttribute("data-role", "navbar");
					w.startElement(UL);
						
						for (UIComponent c : oComp.getChildren()){
							navTab tab = (navTab) c;
							
							w.startElement(LI);
							w.startElement(A);
								if (StringUtils.hasValue(tab.getIcon())){
									w.writeAttribute("data-icon", tab.getIcon());
								}
								String script = XVWScripts.getAjaxCommandScript(tab.getCommand(), XVWScripts.WAIT_DIALOG) + ";return false;";
								w.writeAttribute(ONCLICK, script);
								w.write(tab.getText());
							w.endElement(A);
							w.endElement(LI);
						}
						
					w.endElement(UL);
				w.endElement(DIV);
				
			w.endElement(DIV);
			
		}
		
		@Override
		public void encodeEnd(XUIComponentBase component) throws IOException {
		}

		@Override
		public void decode(XUIComponentBase component) {
			super.decode(component);
		}
		
		@Override
		public boolean getRendersChildren() {
			return true;
		}
	}
	
}
