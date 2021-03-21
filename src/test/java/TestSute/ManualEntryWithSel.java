package TestSute;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import Pages.pageHome;
import Pages.pageManualEntry;

public class ManualEntryWithSel {

	@Test
	public void zoteroBib() {

		System.setProperty("webdriver.gecko.driver",
				"C:\\Users\\33875\\Downloads\\geckodriver-v0.29.0-win64\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		JavascriptExecutor js = (JavascriptExecutor) driver;

		PageFactory.initElements(driver, pageHome.class);
		PageFactory.initElements(driver, pageManualEntry.class);

		WebDriverWait wait = new WebDriverWait(driver, 20);
		try {

			//navigating to the webpage
			driver.get("https://zbib.org/");

			//Entering a new record
			wait.until(ExpectedConditions.visibilityOf(pageHome.manualEntryButton));
			pageHome.manualEntryButton.click();

			wait.until(ExpectedConditions.visibilityOf(pageManualEntry.itemTypeDropDown));
			pageManualEntry.itemTypeDropDown.click();

			wait.until(ExpectedConditions.visibilityOf(pageManualEntry.titleTextBox));
			pageManualEntry.titleTextBox.sendKeys("Hello");

			pageManualEntry.buttonDone.click();
			
			//Verifying success message
			String expectedSuccessMessage = "Your first citation has been added. Citations are stored locally in your browser.Read More";
			String actualSuccessMessage = pageHome.successMessage.getText();

			if (expectedSuccessMessage.contains(actualSuccessMessage)) {
				System.out.println("Item added");
			} else {
				System.out.println("Not added");
			}
			
			js.executeScript("window.scrollBy(0,500)");
			Actions actions = new Actions(driver);
			actions.moveToElement(pageHome.buttonDeleteEntry).perform();
			//wait.until(ExpectedConditions.visibilityOf(pageHome.buttonDeleteEntry));
			pageHome.buttonDeleteEntry.click();

			String expectedDeleteMessage = "Item DeletedUndo";
			String actualDeleteMessage = pageHome.itemdeletedMessage.getText();
			
			if (expectedDeleteMessage.contains(actualDeleteMessage)) {
				System.out.println("Item deleted");
			} else {
				System.out.println("Not deleted");
			}
			
			wait.until(ExpectedConditions.visibilityOf(pageHome.undoButton));
			pageHome.undoButton.click();

		} finally {
			driver.quit();
		}
	}

}
