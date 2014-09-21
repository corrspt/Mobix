package com.corrspt.mobix.components.renderers;

import static netgest.bo.xwc.components.HTMLAttr.CLASS;
import static netgest.bo.xwc.components.HTMLAttr.ID;
import static netgest.bo.xwc.components.HTMLTag.DIV;

import java.io.IOException;

import netgest.bo.xwc.components.classic.Attribute;
import netgest.bo.xwc.components.classic.AttributeLabel;
import netgest.bo.xwc.framework.XUIRenderer;
import netgest.bo.xwc.framework.XUIResponseWriter;
import netgest.bo.xwc.framework.components.XUIComponentBase;

public class AttributeRenderer extends XUIRenderer {
	
	
	@Override
	public void encodeBegin(XUIComponentBase component) throws IOException {
		
		Attribute attribute = (Attribute) component;
		XUIComponentBase input = attribute.getInputComponent();
		AttributeLabel label = attribute.getLabelComponent();
		
		XUIResponseWriter w  = getResponseWriter();
		
		w.startElement(DIV);
			w.writeAttribute(ID, attribute.getClientId());
			w.writeAttribute(CLASS, "ui-field-contain");
			label.encodeAll();
			input.encodeAll();
		w.endElement(DIV);
		
	}
	
	@Override
    public void decode( XUIComponentBase oComp ) {
        
        Attribute oAttrComp;
        oAttrComp = (Attribute)oComp;
        String value = getContext().getRequestParameterMap().get( oComp.getClientId() );
        ((Attribute)oComp).setSubmittedValue( value );
        
        XUIComponentBase label = oAttrComp.getLabelComponent();
        XUIComponentBase input = oAttrComp.getInputComponent();
        
        if( label != null )
        	label.decode( );

        if( input != null )
            input.decode( );
        
        
    }
	
	@Override
	public boolean getRendersChildren() {
		return true;
	}
	
	@Override
	public void encodeChildren(XUIComponentBase component) throws IOException {
		
	}

}
