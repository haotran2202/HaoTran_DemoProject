package commons;

public class BasePageUI {

    public static final String DYNAMIC_TAB = "//div[@id='header-nav']//a[normalize-space()='%s']";

    public static final String DYNAMIC_ACCOUNT_PAGE = "//div[@class='block block-account']//a[normalize-space()='%s']";

    public static final String DYNAMIC_MENU_OPTION = "//div[@id='header-account']//a[contains(@title,'&s')]";

    public static final String DYNAMIC_TEXTBOX = "//input[@id='%s']";
}
