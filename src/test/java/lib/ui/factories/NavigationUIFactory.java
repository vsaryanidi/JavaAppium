package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.NavigationUI;
import lib.ui.android.AndroidArticlePageObject;
import lib.ui.android.AndroidNavigationUI;
import lib.ui.ios.iOSArticlePageObject;
import lib.ui.ios.iOSNavigationUI;
import lib.ui.ios.iOSSearchPageObject;
import lib.ui.mobile_web.MWNavigationUI;
import lib.ui.mobile_web.MWSearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class NavigationUIFactory {

    public static NavigationUI get(RemoteWebDriver driver) {

        if (Platform.getInstance().isAndroid()) {

            return new AndroidNavigationUI(driver);
        } else if
                (Platform.getInstance().isIOS()) {
            return new iOSNavigationUI(driver);
        } else {
            return new MWNavigationUI(driver);
        }

    }
}
