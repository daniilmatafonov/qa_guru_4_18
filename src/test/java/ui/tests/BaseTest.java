package ui.tests;

import com.codeborne.selenide.Configuration;
import config.IConfig;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static helpers.AttachmentHelper.*;

public class BaseTest {

    public static final IConfig CONFIG = ConfigFactory.create(IConfig.class, System.getProperties());
    private static final String ALLURE_SELENIDE_LISTENER_NAME = "AllureSelenide";
    private static final String RAIFF_BANK_DEMO_PAGE_URL = "https://online.raiffeisen.ru/demo";

    @BeforeAll
    static void setup() {
        addListener(ALLURE_SELENIDE_LISTENER_NAME, new AllureSelenide().screenshots(true).savePageSource(true));
        Configuration.baseUrl = RAIFF_BANK_DEMO_PAGE_URL;
        Configuration.browser = CONFIG.browser();
        Configuration.timeout = 5000;
        if (CONFIG.remoteUrl() != null) {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("enableVNC", true);
            capabilities.setCapability("enableVideo", true);
            Configuration.browserCapabilities = capabilities;
            Configuration.remote = CONFIG.remoteUrl();
        }
    }

    @AfterEach
    public void afterEach() {
        attachScreenshot("Last screenshot");
        attachPageSource();
        attachAsText("Browser logs", getLogs());
        if (CONFIG.videoStorage() != null)
            attachVideo();
        closeWebDriver();
    }
}
