package com.corrspt.mobix.theme;

import static netgest.bo.xwc.components.HTMLTag.DIV;

import java.io.IOException;

import com.lowagie.text.html.HtmlTags;

import netgest.bo.xwc.framework.XUIRequestContext;
import netgest.bo.xwc.framework.XUIResponseWriter;
import netgest.bo.xwc.framework.XUIScriptContext;
import netgest.bo.xwc.framework.XUIStyleContext;
import netgest.bo.xwc.framework.XUITheme;
import netgest.bo.xwc.framework.components.XUIViewRoot;

public class MobixTheme implements XUITheme {

	@Override
	public String getResourceBaseUri() {
		return "mobile/";
	}

	@Override
	public void addStyle(XUIStyleContext styleContext) {
		styleContext.addInclude(XUIStyleContext.POSITION_HEADER, "jqueryMobileCss",
				composeUrl("jquery.mobile-1.4.4.min.css"));
		styleContext.addInclude(XUIStyleContext.POSITION_HEADER, "jqueryMobileIcons",
				composeUrl("jquery.mobile.icons-1.4.4.min.css"));
	}

	@Override
	public void addScripts(XUIScriptContext scriptContext) {
		scriptContext.addInclude(XUIStyleContext.POSITION_HEADER, "xwc-core",
				"xwc/js/xwc-core.js");
		scriptContext.addInclude(XUIStyleContext.POSITION_HEADER, "xwc-messages", 
				"xwc/js/localization/xwc-messages_en.js");
		scriptContext.addInclude(XUIStyleContext.POSITION_HEADER, "jqueryJS",
				composeUrl("jquery-2.1.1.min.js"));
		scriptContext.addInclude(XUIStyleContext.POSITION_HEADER, "jqueryMobileJS",
				composeUrl("jquery.mobile-1.4.4.min.js"));
		scriptContext.addInclude(XUIStyleContext.POSITION_HEADER, "mobix",
				composeUrl("mobix.js"));
	}

	@Override
	public String getBodyStyle() {
		return "";
	}

	@Override
	public String getHtmlStyle() {
		return "";
	}

	@Override
	public String getDocType() {
		return "<!DOCTYPE html>";
	}

	@Override
	public void writeHeader(XUIResponseWriter writer) throws IOException {
		writer.startElement( HtmlTags.META);
		writer.writeAttribute("name", "viewport");
		writer.writeAttribute("content", "width=device-width, initial-scale=1");
		writer.endElement( HtmlTags.META );

	}

	@Override
	public void writePostBodyContent(XUIRequestContext context,
			XUIResponseWriter writer, XUIViewRoot viewRoot) throws IOException {
		writer.startElement(DIV);
		writer.writeAttribute("id", viewRoot.getClientId());
	}

	@Override
	public void writePreFooterContent(XUIRequestContext context,
			XUIResponseWriter writer, XUIViewRoot viewRoot) throws IOException {
		writer.endElement(DIV);
	}
	
	public final String composeUrl(String relUri) {
		return getResourceBaseUri() + relUri;
	}

}
