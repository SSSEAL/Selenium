package lab2;


import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;


import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import static org.junit.Assert.*;

import com.csvreader.CsvReader;

@RunWith(Parameterized.class)
public class selelab {
	  private static WebDriver driver;
	  private String baseUrl;
	  private boolean acceptNextAlert = true;
	  private StringBuffer verificationErrors = new StringBuffer();
	  private String id = null;
	  private String github = null;

	  public selelab(String id, String github){
	      this.id= id;
	      this.github = github;
	  }
	  
	  @BeforeClass
	  public static void set() throws Exception {
			System.setProperty("webdriver.firefox.bin", "D:/firebox/firefox.exe");
		    driver = new FirefoxDriver();
	  }
	  
	  @Before
	  public void setUp() throws Exception {
	    baseUrl = "http://121.193.130.195:8080";
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  }
	  
	  @Parameters
	  public static Collection<Object[]> getData() throws IOException{
	      Object[][] obj = new Object[117][];
	      CsvReader r = new CsvReader("D://学习//大三下//软件测试//lab2//inputgit.csv", ',',Charset.forName("GBK"));
	      r.readHeaders();
	      int count = 0;
	      while(r.readRecord()){
	          obj[count] = new Object[]{r.get("学号"), r.get("github地址")};
	          count++;
	      }
	      return Arrays.asList(obj);
	  }
	  
	  @Test
	  public void test2() throws Exception {
		driver.get(baseUrl + "/");
  	    driver.findElement(By.id("name")).clear();
  	    driver.findElement(By.id("name")).sendKeys(this.id);
  	    driver.findElement(By.id("pwd")).clear();
  	    driver.findElement(By.id("pwd")).sendKeys(this.id.substring(4));
  	    driver.findElement(By.id("submit")).click();
  	    assertEquals(this.github, driver.findElement(By.xpath("//tbody[@id='table-main']/tr[3]/td[2]")).getText());
	  }

	  @After
	  public void tearDown() throws Exception {
	    String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) {
	      fail(verificationErrorString);
	    }
	  }
	  
	  @AfterClass
	  public static void tear() throws Exception {
		  driver.quit();
	  }
}
