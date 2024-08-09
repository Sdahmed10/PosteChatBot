import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class QuestionsDivers {

    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        // Maximize the browser window
        driver.manage().window().maximize();
        // Clear all cookies
        driver.manage().deleteAllCookies();
        // Set implicit wait time for the WebDriver
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
        // Open the target website
        driver.get("https://postebubble.messaggera.com/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        // Locate and click on the chat icon
        WebElement messagera = driver.findElement(By.xpath("//*[@id=\"taki-chat-XM-47\"]/div/div[3]/div/img"));
        messagera.click();
        // Locate and click on the 'Utilisation D17' option
        WebElement utilisation = driver.findElement(By.xpath("//p[normalize-space()='Utilisation D17']"));
        utilisation.click();

        // List of questions to ask
        String[] questions = {
                "Qu'est-ce que D17 de la poste tunisienne ?",
                "Comment utiliser le service D17 ?",
                "Quels sont les avantages de D17 ?",
                "Combien coûte le service D17 ?",
                "Comment puis-je m'inscrire à D17 ?"
        };

        for (String question : questions) {
            // Locate the text area and ensure it is visible and interactable
            WebElement message = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//textarea[@id='textarea-chatbox-footer']")));

            // Debugging information
            System.out.println("Message box is displayed: " + message.isDisplayed());
            System.out.println("Message box is enabled: " + message.isEnabled());

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", message);
            message.sendKeys(question);
            message.sendKeys(Keys.ENTER);

            // Wait until the response message is visible
            WebElement responseElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[contains(@class,'message-operator ai-message')])")));

            // Retrieve and print the response
            String responseText = responseElement.getText();
            System.out.println("Response to \"" + question + "\": " + responseText);

            // Pause to ensure the response is fully loaded and to avoid sending messages too quickly
            Thread.sleep(5000);
        }

        // Close the browser
        driver.quit();
    }
}
