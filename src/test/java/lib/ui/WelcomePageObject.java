package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WelcomePageObject extends MainPageObject {

    private static final String

    STEP_LEARN_MORE_LINK="xpath://XCUIElementTypeStaticText[@name='Learn more about Wikipedia']",
    STEP_NEW_WAYS_TO_EXPLORE_TEXT="id:New ways to explore",
    STEP_ADD_OR_PREFERRED_LANG_LINK="xpath://XCUIElementTypeStaticText[@name='Add or edit preferred languages']",
    STEP_LEARN_MORE_ABOUT_DATA_COLLECTED="xpath://XCUIElementTypeStaticText[@name='Learn more about data collected']",
    NEXT_LINK="xpath://XCUIElementTypeButton[@name='Next']",
    GET_STARTED_BUTTON="xpath://XCUIElementTypeStaticText[@name='Get started']",
    SKIP = "id:Skip"
    ;

    public WelcomePageObject(RemoteWebDriver driver) {

        super(driver);
    }


    public void waitForLearnMoreLink() {

        this.waitForElementPresent(
                STEP_LEARN_MORE_LINK,
                "Cannot find 'Learn more about Wikipedia' link",
                10);


    }


    public void clickNextButton() {

      this.waitForElementAndClick(
                NEXT_LINK,
                "Cannot find and click button 'Next'",
                10);

    }

    public void waitForForNewWayExploreText() {

        this.waitForElementPresent(
                STEP_NEW_WAYS_TO_EXPLORE_TEXT,
                "Cannot find 'New ways to explore' link",
                10);

    }

    public void waitForAddOrEditPrefferedLangText() {

        this.waitForElementPresent(
                STEP_ADD_OR_PREFERRED_LANG_LINK,
                "Cannot find 'Add or edit preferred languages' link",
                10);

    }

    public void waitForLearnMoreAboutDataCollected() {

        this.waitForElementPresent(
                STEP_LEARN_MORE_ABOUT_DATA_COLLECTED,
                "Cannot find 'Learn more about data collected' link",
                10);

    }

    public void clickGetStartedButton() {

        this.waitForElementAndClick(
                GET_STARTED_BUTTON,
                "Cannot find and click button 'Get started'",
                10);

    }

    public void clickSkip() {
        this.waitForElementAndClick(
                SKIP,
                "Cannot find and ckick Skip button",
                5
        );
    }
}