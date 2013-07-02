package com.h2i.imagesearch;

public interface Browser {
	public void open();
	public void loadUrl();
	public String search(String path);
	public void close();
}
