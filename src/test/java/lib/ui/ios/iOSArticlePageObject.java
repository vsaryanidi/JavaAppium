package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class iOSArticlePageObject extends ArticlePageObject {

    static {
        TITLE = "id:Java (programming language)";
        ARTICLE_DESCRIPTION= "id:Island of Indonesia";
        FOOTER_ELEMENT = "id:View article in browser";
        OPTIONS_ADD_MY_LIST_BUTTON = "id:Save for later";
        CLOSE_ARTICLE_BUTTON = "id:Back";
        ADD_TO_MY_EXISTING_LIST_TPL = "xpath://*[@resource-id='org.wikipedia:id/item_title'][@text='{LIST_NAME}']";
    }

    public iOSArticlePageObject(AppiumDriver driver) {

        super(driver);
    }

}
