package ui.pages;

import io.qameta.allure.Step;
import ui.BasePage;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class CardsPage extends BasePage {

    @Step("Открыть раздел банковских карт")
    public void openCardsSection() {
        $("nav.left-sidebar__nav > a[data-qa-node='left-sidebar__cards']").click();
    }

    @Step("Установить флажок Дебетовые")
    public void clickCheckBoxDebitCards() {
        $(byText("Дебетовые")).click();
    }

    @Step("Установить флажок Кредитные")
    public void clickCheckBoxCreditCards() {
        $(byText("Кредитные")).click();
    }
}
