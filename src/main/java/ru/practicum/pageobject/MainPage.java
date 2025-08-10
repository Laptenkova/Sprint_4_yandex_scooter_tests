package ru.practicum.pageobject;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Класс MainPage для работы с разделом "Вопросы о важном" на главной странице.
 * Содержит локаторы и методы для взаимодействия с вопросами и ответами.
 * Позволяет раскрывать вопросы и получать показанный текст ответов.
 */
public class MainPage {
    private WebDriver driver;
    private WebDriverWait wait;

    /**
     * Локаторы кнопок вопросов
     */
    private final By cookiesButton = By.cssSelector(".App_CookieButton__3cvqF");
    private final By costQuestionButton = By.id("accordion__heading-0");
    private final By multipleScootersQuestionButton = By.id("accordion__heading-1");
    private final By rentalTimeQuestionButton = By.id("accordion__heading-2");
    private final By todayOrderQuestionButton = By.id("accordion__heading-3");
    private final By extensionQuestionButton = By.id("accordion__heading-4");
    private final By chargingQuestionButton = By.id("accordion__heading-5");
    private final By cancellationQuestionButton = By.id("accordion__heading-6");
    private final By deliveryBoundsQuestionButton = By.id("accordion__heading-7");

    /**
     * Локаторы блоков с ответами
     */
    private final By costAnswer = By.id("accordion__panel-0");
    private final By multipleScootersAnswer = By.id("accordion__panel-1");
    private final By rentalTimeAnswer = By.id("accordion__panel-2");
    private final By todayOrderAnswer = By.id("accordion__panel-3");
    private final By extensionAnswer = By.id("accordion__panel-4");
    private final By chargingAnswer = By.id("accordion__panel-5");
    private final By cancellationAnswer = By.id("accordion__panel-6");
    private final By deliveryBoundsAnswer = By.id("accordion__panel-7");


    /** Локаторы кнопки "Заказать".
     * На странице присутствует две кнопки "Заказать":
     * одна в верхней части страницы (в хедере), другая в середине страницы(после вопросов).
     * локаторы указывает на нужную из них.
     */
    private final By orderButtonTop = By.cssSelector("div.Header_Nav__AGCXC > button.Button_Button__ra12g");
    private final By orderButtonBottom = By.cssSelector(".Button_Button__ra12g.Button_Middle__1CSJM");

    /**
     * Конструктор класса, инициализирует WebDriver и WebDriverWait.
     *
     * @param driver WebDriver для взаимодействия с браузером.
     */
    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    /**
     * Кликает на кнопку "Принять куки" для перехода ко второй странице заказа
     */
    public void clickCookiesAccept() {
        wait.until(ExpectedConditions.elementToBeClickable(cookiesButton)).click();
    }

    /**
     * Вспомогательный метод для клика по вопросу.
     */
    private void clickQuestion(By questionLocator) {

        /**
         * Ждет, пока элемент станет кликабельным
         **/
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(questionLocator));

