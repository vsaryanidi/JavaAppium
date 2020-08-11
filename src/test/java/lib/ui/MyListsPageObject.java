package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import lib.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.regex.Pattern;

abstract public class MyListsPageObject extends MainPageObject{

    protected static String
            FOLDER_BY_NAME_TPL,
            ARTICLE_BY_TITLE_TPL,
            ARTICLE_BY_REF_TPL,
            ARTICLE_REF,
            REMOVE_FROM_SAVED_BUTTON,
            CLOSE_BUTTON;

    private static String getFolderXpathByName (String name_of_folder) {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }

    private static String getSaveArticleXpathByTitle (String article_title) {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
    }

    private static String getRemoveButtonByTitle (String article_title) {
        return REMOVE_FROM_SAVED_BUTTON.replace("{TITLE}", article_title);
    }

    private static String getSavedArticleByRef (String article_ref) {

        String[] ref = article_ref.split(Pattern.quote(".org"),2);
        String ref_wiki = ref[0] + ".org";
        String ref_article = ref[1];

        return ARTICLE_BY_REF_TPL.replace("{REF}", ref_article);
    }



    public MyListsPageObject (RemoteWebDriver driver) {
        super(driver);
    }

    public void openFolderByName (String name_of_folder) {

        String folder_name_xpath = getFolderXpathByName(name_of_folder);

        this.waitForElementAndClick(
                folder_name_xpath,
                "Cannot find folder by name " + name_of_folder,
                5
        );
    }


    public WebElement waitForRefSavedArticleElement() {
        return this.waitForElementPresent(ARTICLE_REF, "Cannot find article ref on page!", 15);
    }

    public void waitForArticleToAppearByTitle (String article_title) {

        String article_title_xpath = getSaveArticleXpathByTitle(article_title);

        this.waitForElementPresent(
                article_title_xpath,
                "Cannot find saved article by title " + article_title,
                15
        );
    }

    public void waitForArticleToAppearByRef (String article_ref) {

        if (Platform.getInstance().isMW()) {
            String saved_article_by_ref = this.getSavedArticleByRef(article_ref);
            this.waitForElementPresent(
                    saved_article_by_ref,
                    "Cannot find saved article by ref " + article_ref,
                    15
            );
        } else {
            return;
        }
    }



    public void waitForArticleToDisappearByTitle (String article_title) {

        /*this.waitForArticleToAppearByTitle(article_title);*/
        String article_title_xpath = getSaveArticleXpathByTitle(article_title);

        this.waitForElementNotPresent(
                article_title_xpath,
                "Saved article still present with title " + article_title,
                15
        );
    }

    public void swipeByArticleToDelete(String article_title) {

        this.waitForArticleToAppearByTitle(article_title);
        String article_xpath = getSaveArticleXpathByTitle(article_title);

        if (!Platform.getInstance().isMW()) {
            this.swipeElementToLeft(
                    article_xpath,
                    "Cannot find save article " + article_title
            );
        } else {
            String remove_locator = getRemoveButtonByTitle(article_title);
            this.waitForElementAndClick(
                    remove_locator,
                    "Cannot click button to remove article from saved",
                            10
            );
        }
        if (Platform.getInstance().isIOS()) {
            this.clickElementToTheRightUpperCorner(article_xpath, "Cannot find saved article");
        }

        if (Platform.getInstance().isMW()) {
            driver.navigate().refresh();
        }
        this.waitForArticleToDisappearByTitle(article_title);
    }

    public void closeSyncYourSavedArticle() {

        this.waitForElementAndClick(CLOSE_BUTTON, "Cannot find X button", 10);
    }



}
