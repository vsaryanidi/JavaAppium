package lib.ui.mobile_web;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;
import tests.ArticleTests;

public class MWArticlePageObject extends ArticlePageObject {

    static {
        TITLE = "css:#content h1";
        ARTICLE_DESCRIPTION = "id:org.wikipedia:id/view_page_subtitle_text";
        ARTICLE_REF = "xpath://div/a[contains(@class, 'minerva__tab selected')]";
        FOOTER_ELEMENT = "css:footer";
        OPTIONS_ADD_MY_LIST_BUTTON = "css:a#ca-watch.mw-ui-icon-wikimedia-star-base20";
        OPTIONS_REMOVE_FROM_MY_LIST_BUTTON="css:a#ca-watch.mw-ui-icon-wikimedia-unStar-progressive";
        ADD_TO_MY_EXISTING_LIST_TPL = "xpath://*[@resource-id='org.wikipedia:id/item_title'][@text='{LIST_NAME}']";
    }

    public MWArticlePageObject (RemoteWebDriver driver) {

        super(driver);
    }
}
