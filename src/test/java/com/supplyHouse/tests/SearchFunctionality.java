package com.supplyHouse.tests;

import com.supplyHouse.pageObjects.HomePage;
import com.supplyHouse.utils.ConfigReader;
import com.supplyHouse.utils.Driver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebElement;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SearchFunctionality {


    @ParameterizedTest
    @CsvFileSource(resources = "/searchWords.csv",numLinesToSkip = 1)
    public void test(String searchKeyword){

        Driver.getDriver().get(ConfigReader.read("url"));

        HomePage homePage = new HomePage();
        homePage.searchInput.sendKeys(searchKeyword);
        homePage.searchInput.click();

        //• Auto-Suggested term
        //Assert: The contained suggestion has searched keyword in grey and other text in bold
        List<WebElement> allSearchKeywords = homePage.getAllSearchKeywords(Driver.getDriver(), searchKeyword);
        Assertions.assertTrue(allSearchKeywords.stream().allMatch(x->x.getAttribute("class").equals("unbold")));

        List<WebElement> allSearchText = homePage.getAllSearchText(Driver.getDriver(), searchKeyword);
        Assertions.assertTrue(allSearchText.stream().allMatch(x->x.getTagName().equals("b")));

        //Assert: The contained suggestion no duplicate terms
        List<String> searchSuggestions = allSearchText.stream().map(WebElement::getText).collect(Collectors.toList());
        Set<String> noDuplicatesSuggestions = new HashSet<>(searchSuggestions);
        Assertions.assertEquals(searchSuggestions.size(),noDuplicatesSuggestions.size());

        //• Categories Suggestion
        //Assert: The results show 3 categories at most and no duplicate terms
        List<WebElement> allCategories = homePage.getAllCategories(Driver.getDriver());
        Assertions.assertTrue(allCategories.size()<=3);

        List<String> allCategoriesText = allCategories.stream().map(WebElement::getText).collect(Collectors.toList());
        Set<String> noDuplicatesCategories = new HashSet<>(allCategoriesText);
        Assertions.assertEquals(allCategoriesText.size(),noDuplicatesCategories.size());

        //• Product Suggestions
        //Assert: The results show 5 items at most
        List<WebElement> allProductSuggestions = homePage.getAllProductSuggestions(Driver.getDriver());
        Assertions.assertTrue(allProductSuggestions.size()<=5);

        //Assert: The results contain images and no duplicate items
        List<WebElement> allProductSuggestionsImg = homePage.getAllProductSuggestionsImg(Driver.getDriver());
        Assertions.assertEquals(allProductSuggestions.size(),allProductSuggestionsImg.size());

        List<String> allProductSuggestionsText = allProductSuggestions.stream().map(WebElement::getText).collect(Collectors.toList());
        Set<String> noDuplicatesProductSuggestions = new HashSet<>(allProductSuggestionsText);
        Assertions.assertEquals(allProductSuggestionsText.size(),noDuplicatesProductSuggestions.size());


    }

    @AfterEach
    public void close(){
        Driver.closeBrowser();
    }

}
