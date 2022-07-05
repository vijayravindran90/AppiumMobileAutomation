package pageObjectRepository;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class HomePageObjectRepository {
    @AndroidFindBy(id="com.amazon.mShop.android.shopping:id/chrome_search_hint_view")
    public MobileElement searchBar;

    @AndroidFindBy(id="com.amazon.mShop.android.shopping:id/rs_search_src_text")
    public MobileElement searchItems;

    public String searchBarText = "Search Amazon.in";
    public String searchItem = "Shoes";

    public String getSearchBarText() {
        return searchBarText;
    }
    public String enterSearchItem(String item)
    {
        searchBar.click();
        searchItems.sendKeys(item);
        return searchBar.getText();
    }

}
