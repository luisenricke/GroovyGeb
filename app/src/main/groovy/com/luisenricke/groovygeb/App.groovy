package com.luisenricke.groovygeb

import geb.Browser
import org.openqa.selenium.WebDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxOptions
import org.openqa.selenium.remote.DesiredCapabilities

class App {

    static String DRIVER_FILE = "C:\\tools\\geckodriver\\geckodriver.exe"

    static void main(String[] args) {
        System.setProperty("webdriver.gecko.driver", DRIVER_FILE)

        DesiredCapabilities capabilities = DesiredCapabilities.firefox()
        capabilities.setCapability("marionette",true)

        FirefoxOptions options = new FirefoxOptions()
        options.setLegacy(true)

        WebDriver driver = new FirefoxDriver(capabilities)

        def browser = new Browser()
        browser.driver = driver

        browser.drive {
            go "http://gebish.org"

            println title == "Geb - Very Groovy Browser Automation"

            $("div.menu a.manuals").click()
            waitFor { !$("#manuals-menu").hasClass("animating") }

            $("#manuals-menu a")[0].click()

            println title.startsWith("The Book Of Geb")
        }
    }
}
