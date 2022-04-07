package tests;

import com.google.common.collect.Lists;
import com.samples.pages.HomePage;
import org.testng.annotations.Test;

import static com.samples.enums.Filters.ALL;
import static com.samples.enums.Pages.HOME_PAGE;

public class HomePageTests extends TestSetup{

    private HomePage homePage;

    @Test(priority = 1, description = "delete a task")
    public void deleteTask(){
        enterTasks();
        homePage.deleteTask("buy a shirt");
    }


    @Test(priority = 2, description = "toggle all tasks")
    public void toggleAllTasks(){
        enterTasks();
        homePage.toggleTasks(ALL);
    }

    @Test(priority = 3, description = "toggle all tasks")
    public void editTask(){
        enterTasks();
        homePage.editTask("buy a shirt", "new task");
    }

    private void enterTasks(){
        baseSteps.startSession();
        baseSteps.getUrl(BASE_URL);
        baseSteps.iShouldBeOnPage(HOME_PAGE);
        homePage = baseSteps.getExpectedPage();
        homePage.enterNewTask(Lists.newArrayList("buy a shirt", "call ben", "call kate","take a picture"));
    }
}
