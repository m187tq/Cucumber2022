package actiondriver;

import actioninterface.ActionInterface;
import helper.logger.LoggerHelper;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import pages.BasePage;
import utilities.datarepo;
import utils.globalVars;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class Action implements ActionInterface {
	public static Logger log = LoggerHelper.getLogger(Action.class);
	@Override
	public void scrollByVisibilityOfElement(WebDriver driver, WebElement ele) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", ele);

	}

	@Override
	public void click(WebDriver driver, WebElement ele) {
		Actions act = new Actions(driver);
		act.moveToElement(ele).click().build().perform();
		System.out.println("click on \"" + ele.getText() + "\"");
		log.info("click on \"" +  ele.getText() + "\"");
	}

	@Override
	public boolean findElement(WebDriver driver, WebElement ele) {
		boolean flag = false;
		try {
			ele.isDisplayed();
			flag = true;
		} catch (Exception e) {
			System.out.println("Location not found: " + ele.getText());
			log.info("Location not found: " + ele.getText());
			flag = false;
		} finally {
			if (flag) {
				System.out.println("Successfully Found element: " + ele.getText());
				log.info("Successfully Found element: " + ele.getText());

			} else {
				System.out.println("Unable to locate element..." + ele.getText());
				log.info("Unable to locate element..." + ele.getText());
			}
		}
		return flag;
	}

	@Override
	public boolean isDisplayed(WebDriver driver, WebElement ele) {
		boolean flag = false;
		flag = findElement(driver, ele);
		if (flag) {
			flag = ele.isDisplayed();
			if (flag) {
				System.out.println("The element is Displayed...: " + ele.getText());
				log.info("The element is Displayed...: " + ele.getText());
			} else {
				log.info("The element is not Displayed...: " + ele.getText());
				System.out.println("The element is not Displayed...: " + ele.getText());
			}
		} else {
			log.info("Not displayed...: " + ele.getText());
			System.out.println("Not displayed...: " + ele.getText());
		}
		return flag;
	}

	@Override
	public boolean isSelected(WebDriver driver, WebElement ele) {
		boolean flag = false;
		flag = findElement(driver, ele);
		if (flag) {
			flag = ele.isSelected();
			if (flag) {
				log.info("The element is Selected...: " + ele.getText());
			} else {
				System.out.println("The element is not Selected...: " + ele.getText());
			}
		} else {
			System.out.println("Not selected...: " + ele.getText());
		}
		return flag;
	}

	@Override
	public boolean isEnabled(WebDriver driver, WebElement ele) {
		boolean flag = false;
		flag = findElement(driver, ele);
		if (flag) {
			flag = ele.isEnabled();
			if (flag) {
				log.info("The element is Enabled...: " + ele.getText());
				System.out.println("The element is Enabled...: " + ele.getText());
			} else {
				log.info("The element is not Enabled...: " + ele.getText());
				System.out.println("The element is not Enabled...: " + ele.getText());

			}
		} else {
			System.out.println("Not Enabled...: " + ele.getText());
		}
		return flag;
	}

	@Override
	public boolean type(WebElement ele, String text) {
		boolean flag = false;
		try {
			flag = ele.isDisplayed();
			ele.clear();
			ele.sendKeys(text);
			log.info("Entered text :"+text);
			System.out.println("Entered text :"+text);
			flag = true;
		} catch (Exception e) {
			log.info("Location Not found...: " + text);
			System.out.println("Location Not found...: " + text);
			flag = false;
		} finally {
			if (flag) {
				log.info("Successfully entered value...: " + text);
				System.out.println("Successfully entered value...: " + text);

			} else {
				log.info("Unable to enter value...: " + text);
				System.out.println("Unable to enter value...: " + text);

			}

		}
		return flag;
	}

	@Override
	public boolean selectBySendkeys(String value,WebElement ele) {
		boolean flag = false;
		try {
			ele.sendKeys(value);
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (flag) {
				log.info("Select value from the DropDown");
			} else {
				System.out.println("Not Selected value from the DropDown");
				// throw new ElementNotFoundException("", "", "")
			}
		}
	}

	@Override
	public boolean selectByIndex(WebElement element, int index) {
		boolean flag = false;
		try {
			Select s = new Select(element);
			s.selectByIndex(index);
			flag = true;
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			if (flag) {
				log.info("Option selected by Index");
			} else {
				System.out.println("Option not selected by Index");
			}
		}
	}

	@Override
	public boolean selectByValue(WebElement element,String value) {
		boolean flag = false;
		try {
			Select s = new Select(element);
			s.selectByValue(value);
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (flag) {
				log.info("Option selected by Value");
			} else {
				System.out.println("Option not selected by Value");
			}
		}
	}

	@Override
	public boolean selectByVisibleText(String visibletext, WebElement ele) {
		boolean flag = false;
		try {
			Select s = new Select(ele);
			s.selectByVisibleText(visibletext);
			flag = true;
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			if (flag) {
				log.info("Option selected by VisibleText");
			} else {
				System.out.println("Option not selected by VisibleText");
			}
		}
	}

	@Override
	public boolean mouseHoverByJavaScript(WebElement ele) {
		boolean flag = false;
		try {
			WebElement mo = ele;
			String javaScript = "var evObj = document.createEvent('MouseEvents');"
					+ "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);"
					+ "arguments[0].dispatchEvent(evObj);";
			JavascriptExecutor js = (JavascriptExecutor) BasePage.getDriver();
			js.executeScript(javaScript, mo);
			flag = true;
			return true;
		}

		catch (Exception e) {

			return false;
		} finally {
			if (flag) {
				log.info("MouseOver Action is performed: " +ele.getText());
				System.out.println("MouseOver Action is performed: " +ele.getText());
			} else {
				log.info("MouseOver Action is not performed: " +ele.getText());
				System.out.println("MouseOver Action is not performed: " +ele.getText());
			}
		}
	}

	@Override
	public boolean JSClick(WebDriver driver, WebElement ele) {
		boolean flag = false;
		try {
			// WebElement element = driver.findElement(locator);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", ele);
			// driver.executeAsyncScript("arguments[0].click();", element);

			flag = true;

		}

		catch (Exception e) {
			throw e;

		} finally {
			if (flag) {
				log.info("Click Action is performed: " +ele.getText());
				System.out.println("Click Action is performed: " +ele.getText());
			} else if (!flag) {
				log.info("Click Action is not performed: " +ele.getText());
				System.out.println("Click Action is not performed: " +ele.getText());
			}
		}
		return flag;
	}

	@Override
	public boolean switchToFrameByIndex(WebDriver driver,int index) {
		boolean flag = false;
		try {
			new WebDriverWait(driver, Duration.ofSeconds(globalVars.getDefaultExplicitTimeout())).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//iframe")));
			driver.switchTo().frame(index);
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (flag) {
				log.info("Frame with index \"" + index + "\" is selected");
				System.out.println("Frame with index \"" + index + "\" is selected");
			} else {
				System.out.println("Frame with index \"" + index + "\" is not selected");
				log.info("Frame with index \"" + index + "\" is not selected");
			}
		}
	}

	/**
	 * This method switch the to frame using frame ID.
	 * 
	 * @param idValue : Frame ID wish to switch
	 * 
	 */
	@Override
	public boolean switchToFrameById(WebDriver driver,String idValue) {
		boolean flag = false;
		try {
			driver.switchTo().frame(idValue);
			flag = true;
			return true;
		} catch (Exception e) {

			e.printStackTrace();
			return false;
		} finally {
			if (flag) {
				log.info("Frame with Id \"" + idValue + "\" is selected");
				System.out.println("Frame with Id \"" + idValue + "\" is selected");
			} else {
				log.info("Frame with Id \"" + idValue + "\" is not selected");
				System.out.println("Frame with Id \"" + idValue + "\" is not selected");
			}
		}
	}

	/**
	 * This method switch the to frame using frame Name.
	 * 
	 * @param nameValue : Frame Name wish to switch
	 * 
	 */
	@Override
	public boolean switchToFrameByName(WebDriver driver,String nameValue) {
		boolean flag = false;
		try {
			driver.switchTo().frame(nameValue);
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (flag) {
				log.info("Frame with Name \"" + nameValue + "\" is selected");
				System.out.println("Frame with Name \"" + nameValue + "\" is selected");
			} else if (!flag) {
				log.info("Frame with Name \"" + nameValue + "\" is not selected");
				System.out.println("Frame with Name \"" + nameValue + "\" is not selected");
			}
		}
	}

	@Override
	public boolean switchToDefaultFrame(WebDriver driver) {
		boolean flag = false;
		try {
			driver.switchTo().defaultContent();
			flag = true;
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (flag) {
				// SuccessReport("SelectFrame ","Frame with Name is selected");
			} else if (!flag) {
				// failureReport("SelectFrame ","The Frame is not selected");
			}
		}
	}

	@Override
	public void mouseOverElement(WebDriver driver,WebElement element) {
		boolean flag = false;
		try {
			new Actions(driver).moveToElement(element).build().perform();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (flag) {
				log.info(" MouserOver Action is performed on: " +element.getText());
				System.out.println(" MouserOver Action is performed on: " +element.getText());
			} else {
				log.info("MouseOver action is not performed on");
				System.out.println("MouseOver action is not performed on");
			}
		}
	}

	@Override
	public boolean moveToElement(WebDriver driver, WebElement ele) {
		boolean flag = false;
		try {
			// WebElement element = driver.findElement(locator);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].scrollIntoView(true);", ele);
			Actions actions = new Actions(driver);
			// actions.moveToElement(driver.findElement(locator)).build().perform();
			actions.moveToElement(ele).build().perform();
			flag = true;
			System.out.println(" Moving to the element & Action is performed on: " +ele.getText());
			log.info(" Moving to the element & Action is performed on: " +ele.getText());
		} catch (Exception e) {
			System.out.println(" Action Not performed on: " +ele.getText());
			log.info(" Action Not performed on: " +ele.getText());
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean mouseover(WebDriver driver, WebElement ele) {
		boolean flag = false;
		try {
			new Actions(driver).moveToElement(ele).build().perform();
			flag = true;
			log.info(" MouserOver Action is performed on: " +ele.getText());
			System.out.println(" MouserOver Action is performed on: " +ele.getText());
			return true;
		} catch (Exception e) {
			log.info("MouseOver action is not performed on: " +ele.getText());
			System.out.println("MouseOver action is not performed on: " +ele.getText());
			return false;
		} finally {
			/*
			 * if (flag) {
			 * SuccessReport("MouseOver ","MouserOver Action is performed on \""+locatorName
			 * +"\""); } else {
			 * failureReport("MouseOver","MouseOver action is not performed on \""
			 * +locatorName+"\""); }
			 */
		}
	}
	@Override
	public boolean draggable(WebDriver driver,WebElement source, int x, int y) {
		boolean flag = false;
		try {
			new Actions(driver).dragAndDropBy(source, x, y).build().perform();
			Thread.sleep(5000);
			flag = true;
			return true;

		} catch (Exception e) {
		
			return false;
			
		} finally {
			if (flag) {
				System.out.println("Draggable Action is performed on \""+source+"\"");			
			} else if(!flag) {
				System.out.println("Draggable action is not performed on \""+source+"\"");
			}
		}
	}
	@Override
	public boolean draganddrop(WebDriver driver,WebElement source, WebElement target) {
		boolean flag = false;
		try {
			new Actions(driver).dragAndDrop(source, target).perform();
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (flag) {
				System.out.println("DragAndDrop Action is performed");
			} else if(!flag) {
				System.out.println("DragAndDrop Action is not performed");
			}
		}
	}
	
	@Override
	public boolean slider(WebDriver driver,WebElement ele, int x, int y) {
		boolean flag = false;
		try {
			// new Actions(driver).dragAndDropBy(dragitem, 400, 1).build()
			// .perform();
			new Actions(driver).dragAndDropBy(ele, x, y).build().perform();// 150,0
			Thread.sleep(5000);
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (flag) {
				System.out.println("Slider Action is performed");
			} else {
				System.out.println("Slider Action is not performed");
			}
		}
	}
	
	@Override
	public boolean rightclick(WebDriver driver,WebElement ele) {
		boolean flag = false;
		try {
			Actions clicker = new Actions(driver);
			clicker.contextClick(ele).perform();
			flag = true;
			return true;
			// driver.findElement(by1).sendKeys(Keys.DOWN);
		} catch (Exception e) {

			return false;
		} finally {
			if (flag) {
				log.info("RightClick Action is performed");
				System.out.println("RightClick Action is performed");
			} else {
				log.info("RightClick Action is not performed");
				System.out.println("RightClick Action is performed");
			}
		}
	}
	
	@Override
	public boolean switchWindowByTitle(WebDriver driver,String windowTitle, int count) {
		boolean flag = false;
		try {
			Set<String> windowList = driver.getWindowHandles();

			String[] array = windowList.toArray(new String[0]);

			driver.switchTo().window(array[count-1]);

			if (driver.getTitle().contains(windowTitle)){
				flag = true;
			}else{
				flag = false;
			}
			return flag;
		} catch (Exception e) {
			//flag = true;
			return false;
		} finally {
			if (flag) {
				log.info("Navigated to the window with title");
			} else {
				log.info("The Window with title is not Selected");
			}
		}
	}
	@Override
	public boolean switchToNewWindow(WebDriver driver) {
		boolean flag = false;
		try {

			Set<String> s=driver.getWindowHandles();
			Object popup[]=s.toArray();
			driver.switchTo().window(popup[1].toString());
			flag = true;
			return flag;
		} catch (Exception e) {
			flag = false;
			return flag;
		} finally {
			if (flag) {
				log.info("Window is Navigated with title");
			} else {
				System.out.println("The Window with title: is not Selected");
			}
		}
	}
	@Override
	public boolean switchToOldWindow(WebDriver driver) {
		boolean flag = false;
		try {

			Set<String> s=driver.getWindowHandles();
			Object popup[]=s.toArray();
			driver.switchTo().window(popup[0].toString());
			flag = true;
			return flag;
		} catch (Exception e) {
			flag = false;
			return flag;
		} finally {
			if (flag) {
				System.out.println("Focus navigated to the window with title");			
			} else {
				System.out.println("The Window with title: is not Selected");
			}
		}
	}
	@Override
	public int getColumncount(WebElement row) {
		List<WebElement> columns = row.findElements(By.tagName("td"));
		int a = columns.size();
		System.out.println(columns.size());
		for (WebElement column : columns) {
			System.out.print(column.getText());
			System.out.print("|");
		}
		return a;
	}
	
	@Override
	public int getRowCount(WebElement table) {
		List<WebElement> rows = table.findElements(By.tagName("tr"));
		int a = rows.size() - 1;
		return a;
	}
	
	
	/**
	 * Verify alert present or not
	 * 
	 * @return: Boolean (True: If alert preset, False: If no alert)
	 * 
	 */
	@Override
	public boolean Alert(WebDriver driver) {
		boolean presentFlag = false;
		Alert alert = null;

		try {
			// Check the presence of alert
			alert = driver.switchTo().alert();
			// if present consume the alert
			alert.accept();
			presentFlag = true;
		} catch (NoAlertPresentException ex) {
			// Alert present; set the flag

			// Alert not present
			ex.printStackTrace();
		} finally {
			if (!presentFlag) {
				log.info("The Alert is handled successfully");
				System.out.println("The Alert is handled successfully");
			} else{
				log.info("There was no alert to handle");
				System.out.println("There was no alert to handle");
			}
		}

		return presentFlag;
	}
	@Override
	public boolean launchUrl(WebDriver driver,String url) {
		boolean flag = false;
		try {
			driver.navigate().to(url);
			flag = true;
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			if (flag) {
				log.info("Successfully launched \""+url+"\"");
				System.out.println("Successfully launched \""+url+"\"");
			} else {
				log.info("Failed to launch \""+url+"\"");
				System.out.println("Failed to launch \""+url+"\"");
			}
		}
	}
	
	@Override
	public boolean isAlertPresent(WebDriver driver) 
	{ 
		try 
		{ 
			driver.switchTo().alert(); 
			return true; 
		}   // try 
		catch (NoAlertPresentException Ex) 
		{ 
			return false; 
		}   // catch 
	}

	@Override
	public String getCurrentURL(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	@Override
	public String getTitle(WebDriver driver) {
		boolean flag = false;

		String text = driver.getTitle();
		if (flag) {
			log.info("Title of the page is: \""+text+"\"");
			System.out.println("Title of the page is: \""+text+"\"");
		}
		return text;
	}

	@Override
	public boolean getText(WebDriver driver, WebElement ele) {
		boolean flag = false;
		try {
			ele.getText();
			flag = true;
		} catch (Exception e) {
			System.out.println("Got element text : " + ele.getText());
			log.info("Got element text: " + ele.getText());
			flag = false;
		} finally {
			if (flag) {
				System.out.println("Successfully Found element text: " + ele.getText());
				log.info("Successfully Found element text: " + ele.getText());

			} else {
				System.out.println("Unable to get element text..." + ele.getText());
				log.info("Unable to get element text..." + ele.getText());
			}
		}
		return flag;
	}

	@Override
	public boolean getText(WebDriver driver, WebElement ele, String text) {
		boolean flag = false;
		try {
			ele.getText();
			flag = true;
		} catch (Exception e) {
			System.out.println("Got element text : " + ele.getText());
			log.info("Got element text: " + ele.getText());
			flag = false;
		} finally {
			if (flag) {
				System.out.println("Successfully Found element text: " + ele.getText());
				log.info("Successfully Found element text: " + ele.getText());

			} else {
				System.out.println("Unable to get element text..." + ele.getText());
				log.info("Unable to get element text..." + ele.getText());
			}
		}
		return flag;
	}

	@Override
	public boolean click1(WebElement locator, String locatorName) {
		boolean flag = false;
		try {
			locator.click();
			flag = true;
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			if (flag) {
				log.info("Able to click on \""+locatorName+"\"");
				System.out.println("Able to click on \""+locatorName+"\"");
			} else {
				log.info("Click Unable to click on \""+locatorName+"\"");
				System.out.println("Click Unable to click on \""+locatorName+"\"");
			}
		}

	}
	
	@Override
	public void fluentWait(WebDriver driver,WebElement element, int timeOut) {
	    Wait<WebDriver> wait = null;
	    try {
	        wait = new FluentWait<WebDriver>((WebDriver) driver)
	        		.withTimeout(Duration.ofSeconds(20))
	        	    .pollingEvery(Duration.ofSeconds(2))
	        	    .ignoring(Exception.class);
	        wait.until(ExpectedConditions.visibilityOf(element));
	        element.click();
			log.info("FluentWait Waiting for 20 SECONDS.....");
			System.out.println("FluentWait Waiting for 20 SECONDS.....");
	    }catch(Exception e) {
			log.info("FluentWait FAILED to wait.....");
			System.out.println("FluentWait FAILED to wait.....");
	    }
	}
	@Override
	public void implicitWait(WebDriver driver, int timeOut) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(datarepo.getThreeSeconds()));
		log.info("implicitWait Waiting for 3 SECONDS.....");
		System.out.println("implicitWait Waiting for 3 SECONDS.....");
	}
	@Override
	public void explicitWait(WebDriver driver, WebElement element, int timeOut ) {
		WebDriverWait wait = new WebDriverWait(BasePage.getDriver(),Duration.ofSeconds(globalVars.getExplicitWait()));
		wait.until(ExpectedConditions.visibilityOf(element));
		log.info("explicitWait Waiting for 15 SECONDS.....");
		System.out.println("explicitWait Waiting for 15 SECONDS.....");
	}

	@Override
	public void pageLoadTimeOut(WebDriver driver, int timeOut) {

	}

	@Override
	public String screenShot(WebDriver driver, String filename) {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "\\ScreenShots\\" + filename + "_" + dateName + ".png";

		try {
			FileUtils.copyFile(source, new File(destination));
		} catch (Exception e) {
			e.getMessage();
		}
		// This new path for jenkins
		String newImageString = "http://localhost:8082/job/MyStoreProject/ws/MyStoreProject/ScreenShots/" + filename + "_"
				+ dateName + ".png";
		return newImageString;
	}
	@Override
	public String getCurrentTime() {
		String currentDate = new SimpleDateFormat("yyyy-MM-dd-hhmmss").format(new Date());
		return currentDate;
	}

}
