package com.elliot.web.tag;

import java.util.List;

import javax.servlet.jsp.PageContext;

public interface IListTaglibProvider {
	public List getData(IListTaglibParam param, PageContext pageContext);
}
