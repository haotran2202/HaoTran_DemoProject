package commons;

public class BasePageUI {

    public static final String DYNAMIC_TAB = "//div[@id='header-nav']//a[normalize-space()='%s']";

    public static final String DYNAMIC_ACCOUNT_PAGE_BY_TEXT = "//div[@class='block block-account']//a[normalize-space()='%s']";

    public static final String DYNAMIC_MENU_OPTION_BY_TEXT = "//div[@id='header-account']//a[contains(@title,'%s')]";

    public static final String DYNAMIC_TEXTBOX_BY_ID = "//input[@id='%s']";

    public static final String DYNAMIC_TEXTBOX_BY_NAME = "//input[@name='%s']";

    public static final String DYNAMIC_BUTTON_BY_TITLE = "//button[@title='%s']";

    public static final String DYNAMIC_LINK_BY_TITLE = "//a[@title='%s']";

    public static final String DYNAMIC_LINK_BY_TEXT = "//a[text()='%s']";

}
