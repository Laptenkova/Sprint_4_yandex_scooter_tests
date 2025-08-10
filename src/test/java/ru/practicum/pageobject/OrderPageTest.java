package ru.practicum.pageobject;

import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.practicum.factory.DriverFactory;

import static org.junit.Assert.assertTrue;

public class OrderPageTest {

    @Rule
    public DriverFactory factory = new DriverFactory();


    @Test
    public void shouldCompleteOrderSuccessfully() {
        WebDriver driver = factory.getDriver();
        MainPage mainPage = new MainPage(driver);
        mainPage.clickCookiesAccept();
        OrderPage orderPage = mainPage.clickTopOrderButton();

        fillOrderForm(
                orderPage,
                "Иван",
                "Иванов",
                "Мясницкая, 19",
                "Чистые пруды",
                "89512345678",
                "10.08.2025",
                "сутки",
                true,
                "Привезти после 18:00");

        assertTrue(orderPage.isSuccessModalDisplayed());
    }

    @Test
    public void shouldCompleteOrderViaBottomButton() {
        WebDriver driver = factory.getDriver();
        MainPage mainPage = new MainPage(driver);
        mainPage.clickCookiesAccept();
        OrderPage orderPage = mainPage.clickBottomOrderButton();

        fillOrderForm(
                orderPage,
                "Иван",
                "Иванов",
                "Мясницкая, 19",
                "Чистые пруды",
                "89512345678",
                "10.08.2025",
                "сутки",
                true,
                "Привезти после 18:00");

        assertTrue(orderPage.isSuccessModalDisplayed());
    }

    private void fillOrderForm(
            OrderPage orderPage,
            String name,
            String surname,
            String address,
            String metro,
            String phone,
            String date,
            String duration,
            boolean isBlack,
            String comment) {
        orderPage.enterName(name);
        orderPage.enterSurname(surname);
        orderPage.enterAddress(address);
        orderPage.enterMetro(metro);
        orderPage.enterPhone(phone);
        orderPage.clickNextButton();

        orderPage.enterDeliveryDate(date);
        orderPage.selectRentDuration(duration);
        orderPage.setBlackPearlSelection(isBlack);
        orderPage.enterCourierComment(comment);
        orderPage.clickOrder();

        orderPage.clickConfirm();
    }
}