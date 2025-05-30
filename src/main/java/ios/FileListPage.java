package ios;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.regex.Pattern;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import utils.LocProperties;
import utils.entities.OCFile;
import utils.log.Log;

public class FileListPage extends CommonPage {

    @iOSXCUITFindBy(id = "client.file-add")
    private WebElement plusButton;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Create folder\"]")
    private WebElement createFolder;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Upload from your photo library\"]")
    private WebElement uploadFile;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Create shortcut\"]")
    private WebElement createShortcut;

    @iOSXCUITFindBy(id = "Open link")
    private WebElement openLink;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeTabBar[@name=\"Tab Bar\"]")
    private WebElement bottomBar;

    @iOSXCUITFindBy(id = "share-add-group")
    private WebElement share;

    @iOSXCUITFindBy(id = "Files")
    private WebElement browseRoot;

    @iOSXCUITFindBy(id = "Back")
    private WebElement backArrow;

    @iOSXCUITFindBy(id = "Close actions menu")
    private WebElement closeActions;

    @iOSXCUITFindBy(id = "client.folder-action")
    private WebElement threeDotButton;

    @iOSXCUITFindBy(id = "Personal")
    private WebElement personal;

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeButton[@name=\"Show/Hide sidebar\"])[2]")
    private WebElement sideMenuOpener;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Spaces\"]")
    private WebElement spaces;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name=\"Delete\"]")
    private WebElement delete;

    @iOSXCUITFindBy(xpath = xpath_openin)
    private List<WebElement> openIn;

    @iOSXCUITFindBy(id = "UIActivityContentView")
    private WebElement externalApps;

    @iOSXCUITFindBy(id = "client.search")
    private WebElement searchOption;

    @iOSXCUITFindBy(id = "Folder")
    private WebElement folderSearchOption;

    @iOSXCUITFindBy(id = "Server")
    private WebElement serverSearchOption;

    @iOSXCUITFindBy(id = "name + contents")
    private List<WebElement> serverSearchScopeNameContents;

    @iOSXCUITFindBy(id = "contents")
    private List<WebElement> serverSearchScopeContents;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[@name=\"Personal\"]")
    private WebElement patternToSearch;

    @iOSXCUITFindBy(id = "No matches")
    private WebElement noMatchesSearch;

    //Actions in action menu
    private final String xpath_delete = "//XCUIElementTypeCell[@name=\"com.owncloud.action.delete\"]";
    private final String xpath_rename = "//XCUIElementTypeCell[@name=\"com.owncloud.action.rename\"]";
    private final String xpath_move = "//XCUIElementTypeCell[@name=\"com.owncloud.action.move\"]";
    private final String xpath_copy = "//XCUIElementTypeCell[@name=\"com.owncloud.action.copy\"]";
    private final String xpath_cut = "//XCUIElementTypeCell[@name=\"com.owncloud.action.cutpasteboard\"]";
    private final String xpath_paste = "//XCUIElementTypeCell[@name=\"com.owncloud.action.importpasteboard\"]";
    private final String xpath_duplicate = "//XCUIElementTypeCell[@name=\"com.owncloud.action.duplicate\"]";
    private final String xpath_copydirectory = "//XCUIElementTypeButton[@name=\"Choose destination directory…\"]";
    private final String xpath_openin = "//XCUIElementTypeCell[@name=\"com.owncloud.action.openin\"]";

    //Actions in contextual menu menu
    private final String id_delete = "Delete";
    private final String id_rename = "Rename";
    private final String id_move = "Move";
    private final String id_copy = "Copy";
    private final String id_cut = "Cut";
    private final String id_duplicate = "Duplicate";
    private final String id_avoffline = "Make available offline";
    private final String id_favorite = "Favorite item";
    private final String id_unfavorite = "Unfavorite item";
    private final String id_sharing = "com.owncloud.action.collaborate";
    private final String id_sharing_c = "Sharing";
    private final String id_addSidebar = "Add to sidebar";
    private final String id_removeSidebar = "Remove from sidebar";
    private final String id_openin = "Open in";

    public static FileListPage instance;

    private FileListPage() {
        super();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public static FileListPage getInstance() {
        if (instance == null) {
            instance = new FileListPage();
        }
        return instance;
    }

    public void openItemInList(String itemName) {
        Log.log(Level.FINE, "Starts: Open item in list");
        findId(itemName).click();
    }

    public void refreshBySwipe() {
        swipe(0.50, 0.40, 0.50, 0.90);
    }

    public void createFolder() {
        Log.log(Level.FINE, "Starts: create folder");
        openPlusButton();
        createFolder.click();
    }

    public void createShortcut() {
        Log.log(Level.FINE, "Starts: create shortcut");
        openPlusButton();
        createShortcut.click();
    }

    public void openShortcutLink() {
        Log.log(Level.FINE, "Starts: open shortcut link");
        openLink.click();
    }

    public void openThreeDotButton() {
        Log.log(Level.FINE, "Starts: Open three dot button");
        threeDotButton.click();
    }

    public void pasteAction() {
        Log.log(Level.FINE, "Starts: Paste action");
        findXpath(xpath_paste).click();
    }

    public void openPlusButton() {
        Log.log(Level.FINE, "Starts: Open plus button");
        plusButton.click();
    }

    public void uploadFromGallery() {
        Log.log(Level.FINE, "Starts: Upload file from Gallery");
        refreshBySwipe();
        openPlusButton();
        uploadFile.click();
        acceptLibraryPermission();
        waitById( "Cancel");
    }

    public void openSidebar() {
        Log.log(Level.FINE, "Starts: Open sidebar");
        sideMenuOpener.click();
    }

    public void openCollection(String option) {
        Log.log(Level.FINE, "Starts: Open Quick Access collection: " + option);
        String collectionXpath = "//XCUIElementTypeStaticText[@name=\"" + option + "\"]";
        findXpath(collectionXpath).click();
    }

    public void openSpacesList() {
        Log.log(Level.FINE, "Starts: Open Spces list");
        spaces.click();
    }

    public void openQuickAccessOption(String option){
        Log.log(Level.FINE, "Starts: Open Quick Access option: " + option);
        findXpath("//XCUIElementTypeButton[@name=\""+ option +"\"]").click();
    }

    public void openItemSidebar(String itemName) {
        Log.log(Level.FINE, "Starts: Open Item in sidebar: " + itemName);
        switch (itemName) {
            case "shared with me"-> {
                findXpath("//XCUIElementTypeStaticText[@name=\"Shares\"]").click();
                findXpath("//XCUIElementTypeStaticText[@name=\"Shared with me\"]").click();
            }
            case "shared by me"-> {
                findXpath("//XCUIElementTypeStaticText[@name=\"Shares\"]").click();
                findXpath("//XCUIElementTypeStaticText[@name=\"Shared by me\"]").click();
            }
            case "shared by link"-> {
                findXpath("//XCUIElementTypeStaticText[@name=\"Shares\"]").click();
                findXpath("//XCUIElementTypeStaticText[@name=\"Shared by link\"]").click();
            }
            case "search"-> findXpath("//XCUIElementTypeStaticText[@name=\"Search\"]").click();
            default -> findXpath("(//XCUIElementTypeStaticText[@name=\"" + itemName + "\"])[2]").click();
        }
    }

    public void executeOperation(String operation, String itemName, String typeItem, String menu) {
        Log.log(Level.FINE, "Starts: execute operation: " + operation + " " +
                itemName + " " + menu);
        switch (menu) {
            case "Actions" -> {
                selectItemListActions(itemName);
                selectOperationFromActions(operation);
            }
            case "Contextual" -> {
                selectItemListContextual(itemName);
                selectOperationFromContextual(operation);
            }
        }
    }

    public void serverSideSearch (String searchType, String pattern){
        Log.log(Level.FINE, "Starts: Search in server: " + pattern);
        searchOption.click();
        folderSearchOption.click();
        serverSearchOption.click();
        selectSearchCriteria(searchType);
        patternToSearch.sendKeys(pattern);
    }

    private void selectSearchCriteria(String searchType) {
        switch (searchType) {
            case ("contents") -> {
                if (!serverSearchScopeNameContents.isEmpty()) { //switch options
                    serverSearchScopeNameContents.get(0).click();
                    serverSearchScopeContents.get(0).click();
                }
            }
            case ("name and contents") -> {
                if (!serverSearchScopeContents.isEmpty()) { //switch options
                    serverSearchScopeContents.get(0).click();
                    serverSearchScopeNameContents.get(0).click();
                }
            }
        }
    }

    public boolean areNotMatches(){
        Log.log(Level.FINE, "Starts: Check if there are not matches");
        return noMatchesSearch.isDisplayed();
    }

    public boolean isItemInList(String itemName) {
        Log.log(Level.FINE, "Starts: Check if item is in list: " + itemName);
        return findId(itemName).isDisplayed();
    }

    public boolean isNotItemInList(String itemName) {
        Log.log(Level.FINE, "Starts: Check if item is NOT in list: " + itemName);
        return findListId(itemName).isEmpty();
    }

    public boolean isListEmpty() {
        Log.log(Level.FINE, "Starts: Check if filelist is empty");
        return !findListId("No contents").isEmpty();
    }

    public boolean isItemInScreen(String itemName) {
        Log.log(Level.FINE, "Starts: Check if item is in screen: " + itemName);
        //A system notification can rise at this point. We refuse it
        //This can be managed via capabilities but affects other test cases.
        if (!dontAllow.isEmpty()) {
            dontAllow.get(0).click();
        }
        return !findListId(itemName).isEmpty();
    }

    public boolean isItemInSidebar(String itemName) {
        Log.log(Level.FINE, "Starts: Check if item is in sidebar: " + itemName);
        Log.log(Level.FINE, "Elements: " + findListId(itemName).size());
        return !findListXpath("//XCUIElementTypeCell[@name=\"" + itemName + "\"]").isEmpty();
    }

    public void selectItemListActions(String itemName) {
        Log.log(Level.FINE, "Starts: select actions item from list: " + itemName);
        String name = itemName;
        name = browseTo(itemName);
        openCard(name);
    }

    private void selectItemListContextual(String itemName) {
        Log.log(Level.FINE, "Starts: select contextual item from list: " + itemName);
        WebElement listCell = findId(itemName);
        longPress(listCell);
    }

    public String getPrivateLink(String scheme, String linkOriginal) {
        Log.log(Level.FINE, "Starts: Create private link: " + scheme + " " + linkOriginal);
        String originalScheme = getScheme(linkOriginal);
        Log.log(Level.FINE, "Original scheme: " + originalScheme);
        String linkToOpen = linkOriginal.replace(originalScheme, scheme);
        Log.log(Level.FINE, "Link to open: " + linkToOpen);
        return linkToOpen;
    }

    public void openPrivateLink(String privateLink) {
        Log.log(Level.FINE, "Starts: Open private link: " + privateLink);
        //Accept opening files in oC. Just one time...
        if (!findListId("Open").isEmpty()) {
            findId("Open").click();
        }
        driver.get(privateLink);
    }

    public void openFakePrivateLink() {
        Log.log(Level.FINE, "Starts: Open fake private link");
        String originalScheme = getScheme(System.getProperty("server"));
        String fakeURL = System.getProperty("server").replace(originalScheme, "owncloud") + "/f/11111111111";
        Log.log(Level.FINE, "Fake URL: " + fakeURL);
        driver.get(fakeURL);
    }

    private String getScheme(String originalURL) {
        return originalURL.split("://")[0];
    }

    public boolean privateLinkFailed() {
        return findXpath("//XCUIElementTypeStaticText[@name=\"Link resolution failed\"]").isDisplayed();
    }

    public boolean isItemOpened(String itemType, String itemName) {
        Log.log(Level.FINE, "Starts: checking if item is opened: " + itemType + " " + itemName);
        if (itemType.equals("file")) {
            Log.log(Level.FINE, "Opening file");
            return findId(itemName).isDisplayed();
        } else if (itemType.equals("folder")) {
            Log.log(Level.FINE, "Opening folder");
            return findId(itemName).isDisplayed();
        }
        return false;
    }

    public boolean isExternalApp() {
        Log.log(Level.FINE, "Starts: checking if external app menu is visible");
        return externalApps.isDisplayed();
    }

    public boolean isOpenInVisible() {
        Log.log(Level.FINE, "Starts: checking if open in menu is visible");
        return !openIn.isEmpty();
    }


    protected String browseTo(String path) {
        Log.log(Level.FINE, "Browse to path: " + path);
        String completePath = Pattern.quote("/");
        String[] route = path.split(completePath);
        Log.log(Level.FINE, "Route length: " + route.length);
        for (int j = 0; j < route.length; j++) {
            Log.log(Level.FINE, "Chunk: " + j + ": " + route[j]);
        }
        if (route.length > 0) { //we have to browse
            int i;
            for (i = 0; i < route.length - 1; i++) {
                Log.log(Level.FINE, "Browsing to: " + route[i]);
                browse(route[i]);
            }
            return route[i];
        } else { //root folder, nothing to do
            return path;
        }
    }

    public void selectOperationFromActions(String operationName) {
        WebElement operation = null;
        Log.log(Level.FINE, "Starts actions: " + operationName);
        switch (operationName) {
            case "delete" -> operation = findXpath(xpath_delete);
            case "rename" -> operation = findXpath(xpath_rename);
            case "move" -> operation = findXpath(xpath_move);
            case "copy" -> operation = findXpath(xpath_copy);
            case "duplicate" -> operation = findXpath(xpath_duplicate);
            case "make available offline" -> operation = findId(id_avoffline);
            case "cut" -> operation = findXpath(xpath_cut);
            case "paste" -> operation = findXpath(xpath_paste);
            case "share" -> operation = findId(id_sharing);
            case "favorite" -> operation = findId(id_favorite);
            case "unfavorite" -> operation = findId(id_unfavorite);
            case "add to the sidebar" -> operation = findId(id_addSidebar);
            case "remove from the sidebar" -> operation = findId(id_removeSidebar);
            case "open in" -> operation = findXpath(xpath_openin);
        }
        operation.click();
        if (operationName.equals("copy")) {
            Log.log(Level.FINE, "Selecting copy to directory");
            findXpath(xpath_copydirectory).click();
        } else if (operationName.equals("make available offline")) {
            Log.log(Level.FINE, "Wait file to be downloaded");
        }
    }

    public void selectOperationFromContextual(String operationName) {
        WebElement operation = null;
        Log.log(Level.FINE, "Starts contextual: " + operationName);
        switch (operationName) {
            case "delete" -> operation = findId(id_delete);
            case "rename" -> operation = findId(id_rename);
            case "move" -> operation = findId(id_move);
            case "copy" -> operation = findId(id_copy);
            case "cut" -> operation = findId(id_cut);
            case "duplicate" -> operation = findId(id_duplicate);
            case "make available offline" -> operation = findId(id_avoffline);
            case "share" -> operation = findId(id_sharing_c);
            case "favorite" -> operation = findId(id_favorite);
            case "unfavorite" -> operation = findId(id_unfavorite);
            case "add to the sidebar" -> operation = findId(id_addSidebar);
            case "remove from the sidebar" -> operation = findId(id_removeSidebar);
            case "open in" -> operation = findId(id_openin);
        }
        operation.click();
        if (operationName.equals("copy")) {
            Log.log(Level.FINE, "Selecting copy to directory");
            findXpath(xpath_copydirectory).click();
        } else if (operationName.equals("make available offline")) {
            Log.log(Level.FINE, "Wait file to be downloaded");
        }
    }

    public void closeActions() {
        Log.log(Level.FINE, "Starts: Close Actions Menu");
        closeActions.click();
    }

    public void browseRoot() {
        Log.log(Level.FINE, "Starts: browse to root");
        //assuming 1st level... to improve
        backArrow.click();
    }

    public void acceptDeletion() {
        Log.log(Level.FINE, "Starts: accept deletion");
        delete.click();
    }

    public void openCard(String itemName) {
        Log.log(Level.FINE, "Starts: openCard for " + itemName);
        findId("More for " + itemName).click();
    }

    public boolean isMarkedAsAvOffline(String itemName) {
        Log.log(Level.FINE, "Starts: Check if file is av. offline");
        String finalName = browseTo(itemName);
        Log.log(Level.FINE, "Final file name: " + finalName);
        refreshBySwipe();
        openCard(finalName);
        String switchAvOff = "//XCUIElementTypeSwitch[@name=\"Make available offline\"]";
        boolean switchStatus = findXpath(switchAvOff).getAttribute("value").equals("1");
        Log.log(Level.FINE, "Av. Offline status: " + switchStatus);
        return switchStatus;
    }

    public boolean isAvOfflineAvailable(String itemName) {
        Log.log(Level.FINE, "Starts: Check if av. offline is available");
        String finalName = browseTo(itemName);
        Log.log(Level.FINE, "Final file name: " + finalName);
        refreshBySwipe();
        openCard(finalName);
        return !findListId(id_avoffline).isEmpty();
    }

    public boolean itemIsFavorite(String itemName) {
        selectItemListActions(itemName);
        return !findListId(id_unfavorite).isEmpty();
    }

    public boolean displayedList(String path, ArrayList<OCFile> listServer) {
        boolean found = true;
        browseToFolder(path); //moving to the folder
        for (OCFile ocfile : listServer) {
            Log.log(Level.FINE, "Checking item in list: " + ocfile.getName());
            //Server returns the username as value. Here, we skip it.
            //in oCIS, id is returned instead of name in reference.
            //Shortcut: username > 15 = id (check a best method)
            if (ocfile.getName().equalsIgnoreCase(LocProperties.getProperties().getProperty("userNameDefault")) ||
                    ocfile.getName().length() > 15) {
                continue;
            }
            if (!isItemInList(ocfile.getName())) {
                Log.log(Level.FINE, "Item " + ocfile.getName() + " is not in the list");
                found = false;
                break;
            }
        }
        return found;
    }
}
