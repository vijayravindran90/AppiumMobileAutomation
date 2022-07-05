package testSuite;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.Test;
import pages.BaseConfig;
import pages.HomePage;
public class HomePageTest extends BaseConfig {

    /*@Test
    public void validateHomePage()
    {
        new HomePage(new BaseConfig().getMobileDriver())
                .assertSearchBarText();
    }*/
    @Test
    public void assertSearchItem()
    {
        new HomePage(new BaseConfig().getMobileDriver())
                .searchItem();
    }
}
