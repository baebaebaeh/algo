package selenium_practic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class SeleniumSRT {

	public static void main(String[] args) {
		// 드라이버를 생성해줌
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://etk.srail.kr/main.do");
		
		// 출발역
		Select departure = new Select(driver.findElement(By.name("dptRsStnCd")));
		// 텍스트값
		departure.selectByVisibleText("동탄");
		
		// 도착역
		Select arrival = new Select(driver.findElement(By.name("arvRsStnCd")));
		// value값
		arrival.selectByValue("0551");		

//		driver.findElement(By.cssSelector("#search-form > fieldset > a")).click();
	}

}




