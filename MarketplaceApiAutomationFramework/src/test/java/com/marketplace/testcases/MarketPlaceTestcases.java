package com.marketplace.testcases;

import org.testng.annotations.Test;

import com.marketplace.base.BaseMarket;
import com.marketplace.base.MarketPlaceBase;
import com.marketplace.utils.PropertyReader;
import com.relevantcodes.extentreports.LogStatus;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class MarketPlaceTestcases extends BaseMarket {

	@Test(enabled = true, priority = 1)
	public void GetProductCategoryList() {
		test.log(LogStatus.INFO, "All Product Category List Showing");
		String productUrl = PropertyReader.getConfigProperty("GetCategoryList");
		ValidatableResponse validatableResponse = BaseMarket.getcall(productUrl);
		validatableResponse.assertThat().log().all().extract().response();
		validatableResponse.statusCode(200);
		test.log(LogStatus.INFO, "Showing Status Code 200");

	}

	@Test(enabled = true, priority = 2)
	public void AddNewProduct() {
		test.log(LogStatus.INFO, "Add New Product according to category");
		String productUrl = PropertyReader.getConfigProperty("AddProductUrl");
		String FirstPerameterKey = PropertyReader.getConfigProperty("FirPeramKey");
		String ProductPayload = PropertyReader.getDataProperty("addProduPayload");
		String SecondPerameterKey = PropertyReader.getConfigProperty("SeconPeramKey");
		String imageURL = PropertyReader.getDataProperty("ImageUrl");

		Response response = BaseMarket.postApiCall1(productUrl, FirstPerameterKey, ProductPayload, SecondPerameterKey,
				imageURL);
		test.log(LogStatus.INFO, "Product Successfully added");
		test.log(LogStatus.INFO, "Showing Status Code 200");

	}

	@Test(enabled = true, priority = 3)
	public void AddProductColour() {
		test.log(LogStatus.INFO, "Add colour according to product ");
		String productUrl = PropertyReader.getConfigProperty("AddProductColourUrl");

		String imageURL = PropertyReader.getDataProperty("ColourImage");

		Response response = BaseMarket.postUpdateApiCall2(productUrl, imageURL);
		test.log(LogStatus.INFO, "Product colour added Successfully");
		test.log(LogStatus.INFO, "Showing Status Code 200");

	}

	@Test(enabled = true, priority = 4)
	public void GetProductList() {
		test.log(LogStatus.INFO, "All Product list showing ");
		String productUrl = PropertyReader.getConfigProperty("GetProductList");
		ValidatableResponse validatableResponse = BaseMarket.getcall(productUrl);
		validatableResponse.assertThat().log().all().extract().response();
		validatableResponse.statusCode(200);
		test.log(LogStatus.INFO, "Showing Status Code 200");
	}

	@Test(enabled = true, priority = 5)
	public void FindProductDetails() {
		test.log(LogStatus.INFO, "Particular Product details showing ");
		String productUrl = PropertyReader.getConfigProperty("ProductDetails");
		Response response = BaseMarket.getApiPathParamcall(productUrl);
		test.log(LogStatus.INFO, "Showing Status Code 200");

	}

	@Test(enabled = true, priority = 6)
	public void ProductColourView() {
		test.log(LogStatus.INFO, "Product colour showing ");
		String productUrl = PropertyReader.getConfigProperty("ProductColourDetailsView");
		ValidatableResponse validatableResponse = BaseMarket.getApiPathParamcal2(productUrl);
		test.log(LogStatus.INFO, "Showing Status Code 200");

	}

	@Test(enabled = true, priority = 7)
	public void UserBuyProduct() {
		test.log(LogStatus.INFO, "User buy new product ");
		String productUrl = PropertyReader.getConfigProperty("ProductBuyUrl");
        Response response = BaseMarket.postBuyApiCall(productUrl);
        test.log(LogStatus.INFO, "Showing Status Code 200");

	}

	@Test(enabled = true, priority = 8)
	public void DeleteProduct() {
		test.log(LogStatus.INFO, "User delete a product ");
		String productUrl = PropertyReader.getConfigProperty("DeleteProduct");
		ValidatableResponse validatableResponse = MarketPlaceBase.DELcall(productUrl);
		validatableResponse.assertThat().log().all().extract().response();
		validatableResponse.statusCode(200);
		 test.log(LogStatus.INFO, "Showing Status Code 200");
	}
}
