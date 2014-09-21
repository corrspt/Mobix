package com.corrspt.mobix.components;

import java.io.IOException;

import netgest.bo.xwc.framework.XUIBaseProperty;
import netgest.bo.xwc.framework.XUIRenderer;
import netgest.bo.xwc.framework.components.XUICommand;
import netgest.bo.xwc.framework.components.XUIComponentBase;


/**
 * 
 */
public class navTab extends XUIComponentBase {

	private XUIBaseProperty<String> text = 
		new XUIBaseProperty<String>("text", this, "" );
	
	private XUIBaseProperty<String> icon = 
			new XUIBaseProperty<String>("icon", this, "" );
	
	private XUIBaseProperty<String> action = 
			new XUIBaseProperty<String>("action", this, "" );
	
	private XUIBaseProperty<String> value = 
			new XUIBaseProperty<String>("value", this, "" );

	@Override
	public void initComponent() {
		super.initComponent();
		if (findComponent(getClientId() + "_cmd") == null){
			XUICommand cmd = new XUICommand();
			cmd.setActionExpression(createMethodBinding(getAction()));
			cmd.setValue(getValue());
			cmd.setId(getId() + "_cmd"); 
			getChildren().add(cmd);
		}
	}
	
	public XUICommand getCommand(){
		if (getChildCount() > 0){
			return (XUICommand) getChild(0);
		}
		return null;
	}

	public void setText( String text ) {
		this.text.setValue( text );
	}
	
	public String getText() {
		return this.text.getValue();
	}
	
	public String getIcon() {
		return this.icon.getValue();
	}
	
	public void setIcon( String text ) {
		this.icon.setValue( text );
	}
	
	public String getAction(){
		return this.action.getValue();
	}
	
	public void setAction(String action){
		this.action.setValue(action);
	}

	public String getValue(){
		return this.value.getValue();
	}
	
	public void setValue(String action){
		this.value.setValue(action);
	}
	
	@Override
	public void preRender() {
		
	}
	
	public static class XEOHTMLRenderer extends XUIRenderer {
			
		@Override
		public void encodeBegin(XUIComponentBase component) throws IOException {
										
		}
		
		@Override
		public void encodeEnd(XUIComponentBase component) throws IOException {
		}

		@Override
		public void decode(XUIComponentBase component) {
			super.decode(component);
		}						
	}
	
}
