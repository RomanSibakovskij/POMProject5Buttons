package lt.techin.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class ButtonsPageTest {


    WebDriver driver;

    @BeforeEach
    void setup(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://webdriveruniversity.com/Click-Buttons/index.html");
    }


    //Test 1
    @Test
    void webElementTest(){
        ButtonPage buttonPage = new ButtonPage(driver);

        //button click
        buttonPage.clickWebElementClickMeButton();
        System.out.println("The button is clicked");
        System.out.println("");

        //assertion the correct popup window appears
        assertTrue(buttonPage.isCongratulationsPopupDisplayed(), "Congratulations popup is displayed.");
        buttonPage.closeWebElementPopUpMessage();
    }

    //Test 2
    @Test
    void jsBoxClickTest(){
        ButtonPage buttonPage = new ButtonPage(driver);

        //button click
        buttonPage.clickJsClickMeButton();

        // Verify whether the JS popup is displayed
        System.out.println("Is JS Popup Displayed: " + buttonPage.isJSPopupMessageDisplayed());
        System.out.println("");
        // Print the JS popup title
        System.out.println("JS Popup Title: " + buttonPage.getJSPopupTitle());
        System.out.println("");

        //assert the correct popup message is being displayed
        assertTrue(buttonPage.isJSPopupMessageDisplayed(), "JS popup should be displayed.");
        assertEquals("It’s that Easy!! Well I think it is.....", buttonPage.getJSPopupTitle(), "Correct JS popup title.");

        //close the popup
        buttonPage.closeJSPopupMessageBox();
    }

    //Test 3
    @Test
    void clickActionMoveAndClickTest(){
        ButtonPage buttonPage = new ButtonPage(driver);

        //button click
        buttonPage.clickActionMoveAndClickButton();
        System.out.println("The button is clicked");
        System.out.println("");

        //validate popup message display and partial content
        String expectedFirstLine = "Well done! the Action Move & Click can become very useful!";
        assertEquals(expectedFirstLine, buttonPage.confirmActionMoveAndClickPopUpMessageHeader(), "Correct Action Move & Click popup first line.");

        //close popup
        buttonPage.closeActionMoveAndClickPopupWithCloseButton();
    }


    @AfterEach
    void close() throws InterruptedException {
        Thread.sleep(3000);
        driver.close();
    }




}

