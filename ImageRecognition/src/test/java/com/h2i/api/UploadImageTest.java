package com.h2i.api;

import static org.junit.Assert.assertTrue;

import java.io.UnsupportedEncodingException;

import org.junit.Test;

import com.sun.jersey.core.header.MediaTypes;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.spi.container.TestContainerFactory;
import com.sun.jersey.test.framework.spi.container.http.HTTPContainerFactory;

public class UploadImageTest extends JerseyTest {
	public static final String PACKAGE_NAME = "com.h2i.api.resource";

	public UploadImageTest() throws Exception {
		super(PACKAGE_NAME);
	}

	protected TestContainerFactory getTestContainerFactory() {
		return new HTTPContainerFactory();

	}

	@Test
	public void testUploadTestResponse() throws UnsupportedEncodingException {
		String response = webResource.path("/rest/UploadTest").get(String.class);

		System.out.println(response);

	}

	/**
	 * Test if a WADL document is available at the relative path
	 * "application.wadl".
	 */
	@Test
	public void testApplicationWadl() {
		String serviceWadl = webResource.path("application.wadl")
				.accept(MediaTypes.WADL).get(String.class);

		assertTrue(serviceWadl.length() > 0);
	}

}
