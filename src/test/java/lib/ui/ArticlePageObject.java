package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class ArticlePageObject extends MainPageObject {

    protected static String
            TITLE,
            ARTICLE_DESCRIPTION,
            ARTICLE_REF,
            FOOTER_ELEMENT,
            OPTIONS_BUTTON,
            OPTIONS_ADD_MY_LIST_BUTTON,
            OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
            ADD_TO_MY_LIST_OVERLAY,
            MY_LIST_NAME_INPUT,
            MY_LIST_OK_BUTTON,
            CLOSE_ARTICLE_BUTTON,
            ADD_TO_MY_EXISTING_LIST_TPL;

    /*TEMPLATES METHODS*/
    private static String getMyListName(String list_name) {

        return ADD_TO_MY_EXISTING_LIST_TPL.replace("{LIST_NAME}", list_name);
    }
    /*TEMPLATES METHODS*/

    public ArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }

    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(TITLE, "Cannot find article title on page!", 15);
    }

    public WebElement waitForRefArticleElement() {
        return this.waitForElementPresent(ARTICLE_REF, "Cannot find article ref on page!", 15);
    }

    public WebElement waitForArticleDescriptionElement() {
        return this.waitForElementPresent(ARTICLE_DESCRIPTION, "Cannot find article description on page!", 15);

    }

    public String getArticleTitle() {
        WebElement title_element = waitForTitleElement();
        if (Platform.getInstance().isAndroid()) {
            return title_element.getAttribute("text");
        } else if (Platform.getInstance().isIOS()) {
            return title_element.getAttribute("name");
        } else {
            return title_element.getText();
        }
    }


    public String getArticleDescription() {


        if (Platform.getInstance().isIOS()) {
            WebElement article_description_element = waitForArticleDescriptionElement();
            return article_description_element.getAttribute("name");
        } else if (Platform.getInstance().isAndroid()){
            WebElement article_description_element = waitForArticleDescriptionElement();
            return article_description_element.getAttribute("text");
        } else {
            return "Cannot find this element for " + Platform.getInstance().getPlatformVar();
        }

    }

    public String getArticleRef() {

        if (Platform.getInstance().isMW()) {
            WebElement ref_article = this.waitForRefArticleElement();
            return ref_article.getAttribute("href");
        } else {
            return "Cannot find this element for " + Platform.getInstance().getPlatformVar();
        }

    }

    public void swipeToFooter() {
        if (Platform.getInstance().isAndroid()) {
            this.swipeUpToFindElement(
                    FOOTER_ELEMENT,
                    "Cannot find the end of article",
                    40
            );
        } else if (Platform.getInstance().isIOS()) {
            this.swipeUpTitleElementAppear(
                    FOOTER_ELEMENT,
                    "Cannot find the end of article",
                    40);
        } else {
            this.scrollWebPageTillElementNotVisible(
                    FOOTER_ELEMENT,
                    "Cannot find the end of article",
                    40
            );
        }
    }

    public void addArticleToMyList(String name_of_folder) {
        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Cannot find button to open article option",
                20
        );


        System.out.println(driver.findElements(By.xpath("//*[@text='Add to reading list']")));

        this.waitForElementAndClick(
                OPTIONS_ADD_MY_LIST_BUTTON,
                "Cannot find option to add article to reading list",
                15);

        this.waitForElementAndClick(
                ADD_TO_MY_LIST_OVERLAY,
                "Cannot find 'GOT IT' tip overlay",
                5
        );

        this.waitForElementAndClear(
                MY_LIST_NAME_INPUT,
                "Cannot find input to set name of articles folder",
                5
        );


        this.waitForElementAndSendKeys(
                MY_LIST_NAME_INPUT,
                name_of_folder,
                "Cannot put text into articles folder input",
                5);

        this.waitForElementAndClick(
                MY_LIST_OK_BUTTON,
                "Cannot press OK button",
                5
        );
    }

    public void closeArticle() {

        if (Platform.getInstance().isIOS() || Platform.getInstance().isAndroid()) {
            this.waitForElementAndClick(
                    CLOSE_ARTICLE_BUTTON,
                    "Cannot close article, cannot find X link",
                    5
            );
        } else {
            System.out.println("Method swipeElementToLeft() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    public void addArticleToMyExistingList(String name_of_folder) {
        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Cannot find button to open article option",
                20
        );

        System.out.println(driver.findElements(By.xpath("//*[@text='Add to reading list']")));

        this.waitForElementAndClick(
                OPTIONS_ADD_MY_LIST_BUTTON,
                "Cannot find option to add article to reading list",
                15);

        String list_name_xpath = getMyListName(name_of_folder);

        this.waitForElementAndClick(
                list_name_xpath,
                "Cannot find folder " + name_of_folder + " in saved reading lists",
                5
        );

    }

    public void assertArticleTitlePresent() {

        this.assertElementPresent(
                TITLE,
                "Article title is not present in article page");

    }

    public void removeArticleFromSavedIfItAdded() {
        if (this.isElementPresent(OPTIONS_REMOVE_FROM_MY_LIST_BUTTON)) {

            this.waitForElementAndClick(OPTIONS_REMOVE_FROM_MY_LIST_BUTTON, "Cannot click button to remove from saved", 1);

            this.waitForElementPresent(OPTIONS_REMOVE_FROM_MY_LIST_BUTTON, "Cannot find button to add to saved list after removing it from this list before", 1);
        }
    }

    public void addArticleToMySaved() {

        if (Platform.getInstance().isMW()) {
            this.removeArticleFromSavedIfItAdded();
        }
        waitForElementAndClick(OPTIONS_ADD_MY_LIST_BUTTON, "Cannot find option to add article to reading list", 5);

    }



}
