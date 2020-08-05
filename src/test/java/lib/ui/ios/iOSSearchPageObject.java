package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class iOSSearchPageObject extends SearchPageObject {

    static {
        SEARCH_INIT_ELEMENT = "id:Search Wikipedia";
        SEARCH_INPUT = "id:Search Wikipedia";
        SEARCH_CANCEL_BUTTON = "xpath://XCUIElementTypeStaticText[@name='Cancel']";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://XCUIElementTypeStaticText[@name='{SUBSTRING}']";
        SEARCH_RESULT_ELEMENT = "xpath://XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeCollectionView/XCUIElementTypeCell";
        SEARCH_EMPTY_RESULT_ELEMENT = "id:No results found";
        SEARCH_LINE = "xpath://XCUIElementTypeSearchField[@name='Search Wikipedia']";
        SEARCH_LIST_ITEM_TITLE = "xpath://XCUIElementTypeOther[2]/XCUIElementTypeCollectionView/XCUIElementTypeCell/XCUIElementTypeOther[2]/XCUIElementTypeStaticText[1]";
        SEARCH_BY_NAME_AND_DESCRIPTION_TPL = "xpath://XCUIElementTypeStaticText[contains(@name, '{TITLE}')]/following-sibling::XCUIElementTypeStaticText[contains(@name, '{DESCRIPTION}')]";
    }

    public iOSSearchPageObject(AppiumDriver driver) {

        super(driver);
    }
}
