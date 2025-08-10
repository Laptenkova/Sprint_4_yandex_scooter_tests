package ru.practicum.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.practicum.constants.OrderPageConstants;

import java.time.Duration;

import static ru.practicum.constants.OrderPageConstants.DURATION_DROPDOWN_CSS;
import static ru.practicum.constants.OrderPageConstants.RENT_DURATION_OPTION_XPATH_TEMPLATE;
import static ru.practicum.constants.TimeOutConstants.WAIT_TIMEOUT_SECONDS;

/**
 * Класс для страницы оформления заказа самоката.
 * Содержит локаторы и методы для взаимодействия с элементами формы заказа,
 * а также с модальными окнами подтверждения и успешного оформления заказа.
 */
public class OrderPage {

    private final WebDriver driver;
    private WebDriverWait wait;

    // Локаторы элементов
    private final By nameField = By.cssSelector(OrderPageConstants.NAME_FIELD_CSS);
    private final By surnameField = By.cssSelector(OrderPageConstants.SURNAME_FIELD_CSS);
    private final By addressField = By.cssSelector(OrderPageConstants.ADDRESS_FIELD_CSS);
    private final By metroField = By.cssSelector(OrderPageConstants.METRO_FIELD_INPUT_CSS);
    private final By metroFieldFirstOption = By.cssSelector(OrderPageConstants.METRO_FIELD_OPTION_CSS);
    private final By phoneField = By.cssSelector(OrderPageConstants.PHONE_FIELD_CSS);
    private final By cookiesButton = By.cssSelector(OrderPageConstants.COOKIES_BUTTON_CSS);
    private final By nextButton = By.cssSelector(OrderPageConstants.NEXT_BUTTON_CSS);
    private final By dateField = By.cssSelector(OrderPageConstants.DATE_FIELD_CSS);
    private final By rentDropdownPlaceholder = By.cssSelector(DURATION_DROPDOWN_CSS);
    private final By colorCheckbox = By.cssSelector(OrderPageConstants.COLOR_CHECKBOX_CSS);
    private final By commentField = By.cssSelector(OrderPageConstants.COMMENT_FIELD_CSS);
    private final By orderButton = By.xpath(OrderPageConstants.ORDER_BUTTON_XPATH);
    private final By modalWindow = By.cssSelector(OrderPageConstants.MODAL_WINDOW_CSS);
    private final By confirmButton = By.xpath(OrderPageConstants.CONFIRM_BUTTON_XPATH);
    private final By statusButton = By.xpath(OrderPageConstants.STATUS_BUTTON_XPATH);

    /**
     * Инициализирует новый экземпляр страницы оформления заказа
     *
     * @param driver WebDriver для взаимодействия с браузером
     */
    public OrderPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS));
    }

    /**
     * Заполняет поле "Имя" указанным значением
     *
     * @param name Имя для ввода
     */
    public void enterName(String name) {
        wait.until(ExpectedConditions.elementToBeClickable(nameField)).sendKeys(name);
    }

    /**
     * Заполняет поле "Фамилия" указанным значением
     *
     * @param surname Фамилия для ввода
     */
    public void enterSurname(String surname) {
        wait.until(ExpectedConditions.elementToBeClickable(surnameField)).sendKeys(surname);
    }

    /**
     * Заполняет поле "Адрес: куда привезти заказ" указанным значением
     *
     *  @param address Адрес для ввода
     */
    public void enterAddress(String address) {
        wait.until(ExpectedConditions.elementToBeClickable(addressField)).sendKeys(address);
    }

    /**
     * Заполняет поле "Станция метро" указанным значением и выбирает станцию из выпадающего списка.
     *
     * @param metro название станции метро для выбора
     */
    public void enterMetro(String metro) {
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(metroField));
        input.click();
        input.sendKeys(metro);

        wait.until(ExpectedConditions.elementToBeClickable(metroFieldFirstOption)).click();
    }

    /**
     * Заполняет поле "Телефон: на него позвонит курьер" указанным значением
     *
     * @param phone Номер телефона для ввода
     */
    public void enterPhone(String phone) {
        wait.until(ExpectedConditions.elementToBeClickable(phoneField)).sendKeys(phone);
    }

    /**
     * Кликает на кнопку "Принять куки"
     */
    public void clickCookiesAccept() {
        wait.until(ExpectedConditions.elementToBeClickable(cookiesButton)).click();
    }

    /**
     * Кликает на кнопку "Далее" для перехода для перехода к следующему шагу
     */
    public void clickNextButton() {
        wait.until(ExpectedConditions.elementToBeClickable(nextButton)).click();
    }

    /**
     * Заполняет поле "Когда привезти самокат" указанным значением
     *
     * @param date Дата в формате строки
     */
    public void enterDeliveryDate(String date) {
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(dateField));
        input.click();
        input.sendKeys(date);
        input.sendKeys(Keys.ENTER);
    }

    /**
     * Выбирает срок аренды из выпадающего списка.
     *
     * @param duration Срок аренды (например, "сутки")
     */
    public void selectRentDuration(String duration) {
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(rentDropdownPlaceholder));
        dropdown.click();

        By optionLocator = By.xpath(String.format(RENT_DURATION_OPTION_XPATH_TEMPLATE, duration));
        wait.until(ExpectedConditions.elementToBeClickable(optionLocator)).click();
    }

    /**
     * Устанавливает состояние выбора цвета "Чёрный жемчуг"
     *
     * @param shouldBeSelected true - выбрать цвет, false - отменить выбор
     */
    public void setBlackPearlSelection(boolean shouldBeSelected) {
        WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(colorCheckbox));
        if (checkbox.isSelected() != shouldBeSelected) {
            checkbox.click();
        }
    }

    /**
     * Заполняет поле "Комментарий для курьера"
     *
     * @param comment Текст комментария
     */
    public void enterCourierComment(String comment) {
        wait.until(ExpectedConditions.elementToBeClickable(commentField)).sendKeys(comment);
    }

    /**
     * Кликает на кнопку "Заказать" для отправки формы
     */
    public void clickOrder() {
        wait.until(ExpectedConditions.elementToBeClickable(orderButton)).click();
    }

    /**
     * Кликает на кнопку "Да" в окне подтверждения заказа
     */
    public void clickConfirm() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(modalWindow));
        wait.until(ExpectedConditions.elementToBeClickable(confirmButton)).click();
    }

    /**
     * Проверяет, отображается ли окно успешного оформления заказа с текстом "Заказ оформлен"
     *
     * @return true если окно отображается и содержит ожидаемый текст
     */
    public boolean isSuccessModalDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(modalWindow)).getText().contains("Заказ оформлен");
    }

    /**
     * Кликает на кнопку "Посмотреть статус" в окне успешного оформления заказа
     */
    public void clickViewStatus() {
        wait.until(ExpectedConditions.elementToBeClickable(statusButton)).click();
    }
}