package ios;

import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.logging.Level;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import utils.log.Log;

public class SpacesPage extends CommonPage {

    public static SpacesPage instance;

    private SpacesPage(){
        super();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public static SpacesPage getInstance() {
        if (instance == null) {
            instance = new SpacesPage();
        }
        return instance;
    }

    public boolean areAllSpacesVisible(List<List<String>> spaces){
        Log.log(Level.FINE, "Starts: check all spaces are visible");
        for (List<String> rows : spaces) {
            String name = rows.get(0);
            String description = rows.get(1);
            if (findListId(name).isEmpty() && findListId(description).isEmpty()){
                return false;
            }
        }
        return true;
    }
}
