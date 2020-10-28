package URL;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.opencsv.CSVWriter;

public class BLMAIN {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
		String csvFile = "C:\\1234.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        WebDriver driver;
     System.setProperty("webdriver.gecko.driver", "C:\\geckodriver.exe");  
       // System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
		driver = new FirefoxDriver();
		//driver = new ChromeDriver();
	//	JavascriptExecutor js = (JavascriptExecutor) driver;
		String divdata;
	//	String skudata;
	CSVWriter writer = new CSVWriter(new FileWriter("C:\\Users\\Bilal's\\Desktop\\divdata\\test.csv",true));
		
		 //FileWriter pw = new FileWriter("\"C:\\\\Users\\\\Bilal's\\\\Desktop\\\\divdata\\\\test.csv\"");
	        
	       
		
        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] appl = line.split(cvsSplitBy);
               
                System.out.println(appl[0]);
               
               
                //driver.manage().window().setSize(new Dimension(375,667));
                driver.get(appl[0]);
                
                driver.findElement(By.cssSelector("#product-listing > div.col-sm-6 > div > div > h3")).click();
                Thread.sleep(3000);
               divdata = driver.findElement(By.xpath("/html/body/form/div[2]/div[3]/div[2]/div[5]/div/div[1]")).getAttribute("outerHTML");
                System.out.println(divdata);
          //     skudata = driver.findElement(By.cssSelector(".item-sku")).getText();
             //   XWPFDocument document = new XWPFDocument();
                
                
                String [] record = divdata.split("!$$");
           //    String [] record1 = skudata.split("!");
                
               
             writer.writeNext(record);
       //    writer.writeNext(record1);
               
                
      
    			
    			driver.manage().window().maximize();
    			//Thread.sleep(2000);
    			((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
    			//js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
            }
            writer.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


	}

}
