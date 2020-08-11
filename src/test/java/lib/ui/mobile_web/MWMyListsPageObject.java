package lib.ui.mobile_web;

import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWMyListsPageObject extends MyListsPageObject {

    static {

        ARTICLE_BY_TITLE_TPL = "xpath://li[contains(@class, 'page-summary')]//h3[contains(text(), '{TITLE}')]";
        REMOVE_FROM_SAVED_BUTTON = "xpath://li[contains(@class, 'page-summary')]//h3[contains(text(), '{TITLE}')]/../../a[contains(@class, 'watched')]";
        ARTICLE_REF = "xpath://ul[contains(@class,'mw-mf-watchlist-page-list')]//a[@class='title']";
        ARTICLE_BY_REF_TPL= "xpath://ul[contains(@class,'mw-mf-watchlist-page-list')]//a[@class='title'][contains(@href, '{REF}')]";
    }

    public MWMyListsPageObject (RemoteWebDriver driver) {

        super(driver);
    }
}
