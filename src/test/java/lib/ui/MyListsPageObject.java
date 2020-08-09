package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class MyListsPageObject extends MainPageObject{

    protected static String
            FOLDER_BY_NAME_TPL,
            ARTICLE_BY_TITLE_TPL,
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

    public void waitForArticleToAppearByTitle (String article_title) {

        String article_title_xpath = getSaveArticleXpathByTitle(article_title);

        this.waitForElementPresent(
                article_title_xpath,
                "Cannot find saved article by title " + article_title,
                15
        );
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
