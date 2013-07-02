package com.h2i.api.resource;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.h2i.imagesearch.factory.BrowserFactory;
import com.h2i.imagesearch.factory.FireFoxBrowserFactoryImpl;
import com.h2i.imagesearch.search.SearchBuilder;

@Path("/identifyImage")
public class ImageSearch {

	/**
	 * @param request
	 * @return
	 */
	@POST
	public String imageSearch(@Context HttpServletRequest request) {

		String response = "none";
		try {
			if (ServletFileUpload.isMultipartContent(request)) {
				String imgPath = uploadFile(request);
				response = search(imgPath);
			}

		} catch (FileUploadException ex) {
			response += "Error encountered while parsing the request " + ex;
		} catch (Exception ex) {
			response += "Error encountered while uploading file " + ex;
		}
		
		return response;
	}

	/**
	 * @param request
	 * @return
	 * @throws FileUploadException
	 * @throws Exception
	 */
	private String uploadFile(HttpServletRequest request)
			throws FileUploadException, Exception {
		String uploadedFName = "";

		// Create a factory for disk-based file items
		DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();

		String path = File.separatorChar + "tmp" + File.separatorChar;
		File destinationDir = new File(path);

		// Create a new file upload handler
		ServletFileUpload uploadHandler = new ServletFileUpload(fileItemFactory);

		/*
		 * Parse the request
		 */
		List items = uploadHandler.parseRequest(request);
		Iterator itr = items.iterator();

		while (itr.hasNext()) {
			FileItem item = (FileItem) itr.next();
			/*
			 * Handle Form Fields.
			 */
			if (!item.isFormField()) {
				/*
				 * Write file to the ultimate location.
				 */
				File file = new File(destinationDir, item.getName());
				item.write(file);
				uploadedFName = file.toString();
			}
		}

		return uploadedFName;
	}

	/**
	 * @param path
	 * @return
	 */
	private String search(String path) {
		String response;
		SearchBuilder sb = new SearchBuilder();
		BrowserFactory browserFactory = new FireFoxBrowserFactoryImpl();
		sb.setSearchText("c:" + path);

		response = sb.searchBrowser(browserFactory);
		sb.close();
		return response;
	}
}