package TestSute;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import POJO.AllPoJo;
import Utils.ExcelUtils;
import Utils.Restassured_Automation_Utils;
import Utils.read_Configuration_Propertites;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class ManualEntryWithAPI {

	List<String> listItemType;
	List<String> listField;
	List<String> listCreatorType;

	String URL;
	ExtentReports extent;
	ExtentTest logger;

	Restassured_Automation_Utils restUtils = new Restassured_Automation_Utils();
	Map<String, String> data = new HashMap<String, String>();

	// Primary excel file
	String originalExcelPath = System.getProperty("user.dir") + File.separator + "resources" + File.separator
			+ "TestDataSource.xls";

	// copy of the Primary excel file
	String copyExcelPath = System.getProperty("user.dir") + File.separator + "resources" + File.separator
			+ "TestDataSource.xls";

	// Validating HTTP Strict Transport security
	public void validate_HTTPStrictTransportSecurity(Response response) {

		// Reader header of a give name. In this line we will get Header named Server
		String strictTransportSecurity = response.header("Strict-Transport-Security");
		System.out.println("Server value: " + strictTransportSecurity);

		// Assert.assertEquals("max-age=31536000; includeSubDomains; preload",
		// strictTransportSecurity);

		if ("max-age=31536000; includeSubDomains; preload".equals(strictTransportSecurity)) {
			System.out.println("This is following HTTPStrictTransportSecurity");
		} else {
			System.out.println("This is NOT following HTTPStrictTransportSecurity");

		}
		// Assert.assertEquals("max-age=63072000; includeSubDomains; preload",
		// strictTransportSecurity);
	}

	@BeforeTest(groups = { "IntegrationTests", "EndToEnd", "IntegrationTests1" })
	public void setup() throws IOException {

		/**
		 * STEP-0: GETITNG AN ORG ID AND UPDATING THE SAME IN THE EXCEL
		 */

		read_Configuration_Propertites configDetails = new read_Configuration_Propertites();
		Properties BaseUrl = read_Configuration_Propertites.loadproperty("Configuration");
		URL = BaseUrl.getProperty("ApiBaseUrl");

	}

	@Test(priority = 1)
	public void updateExcelFieds() throws IOException, RowsExceededException, BiffException, WriteException {

		/**
		 * STEP-1: GETITNG AN ORG ID AND UPDATING THE SAME IN THE EXCEL
		 */

		Response itemTypes = restUtils.get_URL_withOneQueryParam(URL, "/itemTypes", "format", "json");
		itemTypes.prettyPrint();

		// Retrieving the OrgId with index from the response
		JsonPath jsonPathEvaluator = itemTypes.jsonPath();
		listItemType = jsonPathEvaluator.get("itemType");
		System.out.println("---------------" + listItemType);

		for (int i = 0; i < listItemType.size(); i++) {
			ExcelUtils.writeToExcel(originalExcelPath, copyExcelPath, listItemType.get(i), 0, i + 1, 0);
		}

		validate_HTTPStrictTransportSecurity(itemTypes);
		Assert.assertEquals(itemTypes.statusCode(), 200);

	}
	
	

	@DataProvider(name = "firstDataProvider")
	public Object[][] itemTypeFields() throws Exception {
		Object[][] retObjArr = ExcelUtils.get_Data(originalExcelPath, "Test", 1);
		System.out.println("getData function executed!!");
		return retObjArr;
	}

	@Test(priority = 2, groups = "IntegrationTests", dataProvider = "firstDataProvider")
	public void itemTypeFields(String itemType)
			throws IOException, RowsExceededException, BiffException, WriteException {

		/**
		 * STEP-2: GETITNG DATA FROM THE EXCEL
		 */

		data.put("itemType", itemType);

		AllPoJo itemTyp = new AllPoJo();
		String allItemType = itemTyp.differentItemType(data);
		System.out.println("--->" + allItemType);

		Response itemTypeFields = restUtils.get_URL_withTwoQueryParams(URL, "/itemTypeFields", "format", "json", "itemType",
				allItemType.substring(13, allItemType.length() - 2));
		itemTypeFields.prettyPrint();

		// Retrieving the OrgId with index from the response
		JsonPath jsonPathEvaluator = itemTypeFields.jsonPath();
		listField = jsonPathEvaluator.get("field");
		System.out.println("---------------" + listField);

		validate_HTTPStrictTransportSecurity(itemTypeFields);
		Assert.assertEquals(itemTypeFields.statusCode(), 200);
	}
	
	

	@DataProvider(name = "secondDataProvider")
	public Object[][] itemTypeCreatorTypes() throws Exception {
		Object[][] retObjArr = ExcelUtils.get_Data(originalExcelPath, "Test", 1);
		System.out.println("getData function executed!!");
		return retObjArr;
	}

	@Test(priority = 3, groups = "IntegrationTests", dataProvider = "secondDataProvider")
	public void itemTypeCreatorTypes(String itemType)
			throws IOException, RowsExceededException, BiffException, WriteException {

		/**
		 * STEP-3: GETITNG AN ORG ID AND UPDATING THE SAME IN THE EXCEL
		 */

		data.put("itemType", itemType);

		AllPoJo itemTyp = new AllPoJo();
		String allItemType = itemTyp.differentItemType(data);
		System.out.println("--->" + allItemType);

		Response itemTypeCreatorTypes = restUtils.get_URL_withTwoQueryParams(URL, "/itemTypeCreatorTypes", "format", "json",
				"itemType", allItemType.substring(13, allItemType.length() - 2));
		itemTypeCreatorTypes.prettyPrint();

		// Retrieving the OrgId with index from the response
		JsonPath jsonPathEvaluator = itemTypeCreatorTypes.jsonPath();
		listCreatorType = jsonPathEvaluator.get("creatorType");
		System.out.println("---------------" + listCreatorType);

		/*
		 * for(int i=0;i<listField.size();i++) {
		 * ExcelUtils.writeToExcel(originalExcelPath, copyExcelPath, listField.get(i),
		 * 1, i, 0); }
		 */

		validate_HTTPStrictTransportSecurity(itemTypeCreatorTypes);
		Assert.assertEquals(itemTypeCreatorTypes.statusCode(), 200);

	}

	@Test(priority = 4, groups = "IntegrationTests")
	public void endToEnd()  {

		/**
		 * STEP-1: GETITNG AN ORG ID AND STORING IN A LIST
		 */

		Response getItemTypes = restUtils.get_URL_withOneQueryParam(URL, "/itemTypes", "format", "json");
		getItemTypes.prettyPrint();

		// Retrieving the OrgId with index from the response
		JsonPath jsonPathEvaluator = getItemTypes.jsonPath();
		listItemType = jsonPathEvaluator.get("itemType");
		System.out.println("---------------" + listItemType);

		/**
		 * STEP-2: READING DATA FROM THE LIST
		 */

		for (int i = 0; i < listItemType.size(); i++) {

			System.out.println("the vaue of i is: " + i + listItemType.get(i));
			Response getItemTypeFields = restUtils.get_URL_withTwoQueryParams(URL, "/itemTypeFields", "format", "json",
					"itemType", listItemType.get(i));
			getItemTypeFields.prettyPrint();

			// Retrieving the OrgId with index from the response
			JsonPath jsonPathEvaluator1 = getItemTypeFields.jsonPath();
			listField = jsonPathEvaluator1.get("field");
			System.out.println("---------------" + listField);

			Response getItemTypeCreatorTypes = restUtils.get_URL_withTwoQueryParams(URL, "/itemTypeCreatorTypes",
					"format", "json", "itemType", listItemType.get(i));
			getItemTypeCreatorTypes.prettyPrint();

			// Retrieving the OrgId with index from the response
			JsonPath jsonPathEvaluator2 = getItemTypeCreatorTypes.jsonPath();
			listCreatorType = jsonPathEvaluator2.get("creatorType");
			System.out.println("---------------" + listCreatorType);

		}

	}

}
