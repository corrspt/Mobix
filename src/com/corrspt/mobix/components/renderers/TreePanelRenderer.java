package com.corrspt.mobix.components.renderers;

import static netgest.bo.xwc.components.HTMLAttr.CLASS;
import static netgest.bo.xwc.components.HTMLAttr.ID;
import static netgest.bo.xwc.components.HTMLAttr.ONCLICK;
import static netgest.bo.xwc.components.HTMLTag.A;
import static netgest.bo.xwc.components.HTMLTag.DIV;
import static netgest.bo.xwc.components.HTMLTag.LI;
import static netgest.bo.xwc.components.HTMLTag.UL;

import java.io.IOException;

import javax.faces.component.UIComponent;

import netgest.bo.xwc.components.classic.TreePanel;
import netgest.bo.xwc.components.classic.scripts.XVWScripts;
import netgest.bo.xwc.components.model.Menu;
import netgest.bo.xwc.framework.XUIRenderer;
import netgest.bo.xwc.framework.XUIResponseWriter;
import netgest.bo.xwc.framework.components.XUIComponentBase;

public class TreePanelRenderer extends XUIRenderer {
	
	@Override
	public void encodeBegin(XUIComponentBase component) throws IOException {
		
		XUIResponseWriter w = getResponseWriter();
		TreePanel tree = (TreePanel) component;
		
		w.startElement(DIV);
		w.writeAttribute(ID, component.getClientId());
		
		w.startElement(DIV);
			w.writeAttribute(ID, component.getId());
			w.writeAttribute("data-role", "panel");
			w.writeAttribute("data-position", "left");
			w.writeAttribute("data-display", "reveal");
			w.writeAttribute("data-theme", "a");
			w.writeAttribute(CLASS, "ui-panel ui-panel-position-left ui-panel-display-reveal ui-panel-closed ui-body-a ui-panel-animate");
			
			w.startElement(UL);
				w.writeAttribute("data-role", "listview");
				
				for (UIComponent c : tree.getChildren()){
					
					Menu m = (Menu) c;
					if (m.getChildCount() > 0 ){
						w.startElement(LI);
							w.writeAttribute("data-role", "divider");
							w.write(m.getText());
						w.endElement(LI);
						for (UIComponent child : m.getChildren()){
							Menu childMenu = (Menu) child;
							encodeMenu(childMenu);
						}
					} else {
						w.startElement(LI);
							w.startElement(A);
								w.write(m.getText());
							w.endElement(A);
						w.endElement(LI);
					}
				}
			w.endElement(UL);
		w.endElement(DIV);
		
		w.endElement(DIV);
		
	}
	
	private void encodeMenu(Menu m) throws IOException {
		XUIResponseWriter w = getResponseWriter();
		w.startElement(LI);
			w.startElement(A);
				String script = XVWScripts.getAjaxCommandScript(m, XVWScripts.WAIT_DIALOG);
				w.writeAttribute(ONCLICK, script);
				w.write(m.getText());
			w.endElement(A);
		w.endElement(LI);
	}
	
	
	@Override
	public boolean getRendersChildren() {
		return true;
	}

}
