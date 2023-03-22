package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import support.Constants;
import support.ExcelReader;
import java.io.IOException;
import java.util.List;
import static support.World.*;
public class AccountPage {

    private static final String AGREEMENTS_POLICIES_UN_TXT = "Agreements & Policies";
	private static final String UNITS_EN_TXT = "Units";
	private static final String LANGUAGE_EN_TXT = "Language";
	private static final String CONFIRM_PASSWORD_EN_TXT = "Confirm Password";
	private static final String NEW_PASSWORD_EN_TXT = "New Password";
	private static final String CURRENT_PASSWORD_EN_TXT = "Current Password";
	private static final String API_KEY_EN_TXT = "API Key";
	private static final String PHONE_NUMBER_EN_TXT = "Phone Number";
	private static final String USERNAME_EN_TXT = "Username *";
	private static final String EMAIL_ADDRESS_EN_TXT = "Email Address";
	private static final String NAME_EN_TXT = "Name *";
	private final WebDriver driver;
    public String accountSizeUnit;
    public String sizesUnit = "";
    public String rainUnits;
    public String temperatureUnit = "";
    public String gradientBarTemp;
    public String loadInSeconds = null;
    public long start;
    String currentWindUnit = "";
    @FindBy(xpath = "//input[@id='email']")
    private WebElement emailF;
    @FindBy(xpath = "//p[normalize-space()='Settings']")
    private WebElement dashboardMenu_Settings;
    @FindBy(xpath = "//p[normalize-space()='Account']")
    private WebElement dashboardMenu_Account;
    @FindBy(xpath = "//div[@id='user-api-key']//input")
    private WebElement account_GenerateKey;
    @FindBy(xpath = "//img[@src='/img/icons/copy.svg']")
    private WebElement account_GenerateKey_CopyBtn;
    @FindBy(xpath = "//img[@src='/img/icons/refresh.svg']")
    private WebElement account_GenerateKey_RefreshBtn;
    @FindBy(xpath = "//img[@src='/img/icons/copy.svg']")
    private WebElement account_GeneratedAPIKey;
    @FindBy(xpath = "//h3[normalize-space()='Apikey has been copied.']")
    private WebElement account_successToast_CopyAPIKey;
    @FindBy(xpath = "//label[@for='distance_millimeters']")
    private WebElement account_sizeUnits_mm;
    @FindBy(xpath = "(//button[normalize-space()='Save'])[1]")
    private WebElement account_SaveBtn;
    @FindBy(xpath = "//div[@class='column-auto measure text-center']")
    private WebElement measurement_gradientBar_MapPage;
    @FindBy(xpath = "//p[@class='1 selected']")
    private WebElement globalOrgSelected_MapPage;
    @FindBy(xpath = "//span[@class='1 caret']")
    private WebElement globalOrgDropdownBtn_MapPage;
    @FindBy(xpath = "//div[@class='1 dropdown-selector open']//input[@type='search']")
    private WebElement globalOrgDropdownSearch_MapPage;
    @FindBy(xpath = "//p[normalize-space()='arable-team']")
    private WebElement globalOrgSearchSuggestion_MapPage;
    @FindBy(xpath = "(//arable-site-summary-card/div)[1]")
    private WebElement firstSiteIdF;
    @FindBy(xpath = "//p[normalize-space()='Map']")
    private WebElement mapPage;
    @FindBy(xpath = "(//span[@class='measure'])[2]")
    private WebElement mapPage_Rain_Units;
    @FindBy(xpath = "//span[text()='Português']")
    private WebElement portugues_language;
    @FindBy(xpath = "//label[@for='name']")
    private WebElement name_lbl;
    @FindBy(xpath = "//label[@for='email']")
    private WebElement email_lbl;
    @FindBy(xpath = "//label[@for='username']")
    private WebElement username_lbl;
    @FindBy(xpath = "//label[@for='phone']")
    private WebElement phone_lbl;
    @FindBy(xpath = "//label[text()='API Key']")
    private WebElement api_key_lbl;
    @FindBy(xpath = "//label[text()='Chave API']")
    private WebElement api_key_pt;
    @FindBy(xpath = "//label[@for='current-password']")
    private WebElement curr_pwd_lbl;
    @FindBy(xpath = "//label[@for='new-password']")
    private WebElement new_pwd_lbl;
    @FindBy(xpath = "//label[@for='confirm-password']")
    private WebElement conf_pwd_lbl;
    @FindBy(xpath = "//label[text()='Language']")
    private WebElement language_lbl;
    @FindBy(xpath = "//label[text()='Idioma']")
    private WebElement language_lbl_pt;
    @FindBy(xpath = "//label[text()='Units']")
    private WebElement units_lbl;
    @FindBy(xpath = "//label[text()='Unidades']")
    private WebElement units_lbl_pt;
    @FindBy(xpath = "//label[text()='Agreements & Policies']")
    private WebElement agreement_policies_lbl;
    @FindBy(xpath = "//label[text()='Acordos e políticas']")
    private WebElement agreement_policies_lbl_pt;
    
