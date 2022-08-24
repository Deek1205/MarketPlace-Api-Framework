package com.marketplace.base;

import java.io.File;
import java.util.Map;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.marketplace.utils.ExtentReportListner;
import com.marketplace.utils.PropertyReader;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

@Listeners(ExtentReportListner.class)
public class BaseMarket extends ExtentReportListner {

	public static int idvalue;
	public static String ColourName;
	public static int ColourID;

	public static String jsonPayload1;
	public static String endPointURL1;
	public static String First_Perameter_Key1;
	public static String Second_Perameter_Key1;
	public static String ImageUrl1;
	public static ValidatableResponse validatableResponse = null;

	static String baseUrl = PropertyReader.getConfigProperty("baseUrl");

	static public Response postApiCall1(String endPointURL, String FirPeramKey, String addProduPayload,
			String SeconPeramKey, String ImageUrl) {

		endPointURL1 = endPointURL;
		First_Perameter_Key1 = FirPeramKey;
		jsonPayload1 = addProduPayload;
		Second_Perameter_Key1 = SeconPeramKey;
		ImageUrl1 = ImageUrl;

		RestAssured.baseURI = baseUrl;

		Response response = (Response) RestAssured.given().contentType("multipart/form-data")
				.multiPart(FirPeramKey, addProduPayload, "multipart/form-data")
				.multiPart(SeconPeramKey, new File(ImageUrl), "multipart/form-data").post(endPointURL).then().log()
				.all().extract().response();
		idvalue = response.path("Data.product_id");
		System.out.println("print id:" + idvalue);

		return response;
	}

	static public Response postUpdateApiCall2(String endPointURL, String ImageUrl) {
		RestAssured.baseURI = baseUrl;
		Response response = (Response) RestAssured.given().contentType("multipart/form-data")
				.multiPart("image", new File(ImageUrl), "multipart/form-data")
				.multiPart("vendor_id", 284, "multipart/form-data")
				.multiPart("product_id", idvalue, "multipart/form-data")
				.multiPart("colour_name", "redyello", "multipart/form-data")
				.multiPart("quantity", 5, "multipart/form-data").multiPart("length", 6, "multipart/form-data")
				.multiPart("weight", 9, "multipart/form-data").post(endPointURL).then().log().all().extract()
				.response();
		ColourName = response.path("Data.colour_name");
		System.out.println("Colour Name:" + ColourName);

		ColourID = response.path("Data.colour_id");
		System.out.println("print id:" + ColourID);

		return response;

	}

	static public ValidatableResponse getcall(String endPointURL) {
		endPointURL1 = endPointURL;
		RestAssured.baseURI = baseUrl;
		validatableResponse = RestAssured.given().when().contentType(ContentType.JSON).get(endPointURL).then().log()
				.all();
		return validatableResponse;

	}

	static public Response getApiPathParamcall(String endPointURL) {

		endPointURL1 = endPointURL;
		RestAssured.baseURI = baseUrl;
		Response response = (Response) RestAssured.given().queryParam("product id", idvalue).when()
				.contentType(ContentType.JSON).get(endPointURL).then().log().all();

		return response;
	}

	static public ValidatableResponse getApiPathParamcal2(String endPointURL) {
		endPointURL1 = endPointURL;
		RestAssured.baseURI = baseUrl;
		validatableResponse = RestAssured.given().queryParam("product id", idvalue).queryParam("colour id", ColourID)
				.when().contentType(ContentType.JSON).get(endPointURL).then().log().all();
		return validatableResponse;

	}

	static public Response postBuyApiCall(String endPointURL) {
		RestAssured.baseURI = baseUrl;
		Response response = (Response) RestAssured.given().queryParam("product id", idvalue)
				.contentType("multipart/form-data").multiPart("quantity", 2, "multipart/form-data")
				.multiPart("payment_method", "Cash_On_Delivery", "multipart/form-data")
				.multiPart("pickup", "True", "multipart/form-data")
				.multiPart("address", "It Park Indore", "multipart/form-data")
				.multiPart("colour_name", "ColourName", "multipart/form-data").post(endPointURL).then().log().all()
				.extract().response();
		return response;
	}

	static public ValidatableResponse DELcall(String endPointURL) {
		endPointURL1 = endPointURL;
		RestAssured.baseURI = baseUrl;
		validatableResponse = RestAssured.given().queryParam("product id", idvalue).when().contentType(ContentType.JSON)
				.delete(endPointURL).then().log().all();
		return validatableResponse;

	}
}