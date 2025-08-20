package ru.practicum.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

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
    private final By nameField = By.cssSelector("[placeholder='* Имя']");
    private final By surnameField = By.cssSelector("[placeholder='* Фамилия']");
    private final By addressField = By.cssSelector("[placeholder='* Адрес: куда привезти заказ']");
    private final By metroField = By.cssSelector(".select-search__input");
    private final By metroFieldFirstOption = By.cssSelector(".select-search__option");
    private final By phoneField = By.cssSelector("[placeholder='* Телефон: на него позвонит курьер']");
    private final By nextButton = By.cssSelector(".Button_Middle__1CSJM");
    private final By dateField = By.cssSelector("[placeholder='* Когда привезти самокат']");
    private final By rentDropdownPlaceholder = By.cssSelector("div.Dropdown-placeholder");
    private final By colorCheckboxBlack = By.cssSelector("input#black");
    private final By colorCheckboxGrey = By.cssSelector("input#grey");
    private final By commentField = By.cssSelector("[placeholder='Комментарий для курьера']");
    private final By orderButton = By.xpath("//div[@class='Order_Buttons__1xGrp']/button[2]");
    private final By modalWindow = By.cssSelector("div.Order_Modal__YZ-d3");
    private final By confirmButton = By.xpath("//button[text()='Да']");
    private final By statusButton = By.xpath("//button[text()='Посмотреть статус']");

    // Шаблон для выбора срока аренды
    private static final String RENT_DURATION_OPTION_XPATH_TEMPLATE =
            "//div[contains(@class, 'Dropdown-option') and text()='%s']";

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
     * @param address Адрес для ввода
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
     * Устанавливает выбор цвета самоката: "Чёрный жемчуг" или "Серый".
     * Гарантирует кликабельность элементов перед взаимодействием.
     *
     * @param shouldBeSelected true - выбрать "Чёрный жемчуг", false - выбрать "Серый"
     */
    public void setBlackPearlSelection(boolean shouldBeSelected) {
        WebElement checkBoxBlack = wait.until(ExpectedConditions.elementToBeClickable(colorCheckboxBlack));
        WebElement checkBoxGrey = wait.until(ExpectedConditions.elementToBeClickable(colorCheckboxGrey));
        if (checkBoxBlack.isSelected() != shouldBeSelected) {
            checkBoxBlack.click();
        } else {
            checkBoxGrey.click();
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