package tests;

import com.samples.pages.CareersPage;
import com.samples.pages.HomePage;
import org.testng.annotations.Test;
import static com.samples.enums.Pages.CAREERS_PAGE;
import static com.samples.enums.Pages.HOME_PAGE;
import static com.samples.enums.Sites.ISRAEL;

public class CareersPageTests extends TestSetup{

    @Test(priority = 1, description = "get number of open positions in israel")
    public void getNumberOfOpenPositions(){
        baseSteps.startSession();
        baseSteps.getUrl(BASE_URL);
        baseSteps.iShouldBeOnPage(HOME_PAGE);
        HomePage homePage = baseSteps.getExpectedPage();
        homePage.goToCareersPage();
        baseSteps.iShouldBeOnPage(CAREERS_PAGE);
        CareersPage careersPage = baseSteps.getExpectedPage();
        careersPage.selectSite(ISRAEL);
        baseSteps.switchWindow();
        careersPage.verifyNumberOfOpenPositions("96");
    }
}
