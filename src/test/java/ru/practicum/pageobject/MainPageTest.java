package ru.practicum.pageobject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.practicum.factory.DriverFactory;

import static org.junit.Assert.assertTrue;

public class MainPageTest {

    private WebDriver driver;
    private MainPage mainPage;

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
        mainPage = new MainPage(driver);
    }

    @After
    public void tearDown() {
        /**
         *  Закрывает драйвер после каждого теста, чтобы освободить ресурсы
         */
        driver.quit();
    }

    @Test
    public void shouldShowsCostAnswer() {
        /**
         * 1. Раскрывает вопрос "Сколько это стоит? И как оплатить?"
         */
        mainPage.expandCostQuestion();

        /**
         * Получает текст ответа на этот вопрос
         */
        String answer = mainPage.getCostAndPaymentAnswer();

        /**
         * Проверяет, что ответ содержит ожидаемую информацию о стоимости и оплате
         */
        assertTrue(answer.contains("Сутки — 400 рублей. Оплата курьеру — наличными или картой."));
    }

    @Test
    public void shouldShowsMultipleScootersAnswer() {
        /**
         * 2. Раскрывает вопрос "Хочу сразу несколько самокатов! Так можно?"
         * /
         mainPage.expandMultipleScootersQuestion();

         /**
         * Получает текст ответа на этот вопрос
         */
        String answer = mainPage.getMultiScootersAllowedAnswer();

        /**
         * Проверяет, что ответ содержит ожидаемое пояснение о количестве заказов
         */
        assertTrue(answer.contains("Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."));
    }

    @Test
    public void shouldShowRentalTimeAnswer() {
        /**
         * 3. Раскрывает вопрос "Как рассчитывается время аренды?"
         */
        mainPage.expandRentalTimeQuestion();

        /**
         * Получает текст ответа на этот вопрос
         * */
        String answer = mainPage.getRentalTimeAnswer();

        /**
         * Проверяет, что ответ содержит разъяснение по началу и окончанию аренды
         * */
        assertTrue(answer.contains("Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."));
    }

    @Test
    public void shouldShowTodayOrderAnswer() {
        /**
         * 4. Раскрывает вопрос "Можно ли заказать самокат прямо на сегодня?" */
        mainPage.expandTodayOrderQuestion();

        /**
         * Получает текст ответа на этот вопрос
         * */
        String answer = mainPage.getTodayOrderAnswer();

        /**
         * Проверяет, что ответ содержит информацию о невозможности заказа на сегодня
         * */
        assertTrue(answer.contains("Только начиная с завтрашнего дня. Но скоро станем расторопнее."));
    }

    @Test
    public void shouldShowRentalExtensionAnswer() {
        /**
         * 5. Раскрывает вопрос "Можно ли продлить заказ или вернуть самокат раньше?"
         */
        mainPage.expandRentalExtensionQuestion();

        /**
         * Получает текст ответа на этот вопрос
         */
        String answer = mainPage.getRentalExtensionAnswer();

        /**
         * Проверяет, что ответ содержит информацию о невозможности продления сейчас и вариантах поддержки
         */
        assertTrue(answer.contains("Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."));
    }

    @Test
    public void shouldShowChargingOptionAnswer() {
        /**
         * 6. Раскрывает вопрос "Вы привозите зарядку вместе с самокатом?"
         * */
        mainPage.expandChargingOptionQuestion();

        /**
         * Получает текст ответа на этот вопрос
         */
        String answer = mainPage.getChargingOptionAnswer();

        /**
         * Проверяет, что ответ содержит информацию о полной зарядке при доставке
         */
        assertTrue(answer.contains("Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."));
    }

    @Test
    public void shouldShowCancellationPolicyAnswer() {
        /**
         * 7. Раскрывает вопрос "Можно ли отменить заказ?"
         * */
        mainPage.expandCancellationPolicyQuestion();

        /**
         * Получает текст ответа на этот вопрос
         */
        String answer = mainPage.getCancellationPolicyAnswer();

        /**
         * Проверяет, что ответ содержит информацию о возможности отмены без штрафа и без объяснений
         */
        assertTrue(answer.contains("Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."));
    }


    @Test
    public void shoudShowDeliveryBoundsAnswer() {

        /**
         * 8. Раскрывает вопрос "Я жизу за МКАДом, привезёте?"
         */
        mainPage.expandDeliveryBoundsQuestion();

        /**
         Получает текст ответа на этот вопрос
         */
        String answer = mainPage.getDeliveryBoundsAnswer();

        /**
         * Проверяеn, что ответ содержит информацию о покрытии доставки Москвы и Московской области
         * */
        assertTrue(answer.contains("Да, обязательно. Всем самокатов! И Москве, и Московской области."));
    }
}

