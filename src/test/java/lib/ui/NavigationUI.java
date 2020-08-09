package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class NavigationUI extends MainPageObject{

    protected static String
            MY_LISTS_LINK,
            OPEN_NAVIGATION;

    public NavigationUI (RemoteWebDriver driver) {
        super(driver);
    }

    public void clickMyLists() {

        if (Platform.getInstance().isMW()) {

            this.tryClickElementWithFewAttemts(MY_LISTS_LINK, "Cannot find navigation button to My lists", 5);
        }
        else {
        this.waitForElementAndClick(
                MY_LISTS_LINK,
                "Cannot find navigation button to My lists",
                5
        );
        }
        System.out.println(MY_LISTS_LINK);
    }

    public void openNavigation() {
        if (Platform.getInstance().isMW()) {
            waitForElementAndClick(OPEN_NAVIGATION, "Cannot find and click Navigation button",5);
        } else {
            System.out.println("Method swipeElementToLeft() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }



}
