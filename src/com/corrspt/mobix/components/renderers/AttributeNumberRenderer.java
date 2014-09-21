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
import netgest.bo.xwc.components.classic.AttributeNumber;
import netgest.bo.xwc.framework.XUIRenderer;
import netgest.bo.xwc.framework.XUIResponseWriter;
import netgest.bo.xwc.framework.components.XUIComponentBase;

public class AttributeNumberRenderer extends XUIRenderer {

	@Override
	public void encodeBegin(XUIComponentBase component) throws IOException {
		XUIResponseWriter w = getResponseWriter();
		AttributeNumber number = (AttributeNumber) component;
		w.startElement( DIV );
			w.writeAttribute( ID , number.getClientId() );
			w.writeAttribute( CLASS , "ui-input-text ui-body-inherit ui-corner-all ui-shadow-inset" );
			
			w.startElement( INPUT );
				w.writeAttribute( ID , number.getClientId() + "_i" );
				w.writeAttribute( NAME , number.getClientId() );
				w.writeAttribute( VALUE , number.getDisplayValue() );
				
				w.writeAttribute( TYPE , "number" );
				w.writeAttribute( "pattern" , "[0-9]*" );
				
				
				if (number.isRequired())
					w.writeAttribute( "required" , true );
				if (number.isDisabled())
					w.writeAttribute( DISABLED , true );
				if (number.isReadOnly()){
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
