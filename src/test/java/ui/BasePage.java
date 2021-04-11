package ui;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class BasePage {

    @Step("Закрыть пригласительное окно")
    public void closeInvitationPopUp() {
        $("icon-close.one-click-popup__close").click();
    }

    @Step("Ввести смс-код")
    public void fillSmsGoalCode(String code) {
        $("confirm-operation-sms input").setValue(code);
    }

    @Step("Подтвердить операцию")
    public void approve() {
        $("button.confirm-operation-row__side-submit").click();
    }

    @Step("Проверить корректность проведения операции")
    public void validateOperation() {
        $("success-operation-title.success-operation-title").shouldHave(text("Операция выполнена"));
    }
}
