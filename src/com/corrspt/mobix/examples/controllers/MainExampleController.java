package com.corrspt.mobix.examples.controllers;

import org.json.JSONException;
import org.json.JSONObject;

import netgest.bo.xwc.framework.XUIRequestContext;
import netgest.bo.xwc.framework.components.XUICommand;
import netgest.bo.xwc.framework.controllers.Scene;
import netgest.bo.xwc.xeo.beans.ViewerConfig;
import netgest.bo.xwc.xeo.controllers.MainController;

public class MainExampleController extends MainController {

	@Override
	public void init() {
		
	}

	@Override
	public boolean canClose(CloseOption option) {
		return true;
	}
	
	private ViewerConfig getViewerConfig(XUIRequestContext context) throws JSONException {
		return new ViewerConfig( new JSONObject( 
                (String)((XUICommand)context.getEvent().getSource()).getValue() 
        ));
	}
	
	private void checkClearAllScenes( XUICommand command ) {
		Object commandArgument = command.getCommandArgument();
		 if (commandArgument != null){
			 if("clear".equals( commandArgument.toString() )){
				 getStory().clearAllScenes();
			 }
		 }
	}
	
	public void openViewer() throws Exception{
		XUIRequestContext   oRequestContext = getRequestContext();
		ViewerConfig oViewerConfig = getViewerConfig( oRequestContext );
		XUICommand command = (XUICommand) oRequestContext.getEvent().getComponent();
		
		checkClearAllScenes( command );
		
		String sViewerName = oViewerConfig.getViewerName(); 
		Scene scene = new Scene( sViewerName );
		if ( !getStory().checkAndReplaceAllScenesInSameScopeWith(scene) ){
			System.out.println("SHIT");
		}
	}

	
}
