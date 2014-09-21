package com.corrspt.mobix.examples.controllers;

import netgest.bo.xwc.framework.controllers.Controller;
import netgest.bo.xwc.framework.controllers.Scene;

public class ActionButtonController extends Controller {
	
	public void navigate(){
		
		Scene scene = new Scene("actionButton2.xvw");
		renderScene(scene);
		
	}
	
	public void navigate2(){
		Scene scene = new Scene("actionButton3.xvw");
		renderScene(scene);
	}
	
	

	@Override
	public void init() { }

	@Override
	public boolean canClose(CloseOption option) {
		return true;
	}

}
