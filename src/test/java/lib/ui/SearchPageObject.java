package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class SearchPageObject extends MainPageObject{

    protected static String
            SEARCH_INIT_ELEMENT,
            SEARCH_INPUT,
            SEARCH_CANCEL_BUTTON,
            SEARCH_RESULT_BY_SUBSTRING_TPL,
            SEARCH_RESULT_ELEMENT,
            SEARCH_EMPTY_RESULT_ELEMENT,
            SEARCH_LINE,
            SEARCH_LIST_ITEM_TITLE,
            SEARCH_BY_NAME_AND_DESCRIPTION_TPL
                    ;

    public SearchPageObject (RemoteWebDriver driver){

        super(driver);
    }

    /*TEMPLATES METHODS*/

    private static String getResultSearchElement (String substring) {

        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    public static String getTitleAndDescriptionOfElement (String title, String description) {

        return SEARCH_BY_NAME_AND_DESCRIPTION_TPL.replace("{TITLE}", title).replace("{DESCRIPTION}", description);
    }

    /*TEMPLATES METHODS*/

    public void initSearchInput() {
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Cannot find and click search init element",5);
        this.waitForElementPresent(SEARCH_INIT_ELEMENT,"Cannot find search input after clicking search init element");
    }

    public void waitForCancelButtonToAppear () {
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON, "Cannot find search cancel button", 5);
    }

    public void waitForCancelButtonToDisappear () {
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON, "Search cancel button is still present", 5);
    }

    public void clickCancelSearch () {
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Cannot find and click search cancel button", 5);
    }


    public void typeSearchLine(String search_line) {

        this.waitForElementAndSendKeys(SEARCH_INPUT, search_line, "Cannot find and type into search input", 10);
    }

    public void waitForSearchResult (String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(search_result_xpath,"Cannot find search result with substring " + substring);
    }

    public void waitForSearchResult () {
        this.waitForElementPresent(SEARCH_RESULT_ELEMENT,"Cannot find search result with substring " + SEARCH_RESULT_ELEMENT);
    }

    public void clickByArticleWithSubstring (String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(search_result_xpath,"Cannot find and click result with substring " + substring, 10);
    }

    public int getAmountOfFoundArticles() {

        this.waitForElementPresent(
                SEARCH_RESULT_ELEMENT,
                "Cannot find anything by the request " + SEARCH_RESULT_ELEMENT,
                15
        );
        return this.getAmountOfElements(SEARCH_RESULT_ELEMENT);
    }

    public void waitForEmptyResultLabel () {

        this.waitForElementPresent(
                        SEARCH_EMPTY_RESULT_ELEMENT,
                        "Cannot find empty result label by request " + SEARCH_EMPTY_RESULT_ELEMENT,
                        15);
    }

    public void assertThereIsNoResultOfSearch () {

        this.assertsElementNotPresent(
                SEARCH_RESULT_ELEMENT,
                "We supposed not to find any results"
        );
    }

    public void assertTextInSearchLine (String search_text) {

        this.assertElementHasText(
                SEARCH_LINE,
                search_text,
                "You see the unexpected value of the text attribute"
        );
    }

    public void clearTextFromSearchLine () {

        this.waitForElementAndClear(
                SEARCH_LINE,
                "You see the unexpected value of the text attribute",
                5
        );
    }

    public void checkContainTextInSearchResult (String search_word) {

        this.getElements(
                SEARCH_LIST_ITEM_TITLE,
                search_word);

    }

    public void waitForElementByTitleAndDescription(String title, String description) {

        String xpath_with_title_and_description = getTitleAndDescriptionOfElement(title,description);
        this.waitForElementPresent(xpath_with_title_and_description,"Cannot find result with title " + title + " and description " + description, 10);

    }
}
