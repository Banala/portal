package com.h2i.imagesearch.search;

import com.h2i.imagesearch.Browser;
import com.h2i.imagesearch.factory.BrowserFactory;

public class SearchBuilder {
	private String searchText;
	private Browser browser;
	
	public String getSearchText(){
		return searchText;
	}
	
	public void setSearchText(String searchText){
		this.searchText = searchText;
	}
	public String searchBrowser(BrowserFactory browserFactory){
		browser = browserFactory.getBrowser();	
		browser.open();
		browser.loadUrl();
		
		return browser.search(getSearchText());

	}
	
	public void close(){
		browser.close();		
	}
}
