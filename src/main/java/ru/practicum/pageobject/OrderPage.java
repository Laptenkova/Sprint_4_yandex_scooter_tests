package ru.practicum.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Page Object класс для страницы оформления заказа самоката.
 * Содержит локаторы и методы для взаимодействия с элементами формы заказа,
 * а также с модальными окнами подтверждения и успешного оформления заказа.
 */
public class OrderPage {

    /**
     * WebDriver для взаимодействия с браузером
     */
    private WebDriver driver;

    /**
     * Явное ожидание для синхронизации действий с элементами страницы
     */
    private WebDriverWait wait;

    /**
     * Локаторы элементов первой страницы формы заказа
     * Поле ввода "Имя"
     */
    private final By nameField = By.cssSelector("[placeholder='* Имя']");

    /**
     * Поле ввода "Фамилия"
     */
    private final By surnameField = By.cssSelector("[placeholder='* Фамилия']");

    /**
     * Поле ввода "Адрес: куда привезти заказ"
     */
    private final By addressField = By.cssSelector("[placeholder='* Адрес: куда привезти заказ']");

    /**
     * Поле выбора станции метро (выпадающий список с поиском)
     */
    private final By metroField = By.cssSelector(".select-search__input");

    /**
     * Поле ввода "Телефон: на него позвонит курьер"
     */
    private final By phoneField = By.cssSelector("[placeholder='* Телефон: на него позвонит курьер']");

    /**
     * Кнопка "Далее" — переход ко второй странице формы
     */
    private final By nextButton = By.cssSelector(".Button_Middle__1CSJM");

    /**
     * Локаторы элементов второй страницы формы заказа
     * Поле выбора даты "Когда привезти самокат"
     */
    private final By dateField = By.cssSelector("[placeholder='* Когда привезти самокат']");

    /**
     * Выпадающий список "Срок аренды"
     */
    private final By durationDropdown = By.cssSelector(".Dropdown-placeholder:not(.is-selected)");

    /**
     * Чекбокс для выбора цвета самоката "Черный жемчуг"
     */
    private final By colorCheckbox = By.cssSelector("input#black");

    /**
     * Поле ввода комментария для курьера
     */
    private final By commentField = By.cssSelector("[placeholder='Комментарий для курьера']");

    /**
     * Кнопка "Заказать" — отправляет заказ и открывает модальное окно
     */
    private final By orderButton = By.xpath("//button[text()='Заказать']");

    /**
     * Локаторы для модальных окон подтверждения и успешного заказа
     * Модальное окно с подтверждением заказа "Хотите оформить заказ?"
     */
    private final By modalWindow = By.cssSelector("div.Order_Modal__YZ-d3");

    /**
     * Кнопка "Да" в окне подтверждения заказа
     */
    private final By confirmButton = By.xpath("//button[text()='Да']");

    /**
     * Кнопка "Посмотреть статус" в окне успешного оформления заказа
     */
    private final By statusButton = By.xpath("//button[text()='Посмотреть статус']");

    /**
     * Конструктор класса.
     * Инициализирует WebDriver и WebDriverWait.
     */
    public OrderPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    /**
     * Методы работы с первой страницей формы заказа
     * Заполняет поле "Имя" указанным значением
     */
    public void enterName(String name) {
        wait.until(ExpectedConditions.elementToBeClickable(nameField)).sendKeys(name);
    }

    /**
     * Заполняет поле "Фамилия" указанным значением
     */
    public void enterSurname(String surname) {
        wait.until(ExpectedConditions.elementToBeClickable(surnameField)).sendKeys(surname);
    }

    /**
     * Заполняет поле "Адрес: куда привезти заказ" указанным значением
     */
    public void enterAddress(String address) {
        wait.until(ExpectedConditions.elementToBeClickable(addressField)).sendKeys(address);
    }

    /**
     * Заполняет поле "Станция метро" - вводит значение и выбирает станцию
     */
    public void enterMetro(String metro) {
        wait.until(ExpectedConditions.elementToBeClickable(metroField)).sendKeys(metro);
    }

    /**
     * Заполняет поле "Телефон: на него позвонит курьер" указанным значением
     */
    public void enterPhone(String phone) {
        wait.until(ExpectedConditions.elementToBeClickable(phoneField)).sendKeys(phone);
    }

    /**
     * Кликает на кнопку "Далее" для перехода ко второй странице заказа
     */
    public void clickNextButton() {
        wait.until(ExpectedConditions.elementToBeClickable(nextButton)).click();
    }

    /**
     * Методы работы со второй страницей формы заказа
     * Заполняет поле "Когда привезти самокат" - выбирает дату
     */
    public void enterDeliveryDate(String date) {
        wait.until(ExpectedConditions.elementToBeClickable(dateField)).sendKeys(date);
    }

    /**
     * Выбирает срок аренды из выпадающего списка "Срок аренды"
     */
    public void selectRentDuration(String duration) {
        wait.until(ExpectedConditions.elementToBeClickable(durationDropdown)).click();
        By optionLocator = By.xpath("//div[contains(@class, 'Dropdown-option') and normalize-space(text())='" + duration + "']");
        wait.until(ExpectedConditions.elementToBeClickable(optionLocator)).click();
    }

    /**
     * Заполняет поле "Цвет самоката" - выбирает цвет "Чёрный жемчуг"(проставляет чекбокс)
     */
    public void setBlackPearlSelection(boolean shouldBeSelected) {
        WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(colorCheckbox));

        if (checkbox.isSelected() != shouldBeSelected) {
            checkbox.click();
        }
    }

    /**
     * Заполняет поле "Комментарий для курьера" указанным значением
     */
    public void enterCourierComment(String comment) {
        wait.until(ExpectedConditions.elementToBeClickable(commentField)).sendKeys(comment);
    }

    /**
     * Кликает на кнопку "Заказать"
     */
    public void clickOrder() {
        wait.until(ExpectedConditions.elementToBeClickable(orderButton)).click();
    }

    /**
     * Методы работы с модальными окнами
     * Кликает на кнопку "Да" в окне подтверждения заказа с сообщением "Хотите оформить заказ?"
     */
    public void clickConfirm() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(modalWindow));
        wait.until(ExpectedConditions.elementToBeClickable(confirmButton)).click();
    }

    /**
     * Проверяет, что окно успешного оформления заказа отображается, и содержит текст "Заказ оформлен".
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