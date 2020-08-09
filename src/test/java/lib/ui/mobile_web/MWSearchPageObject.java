package lib.ui.mobile_web;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWSearchPageObject extends SearchPageObject {

    static {
        SEARCH_INIT_ELEMENT = "css:button#searchIcon";
        SEARCH_INPUT = "css:form>input.search";
        SEARCH_CANCEL_BUTTON = "css:button.cancel";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://div[contains(@class, 'wikidata-description')][contains(text(), '{SUBSTRING}')]";
        SEARCH_RESULT_ELEMENT = "css:ul.page-list>li.page-summary";
        SEARCH_EMPTY_RESULT_ELEMENT = "css:p.without-results";
        SEARCH_LINE = "css:form>input.search";
        SEARCH_LIST_ITEM_TITLE = "xpath://XCUIElementTypeOther[2]/XCUIElementTypeCollectionView/XCUIElementTypeCell/XCUIElementTypeOther[2]/XCUIElementTypeStaticText[1]";
        SEARCH_BY_NAME_AND_DESCRIPTION_TPL = "xpath://XCUIElementTypeStaticText[contains(@name, '{TITLE}')]/following-sibling::XCUIElementTypeStaticText[contains(@name, '{DESCRIPTION}')]";
    }

    public MWSearchPageObject(RemoteWebDriver driver) {

        super(driver);
    }
}

