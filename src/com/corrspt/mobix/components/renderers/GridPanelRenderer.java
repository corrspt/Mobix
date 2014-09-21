package com.corrspt.mobix.components.renderers;

import static netgest.bo.xwc.components.HTMLAttr.CLASS;
import static netgest.bo.xwc.components.HTMLAttr.ID;
import static netgest.bo.xwc.components.HTMLTag.DIV;
import static netgest.bo.xwc.components.HTMLTag.TABLE;
import static netgest.bo.xwc.components.HTMLTag.TBODY;
import static netgest.bo.xwc.components.HTMLTag.TD;
import static netgest.bo.xwc.components.HTMLTag.TH;
import static netgest.bo.xwc.components.HTMLTag.THEAD;
import static netgest.bo.xwc.components.HTMLTag.TR;

import java.io.IOException;
import java.util.Iterator;

import netgest.bo.xwc.components.classic.GridPanel;
import netgest.bo.xwc.components.connectors.DataRecordConnector;
import netgest.bo.xwc.components.model.Column;
import netgest.bo.xwc.framework.XUIRenderer;
import netgest.bo.xwc.framework.XUIResponseWriter;
import netgest.bo.xwc.framework.components.XUIComponentBase;

public class GridPanelRenderer extends XUIRenderer {
	
	@Override
	public void encodeBegin(XUIComponentBase component) throws IOException {
		XUIResponseWriter w = getResponseWriter();
		GridPanel grid = (GridPanel) component;
		
		w.startElement(DIV);
			w.writeAttribute(ID, grid.getClientId());
			
			w.startElement(TABLE);
				w.writeAttribute("data-role", "table");
				w.writeAttribute("data-mode", "columntoggle");
				w.writeAttribute(CLASS, "ui-responsive table-stroke ");
				w.writeAttribute(ID, grid.getId() + "_table");
				
				w.startElement(THEAD);
				w.startElement(TR);
				int i = 1;
				for( Column column : grid.getColumns() ) {
					w.startElement( TH );
					w.writeAttribute("data-priority", i);
					w.write(column.getLabel());
					w.endElement( TH );
					i++;
				}
				w.endElement(TR);
				w.endElement( THEAD );
				w.startElement(TBODY);
				
				Iterator<DataRecordConnector> resultIt = grid.getDataSource().iterator();
		        
		        while( resultIt.hasNext() ) {
					
					DataRecordConnector oRecordConnector = resultIt.next();
					
					w.startElement( TR );
					
					for( Column column : grid.getColumns() ) {
		    			w.startElement( TD );
		    			String displayValue = oRecordConnector.getAttribute(column.getDataField()).getDisplayValue();
		    			w.write( displayValue );
		    			w.endElement( TD );
					}
					w.endElement( TR );
				}
				
				w.endElement(TBODY);
		
			w.endElement(TABLE);
		w.endElement(DIV);
	}
	

	
	
	@Override
	public boolean getRendersChildren() {
		return true;
	}
	
	@Override
	public void encodeChildren(XUIComponentBase component) throws IOException { }

}
