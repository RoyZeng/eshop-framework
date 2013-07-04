package com.elliot.web.tag.html;

import com.elliot.web.tag.ListTaglibSupport;
import com.elliot.web.tag.html.support.GridBodyParam;
import com.elliot.web.tag.html.support.GridBodyProvider;

import javax.servlet.jsp.tagext.Tag;

public class GridBodyTaglib extends ListTaglibSupport {
	private GridBodyProvider gridBodyProvider;
	
	public GridBodyTaglib() {
		gridBodyProvider = new GridBodyProvider();
	}
	
	protected void loadProvider() {
		
		Tag tag = this.getParent();
		
		if(tag!=null){
			GridTaglib gridTaglib =(GridTaglib)tag;
			GridBodyParam bodyparm = new GridBodyParam();
			bodyparm.setFrom( gridTaglib.getFrom() );
			this.param =  bodyparm;
			
			this.tagProvider = this.gridBodyProvider;
		} else {
			this.print("body tag must be included in grid tag");
		}
	}
	
	
	protected String postStart() {
		return "<tr>";
	}

	
	protected String postEnd() {
		return "</tr>";
	}

	
	protected String postStartOnce() {
		return "<tbody>";
	}
	
	
	protected String postEndOnce() {
		return "</tbody>";
	}
}
