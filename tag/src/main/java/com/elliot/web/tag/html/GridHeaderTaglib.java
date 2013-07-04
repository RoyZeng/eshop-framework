package com.elliot.web.tag.html;

import com.elliot.web.tag.HtmlTaglib;

public class GridHeaderTaglib extends HtmlTaglib {
	
	
	protected String postStart() {
		return "<thead><tr>";
	}
	
	
	protected String postEnd() {
		return "</tr></thead>";
	}
}
