package ui.pages;

import io.qameta.allure.Step;
import ui.BasePage;

import static com.codeborne.selenide.Selenide.$;

public class LoansPage extends BasePage {

    @Step("Открыть раздел кредитов")
    public void openLoansSection() {
        $("nav.left-sidebar__nav > a[data-qa-node='left-sidebar__loans']").click();
    }

    @Step("Погасить потребительский кредит досрочно")
    public void closeLoan() {
        $("div.product-layout__footer div.product-layout__footer-side_left > div:nth-child(2)").click();
    }

    @Step("Выбрать всю сумму для погашения кредита")
    public void chooseFullLoanSum() {
        $("div.quick-sums__container > form-tag:nth-child(1)").click();
    }

    @Step("Подать заявление")
    public void submitRequest() {
        $("button[data-qa-node='submit-button']").click();
    }
}
