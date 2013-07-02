package com.h2i.imagesearch.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import org.apache.log4j.Logger;

public class CommonUtils {
	
	//private static final String SEARCH_PAGE = "yahoo.com/search";
	private static final String SORRY_PAGE = "Sorry, the page you requested was not found";
	
	private static Logger log = Logger.getLogger(CommonUtils.class.getName());


	
	/**
	 * @param checkURL
	 * @return
	 */
	public static boolean validateURL(String checkURL) {
		try {
			URL url = new URL(checkURL);
			URLConnection connection = url.openConnection();
			connection.connect();
			if (connection instanceof HttpURLConnection) {
				HttpURLConnection httpConnection = (HttpURLConnection) connection;
				httpConnection.setConnectTimeout(500);
				int code = httpConnection.getResponseCode();
				String pageContents = getPageContents(connection);

				if (((code == 200 || code == 301 || code == 302 || code == 403))) {
					if (!pageContents.contains(SORRY_PAGE)) {		//&& (!pageContents.contains(SEARCH_PAGE))) {
						return true;
					}else {
						log.error("Redirected to yahoo search page " +  checkURL);
						return false;
					}
				} else {
					log.error(checkURL + " returned - " + code);
					return false;
				}
			} else {
				log.error("error - not a http request!");
				return false;
			}
		} catch (Exception e) {
			log.error("Validation failed " + e.getMessage());
			return false;
		}
	}
	
	/**
	 * @param connection
	 * @return
	 * @throws IOException
	 */
	public static String getPageContents(URLConnection connection)	throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(
				connection.getInputStream()));

		StringBuilder response = new StringBuilder();
		String inputLine;

		while ((inputLine = in.readLine()) != null)
			response.append(inputLine);

		in.close();
		return response.toString();
	}
	
}