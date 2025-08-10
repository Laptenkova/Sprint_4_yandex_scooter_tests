package ru.practicum.pageobject;

import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.practicum.factory.DriverFactory;

import static org.junit.Assert.assertTrue;

/**
 * Тестовый класс для проверки успешного оформления заказа самоката.
 * Проверяет оформление заказа через верхнюю и нижнюю кнопку "Заказать" на главной странице
 */
public class OrderPageTest {

    @Rule
    public DriverFactory factory = new DriverFactory();

    /**
     * Проверяет успешное оформление заказа при использовании верхней кнопки "Заказать"
     * Заполняет форму заказа тестовыми данными и проверяет отображение окна успешного оформления
     */
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
                "11.08.2025",
                "сутки",
                true,
                "Привезти после 18:00");

        assertTrue(orderPage.isSuccessModalDisplayed());
    }

    /**
     * Проверяет успешное оформление заказа при использовании нижней кнопки "Заказать"
     * Заполняет форму заказа альтернативными тестовыми данными и проверяет отображение окна успешного оформления
     */
    @Test
    public void shouldCompleteOrderViaBottomButton() {
        WebDriver driver = factory.getDriver();
        MainPage mainPage = new MainPage(driver);
        mainPage.clickCookiesAccept();
        OrderPage orderPage = mainPage.clickBottomOrderButton();

        fillOrderForm(
                orderPage,
                "Петя",
                "Петров",
                "Остоженка, 47",
                "Парк культуры",
                "89112223344",
                "12.08.2025",
                "трое суток",
                true,
                "Позвонить за час до доставки");

        assertTrue(orderPage.isSuccessModalDisplayed());
    }

    /**
     * Заполняет форму заказа указанными данными и подтверждает заказ
     *
     * @param orderPage Страница оформления заказа
     * @param name      Имя заказчика
     * @param surname   Фамилия заказчика
     * @param address   Адрес доставки
     * @param metro     Станция метро
     * @param phone     Номер телефона
     * @param date      Дата доставки
     * @param duration  Срок аренды
     * @param isBlack   Выбран ли цвет "Черный жемчуг"
     * @param comment   Комментарий для курьера
     */
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