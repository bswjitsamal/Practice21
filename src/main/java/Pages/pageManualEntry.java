package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class pageManualEntry {
	
	private WebDriver driver;

	// Creating constructor
	public pageManualEntry(WebDriver driver) {
		this.driver = driver;
	}
	
	@FindBy(how = How.XPATH, using = ".//div[@class='Select-value']/span[contains(text(),'Book')]")
	public static WebElement itemTypeDropDown;
	
	
	@FindBy(how = How.XPATH, using = ".//input[@id='title']")
	public static WebElement titleTextBox;
	
	@FindBy(how = How.XPATH, using = ".//button[contains(text(),'Done')]")
	public static WebElement buttonDone;	
	
	@FindBy(how = How.XPATH, using = ".//div[@class='Select-value']/span[contains(text(),'Author')]")
	public static WebElement authorDropDown;
	
	
	@FindBy(how = How.XPATH, using = ".//input[@placeholder='last name']")
	public static WebElement lastNameTextBox;
	
	@FindBy(how = How.XPATH, using = ".//input[@placeholder='last name']")
	public static WebElement firstNameTextBox;
	
	
	@FindBy(how = How.XPATH, using = ".//input[@id='series']")
	public static WebElement seriesTextBox;
	
	@FindBy(how = How.XPATH, using = ".//input[@id='seriesNumber']")
	public static WebElement seriesNumberTextBox;
	
	
	@FindBy(how = How.XPATH, using = ".//input[@id='volume']")
	public static WebElement volumeTextBox;
	
	@FindBy(how = How.XPATH, using = ".//input[@id='numberOfVolumes']")
	public static WebElement numberOfVolumesTextBox;
	
	
	@FindBy(how = How.XPATH, using = ".//input[@id='edition']")
	public static WebElement editionTextBox;
	
	@FindBy(how = How.XPATH, using = ".//input[@id='place']")
	public static WebElement placeTextBox;
	
	@FindBy(how = How.XPATH, using = ".//input[@id='publisher']")
	public static WebElement publisherTextBox;
	
	@FindBy(how = How.XPATH, using = ".//input[@id='date']")
	public static WebElement dateTextBox;
	
	@FindBy(how = How.XPATH, using = ".//input[@id='original-date']")
	public static WebElement originalDateTextBox;
	
	@FindBy(how = How.XPATH, using = ".//input[@id='numPages']")
	public static WebElement numPagesTextBox;
	
	@FindBy(how = How.XPATH, using = ".//input[@id='language']")
	public static WebElement languageTextBox;
	
	@FindBy(how = How.XPATH, using = ".//input[@id='ISBN']")
	public static WebElement ISBNTextBox;
	
	@FindBy(how = How.XPATH, using = ".//input[@id='shortTitle']")
	public static WebElement shortTitleTextBox;
	
	@FindBy(how = How.XPATH, using = ".//input[@id='url']")
	public static WebElement urlTextBox;
	
	@FindBy(how = How.XPATH, using = ".//input[@id='accessDate']")
	public static WebElement accessDateTextBox;
	
	@FindBy(how = How.XPATH, using = ".//input[@id='archive']")
	public static WebElement archiveTextBox;
	
	@FindBy(how = How.XPATH, using = ".//input[@id='archiveLocation']")
	public static WebElement archiveLocationTextBox;
	
	@FindBy(how = How.XPATH, using = ".//input[@id='libraryCatalog']")
	public static WebElement libraryCatalogTextBox;
	
	@FindBy(how = How.XPATH, using = ".//textarea[@id='abstractNote']")
	public static WebElement abstractNoteTextArea;
	
	@FindBy(how = How.XPATH, using = ".//textarea[@id='extra']")
	public static WebElement extraTextArea;
	
	
	
}
