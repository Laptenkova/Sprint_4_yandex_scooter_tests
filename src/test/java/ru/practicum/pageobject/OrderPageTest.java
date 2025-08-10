package ru.practicum.pageobject;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import ru.practicum.factory.DriverFactory;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class OrderPageTest {

    @Rule
    public DriverFactory factory = new DriverFactory();

    private final String name;
    private final String surname;
    private final String address;
    private final String metro;
    private final String phone;
    private final String date;
    private final String duration;
    private final boolean isBlack;
    private final String comment;
    private final boolean useTopButton;

    public OrderPageTest(String name,
                         String surname,
                         String address,
                         String metro,
                         String phone,
                         String date,
                         String duration,
                         boolean isBlack,
                         String comment,
                         boolean useTopButton) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
        this.date = date;
        this.duration = duration;
        this.isBlack = isBlack;
        this.comment = comment;
        this.useTopButton = useTopButton;
    }

    @Parameterized.Parameters(name = "Тест заказа с данными: {0} {1}, кнопка: {9}")
    public static Object[][] getOrderData() {
        return new Object[][]{
                // Тест 1: данные для верхней кнопки заказа
                {
                        "Иван",
                        "Иванов",
                        "Мясницкая, 19",
                        "Чистые пруды",
                        "89512345678",
                        "11.08.2025",
                        "сутки",
                        true,
                        "Привезти после 18:00",
                        true
                },
                // Тест 2: альтернативные данные для нижней кнопки заказа
                {
                        "Петя",
                        "Петров",
                        "Остоженка, 47",
                        "Парк культуры",
                        "89112223344",
                        "12.08.2025",
                        "трое суток",
                        true,
                        "Позвонить за час до доставки",
                        false
                }
        };
    }

    /**
     * Тест проверяет успешное оформление заказа с разными данными
     * и через разные кнопки (верхнюю/нижнюю)
     */
    @Test
    public void shouldCompleteOrderSuccessfully() {
        WebDriver driver = factory.getDriver();
        MainPage mainPage = new MainPage(driver);
        mainPage.clickCookiesAccept();

        OrderPage orderPage = useTopButton ?
                mainPage.clickTopOrderButton() :
                mainPage.clickBottomOrderButton();

        fillOrderForm(orderPage);
        assertTrue(orderPage.isSuccessModalDisplayed());
    }

    private void fillOrderForm(OrderPage orderPage) {
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