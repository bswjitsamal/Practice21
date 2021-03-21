package Utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Restassured_Automation_Utils {

	public Response get_URL_Without_Params(String BaseURL) {

		return RestAssured.given().header("Content-Type", "application/json").log().all().get(BaseURL);

	}

	public Response get_URL_withOneQueryParam(String BaseURL, String URI, String value, String pathParam) {

		BaseURL = BaseURL + URI;
		return RestAssured.given().header("Content-Type", "application/json").queryParam(value, pathParam).log().all()
				.get(BaseURL);

	}

	public Response get_URL_withTwoQueryParams(String BaseURL, String URI, String value, String pathParam,
			String valueOne, String pathParamOne) {

		BaseURL = BaseURL + URI;
		return RestAssured.given().header("Content-Type", "application/json").queryParam(value, pathParam)
				.queryParam(valueOne, pathParamOne).log().all().get(BaseURL);

	}

}
