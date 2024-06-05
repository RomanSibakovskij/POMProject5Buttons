package lt.techin.example;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ButtonPage {

    @FindBy(xpath = "//*[@id=\"button3\"]")
    private WebElement actionMoveAndClickButton;

    @FindBy(xpath = "//button[@data-dismiss='modal']")
    private WebElement closeWebElementPopUpButton;

    WebDriver driver;

    public ButtonPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    //wait for clickable element to be discovered
    private void waitForElementToBeClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    //wait for popup to appear
    private void waitForAlert() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());
    }

    //Task 1 WebElement box 'Click Me' button click
    public void clickWebElementClickMeButton() {
        WebElement clickWebElementButton = (WebElement) ((JavascriptExecutor) driver).executeScript("return document.querySelector('#button1')");
        clickWebElementButton.click();
    }

    //Task 1 confirm the popup success message appears after click
    public String confirmWebElementPopUpTabIsDisplayed(){
        waitForAlert();
        Alert popupAlert = driver.switchTo().alert();
        String popUpText = popupAlert.getText();
        popupAlert.accept();
        return popUpText;
    }

    //Task 1 verify whether the popup message is being displayed
    public boolean isCongratulationsPopupDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//h4[@class='modal-title']"), "Congratulations!"));
    }

    //close the popup
    public void closeWebElementPopUpMessage(){
        closeWebElementPopUpButton.click();
    }

    //Task 2 JS box 'Click Me' button click
    public void clickJsClickMeButton() {
        WebElement button = (WebElement) ((JavascriptExecutor) driver).executeScript("return document.querySelector('#button2')");
        button.click();
    }

    //Task 2 confirm the popup success message appears after click
    public boolean isJSPopupMessageDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("myModalJSClick")));
            return popup.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }
    //Task 2 confirm the popup message is valid
    public String getJSPopupTitle() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement popUpTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='myModalJSClick']//h4[@class='modal-title']")));
        return popUpTitle.getText().trim();
    }
    //close the popup
    public void closeJSPopupMessageBox() {
        WebElement closeButton = driver.findElement(By.xpath("//div[@id='myModalJSClick']//button[text()='Close']"));
        System.out.println("Close button found: " + (closeButton != null));
        System.out.println("");
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", closeButton);
        System.out.println("Close button clicked.");
        System.out.println("");
    }

    //Task 3 Action Move and Click box 'Click Me' button click
    public void clickActionMoveAndClickButton(){
        waitForElementToBeClickable(actionMoveAndClickButton);
        if(actionMoveAndClickButton.isDisplayed()){
            Actions actions = new Actions(driver);
            actions.moveToElement(actionMoveAndClickButton).click().perform();
        }
    }

    //Task 3 confirm the popup message is displayed
    public boolean isActionMoveAndClickPopupDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("myModalMoveClick")));
            boolean isDisplayed = popup.isDisplayed();
            System.out.println("Action Move & Click popup is displayed: " + isDisplayed);
            System.out.println("");
            return isDisplayed;
        } catch (TimeoutException e) {
            System.out.println("Action Move & Click popup is not displayed.");
            System.out.println("");
            return false;
        }
    }
    //Task 3 confirm the popup message is valid by extracting its title
    public String confirmActionMoveAndClickPopUpMessageHeader(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("myModalMoveClick")));
        String popupMessage = popup.findElement(By.className("modal-title")).getText();
        System.out.println("Action Move & Click popup first line: " + popupMessage);
        System.out.println("");
        return popupMessage;
    }

    //close popup
    public void closeActionMoveAndClickPopupWithCloseButton() {
        WebElement closeButton = driver.findElement(By.xpath("//div[@id='myModalMoveClick']//button[text()='Close']"));
        closeButton.click();
    }

}
