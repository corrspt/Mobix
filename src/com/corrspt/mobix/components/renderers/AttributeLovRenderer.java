package com.corrspt.mobix.components.renderers;

import static netgest.bo.xwc.components.HTMLAttr.CLASS;
import static netgest.bo.xwc.components.HTMLAttr.DISABLED;
import static netgest.bo.xwc.components.HTMLAttr.ID;
import static netgest.bo.xwc.components.HTMLAttr.NAME;
import static netgest.bo.xwc.components.HTMLAttr.SELECTED;
import static netgest.bo.xwc.components.HTMLAttr.STYLE;
import static netgest.bo.xwc.components.HTMLAttr.VALUE;
import static netgest.bo.xwc.components.HTMLTag.DIV;
import static netgest.bo.xwc.components.HTMLTag.OPTION;
import static netgest.bo.xwc.components.HTMLTag.SELECT;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import netgest.bo.xwc.components.classic.AttributeBase;
import netgest.bo.xwc.components.classic.AttributeLov;
import netgest.bo.xwc.framework.XUIRenderer;
import netgest.bo.xwc.framework.XUIResponseWriter;
import netgest.bo.xwc.framework.components.XUIComponentBase;

public class AttributeLovRenderer extends XUIRenderer {
	
	@Override
	public void encodeBegin(XUIComponentBase comp) throws IOException {
		XUIResponseWriter w  = getResponseWriter();
		AttributeLov lov = (AttributeLov) comp;
		Map<Object,String> lovMap = lov.getLovMap();
		Iterator<Entry<Object,String>> it = lovMap.entrySet().iterator();
		Object value = lov.getValue();
		String componentValue = "";
		if (value != null)
			componentValue = value.toString();
		
		w.startElement( DIV );
			w.writeAttribute( ID , lov.getClientId() );
			w.writeAttribute( CLASS , "ui-input-text ui-body-inherit ui-corner-all ui-shadow-inset" );
			
			w.startElement( SELECT );
				w.writeAttribute( ID , lov.getClientId() + "_i" );
				w.writeAttribute( NAME, lov.getClientId() );
				w.writeAttribute( STYLE , "width:100%" );
			
				if (lov.isRequired())
					w.writeAttribute( "required" , true );
			
				if (lov.isDisabled()) {
					w.writeAttribute( DISABLED , true );
				}
				
				if (lov.isReadOnly()) {
					w.writeAttribute( DISABLED , true );
				}
				
				writeRemainingOptions( w , it , componentValue );
			
			w.endElement( SELECT );
			

			
		w.endElement( DIV );
	}
	
	private void writeRemainingOptions(XUIResponseWriter w,
			Iterator< Entry< Object , String >> it, String componentValue)
			throws IOException {
		while (it.hasNext()){
			
			Entry<Object,String> entry = it.next();
			w.startElement( OPTION );
				String currentValue = entry.getKey().toString();
				w.writeAttribute( VALUE , currentValue );
				if (currentValue.equals( componentValue )){
					w.writeAttribute( SELECTED	, true );
				}
				w.write( entry.getValue() );
			w.endElement( OPTION );
		}
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
