package com.corrspt.mobix.components.renderers;

import static netgest.bo.xwc.components.HTMLAttr.FOR;
import static netgest.bo.xwc.components.HTMLTag.LABEL;

import java.io.IOException;

import javax.faces.component.UIComponent;

import netgest.bo.xwc.components.classic.Attribute;
import netgest.bo.xwc.components.classic.AttributeLabel;
import netgest.bo.xwc.framework.XUIRenderer;
import netgest.bo.xwc.framework.XUIResponseWriter;
import netgest.bo.xwc.framework.components.XUIComponentBase;
import netgest.utils.StringUtils;

public class AttributeLabelRenderer extends XUIRenderer {
	
	@Override
	public void encodeBegin(XUIComponentBase component) throws IOException {
		XUIResponseWriter w = getResponseWriter();
		
		String forLabel = "";
		UIComponent parent = component.getParent();
		if (parent instanceof Attribute){
			 Attribute parentAttribute = (Attribute) parent;
			 forLabel = parentAttribute.getInputComponent().getClientId();
		}
		
		AttributeLabel label = (AttributeLabel) component;
//		w.startElement(DIV);
//			w.writeAttribute(ID, component.getClientId());
			w.startElement(LABEL);
				if (StringUtils.hasValue(forLabel)){
					w.writeAttribute(FOR, forLabel);
				}
				w.write(label.getText());
			w.endElement(LABEL);
//		w.endElement(DIV);
	}

}
