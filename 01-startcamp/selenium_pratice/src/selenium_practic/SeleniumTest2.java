package selenium_practic;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumTest2 {

	public static void main(String[] args) {
		// 1. 크롬 드라이버를 사용할 준비를 한다.
		// 자바와 크롬을 연동하기 위한 준비
		WebDriver driver = new ChromeDriver();

		// 2. 접속하고자 하는 웹페이지의 주소를 작성한다.
		driver.get("https://finance.naver.com/");
//		String mainPage = driver.getWindowHandle();
//		driver.switchTo().window(mainPage);

		// input box를 받아옴
		WebElement searchBox = driver
				.findElement(By.cssSelector("#stock_items"));
		searchBox.sendKeys("삼성전자"); // inputBox에 삼성전자 라는 문자를 보냄

//		 1. 클릭 -> 안됨
//		 driver.findElement(By.xpath("/html/body/div[2]/div[1]/div/div[2]/div[1]/form/div/div/div/div[1]/div/ul/li[1]/div[1]")).click();

		// 2. Enter버튼 입력
		searchBox.sendKeys(Keys.ENTER);
	}

}




