package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import lib.Platform;

abstract public class ArticlePageObject extends MainPageObject{

    protected static String
                TITLE,
                ARTICLE_DESCRIPTION,
                FOOTER_ELEMENT,
                OPTIONS_BUTTON,
                OPTIONS_ADD_MY_LIST_BUTTON,
                ADD_TO_MY_LIST_OVERLAY,
                MY_LIST_NAME_INPUT,
                MY_LIST_OK_BUTTON,
                CLOSE_ARTICLE_BUTTON,
                ADD_TO_MY_EXISTING_LIST_TPL
                ;

    /*TEMPLATES METHODS*/
    private static String getMyListName (String list_name) {

        return ADD_TO_MY_EXISTING_LIST_TPL.replace("{LIST_NAME}", list_name);
    }
    /*TEMPLATES METHODS*/

    public ArticlePageObject (AppiumDriver driver) {
        super(driver);
    }

    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(TITLE,"Cannot find article title on page!", 15);
    }

    public WebElement waitForArticleDescriptionElement() {
        return this.waitForElementPresent(ARTICLE_DESCRIPTION,"Cannot find article description on page!", 15);

        }

    public String getArticleTitle() {
        WebElement title_element = waitForTitleElement();
        if (Platform.getInstance().isAndroid()) {
            return title_element.getAttribute("text");
        } else {
            return title_element.getAttribute("name");
        }

    }


    public String getArticleDescription() {
        WebElement article_description_element = waitForArticleDescriptionElement();

        if (Platform.getInstance().isIOS()) {
        return article_description_element.getAttribute("name");
        } else {
            return article_description_element.getAttribute("text");
        }

    }

    public void swipeToFooter() {
        if (Platform.getInstance().isAndroid()) {
            this.swipeUpToFindElement(
                    FOOTER_ELEMENT,
                    "Cannot find the end of article",
                    40
            );
        } else {this.swipeUpTitleElementAppear(FOOTER_ELEMENT, "Cannot find the end of article", 40);}
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

    public void closeArticle () {
        this.waitForElementAndClick(
                CLOSE_ARTICLE_BUTTON,
                "Cannot close article, cannot find X link",
                5
        );
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

    public void assertArticleTitlePresent () {

        this.assertElementPresent(
                TITLE,
                "Article title is not present in article page");

    }

    public void addArticleToMySaved() {
        waitForElementAndClick(OPTIONS_ADD_MY_LIST_BUTTON, "Cannot find option to add article to reading list", 5);
    }
}
