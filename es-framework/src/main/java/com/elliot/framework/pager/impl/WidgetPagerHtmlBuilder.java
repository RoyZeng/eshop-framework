package com.elliot.framework.pager.impl;

import com.elliot.framework.pager.AbstractPageHtmlBuilder;

public class WidgetPagerHtmlBuilder extends AbstractPageHtmlBuilder {
	public WidgetPagerHtmlBuilder(long _pageNum, long _totalCount, int _pageSize) {
		super(_pageNum, _totalCount, _pageSize);
	}

	@Override
	protected String getUrlStr(long page) {
		
		return null;
	}

}
