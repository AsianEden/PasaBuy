import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.JavascriptExecutor;

public class Ticket2Me {
	public static void main(String[] args) throws InterruptedException {
		// Set the path to the chromedriver executable
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

		// Create a new Chrome webdriver instance
		WebDriver driver = new ChromeDriver();

		// Maximize window
		driver.manage().window().maximize();
		
		// Login to Facebook 
		driver.get("https://mbasic.facebook.com");		
		Thread.sleep(2000);
		WebElement username = driver.findElement(By.xpath("//input[@name='email']"));
		username.sendKeys("alicestacks200@gmail.com");
		Thread.sleep(2000);
		WebElement password = driver.findElement(By.xpath("//input[@name='pass']"));
		password.sendKeys("ShimakazePHistheMLBBGoatNamba1");
		Thread.sleep(2000);
		WebElement loginButton = driver.findElement(By.xpath("//input[@name='login']"));
		loginButton.click();		
		Thread.sleep(2000);
		
		// Login to Ticket2me via Facebook session
		driver.get("https://ticket2me.net/auth/facebook");
		Thread.sleep(2000);

		// Initialize JavascriptExecutor for clicking some complicated elements
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		
		// Loop until ticket for May 7 is available
		boolean loop = true;
		while (loop) {

			try {

				// Navigate to the Ticket2Me event page
				 driver.get("https://ticket2me.net/e/37033");

				// test event
				// driver.get("https://ticket2me.net/e/37463");

				// Automation will click next page in the calendar if it still displays April
				if (!driver
						.findElement(
								By.xpath("//table[@class='table']/tbody/tr[@class='calendar-month-header']/td/span"))
						.getText().equalsIgnoreCase("May 2023")) {
					WebElement calendarNextButton = driver.findElement(By.xpath(
							"//div[@class='calendar-month-navigation']/span/i[@class='fa fa-chevron-circle-right']"));
					jse.executeScript("arguments[0].scrollIntoView()", calendarNextButton);
					jse.executeScript("arguments[0].click();", calendarNextButton);
				}

				// Automation clicks the date
				WebElement date = driver
						.findElement(By.xpath("//table[@class='table']/tbody/tr/td/div/span[text()='7']"));
				jse.executeScript("arguments[0].scrollIntoView()", date);
				jse.executeScript("arguments[0].click();", date);

				// Automation clicks the Buy button
				WebElement buyButton = driver.findElement(By.xpath("(//a[@class='btn btn-primary buy-button'])[1]"));
				jse.executeScript("arguments[0].scrollIntoView()", buyButton);
				jse.executeScript("arguments[0].click();", buyButton);

				// Automation enters the number of ticket in the dropdown
				WebElement ticketCountDropDown = driver.findElement(By.xpath(
						"//h3[@class='ticket-type-title']/strong[contains(translate(text(),'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ'), 'GOLD')]/ancestor::div/div/div/div/select"));
				ticketCountDropDown.sendKeys("2");

				// Automation clicks the Continue button
				WebElement continueButton = driver.findElement(By.xpath("//strong[text()='Continue']"));
				jse.executeScript("arguments[0].scrollIntoView()", continueButton);
				jse.executeScript("arguments[0].click();", continueButton);

				loop = false;
				System.out.println("Ticket ready to be paid!");

			} catch (Exception e) {
				loop = true;
				e.printStackTrace();
				System.out.println("Error at ticketing.");
			}
		}

		// Close the browser window
		// driver.quit();
	}
}