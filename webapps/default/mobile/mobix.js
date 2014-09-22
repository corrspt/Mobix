XVW.Wait = function( iWaitMode ) {
	$.mobile.loading('show');
};

XVW.NoWait = function() {
	$.mobile.loading('hide');
};

XVW.ErrorDialog = function( sTitle, sMessage ) {
	console.log(sTitle + " " + sMessage);
};

Ext = {};
Ext.onReady = function (){
	
}

ExtXeo = {};
ExtXeo.layoutMan = function (){};