package Pages;
import Base.Base;
import org.openqa.selenium.By;
public class page extends Base {

      By email= By.xpath("//input[@name='email']");
      By pass=  By.xpath("//input[@name='pass']");
       By login= By.xpath("//button[@name='login']");

  public void login(){
      logger=report.createTest("Login to Facebook");
      try{
          wait(4,email);
          driver.findElement(email).sendKeys(prop.getProperty("email"));
          wait(4,pass);
          driver.findElement(pass).sendKeys(prop.getProperty("pass"));
          driver.findElement(login).click();
          System.out.print("Successfully Executed....");
          Thread.sleep(2000);
          Screenshot("login");
          reportPass("error message generated successfully.");
      }
      catch(Exception e){
         reportFail(e.getMessage());
      }
  }
  public static void main(String[]args) throws IndexOutOfBoundsException{
      page p=new page();
      p.driversetup();
      p.openURL();
      p.login();
      p.endReport();
      p.closebrowser();
  }

    }

