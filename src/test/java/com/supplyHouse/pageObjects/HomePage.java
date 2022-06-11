package com.supplyHouse.pageObjects;

import com.supplyHouse.utils.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class HomePage extends BasePage{

    @FindBy(id = "react-header-search-input")
    public WebElement searchInput;

    public List<WebElement> getAllSearchKeywords(WebDriver driver, String keyword){
        return driver.findElements(By.xpath("//span[.='"+keyword+"']"));
    }

    public List<WebElement> getAllSearchText(WebDriver driver, String keyword){
        return driver.findElements(By.xpath("//span[.='"+keyword+"']/.."));
    }

    public List<WebElement> getAllCategories(WebDriver driver){
        return driver.findElements(By.xpath("//div[@class='sc-bczRLJ HeaderSearchSuggestionText-h71b5o-0 iGsgaG gMXgnS']"));
    }

    public List<WebElement> getAllProductSuggestions(WebDriver driver){
        return driver.findElements(By.xpath(
                "//div[@class='sc-bczRLJ sc-gsnTZi HeaderSearchSuggestions__HeaderSearchProductSuggestionsContainer" +
                        "-sc-1vd0egg-0 hJYFVB jnFvAE dfXouN']/div/div/div/div[1]"));

    }

    public List<WebElement> getAllProductSuggestionsImg(WebDriver driver){
        return driver.findElements(By.xpath(
                "//div[@class='sc-bczRLJ sc-gsnTZi HeaderSearchSuggestions__HeaderSearchProductSuggestionsContainer" +
                "-sc-1vd0egg-0 hJYFVB jnFvAE dfXouN']/div/div/img"));
    }

}