    public AccountPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void verifyGenerateKey(String role) throws InterruptedException {
        webDriverHelper.waitUntilVisible(account_sizeUnits_mm, 100, 1);
        if (account_GenerateKey.isDisplayed()) {
            account_GenerateKey.click();
            webDriverHelper.waitUntilVisible(account_GenerateKey_CopyBtn, 100, 1);
        } else {
            account_GenerateKey_CopyBtn.isDisplayed();
        }
        loadInSeconds = stringHelper.stopTime(start, driver);
        ExcelReader.setValueForColumn("Account", loadInSeconds, role);
        account_GenerateKey.click();
        Thread.sleep(200);
        webDriverHelper.waitUntilVisible(account_GenerateKey_CopyBtn, 100, 1);
        Assert.assertTrue(account_GeneratedAPIKey.isDisplayed());
        account_GenerateKey_CopyBtn.click();
        webDriverHelper.waitUntilVisible(account_successToast_CopyAPIKey, 120, 1);
        Assert.assertTrue(account_successToast_CopyAPIKey.isDisplayed());
        Assert.assertTrue(account_GenerateKey_RefreshBtn.isDisplayed());
        String apiKey = account_GeneratedAPIKey.getText();
        account_GenerateKey_RefreshBtn.click();
        Thread.sleep(200);
        webDriverHelper.waitUntilVisible(account_GeneratedAPIKey, 30, 1);
        String NewApiKey = account_GeneratedAPIKey.getText();
        Assert.assertEquals(NewApiKey, apiKey);
    }

    public void navigateToPage(String page) throws IOException, InterruptedException {
        webDriverHelper.waitUntilVisible(dashboardMenu_Settings, 30, 1);
        switchOrgToArableTeam();
        webDriverHelper.waitUntilVisible(mapPage_Rain_Units, 30, 1);
        rainUnits = mapPage_Rain_Units.getText();
        gradientBarTemp = measurement_gradientBar_MapPage.getText();
        Actions action = new Actions(driver);
        action.moveToElement(dashboardMenu_Settings).moveToElement(dashboardMenu_Account).click().build().perform();
        loadInSeconds = null;
        start = System.currentTimeMillis();
    }
    public void verifyPortuguesLanguage() throws Exception {
    	jsonPayload = jsonReader.getJsonPayload("pt");
    	webDriverHelper.waitUntilVisible(portugues_language, 30, 1);
    	Assert.assertEquals(NAME_EN_TXT,name_lbl.getText());
    	Assert.assertEquals(EMAIL_ADDRESS_EN_TXT,email_lbl.getText());
    	Assert.assertEquals(USERNAME_EN_TXT,username_lbl.getText());
    	Assert.assertEquals(PHONE_NUMBER_EN_TXT,phone_lbl.getText());
    	Assert.assertEquals(API_KEY_EN_TXT,api_key_lbl.getText());
    	Assert.assertEquals(CURRENT_PASSWORD_EN_TXT,curr_pwd_lbl.getText());
    	Assert.assertEquals(NEW_PASSWORD_EN_TXT,new_pwd_lbl.getText());
    	Assert.assertEquals(CONFIRM_PASSWORD_EN_TXT,conf_pwd_lbl.getText());
    	Assert.assertEquals(LANGUAGE_EN_TXT,language_lbl.getText());
    	Assert.assertEquals(UNITS_EN_TXT,units_lbl.getText());
    	Assert.assertEquals(AGREEMENTS_POLICIES_UN_TXT,agreement_policies_lbl.getText());
    	portugues_language.click();
    	webDriverHelper.waitUntilTextToBePresentInElement(name_lbl, 10, 2,jsonReader.getValueFromKey(jsonPayload, NAME_EN_TXT) );
    	Assert.assertEquals(jsonReader.getValueFromKey(jsonPayload, NAME_EN_TXT),name_lbl.getText());
    	Assert.assertEquals(jsonReader.getValueFromKey(jsonPayload, EMAIL_ADDRESS_EN_TXT),email_lbl.getText());
    	Assert.assertEquals(jsonReader.getValueFromKey(jsonPayload, USERNAME_EN_TXT),username_lbl.getText());
    	Assert.assertEquals(jsonReader.getValueFromKey(jsonPayload, PHONE_NUMBER_EN_TXT),phone_lbl.getText());
    	Assert.assertEquals(jsonReader.getValueFromKey(jsonPayload, API_KEY_EN_TXT),api_key_pt.getText());
    	Assert.assertEquals(jsonReader.getValueFromKey(jsonPayload, CURRENT_PASSWORD_EN_TXT),curr_pwd_lbl.getText());
    	Assert.assertEquals(jsonReader.getValueFromKey(jsonPayload, NEW_PASSWORD_EN_TXT),new_pwd_lbl.getText());
    	Assert.assertEquals(jsonReader.getValueFromKey(jsonPayload, CONFIRM_PASSWORD_EN_TXT),conf_pwd_lbl.getText());
    	Assert.assertEquals(jsonReader.getValueFromKey(jsonPayload, LANGUAGE_EN_TXT),language_lbl_pt.getText());
    	Assert.assertEquals(jsonReader.getValueFromKey(jsonPayload, UNITS_EN_TXT),units_lbl_pt.getText());
    	Assert.assertEquals(jsonReader.getValueFromKey(jsonPayload, AGREEMENTS_POLICIES_UN_TXT),agreement_policies_lbl_pt.getText());
    }

    public void navigateToMapPage() throws IOException, InterruptedException {
        mapPage.click();
        webDriverHelper.waitUntilVisible(mapPage, 30, 1);
    }

    public void switchOrgToArableTeam() throws InterruptedException, IOException {
        if (!(globalOrgSelected_MapPage.getText().contains("arable-team"))) {
            globalOrgDropdownBtn_MapPage.click();
            webDriverHelper.waitUntilVisible(globalOrgDropdownSearch_MapPage, 30, 1);
            globalOrgDropdownSearch_MapPage.sendKeys("arable-team");
            globalOrgSearchSuggestion_MapPage.click();
            webDriverHelper.waitUntilVisible(firstSiteIdF, 150, 1);
        } else {
            Assert.assertTrue(globalOrgSelected_MapPage.getText().contains("arable-team"), "org name is not arable team");
        }
    }
}