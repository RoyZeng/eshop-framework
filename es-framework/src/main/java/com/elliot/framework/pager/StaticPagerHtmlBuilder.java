package com.elliot.framework.pager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.elliot.framework.context.webcontext.ThreadContextHolder;
import com.elliot.framework.utils.RequestUtil;

/**
 * 伪静态列表分页
 * @author andy
 *
 */
public class StaticPagerHtmlBuilder {
	
	protected String url;
	private HttpServletRequest request ;
	private long pageNum;
	private long totalCount;
	private int pageSize;
	private long pageCount;
	private int showCount = 10;
	
	public StaticPagerHtmlBuilder(long _pageNum,long _totalCount,int _pageSize){
		pageNum= _pageNum;
		totalCount= _totalCount;
		pageSize= _pageSize ;
		request =  ThreadContextHolder.getHttpRequest();
	}
	
	public String buildPageHtml() {
		this.init();
		StringBuffer pageStr = new StringBuffer("");
		pageStr.append("<table align=\"right\" class=\"pager\"><tbody><tr>" );
		pageStr.append(this.getHeadString());
		pageStr.append(this.getBodyString());
		pageStr.append(this.getFooterString());
		pageStr.append("</tr></tbody></table>");
		return pageStr.toString();
	}

	
	

	
	
	/**
	 * 计算并初始化信息
	 *
	 */
	private  void init() {	
 
		pageSize = pageSize<1? 1 :pageSize;
		
		pageCount = totalCount / pageSize;
		pageCount = totalCount % pageSize > 0 ? pageCount + 1 : pageCount;
	
		pageNum = pageNum > pageCount ? pageCount : pageNum;
		pageNum = pageNum < 1 ? 1 : pageNum;
		
		 
		url =request.getServletPath();

	 
	}
	
	
	 /**
	  * 生成分页头字串
	  * @return
	  */
	protected String getHeadString() {

		StringBuffer headString = new StringBuffer("");
		headString.append("<td>");
	


		if (pageNum > 1) { //不是第一页，有上一页

			headString.append("<a title=\"上一页\"");
			headString.append(" onmouseout=\"this.className = 'prev'\" ");
			headString.append("  onmouseover=\"this.className = 'onprev'\" ");
			headString.append( " class=\"prev\" " );			
			headString.append(" href=\"");
			headString.append( getUrlStr (this.pageNum-1));
			headString.append("\" >上一页");
			headString.append("</a>\n");
 
		}else{//第一页
			headString.append("<span title=\"已经是第一页\" ");
			headString.append( " class=\"prev\"> 已经是第一页</span>" );
		}
		headString.append("</td>");
		return headString.toString();
	}

	
	
	
	/**
	 * 生成分页尾字串
	 * @return
	 */
	protected String getFooterString() {
		StringBuffer footerStr = new StringBuffer("");
		footerStr.append("<td style=\"padding-right: 20px;\">");
		if (pageNum < pageCount) {

		
			footerStr.append("<a title=\"下一页\" onmouseout=\"this.className = 'next'\" onmouseover=\"this.className = 'onnext'\" class=\"next\" ");
			footerStr.append(" href=\"");
			footerStr.append(  getUrlStr (this.pageNum+1) );
			footerStr.append("\"");
			footerStr.append("下一页</a>");

		}else{
			footerStr.append("<span title=\"已经是最后一页\" class=\"next\">已经是最后一页</span>");
		}
		footerStr.append("</td>\n");
		return footerStr.toString();
	}

	
	
	
	/**
	 * 生成分页主体字串
	 * @return
	 */
	protected String getBodyString() {

		StringBuffer pageStr = new StringBuffer();

		long start = pageNum - showCount / 2;
		start = start <= 1 ? 1 : start;

		long end = start + showCount;
		end = end > pageCount ? pageCount : end;
		pageStr.append("<td>");
		
		for (long i = start; i <= end; i++) {

			
			if (i != pageNum) {
				pageStr.append("<a");
				pageStr.append(" href=\"");
			
				pageStr.append(  getUrlStr ( i ) );
				pageStr.append("\">");
				
				pageStr.append(i);
				pageStr.append("</a>\n");
			} else {
				pageStr.append(" <strong class=\"pagecurrent\">");
				pageStr.append(i);
				pageStr.append("</strong> ");
			}
 
		}
		pageStr.append("</td>");
		return pageStr.toString();
	}

	
	
	/**
	 * 根据页数生成超级连接href的字串
	 * @param page
	 * @return
	 */
	protected   String getUrlStr(long page){
		HttpServletRequest httpRequest = ThreadContextHolder.getHttpRequest();
		String uri = RequestUtil.getRequestUrl(httpRequest);
		String pattern = "(.*)-(\\d+)-(\\d+).html";
		Pattern p = Pattern.compile(pattern, 2 | Pattern.COMMENTS);
		Matcher m = p.matcher(uri);
		String page_url="";
		if (m.find()) {		
			page_url=m.replaceAll("$1-$2");
		}		
 
		page_url=request.getContextPath() +page_url+"-"+page;
		return page_url+".html" ;
		
	}
	
	public static void main(String[] args){
		String url = "/articleList-1-2.html";		
		String pattern = "/(.*)-(\\d+)-(\\d+).html";
		Pattern p = Pattern.compile(pattern, 2 | Pattern.COMMENTS);
		Matcher m = p.matcher(url);
		if (m.find()) {		
//			catid = m.replaceAll("$2");
//			page = m.replaceAll("$3");
			
		}else{
			 
		}
		
		System.out.println(url);
		
	
		
//		if (m.find()) {
//			System.out.println( "1--" + m.replaceAll("page=$2"));
////			System.out.println( "2--" + m.replaceAll("$2"));
//		}
//		
	 
	}
	
	private  String findUrl(String url){
		String pattern = "(.*)(p(\\d))(.*).html";
		Pattern p = Pattern.compile(pattern, 2 | Pattern.DOTALL);
		Matcher m = p.matcher(url);
		if (m.find()) {
			String s = m.replaceAll("$1");
			return s+"-";
		}
		return null;
	}

 
	
}
