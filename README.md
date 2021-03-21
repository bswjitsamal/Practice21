 We are validating API request with RestAssured for the URI: https://zbib.org/
 
 The API's are as fllows:
  1. https://api.zotero.org/itemTypes?format=json (GET Method)
  2. https://api.zotero.org/itemTypeFields?format=json&itemType=book (GET Method)
  3. https://api.zotero.org/itemTypeCreatorTypes?format=json&itemType=book (GET Method)
  
 One test case fails: itemTypeCratorType = 'note'
 
 Validation Steps:
  1. Hear we are performing Unit testing for each API
  2. endToEnd Scenario to vlaidate all are working file -- where as the test case failed while performing 
  3. Using GUI validation using selenium 
