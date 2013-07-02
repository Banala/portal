package com.h2i.imagesearch;

import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FireFoxBrowserImpl implements Browser {

	private static final Logger log = Logger
			.getLogger(FireFoxBrowserImpl.class);
	private ResourceBundle properties = ResourceBundle.getBundle("config");

	private WebDriver driver;

	public void open() {
		this.driver = new FirefoxDriver();
		this.driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
	}

	public void loadUrl() {
		driver.get(properties.getString("imageUrl"));
	}

	public String search(String path) {
		WebElement camera = driver.findElement(By.id(properties
				.getString("cameraId")));
		camera.click();

		WebElement uploadImage = driver.findElement(By.xpath(properties
				.getString("imageUploadPath")));
		uploadImage.click();

		WebElement fileName = driver.findElement(By.id(properties
				.getString("fileNameId")));
		fileName.sendKeys(path);

		if (driver.findElements(By.id(properties.getString("resultStatsId")))
				.size() != 0) {
			WebElement resultStats = driver.findElement(By.id(properties
					.getString("resultStatsId")));
			log.info("Total results found: " + resultStats.getText());
			
			try{
				WebElement links = driver.findElement(By.xpath(properties.getString("searchResultId")));
				log.info("URLs found " + links.getText());
				
				return links.getText();
			}catch(NoSuchElementException nse) {
				return "No matches found";
		    }
			
		}else{
			return "No matches found";
		}
		

		// WebElement links =
		// driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[2]/div[2]/div/div[2]/div/ol/li[2]/div/h3/a"));
		// "/html/body/div[3]/div[2]/div/div[2]/div[2]/div/div[2]/div/ol/li[2]/div/h3/a"
		// "/html/body/div[5]/div[2]/div/div[6]/div/div[3]/div/div[2]/div/ol/div/li/div/h3/a"
		// System.out.println(links.getText());
		// System.out.println(links.getAttribute("href"));
	}

	public void close() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}

}
