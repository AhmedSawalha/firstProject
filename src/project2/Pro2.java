package project2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class Pro2 {
//jnkjnjllkk
    WebDriver driver;
    String THEWEBSITE = "https://www.saucedemo.com/";
    String UserName = "standard_user";
    String Password = "secret_sauce";

    @BeforeTest
    public void mySetup() {
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get(THEWEBSITE);
    }

    @Test(priority = 1)
    public void login() throws InterruptedException {
        WebElement userNameInput = driver.findElement(By.id("user-name"));
        WebElement passwordInput = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        userNameInput.sendKeys(UserName);
        passwordInput.sendKeys(Password);
        loginButton.click();

        
        WebElement productsTitle = driver.findElement(By.className("title"));
        Assert.assertEquals(productsTitle.getText(), "Products", "Login failed or wrong page opened!");
    }

    @Test(priority = 2)
    public void addAllItems() throws InterruptedException {
        
        List<WebElement> addButtons = driver.findElements(By.className("btn_inventory"));

        for (WebElement button : addButtons) {
            button.click();
        }

        WebElement cartBadge = driver.findElement(By.className("shopping_cart_badge"));
        Assert.assertTrue(cartBadge.isDisplayed(), "Cart badge not displayed - items might not be added!");
    }

    @Test(priority = 3)
    public void removeItemFromTheCart() throws InterruptedException {

    	WebElement backPackRemoveButton = driver.findElement(By.id("remove-sauce-labs-backpack"));
        backPackRemoveButton.click();

        
        WebElement addBackButton = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
        Assert.assertTrue(addBackButton.isDisplayed(), "Remove failed - Add to cart button not visible!");
    }

    @Test(priority = 5)
    public void logout() throws InterruptedException {
        WebElement burgerMenu = driver.findElement(By.id("react-burger-menu-btn"));
        burgerMenu.click();
        Thread.sleep(1000); 

        WebElement logoutButton = driver.findElement(By.id("logout_sidebar_link"));
        logoutButton.click();
        Thread.sleep(1000);

        WebElement mainLogo = driver.findElement(By.className("login_logo"));
        Assert.assertTrue(mainLogo.isDisplayed(), "Logout failed - login page not displayed!");
    }
    @Test(priority = 4)
	public void Task() {
		
		
		List<WebElement> ItemsNames = driver.findElements(By.className("inventory_item_name"));
		
		for(int i = 0 ; i < ItemsNames.size();i++) {
			
			String itemName = ItemsNames.get(i).getText();
			System.out.println(itemName.charAt(0));
			
			
			
		}
		
	}

    @AfterTest
    public void myAfterTest() {
        driver.quit();
    }
}
