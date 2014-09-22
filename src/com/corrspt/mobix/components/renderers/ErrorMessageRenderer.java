package com.corrspt.mobix.components.renderers;

import static netgest.bo.xwc.components.HTMLAttr.CLASS;
import static netgest.bo.xwc.components.HTMLAttr.ID;
import static netgest.bo.xwc.components.HTMLAttr.ONCLICK;
import static netgest.bo.xwc.components.HTMLAttr.STYLE;
import static netgest.bo.xwc.components.HTMLTag.A;
import static netgest.bo.xwc.components.HTMLTag.DIV;
import static netgest.bo.xwc.components.HTMLTag.H1;
import static netgest.bo.xwc.components.HTMLTag.P;

import java.io.IOException;
import java.util.Iterator;

import netgest.bo.xwc.framework.XUIMessage;
import netgest.bo.xwc.framework.XUIRenderer;
import netgest.bo.xwc.framework.XUIResponseWriter;
import netgest.bo.xwc.framework.components.XUIComponentBase;

public class ErrorMessageRenderer extends XUIRenderer{
	
	@Override
	public void encodeBegin(XUIComponentBase component) throws IOException {
	
		XUIResponseWriter w = getResponseWriter();
		w.startElement( DIV );
        w.writeAttribute( ID, component.getClientId() );
		
        if (!hasMessagesToRender()){
        	w.writeAttribute(STYLE, "display:none");
        } else {
        	
        	w.startElement(DIV);
        		w.writeAttribute("data-role", "popup");
        		w.writeAttribute(ID, component.getId() + "_err");
        		w.writeAttribute("data-overlay-theme", "b");
        		w.writeAttribute("data-theme", "b");
        		w.writeAttribute("data-dismissable", "false");
        		
        		Iterator<XUIMessage>  oItXuiMessages;
                
                w = getResponseWriter();
                oItXuiMessages = getContext().getMessages();
                
                while (oItXuiMessages.hasNext()){
                	
                	w.startElement(DIV);
        			w.writeAttribute("data-role", "header");
        			w.writeAttribute("data-theme", "a");
	                	XUIMessage currentMsg = oItXuiMessages.next(); 
	                	w.startElement(H1);
	    					w.write(currentMsg.getTitle());
	        			w.endElement(H1);
        			w.endElement(DIV);
                	
        			w.startElement(DIV);
        				w.writeAttribute("role", "main");
        				
        				w.startElement(P);
        					w.write(currentMsg.getMessage());
        				w.endElement(P);
        				
        				w.startElement(A);
        					w.writeAttribute(CLASS, "ui-btn ui-corner-all ui-shadow ui-btn-inline ui-btn-b");
        					String script = String.format("$(XVW.get('%s')).popup('close');", component.getId() + "_err");
        					w.writeAttribute(ONCLICK, script);
        					w.write("Ok");
        				w.endElement(A);
        				
        			w.endElement(DIV);
        			
        			
        			break;
                	
                }
        	w.endElement(DIV);
        	
        	String script = String.format(" var c = $(XVW.get('%s')); c.popup(); c.popup('open'); ", component.getId() + "_err");
            addScriptFooter("showErrorMessages", script);
        }
        
        w.endElement(DIV);
        
        
	}
	
	private boolean hasMessagesToRender(){
		return getContext().getMessages().hasNext();
	}
	
	
	

}
