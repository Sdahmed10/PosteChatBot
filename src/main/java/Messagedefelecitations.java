import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Messagedefelecitations {

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
        Thread.sleep(3000);

        // List of questions to ask
        String[] questions = {

                "J'ai reçu un mail avec le message 'félicitations tu as gagné' qui demande les informations de ma carte. Est-ce que c'est légitime ?",
                "Pourriez-vous m'aider à vérifier la légitimité d'un email que j'ai reçu, prétendument pour une victoire, et demandant des informations sur ma carte ?",
                "J'ai reçu un mail disant que j'ai gagné quelque chose et demandant les infos de ma carte. C’est quoi ce truc ?",
                "Pouvez-vous me conseiller sur la marche à suivre si j'ai reçu un email demandant les informations de ma carte avec le message 'félicitations tu as gagné' ?",
                "Que dois-je faire si je reçois un email prétendant que j'ai gagné quelque chose et demandant des détails sur ma carte ? Comment vérifier si c'est un arnaque ?",



        };

        for (int i = 0; i < questions.length; i++) {
            // Locate the text area and ensure it is visible and interactable
            WebElement message = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//textarea[@id='textarea-chatbox-footer']")));


            // Debugging information
            System.out.println("Message box is displayed: " + message.isDisplayed());
            System.out.println("Message box is enabled: " + message.isEnabled());

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", message);
            message.sendKeys(questions[i]);
            message.sendKeys(Keys.ENTER);

            // Construct the XPath for the current response based on the counter
            String responseXPath = "(//div[contains(@class,'message-operator ai-message')])[" + (i + 1) + "]";

            // Wait until the response message is visible
            WebElement responseElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(responseXPath)));

            // Retrieve and print the response
            String responseText = responseElement.getText();
            System.out.println("Response to \"" + questions[i] + "\": " + responseText);

            // Pause to ensure the response is fully loaded and to avoid sending messages too quickly
            Thread.sleep(5000);
        }

        // Close the browser
        driver.quit();
    }
}






















