package ru.practicum.factory;

import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

import static ru.practicum.utils.DriverConfig.*;

/**
 * Класс для создания и управления экземплярами WebDriver.
 * Класс используется как JUnit @Rule и обеспечивает запуск браузера перед тестом
 * и корректное завершение после выполнения.
 */
public class DriverFactory extends ExternalResource {
    private WebDriver driver;

    /**
     * Инициализирует WebDriver в зависимости от системного свойства "browser".
     * Поддерживаемые браузеры: chrome (по умолчанию), firefox.
     *
     * @return инициализированный WebDriver
     */
    public WebDriver initializeDriver() {
        String browser = System.getProperty(BROWSER, DEFAULT_BROWSER);

        if (FIREFOX_BROWSER.equalsIgnoreCase(browser)) {
            startFirefox();
        } else {
            startChrome();
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        driver.get(BASE_URL);

        return driver;
    }

    /**
     * Возвращает текущий активный экземпляр WebDriver.
     *
     * @return WebDriver
     */
    public WebDriver getDriver() {
        return driver;
    }

    /**
     * Вызывается перед началом выполнения каждого теста.
     * Выполняет инициализацию WebDriver.
     */
    @Override
    protected void before() {
        initializeDriver();
    }

    /**
     * Вызывается после выполнения теста.
     * Завершает работу драйвера и закрывает браузер.
     */
    @Override
    protected void after() {
        driver.quit();
    }

    /**
     * Запускает ChromeDriver
     */
    private void startChrome() {
        driver = new ChromeDriver();
    }

    /**
     * Запускает FirefoxDriver
     */
    private void startFirefox() {
        driver = new FirefoxDriver();
    }
}
