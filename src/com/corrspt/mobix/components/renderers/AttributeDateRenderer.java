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
import netgest.bo.xwc.components.classic.AttributeDate;
import netgest.bo.xwc.framework.XUIRenderer;
import netgest.bo.xwc.framework.XUIResponseWriter;
import netgest.bo.xwc.framework.components.XUIComponentBase;

public class AttributeDateRenderer extends XUIRenderer {

	@Override
	public void encodeBegin(XUIComponentBase component) throws IOException {
		XUIResponseWriter w = getResponseWriter();
		AttributeDate date = (AttributeDate) component;
		w.startElement( DIV );
			w.writeAttribute( ID , date.getClientId() );
			w.writeAttribute( CLASS , "ui-input-text ui-body-inherit ui-corner-all ui-shadow-inset" );
			
			w.startElement( INPUT );
				w.writeAttribute( ID , date.getClientId() + "_i" );
				w.writeAttribute( NAME , date.getClientId() );
				w.writeAttribute( VALUE , date.getDisplayValue() );
				
				w.writeAttribute( TYPE , "date" );
				
				if (date.isRequired())
					w.writeAttribute( "required" , true );
				if (date.isDisabled())
					w.writeAttribute( DISABLED , true );
				if (date.isReadOnly()){
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
