package ru.practicum.factory;

import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

/**
 * Класс для создания и настройки объектов WebDriver для браузеров Chrome и Firefox.
 */
public class DriverFactory extends ExternalResource {

    private WebDriver driver;

    public WebDriver initializeDriver() {

        String browser = System.getProperty("browser", "chrome");

        if ("firefox".equalsIgnoreCase(browser)) {
            startFirefox();
        } else {
            startChrome();
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        driver.get("https://qa-scooter.praktikum-services.ru/");

        return driver;
    }

    /**
     * Возвращает текущий активный WebDriver.
     * Используется в тестах для получения уже инициализированного драйвера без открытия нового окна.
     */
    public WebDriver getDriver() {
        return driver;
    }

    @Override
    protected void before() {
        initializeDriver();
    }

    @Override
    protected void after() {
        driver.quit();
    }

    /**
     * Метод для запуска драйвера Chrome
     */
    private void startChrome() {
        driver = new ChromeDriver();
    }

    /**
     * Метод для запуска драйвера Firefox
     */
    private void startFirefox() {
        driver = new FirefoxDriver();
    }
}
