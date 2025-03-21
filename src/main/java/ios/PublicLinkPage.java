package ios;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.logging.Level;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import utils.date.DateUtils;
import utils.log.Log;

public class PublicLinkPage extends CommonPage {

    @iOSXCUITFindBy(id = "Name")
    private WebElement linkName;

    @iOSXCUITFindBy(id = "Viewer")
    private WebElement viewer;

    @iOSXCUITFindBy(id = "Editor")
    private WebElement editor;

    @iOSXCUITFindBy(id = "Uploader")
    private WebElement uploader;

    @iOSXCUITFindBy(id = "Contributor")
    private WebElement contributor;

    @iOSXCUITFindBy(id = "Set")
    private WebElement setPasswordButton;

    @iOSXCUITFindBy(id = "Generate")
    private WebElement generatePassword;

    @iOSXCUITFindBy(id = "******")
    private WebElement passwordEnabled;

    @iOSXCUITFindBy(className = "XCUIElementTypeSecureTextField")
    private WebElement passwordField;

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeButton[@name=\"Set\"])[2]")
    private WebElement submitPassword;

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name=\"Add\"])[2]")
    private WebElement expirationButton;

    @iOSXCUITFindBy(id = "Date Picker")
    private WebElement datePicker;

    @iOSXCUITFindBy(id = "Month")
    private WebElement monthPicker;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeApplication[@name=\"ownCloud\"]/XCUIElementTypeWindow[1]" +
            "/XCUIElementTypeOther[5]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther" +
            "/XCUIElementTypeOther[2]/XCUIElementTypeDatePicker/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]" +
            "/XCUIElementTypeDatePicker/XCUIElementTypePicker/XCUIElementTypePickerWheel[1]")
    private WebElement monthWheel;

    @iOSXCUITFindBy(id = "Create")
    private WebElement createLink;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Create\"]")
    private WebElement createButton;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Save changes\"]")
    private WebElement saveChanges;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Unshare\"]")
    private WebElement unshareLink;

    public static PublicLinkPage instance;

    private PublicLinkPage() {
        super();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public static PublicLinkPage getInstance() {
        if (instance == null) {
            instance = new PublicLinkPage();
        }
        return instance;
    }

    public void setPermission(String permission) {
        Log.log(Level.FINE, "Starts: Set link permission: " + permission);
        switch (permission) {
            case ("Viewer"): {
                viewer.click();
                break;
            }
            case ("Editor"): {
                editor.click();
                break;
            }
            case ("Uploader"): {
                uploader.click();
                break;
            }
            case ("Contributor"): {
                contributor.click();
                break;
            }
        }
    }

    public void setPassword(String password) {
        Log.log(Level.FINE, "Starts: Add link password: " + password);
        setPasswordButton.click();
        passwordField.sendKeys(password);
        setPasswordButton.click();
    }

    public void setPasswordAuto() {
        Log.log(Level.FINE, "Starts: Add link password automatically");
        generatePassword.click();
    }

    public void setName(String name) {
        Log.log(Level.FINE, "Starts: Add link name: " + name);
        linkName.sendKeys(name);
    }

    public boolean isPasswordEnabled(String itemName) {
        return passwordEnabled.isDisplayed();
    }

    //Day to set: given day of the following month
    public void setExpiration(String day) {
        Log.log(Level.FINE, "Starts: Set Expiration date: " + day);
        expirationButton.click();
        datePicker.click();
        monthPicker.click();
        //No matter which month, wheel moves to th next value. Framework issue
        monthWheel.sendKeys("December");
        datePicker.click();
        findId(day).click();
    }

    public boolean isExpirationCorrect(String day) {
        Log.log(Level.FINE, "Starts: Check expiration day: " + day);
        String displayedDate = DateUtils.displayedDate(day);
        Log.log(Level.FINE, "Date to compare: " + displayedDate);
        String dateInPicker = datePicker.getAttribute("value");
        Log.log(Level.FINE, "Date to check in the screen: " + dateInPicker);
        return dateInPicker.equals(displayedDate);
    }

    public boolean isNameCorrect(String name) {
        Log.log(Level.FINE, "Starts: Check link name: " + name);
        return linkName.getAttribute("value").equals(name);
    }

    public void submitLink() {
        createButton.click();
    }

    public void saveChanges() {
        saveChanges.click();
    }

    public void deleteLink() {
        unshareLink.click();
    }
}