        /**
         * Скроллит элемент в центр видимой области окна браузера
         **/
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);

        try {
            /**
             * Пробует кликнуть стандартным способом
             **/
            element.click();
        } catch (ElementClickInterceptedException e) {

            /**
             * Если клик блокируется — делает клик через JS (обход)
             */
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    /**
     * Вспомогательный метод для получения текста ответа
     */
    private String getAnswerText(By answerLocator) {

        /**
         * Ждет видимость блока с ответом и возвращает его текст, обрезая пробелы по краям
         */
        WebElement answer = wait.until(ExpectedConditions.visibilityOfElementLocated(answerLocator));
        return answer.getText().trim();
    }

    /**
     * Методы для каждого вопроса и ответа
     * 1. Раскрывает блок с ответом на вопрос "Сколько это стоит? И как оплатить?"
     */
    public void expandCostQuestion() {
        /**
         * Кликает по вопросу
         */
        clickQuestion(costQuestionButton);
    }

    /**
     * Возвращает текст ответа на вопрос "Сколько это стоит? И как оплатить?"
     */
    public String getCostAndPaymentAnswer() {
        return getAnswerText(costAnswer);
    }

    /**
     * 2 Раскрывает блок с ответом на вопрос "Хочу сразу несколько самокатов! Так можно?"
     */
    public void expandMultipleScootersQuestion() {
        /**
         * Кликает по вопросу
         */
        clickQuestion(multipleScootersQuestionButton);
    }

    /**
     * Возвращает текст ответа на вопрос "Хочу сразу несколько самокатов! Так можно?"
     */
    public String getMultiScootersAllowedAnswer() {
        return getAnswerText(multipleScootersAnswer);
    }

    /**
     * 3 Раскрывает блок с ответом на вопрос "Как рассчитывается время аренды?"
     */
    public void expandRentalTimeQuestion() {
        /**
         * Кликает по вопросу
         */
        clickQuestion(rentalTimeQuestionButton);
    }

    /**
     * Возвращает текст ответа на вопрос "Как рассчитывается время аренды?"
     */
    public String getRentalTimeAnswer() {
        return getAnswerText(rentalTimeAnswer);
    }

    /**
     * 4 Раскрывает блок с ответом на вопрос "Можно ли заказать самокат прямо на сегодня?"
     */
    public void expandTodayOrderQuestion() {
        /**
         * Кликает по вопросу
         */
        clickQuestion(todayOrderQuestionButton);
    }

    /**
     * Возвращает текст ответа на вопрос "Можно ли заказать самокат прямо на сегодня?"
     */
    public String getTodayOrderAnswer() {
        return getAnswerText(todayOrderAnswer);
    }

    /**
     * 5 Раскрывает блок с ответом на вопрос "Можно ли продлить заказ или вернуть самокат раньше?"
     */
    public void expandRentalExtensionQuestion() {
        /**
         * Кликает по вопросу
         */
        clickQuestion(extensionQuestionButton);
    }

    /**
     * Возвращает текст ответа на вопрос "Можно ли продлить заказ или вернуть самокат раньше?"
     */
    public String getRentalExtensionAnswer() {
        return getAnswerText(extensionAnswer);
    }

    /**
     * 6 Раскрывает блок с ответом на вопрос "Вы привозите зарядку вместе с самокатом?"
     */
    public void expandChargingOptionQuestion() {
        /**
         * Кликает по вопросу
         */
        clickQuestion(chargingQuestionButton);
    }

    /**
     * Возвращает текст ответа на вопрос "Вы привозите зарядку вместе с самокатом?"
     */
    public String getChargingOptionAnswer() {
        return getAnswerText(chargingAnswer);
    }

    /**
     * 7 Раскрывает блок с ответом на вопрос "Можно ли отменить заказ?"
     */
    public void expandCancellationPolicyQuestion() {
        /**
         * Кликает по вопросу
         */
        clickQuestion(cancellationQuestionButton);
    }

    /**
     * Возвращает текст ответа на вопрос "Можно ли отменить заказ?"
     */
    public String getCancellationPolicyAnswer() {
        return getAnswerText(cancellationAnswer);
    }

    /**
     * 8 Раскрывает блок с ответом на вопрос "Я живу за МКАДом, привезете?"
     */
    public void expandDeliveryBoundsQuestion() {
        /**
         * Кликает по вопросу
         */
        clickQuestion(deliveryBoundsQuestionButton);
    }

    /**
     * Возвращает текст ответа на вопрос "Я живу за МКАДом, привезете?"
     */
    public String getDeliveryBoundsAnswer() {
        return getAnswerText(deliveryBoundsAnswer);
    }

    /**
     * Методы для кнопки "Заказать".
     * Кликает на кнопку "Заказать вверху"
     */
    public OrderPage clickTopOrderButton() {
        wait.until(ExpectedConditions.elementToBeClickable(orderButtonTop)).click();

        return new OrderPage(driver);
    }

    /**
     * Кликает на кнопку "Заказать" в середине страницы(после вопросов).
     */
    public OrderPage clickBottomOrderButton() {
        wait.until(ExpectedConditions.elementToBeClickable(orderButtonBottom)).click();

        return new OrderPage(driver);
    }
}


