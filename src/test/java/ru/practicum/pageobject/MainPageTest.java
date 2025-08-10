package ru.practicum.pageobject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.practicum.factory.DriverFactory;

import static org.junit.Assert.assertTrue;

/**
 * Тесты для страницы MainPage.
 * Проверяет корректность раскрытия вопросов и отображения ответов в разделе "Вопросы о важном"
 */
public class MainPageTest {

    private WebDriver driver;
    private MainPage mainPage;

    /**
     * Инициализирует WebDriver и создаёт объект MainPage перед выполнением каждого теста
     */
    @Before
    public void setUp() {
        DriverFactory driverFactory = new DriverFactory();
        driver = driverFactory.initializeDriver();

        mainPage = new MainPage(driver);
    }

    /**
     * Закрывает WebDriver после каждого теста
     */
    @After
    public void tearDown() {
        driver.quit();
    }

    /**
     * Проверяет, что при раскрытии вопроса "Сколько это стоит? И как оплатить?"
     * отображается ожидаемый текст ответа
     */
    @Test
    public void shouldShowsCostAnswer() {
        mainPage.expandCostQuestion();
        String answer = mainPage.getCostAndPaymentAnswer();
        assertTrue(answer.contains("Сутки — 400 рублей. Оплата курьеру — наличными или картой."));
    }

    /**
     * Проверяет, что при раскрытии вопроса "Хочу сразу несколько самокатов! Так можно?"
     * отображается ожидаемый текст ответа
     */
    @Test
    public void shouldShowsMultipleScootersAnswer() {
        mainPage.expandMultipleScootersQuestion();
        String answer = mainPage.getMultiScootersAllowedAnswer();
        assertTrue(answer.contains("Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."));
    }

    /**
     * Проверяет, что при раскрытии вопроса "Как рассчитывается время аренды?"
     * отображается корректное разъяснение
     */
    @Test
    public void shouldShowRentalTimeAnswer() {
        mainPage.expandRentalTimeQuestion();
        String answer = mainPage.getRentalTimeAnswer();
        assertTrue(answer.contains("Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."));
    }

    /**
     * Проверяет, что при раскрытии вопроса "Можно ли заказать самокат прямо на сегодня?"
     * отображается информация о невозможности заказа на сегодня.
     */
    @Test
    public void shouldShowTodayOrderAnswer() {
        mainPage.expandTodayOrderQuestion();
        String answer = mainPage.getTodayOrderAnswer();
        assertTrue(answer.contains("Только начиная с завтрашнего дня. Но скоро станем расторопнее."));
    }

    /**
     * Проверяет, что при раскрытии вопроса "Можно ли продлить заказ или вернуть самокат раньше?"
     * отображается корректная информация о невозможности продления.
     */
    @Test
    public void shouldShowRentalExtensionAnswer() {
        mainPage.expandRentalExtensionQuestion();
        String answer = mainPage.getRentalExtensionAnswer();
        assertTrue(answer.contains("Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."));
    }

    /**
     * Проверяет, что при раскрытии вопроса "Вы привозите зарядку вместе с самокатом?"
     * отображается информация о полной зарядке при доставке.
     */
    @Test
    public void shouldShowChargingOptionAnswer() {
        mainPage.expandChargingOptionQuestion();
        String answer = mainPage.getChargingOptionAnswer();
        assertTrue(answer.contains("Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."));
    }


    /**
     * Проверяет, что при раскрытии вопроса "Можно ли отменить заказ?"
     * отображается информация о возможности отмены без штрафа.
     */
    @Test
    public void shouldShowCancellationPolicyAnswer() {
        mainPage.expandCancellationPolicyQuestion();
        String answer = mainPage.getCancellationPolicyAnswer();
        assertTrue(answer.contains("Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."));
    }

    /**
     * Проверяет, что при раскрытии вопроса "Я живу за МКАДом, привезете?"
     * отображается информация о покрытии доставки.
     */
    @Test
    public void shoudShowDeliveryBoundsAnswer() {
        mainPage.expandDeliveryBoundsQuestion();
        String answer = mainPage.getDeliveryBoundsAnswer();
        assertTrue(answer.contains("Да, обязательно. Всем самокатов! И Москве, и Московской области."));
    }
}

