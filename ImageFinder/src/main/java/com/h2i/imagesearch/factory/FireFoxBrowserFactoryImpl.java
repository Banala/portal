package com.h2i.imagesearch.factory;

import com.h2i.imagesearch.Browser;
import com.h2i.imagesearch.FireFoxBrowserImpl;

public class FireFoxBrowserFactoryImpl implements BrowserFactory {
	public Browser getBrowser(){
		return new FireFoxBrowserImpl();
	}
}
