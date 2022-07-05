package pages;
import config.CommonAppiumTest;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import pageObjectRepository.HomePageObjectRepository;
public class HomePage extends CommonAppiumTest{
    HomePageObjectRepository homePageObjectRepository = new HomePageObjectRepository();

    public HomePage(AppiumDriver<MobileElement> driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), homePageObjectRepository);
    }
    public HomePage assertSearchBarText()
    {
        Assert.assertEquals(homePageObjectRepository.getSearchBarText(),homePageObjectRepository.searchBar.getText());
        return this;
    }
    public HomePage searchItem()
    {
       Assert.assertEquals(homePageObjectRepository.searchItem,homePageObjectRepository.enterSearchItem("Shoes"));
        return this;
    }
}
