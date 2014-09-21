package com.corrspt.mobix.components.renderers;

import static netgest.bo.xwc.components.HTMLAttr.CLASS;
import static netgest.bo.xwc.components.HTMLAttr.DISABLED;
import static netgest.bo.xwc.components.HTMLAttr.ID;
import static netgest.bo.xwc.components.HTMLAttr.NAME;
import static netgest.bo.xwc.components.HTMLAttr.READONLY;
import static netgest.bo.xwc.components.HTMLAttr.TYPE;
import static netgest.bo.xwc.components.HTMLAttr.VALUE;
import static netgest.bo.xwc.components.HTMLTag.DIV;
import static netgest.bo.xwc.components.HTMLTag.INPUT;

import java.io.IOException;
import java.util.Map;

import netgest.bo.xwc.components.classic.AttributeBase;
import netgest.bo.xwc.components.classic.AttributeText;
import netgest.bo.xwc.framework.XUIRenderer;
import netgest.bo.xwc.framework.XUIResponseWriter;
import netgest.bo.xwc.framework.components.XUIComponentBase;

public class AttributeTextRenderer extends XUIRenderer {
	
	@Override
	public void encodeBegin(XUIComponentBase component) throws IOException {
		
		XUIResponseWriter w = getResponseWriter();
		AttributeText text = (AttributeText) component;
		w.startElement( DIV );
			w.writeAttribute( ID , text.getClientId() );
			w.writeAttribute( CLASS , "ui-input-text ui-body-inherit ui-corner-all ui-shadow-inset" );
			
			
			w.startElement( INPUT );
				w.writeAttribute( ID , text.getClientId() + "_i" );
				w.writeAttribute( NAME , text.getClientId() );
				w.writeAttribute( VALUE , text.getDisplayValue() );
				
				w.writeAttribute( TYPE , "text" );
				
				if (text.isRequired())
					w.writeAttribute( "required" , true );
				if (text.isDisabled())
					w.writeAttribute( DISABLED , true );
				if (text.isReadOnly()){
					w.writeAttribute( READONLY , true );
				}
				
			w.endElement( INPUT );
			
		w.endElement( DIV );
	}
	
	@Override
	public void decode(XUIComponentBase component) {
		AttributeBase base = (AttributeBase) component;
		Map<String,String> request = getFacesContext().getExternalContext().getRequestParameterMap();
		String clientId = component.getClientId();
		if (request.containsKey( clientId )){
			String value = request.get( clientId );
			if (value != null){
				base.setSubmittedValue( value );
			}
		}
	}

}
