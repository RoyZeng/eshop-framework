package com.elliot.web.tag.html.support;

import java.util.ArrayList;

import java.util.List;
import javax.servlet.jsp.PageContext;
import com.elliot.framework.database.Page;
import com.elliot.web.tag.IListTaglibParam;
import com.elliot.web.tag.IListTaglibProvider;
import com.elliot.web.tag.html.model.Grid;

public class GridBodyProvider implements IListTaglibProvider {
	public List getData(IListTaglibParam _param, PageContext pageContext) {

		GridBodyParam param = (GridBodyParam) _param;
		String from = param.getFrom();

		Object obj = pageContext.getAttribute(from);
		if (obj == null){
			obj = pageContext.getRequest().getAttribute(from);
			if (obj == null){
				return new ArrayList();
			}
		}
		//	from	即可以是Page对象，也可以是Grid对象。
		Page page = null;
		List list = null;
		if(obj instanceof Page){
			page = (Page)obj;
			list  = (List) page.getResult();
		}
		else if(obj instanceof Grid){
			page = ((Grid)obj).getWebpage();
			list  = (List) page.getResult();
		}else if(obj instanceof List){
			list = (List)obj;
		}
		
		return list;
	}
}
