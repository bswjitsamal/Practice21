package Utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Restassured_Automation_Utils {

	public static final String ALPHANUMERIC_CHARACTERS = "0123456789abcdefghijklmnopqrstuvwxyz";

	public int getRandomNumber(int min, int max) {
		return (int) ((Math.random() * (max - min)) + min);
	}

	public Response get_URL_Without_Params(String BaseURL) {

		return RestAssured.given().header("Content-Type", "application/json").log().all().get(BaseURL);

	}

	public Response get_URL_withOneQueryParam(String BaseURL, String URI, String value, String pathParam) {

		BaseURL = BaseURL + URI;
		return RestAssured.given().header("Content-Type", "application/json").queryParam(value, pathParam).log().all()
				.get(BaseURL);

	}

	public Response get_URL_withTwoQueryParams(String BaseURL, String URI, String value, String pathParam,
			String value1, String pathParam1) {

		BaseURL = BaseURL + URI;
		return RestAssured.given().header("Content-Type", "application/json").queryParam(value, pathParam)
				.queryParam(value1, pathParam1).log().all().get(BaseURL);

	}

}
