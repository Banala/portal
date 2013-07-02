package com.h2i.imagesearch.search;

import java.io.File;

import org.junit.Test;

import com.h2i.imagesearch.factory.BrowserFactory;
import com.h2i.imagesearch.factory.FireFoxBrowserFactoryImpl;

public class TestSearchBuilder {
	
	@Test
	public void testSearch(){
		//String path = "C:\\Users\\umatg\\apache-tomcat-7.0.29\\webapps\\imageRecognition\\publishFiles" + File.separatorChar	+ "publishFiles" + File.separatorChar;
		
		//windows path
		//String path ="C:" + File.separatorChar + "tmp"  +File.separatorChar + "uma-resized.jpg";
		//String path ="C:" + File.separatorChar + "tmp"  +File.separatorChar + "car-tire.jpg";
		//String path ="C:" + File.separatorChar + "tmp"  +File.separatorChar + "Speaker.jpg";
		//String path ="C:" + File.separatorChar + "tmp"  +File.separatorChar + "tgu.jpg";
		String path ="C:" + File.separatorChar + "tmp"  +File.separatorChar + "tst.pdf";
		
		//linux path
		//String path ="/" + "tmp"  +"/" + "uma-resized.jpg";
		
		SearchBuilder sb = new SearchBuilder();
		BrowserFactory browserFactory = new FireFoxBrowserFactoryImpl();
		sb.setSearchText(path);
		//sb.setSearchText(destinationDir.getPath() + File.separatorChar + "car-tire.jpg");
		System.out.println(sb.searchBrowser(browserFactory));		
		sb.close();
	}

}
