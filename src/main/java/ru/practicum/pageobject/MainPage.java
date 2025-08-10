package ru.practicum.pageobject;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.practicum.constants.MainPageConstants;

import java.time.Duration;

import static ru.practicum.constants.MainPageConstants.ORDER_BUTTON_BOTTOM_CSS;
import static ru.practicum.constants.MainPageConstants.ORDER_BUTTON_TOP_CSS;
import static ru.practicum.constants.TimeOutConstants.WAIT_TIMEOUT_SECONDS;

/**
 * Класс  для работы с разделом "Вопросы о важном" на главной странице
 * Содержит методы для взаимодействия с вопросами и кнопками заказа
 */
public class MainPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Локаторы вопросов
    private final By cookiesButton = By.cssSelector(MainPageConstants.COOKIES_BUTTON_CSS);
    private final By costQuestionButton = By.id(MainPageConstants.COST_QUESTION_ID);
    private final By multipleScootersQuestionButton = By.id(MainPageConstants.MULTIPLE_SCOOTERS_QUESTION_ID);
    private final By rentalTimeQuestionButton = By.id(MainPageConstants.RENTAL_TIME_QUESTION_ID);
    private final By todayOrderQuestionButton = By.id(MainPageConstants.TODAY_ORDER_QUESTION_ID);
    private final By extensionQuestionButton = By.id(MainPageConstants.EXTENSION_QUESTION_ID);
    private final By chargingQuestionButton = By.id(MainPageConstants.CHARGING_QUESTION_ID);
    private final By cancellationQuestionButton = By.id(MainPageConstants.CANCELLATION_QUESTION_ID);
    private final By deliveryBoundsQuestionButton = By.id(MainPageConstants.DELIVERY_BOUNDS_QUESTION_ID);

    // Локаторы ответов
    private final By costAnswer = By.id(MainPageConstants.COST_ANSWER_ID);
    private final By multipleScootersAnswer = By.id(MainPageConstants.MULTIPLE_SCOOTERS_ANSWER_ID);
    private final By rentalTimeAnswer = By.id(MainPageConstants.RENTAL_TIME_ANSWER_ID);
    private final By todayOrderAnswer = By.id(MainPageConstants.TODAY_ORDER_ANSWER_ID);
    private final By extensionAnswer = By.id(MainPageConstants.EXTENSION_ANSWER_ID);
    private final By chargingAnswer = By.id(MainPageConstants.CHARGING_ANSWER_ID);
    private final By cancellationAnswer = By.id(MainPageConstants.CANCELLATION_ANSWER_ID);
    private final By deliveryBoundsAnswer = By.id(MainPageConstants.DELIVERY_BOUNDS_ANSWER_ID);

    // Локаторы кнопок заказа
    private final By orderButtonTop = By.cssSelector(ORDER_BUTTON_TOP_CSS);
    private final By orderButtonBottom = By.cssSelector(ORDER_BUTTON_BOTTOM_CSS);

    /**
     * Конструктор класса, инициализирует новый экземпляр страницы
     *
     * @param driver WebDriver для взаимодействия с браузером
     */
    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS));
    }

    /**
     * Нажимает на кнопку принятия куки
     */
    public void clickCookiesAccept() {
        wait.until(ExpectedConditions.elementToBeClickable(cookiesButton)).click();
    }

    /**
     * Выполняет клик по переданному локатору вопроса
     * Скроллит элемент в центр окна и кликает по нему
     * При перехвате клика выполняет обход через JavaScript.
     *
     * @param questionLocator Локатор локатор вопроса для клика
     */
    private void clickQuestion(By questionLocator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(questionLocator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);

        try {
            element.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    /**
     * Получает текст ответа на вопрос из элемента с указанным локатором
     *
     * @param answerLocator Локатор блока ответа
     * @return Текст ответа с обрезанными пробелами
     */
    private String getAnswerText(By answerLocator) {
        WebElement answer = wait.until(ExpectedConditions.visibilityOfElementLocated(answerLocator));
        return answer.getText().trim();
    }

    /**
     * Раскрывает блок с ответом на вопрос "Сколько это стоит? И как оплатить?"
     */
    public void expandCostQuestion() {
        clickQuestion(costQuestionButton);
    }

    /**
     * Возвращает текст ответа на вопрос "Сколько это стоит? И как оплатить?
     *
     * @return текст ответа
     */
    public String getCostAndPaymentAnswer() {
        return getAnswerText(costAnswer);
    }

    /**
     * Раскрывает блок с ответом на вопрос "Хочу сразу несколько самокатов! Так можно?"
     */
    public void expandMultipleScootersQuestion() {
        clickQuestion(multipleScootersQuestionButton);
    }

    /**
     * Возвращает текст ответа на вопрос "Хочу сразу несколько самокатов! Так можно?"
     *
     * @return текст ответа
     */
    public String getMultiScootersAllowedAnswer() {
        return getAnswerText(multipleScootersAnswer);
    }

    /**
     * Раскрывает блок с ответом на вопрос "Как рассчитывается время аренды?"
     */
    public void expandRentalTimeQuestion() {
        clickQuestion(rentalTimeQuestionButton);
    }

    /**
     * Возвращает текст ответа на вопрос "Как рассчитывается время аренды?"
     *
     * @return текст ответа
     */
    public String getRentalTimeAnswer() {
        return getAnswerText(rentalTimeAnswer);
    }

    /**
     * Раскрывает блок с ответом на вопрос "Можно ли заказать самокат прямо на сегодня?"
     */
    public void expandTodayOrderQuestion() {
        clickQuestion(todayOrderQuestionButton);
    }

    /**
     * Возвращает текст ответа на вопрос "Можно ли заказать самокат прямо на сегодня?"
     *
     * @return текст ответа
     */
    public String getTodayOrderAnswer() {
        return getAnswerText(todayOrderAnswer);
    }

    /**
     * Раскрывает блок с ответом на вопрос "Можно ли продлить заказ или вернуть самокат раньше?"
     */
    public void expandRentalExtensionQuestion() {
        clickQuestion(extensionQuestionButton);
    }

    /**
     * Возвращает текст ответа на вопрос "Можно ли продлить заказ или вернуть самокат раньше?"
     *
     * @return текст ответа
     */
    public String getRentalExtensionAnswer() {
        return getAnswerText(extensionAnswer);
    }

    /**
     * Раскрывает блок с ответом на вопрос "Вы привозите зарядку вместе с самокатом?"
     */
    public void expandChargingOptionQuestion() {
        clickQuestion(chargingQuestionButton);
    }

    /**
     * Возвращает текст ответа на вопрос "Вы привозите зарядку вместе с самокатом?"
     *
     * @return текст ответа
     */
    public String getChargingOptionAnswer() {
        return getAnswerText(chargingAnswer);
    }

    /**
     * Раскрывает блок с ответом на вопрос "Можно ли отменить заказ?"
     */
    public void expandCancellationPolicyQuestion() {
        clickQuestion(cancellationQuestionButton);
    }

    /**
     * Возвращает текст ответа на вопрос "Можно ли отменить заказ?"
     *
     * @return текст ответа
     */
    public String getCancellationPolicyAnswer() {
        return getAnswerText(cancellationAnswer);
    }

    /**
     * Раскрывает блок с ответом на вопрос "Я живу за МКАДом, привезете?"
     */
    public void expandDeliveryBoundsQuestion() {
        clickQuestion(deliveryBoundsQuestionButton);
    }

    /**
     * Возвращает текст ответа на вопрос "Я живу за МКАДом, привезете?"
     *
     * @return текст ответа
     */
    public String getDeliveryBoundsAnswer() {
        return getAnswerText(deliveryBoundsAnswer);
    }

    /**
     * Кликает на кнопку "Заказать" вверху
     *
     * @return Страница оформления заказа
     */
    public OrderPage clickTopOrderButton() {
        wait.until(ExpectedConditions.elementToBeClickable(orderButtonTop)).click();
        return new OrderPage(driver);
    }

    /**
     * Кликает на кнопку "Заказать" внизу страницы (после вопросов)
     *
     * @return Страница оформления заказа
     */
    public OrderPage clickBottomOrderButton() {
        wait.until(ExpectedConditions.elementToBeClickable(orderButtonBottom)).click();
        return new OrderPage(driver);
    }
}


