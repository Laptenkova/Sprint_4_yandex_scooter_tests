package ru.practicum.pageobject;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.practicum.factory.DriverFactory;

import static org.junit.Assert.assertTrue;

public class OrderPageTest {

    @Rule
    private DriverFactory factory = new DriverFactory();


    @Before
    public void setUp() {
        /**
         * Инициализирует WebDriver перед каждым тестом
         */
        DriverFactory driverFactory = new DriverFactory();
        driver = driverFactory.initializeDriver();

        /**
         *  Инициализирует объект страницы
         */
        orderPage = new OrderPage(driver);
    }

    @After
    public void tearDown() {
        /**
         *  Закрывает драйвер после каждого теста, чтобы освободить ресурсы
         */
        driver.quit();
    }

    @Test
    public void shouldCompleteOrderSuccessfully() {
        // Тест для верхней кнопки
        //orderPage.startOrderFromHeader();
        fillOrderForm("Иван", "Иванов", "Мясницкая, 19", "Чистые пруды", "89512345678", "10.08.2025", "сутки", true, "Привезти после 18:00");

        assertTrue(orderPage.isSuccessModalDisplayed());
    }]

    @Test
    public void shouldCompleteOrderViaBottomButton() {
        // Тест для нижней кнопки
        //orderPage.startOrderFromFooter();
        fillOrderForm("Петр", "Петров", "Остоженка, 47", "Парк культуры", "89112223344", "11.08.2025", "трое суток", false, "Прошу позвонить за час до доставки");

        assertTrue(orderPage.isSuccessModalDisplayed());
    }

    private void fillOrderForm(
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

    @Test
    public void enterName() {
    }

    @Test
    public void enterSurname() {
    }

    @Test
    public void enterAddress() {
    }

    @Test
    public void enterMetro() {
    }

    @Test
    public void enterPhone() {
    }

    @Test
    public void clickNextButton() {
    }

    @Test
    public void enterDeliveryDate() {
    }

    @Test
    public void selectRentDuration() {
    }

    @Test
    public void setBlackPearlSelection() {
    }

    @Test
    public void enterCourierComment() {
    }

    @Test
    public void clickOrder() {
    }

    @Test
    public void clickYesOnConfirmationModal() {
    }

    @Test
    public void waitForOrderCreatedModal() {
    }

    @Test
    public void clickViewStatus() {
    }
}