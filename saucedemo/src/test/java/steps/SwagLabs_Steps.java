package steps;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SwagLabs_Steps {
	
	WebDriver driver = WebDriverManager.chromedriver().create();
	
	@SuppressWarnings("deprecation")
	@Given("I am on Swag Labs Login Page")
	public void i_am_on_swag_labs_login_page() throws Exception {	
		
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		driver.get(" https://www.saucedemo.com/");	
		System.out.println("Swag Labs launched");		
	}
	
	@When("I enter {string} and {string}")
	public void i_enter_username_and_password(String userName,String password) throws Exception  {
			driver.findElement(By.id("user-name")).sendKeys(userName);	
			Assert.assertEquals(driver.findElement(By.id("user-name")).getAttribute("value"), userName); //userName passing as a string from feature file 
			Thread.sleep(1000);		
			driver.findElement(By.id("password")).sendKeys(password);  // as we have common password for all user names 
			Assert.assertEquals(driver.findElement(By.id("password")).getAttribute("value"), password);
			System.out.println("Entered UserName and Password");
	}

	@And("I click Login Button")
	public void i_click_login_button()  throws Exception{
			driver.findElement(By.id("login-button")).click();
			System.out.println("Login Button clicked");		
	}

	@Then("I should be able to Login")
	public void i_should_be_able_to_login()  throws Exception{ 
		   Assert.assertTrue(driver.findElement(By.id("react-burger-menu-btn"))!=null); //verifying if menu button available after login
		   System.out.println("Login Successfull"); 
	}

	@And("I should be able to select random items")
	public void i_should_be_able_to_select_random_items()  throws Exception{
		List<WebElement> elementList = driver.findElements(By.xpath("//div[@class='inventory_list']/div"));		
		 Random r = new Random();
         Set<Integer> s = new HashSet<Integer>(); 
         int items = 3;  // as we have to select 3 random items
       
		System.out.println("Total List Of Items present are " +elementList.size());
		for(int i=0;i<items;i++ )
		{
			 while(true) {
				 int num = r.nextInt(elementList.size());
				 if (s.contains(num) == false) {
	                    s.add(num);
	                    System.out.println(num);
	                    WebElement button =	driver.findElement(By.xpath("//a[@id='item_" +num + "_title_link']/../../div/button"));
	        			if(button.isDisplayed())
	        			{
	        				button.click();
	        				WebElement selected= driver.findElement(By.xpath("//a[@id='item_" +num + "_title_link']/div"));
	        				System.out.println("Selected Item from Cart List " +selected.getText());
	        			}
	        			else
	        			{
	        			System.out.println("Add to Cart Button Not diaplayed");
	        			}
	                    break;
	                }
				 else
				 {
					 System.out.println("This Cart Already Selected" +num);
				 }
			 }
		}
		
		//Asserting Three Items 
		driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();
		List<WebElement> elementList1 = driver.findElements(By.xpath("//div[@class='cart_item']"));	
		Assert.assertEquals(items,elementList1.size());
		System.out.println("Passed");
		
		Thread.sleep(2000);
		driver.quit();	
	}	

	@When("I enter locked user and password")
	public void i_enter_locked_user_and_password() throws Exception{
		driver.findElement(By.id("user-name")).sendKeys("locked_out_user");
		Assert.assertEquals(driver.findElement(By.id("user-name")).getAttribute("value"), "locked_out_user");// Passing Locked user as user name
		Thread.sleep(1000);		
		driver.findElement(By.id("password")).sendKeys("secret_sauce");  // as we have common password for all user names 
		Assert.assertEquals(driver.findElement(By.id("password")).getAttribute("value"), "secret_sauce"); // asserting password
		System.out.println("Entered UserName and Password");
	}

	@Then("I should not be able to Login")
	public void i_should_not_be_able_to_login() throws Exception {
		driver.findElement(By.xpath("//h3[@data-test='error']")).isDisplayed(); // Asserting as error message is displayed on login failed due to locked user
		System.out.println(driver.findElement(By.xpath("//h3[@data-test='error']")).getText()); // printing error message on console
	  
	}
}
