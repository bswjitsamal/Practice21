package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class pageHome {
	
	private WebDriver driver;

	// Creating constructor
	public pageHome(WebDriver driver) {
		this.driver = driver;
	}
	
	@FindBy(how = How.XPATH, using = ".//button[contains(text(),'Manual Entry')]")
	public static WebElement manualEntryButton;
	
	@FindBy(how = How.XPATH, using = "//button[@title='Delete Entry']")
	public static WebElement buttonDeleteEntry;
	
	@FindBy(how = How.XPATH, using = ".//p[contains(text(),'Item Deleted')]")
	public static WebElement itemdeletedMessage;
	
	
	@FindBy(how = How.XPATH, using = ".//p[@class='text']/button[contains(text(),Undo)]")
	public static WebElement undoButton;
	
	@FindBy(how = How.XPATH, using = "//p[@class='text']")
	public static WebElement successMessage;
	

}
