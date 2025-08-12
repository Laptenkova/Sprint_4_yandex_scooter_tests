package ru.practicum.pageobject;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static ru.practicum.constants.TimeOutConstants.WAIT_TIMEOUT_SECONDS;

/**
 * Класс  для работы с разделом "Вопросы о важном" на главной странице
 * Содержит методы для взаимодействия с вопросами и кнопками заказа
 */
public class MainPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Локатор вопросов
    private final By cookiesButton = By.cssSelector(".App_CookieButton__3cvqF");

    //Шаблон ID панели ответа в аккордеоне.
    //Используется для динамического получения локатора ответа по индексу вопроса.
    private final String ACCORDION_PANEL = "accordion__panel-%s";

    // Локаторы кнопок заказа
    private final By orderButtonTop = By.cssSelector("div.Header_Nav__AGCXC > button.Button_Button__ra12g");
    private final By orderButtonBottom = By.cssSelector(".Button_Button__ra12g.Button_Middle__1CSJM");

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
     * Ожидает, пока кнопка принятия куки станет кликабельной,
     * затем выполняет по ней клик для подтверждения согласия с использованием куки.
     */
    public void clickCookiesAccept() {
        wait.until(ExpectedConditions.elementToBeClickable(cookiesButton)).click();
    }

    /**
     * Выполняет клик по переданному локатору вопроса
     * Перед кликом скроллит элемент в центр окна
     * Если обычный клик блокируется, то выполняет клик через JavaScript.
     *
     * @param questionLocator локатор элемента вопроса для клика
     */
    public void clickQuestion(By questionLocator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(questionLocator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);

        try {
            element.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    /**
     * Получает текст ответа на вопрос по его ID, формируя локатор динамически.
     * Ждет видимости блока с ответом и возвращает его текст без лишних пробелов.
     *
     * @param questionId идентификатор вопроса
     * @return текст ответа
     */
    public String getAnswerText(String questionId) {
        By answerLocator = By.id(String.format(ACCORDION_PANEL, questionId));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(answerLocator)).getText().trim();
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


