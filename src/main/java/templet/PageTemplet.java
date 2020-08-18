package templet;

import org.openqa.selenium.WebElement;
/**
 * @author Rui Guo
 *
 * Guru99 bank: base page object class
 */

public class PageTemplet {
    /**
     * clear the text field before send context.
     * @param webElement
     * @param context
     */
    public void sendKey(WebElement webElement, String context) {
        webElement.clear();
        webElement.sendKeys(context);
    }
}
