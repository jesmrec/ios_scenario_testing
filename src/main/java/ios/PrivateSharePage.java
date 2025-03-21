package ios;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.logging.Level;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import utils.log.Log;

public class PrivateSharePage extends CommonPage {

    @iOSXCUITFindBy(id = "Viewer")
    private WebElement viewer;

    @iOSXCUITFindBy(id = "Editor")
    private WebElement editor;

    @iOSXCUITFindBy(id = "Uploader")
    private WebElement uploader;

    @iOSXCUITFindBy(id = "Contributor")
    private WebElement contributor;

    @iOSXCUITFindBy(id = "Custom")
    private WebElement custom;

    @iOSXCUITFindBy(id = "Read")
    private WebElement read;

    @iOSXCUITFindBy(id = "Upload")
    private WebElement upload;

    @iOSXCUITFindBy(id = "Delete")
    private WebElement delete;

    @iOSXCUITFindBy(id = "Expiration date")
    private WebElement expirationDate;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Invite\"]")
    private WebElement inviteButton;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Save changes\"]")
    private WebElement saveChanges;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Unshare\"]")
    private WebElement unshare;

    public static PrivateSharePage instance;

    private PrivateSharePage() {
        super();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public static PrivateSharePage getInstance() {
        if (instance == null) {
            instance = new PrivateSharePage();
        }
        return instance;
    }

    public void searchSharee(String shareeName, String type) {
        Log.log(Level.FINE, "Starts: Searching for sharee: " + shareeName + " that is a " + type);
        String urlShare = System.getProperty("server").split("://")[1].split(":")[0];
        String searchXpath = "//XCUIElementTypeTextField[@name=\"Alice@" + urlShare + "\"]";
        findXpath(searchXpath).sendKeys(shareeName);
        if (type.equals("user"))
            findXpath("(//XCUIElementTypeStaticText[@name=\"" + shareeName + "\"])[1]").click();
        else //groups return as additional information, the group name itself
            findXpath("(//XCUIElementTypeStaticText[@name=\"" + shareeName +
                    " ("+ shareeName + ")\"])[1]").click();
    }

    public void setPermissions(String permission) {
        Log.log(Level.FINE, "Starts: Set permissions: " + permission);
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
            //Sharing as default as custom. Changeable if needed
            case ("Custom"): {
                custom.click();
                break;
            }
        }
    }

    public void invite() {
        Log.log(Level.FINE, "Starts: Invite sharee");
        inviteButton.click();
    }

    public void removeSharingPermission() {
        Log.log(Level.FINE, "Starts: Remove sharing permission");
        custom.click();
    }

    public void savePermissions() {
        Log.log(Level.FINE, "Starts: Save permissions private share");
        inviteButton.click();
    }

    public void deletePrivateShare() {
        unshare.click();
    }

    public void saveChanges() {
        saveChanges.click();
    }
}
