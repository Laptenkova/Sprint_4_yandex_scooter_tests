package ru.practicum.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

/**
 * Класс для создания и настройки объектов WebDriver для браузеров Chrome и Firefox.
 */
public class DriverFactory {

    /**
     * Экземпляр WebDriver, хранит текущий драйвер браузера.
     */
    private WebDriver driver;

    /**
     * Метод инициализации драйвера в зависимости от указанного браузера.
     */
    public WebDriver initializeDriver() {

        /**
         * Считывает системное свойство "browser", дефолтное значение — "chrome"
         */
        String browser = System.getProperty("browser", "chrome");

        /**
         * В зависимости от значения выбирает нужный драйвер
         */

        if ("firefox".equalsIgnoreCase(browser)) {
            startFirefox();
        } else {
            startChrome();
        }

        /**
         * Устанавливает неявное ожидание для драйвера — 5 секунд
         */
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        /**
         * Максимизирует окно браузера для теста
         */
        driver.manage().window().maximize();

        /**
         * Переходим на главную страницу тестируемого приложения
         */
        driver.get("https://qa-scooter.praktikum-services.ru/");

        /**
         *  Возвращает готовый драйвер
         */
        return driver;
    }

    /**
     * Метод для запуска драйвера Chrome
     */
    public void startChrome() {

        /**
         *  Создаёт новый экземпляр ChromeDriver
         */
        driver = new ChromeDriver();
    }

    /**
     * Метод для запуска драйвера Firefox
     */
    public void startFirefox() {

        /**
         * Создаёт новый экземпляр FirefoxDriver
         */
        driver = new FirefoxDriver();
    }
}
