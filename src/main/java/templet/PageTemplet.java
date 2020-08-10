package templet;

import org.openqa.selenium.WebElement;

public class PageTemplet {
    public void sendKey(WebElement webElement, String context) {
        webElement.clear();
        webElement.sendKeys(context);
    }
}
