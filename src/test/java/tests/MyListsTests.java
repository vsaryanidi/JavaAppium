package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

public class MyListsTests extends CoreTestCase {

    private static final String name_of_folder = "Learning programming";
    private static final String
    login = "VSaryanidi",
    password = "VSaryanidi123456";

    @Test
    public void testSaveFirstArticleToMyList() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("bject-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();

        String article_title = ArticlePageObject.getArticleTitle();

        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyList(name_of_folder);
        } else {
            ArticlePageObject.addArticleToMySaved();
        }


        if (Platform.getInstance().isMW()) {
            AutorizationPegeObject Auth = new AutorizationPegeObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginData(login, password);
            Auth.submitForm();

            ArticlePageObject.waitForTitleElement();
            System.out.println(article_title);
            System.out.println(ArticlePageObject.getArticleTitle());
            assertEquals("We are not on the same page after login", article_title, ArticlePageObject.getArticleTitle());
            System.out.println("assert успешен");
            ArticlePageObject.addArticleToMySaved();
        }

        ArticlePageObject.closeArticle();

        if (Platform.getInstance().isIOS()) {
            SearchPageObject.clickCancelSearch();
        }

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.openNavigation();
        NavigationUI.clickMyLists();

        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);

        if (Platform.getInstance().isIOS()) {
            MyListsPageObject.closeSyncYourSavedArticle();
        }


        if (Platform.getInstance().isAndroid()) {
            MyListsPageObject.openFolderByName(name_of_folder);
        }
            MyListsPageObject.swipeByArticleToDelete(article_title);


    }


    @Test
    public void testSaveTwoArticleToMyListAndDeleteOneOfThem() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("bject-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();

        String article_title = ArticlePageObject.getArticleTitle();

        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyList(name_of_folder);
        } else {
            ArticlePageObject.addArticleToMySaved();
        }

        if (Platform.getInstance().isMW()) {
            AutorizationPegeObject Auth = new AutorizationPegeObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginData(login, password);
            Auth.submitForm();

            ArticlePageObject.waitForTitleElement();
            assertEquals("We are not on the same page after login", article_title, ArticlePageObject.getArticleTitle());
            ArticlePageObject.addArticleToMySaved();
        }

        ArticlePageObject.closeArticle();

        if (Platform.getInstance().isAndroid()) {
            SearchPageObject.initSearchInput();

            SearchPageObject.typeSearchLine("Java");
        }

        SearchPageObject.clickByArticleWithSubstring("sland of Indonesia");

        ArticlePageObject.waitForArticleDescriptionElement();

        String article_description = ArticlePageObject.getArticleDescription();
        System.out.println(article_description);

        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyExistingList(name_of_folder);
        } else {
            ArticlePageObject.addArticleToMySaved();
        }

        ArticlePageObject.closeArticle();

        if (Platform.getInstance().isIOS()) {
            SearchPageObject.clickCancelSearch();
        }

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.clickMyLists();
        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid()) {
            MyListsPageObject.openFolderByName(name_of_folder);
            System.out.println(driver.findElements(By.xpath(article_description)));

        } else {
            MyListsPageObject.closeSyncYourSavedArticle();
        }

        MyListsPageObject.swipeByArticleToDelete(article_description);
        MyListsPageObject.waitForArticleToAppearByTitle(article_title);



    }

}
