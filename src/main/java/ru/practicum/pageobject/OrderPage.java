package ru.practicum.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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

    private final WebDriver driver;
    private WebDriverWait wait;

    private final By nameField = By.cssSelector("[placeholder='* Имя']");
    private final By surnameField = By.cssSelector("[placeholder='* Фамилия']");
    private final By addressField = By.cssSelector("[placeholder='* Адрес: куда привезти заказ']");
    private final By metroField = By.cssSelector(".select-search__input");
    private final By phoneField = By.cssSelector("[placeholder='* Телефон: на него позвонит курьер']");
    private final By cookiesButton = By.cssSelector(".App_CookieButton__3cvqF");
    private final By nextButton = By.cssSelector(".Button_Middle__1CSJM");
    private final By dateField = By.cssSelector("[placeholder='* Когда привезти самокат']");
    private final By durationDropdown = By.cssSelector(".Dropdown-placeholder:not(.is-selected)");
    private final By colorCheckbox = By.cssSelector("input#black");
    private final By commentField = By.cssSelector("[placeholder='Комментарий для курьера']");
    //private final By orderButton = By.xpath("//button[text()='Заказать']");
    private final By orderButton = By.xpath("//div[@class='Order_Buttons__1xGrp']/button[2]");
    private final By modalWindow = By.cssSelector("div.Order_Modal__YZ-d3");
    private final By confirmButton = By.xpath("//button[text()='Да']");
    //private final By confirmButton = By.xpath("//div[@class='Order_Buttons__1xGrp']/button[2]");
    private final By statusButton = By.xpath("//button[text()='Посмотреть статус']");

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
     * Заполняет поле "Станция метро" указанным значением и выбирает станцию из выпадающего списка.
     *
     * @param metro название станции метро, которое нужно ввести и выбрать
     */
    public void enterMetro(String metro) {
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(metroField));
        input.click();
        input.sendKeys(metro);

        // Локатор выпадающего варианта — выбираем первую подходящую станцию
        By firstOption = By.cssSelector(".select-search__option"); // или другой селектор, если у вас есть уникальный
        wait.until(ExpectedConditions.elementToBeClickable(firstOption)).click();
    }


//    /**
//     * Заполняет поле "Станция метро" - вводит значение и выбирает станцию
//     */
//    public void enterMetro(String metro) {
//        wait.until(ExpectedConditions.elementToBeClickable(metroField)).sendKeys(metro);
//    }

    /**
     * Заполняет поле "Телефон: на него позвонит курьер" указанным значением
     */
    public void enterPhone(String phone) {
        wait.until(ExpectedConditions.elementToBeClickable(phoneField)).sendKeys(phone);
    }

    /**
     * Кликает на кнопку "Принять куки" для перехода ко второй странице заказа
     */
    public void clickCookiesAccept() {
        wait.until(ExpectedConditions.elementToBeClickable(cookiesButton)).click();
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
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(dateField));
        input.click();
        input.sendKeys(date);
        input.sendKeys(Keys.ENTER);
    }
    public void selectRentDuration(String duration) {
        // Локатор для placeholder выпадающего списка
        By rentDropdownPlaceholder = By.cssSelector("div.Dropdown-placeholder");

        // Ожидаем кликабельность и кликаем по выпадающему списку
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(rentDropdownPlaceholder));
        dropdown.click();

        // Локатор для конкретной опции по тексту
        By optionLocator = By.xpath("//div[contains(@class, 'Dropdown-option') and normalize-space()='" + duration + "']");

        // Ждём кликабельности опции и кликаем по ней
        wait.until(ExpectedConditions.elementToBeClickable(optionLocator)).click();
    }

//    /**
//     * Выбирает срок аренды из выпадающего списка "Срок аренды"
//     */
//    public void selectRentDuration(String duration) {
//        wait.until(ExpectedConditions.elementToBeClickable(durationDropdown)).click();
//        By optionLocator = By.xpath("//div[contains(@class, 'Dropdown-option') and normalize-space(text())='" + duration + "']");
//        wait.until(ExpectedConditions.elementToBeClickable(optionLocator)).click();
//    }

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