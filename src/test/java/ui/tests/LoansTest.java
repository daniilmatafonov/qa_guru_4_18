package ui.tests;

import com.github.javafaker.Faker;
import io.qameta.allure.AllureId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ui.pages.LoansPage;

import static com.codeborne.selenide.Selenide.open;

@Tag("web")
@Tag("ui-tests")
public class LoansTest extends BaseTest {

    private final LoansPage loansPage = new LoansPage();
    private final Faker faker = new Faker();

    @AllureId("2235")
    @DisplayName("Close customer loan")
    @Test
    public void closeCustomerLoanSuccess() {
        String code = faker.number().digits(4);
        open("/");
        loansPage.closeInvitationPopUp();
        loansPage.openLoansSection();
        loansPage.closeLoan();
        loansPage.chooseFullLoanSum();
        loansPage.submitRequest();
        loansPage.fillSmsGoalCode(code);
        loansPage.approve();
        loansPage.validateOperation();
    }
}
