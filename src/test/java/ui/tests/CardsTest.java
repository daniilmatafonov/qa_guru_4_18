package ui.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ui.pages.CardsPage;

import static com.codeborne.selenide.Selenide.open;

@Tag("web")
@Tag("ui-tests")
public class CardsTest extends BaseTest{

    private final CardsPage cardsPage = new CardsPage();

    @DisplayName("Show credit cards")
    @Test
    public void showCreditCards() {
        open("/");
        cardsPage.closeInvitationPopUp();
        cardsPage.openCardsSection();
        cardsPage.clickCheckBoxDebitCards();
    }
}
