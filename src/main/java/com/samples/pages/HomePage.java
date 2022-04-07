package com.samples.pages;

import com.samples.enums.Filters;
import com.samples.steps.BaseSteps;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

public class HomePage extends BasePage{

    public HomePage(BaseSteps baseSteps) {
        super(baseSteps);
    }

    @Override
    public boolean verifyPage() {
        return baseSteps.getPageTitle().equals("Redux TodoMVC example");
    }

    public void enterNewTask(List<String> tasks){
        for (String task : tasks) {
            baseSteps.setText(enterNewTaskField, task);
            baseSteps.pressEnterButton();
        }
        verifyNumberOfItemsInTheTable("5");
    }

    public void deleteTask(String task) {
        for (WebElement item : items) {
            if (baseSteps.getText(item).equals(task)) {
                baseSteps.hoverOverAnElement(item);
                baseSteps.clickOnElement(item.findElement(By.cssSelector("button.destroy")));
            }
        }
    }

    public void editTask(String task, String newTask) {
        for (WebElement item : items) {
            if (baseSteps.getText(item).equals(task)) {
                baseSteps.doubleClickOnElementAndSetNewText(item, newTask);
            }
        }
    }

    public void toggleTasks(Filters filter) {
        switch (filter){
            case ALL:
                if(!allFilter.isSelected()) baseSteps.clickOnElement(allFilter);
                baseSteps.clickOnElement(toggleTableItems);
                verifyNumberOfItemsInTheTable("No items left");
                break;
            case ACTIVE:
                break;
        }
    }

    private void verifyNumberOfItemsInTheTable(String expected) {
        String actualNumber = numberOfItemsInTable.getText().contains("No") ? "No items left" : numberOfItemsInTable.getText().split(" ")[0];
        Assert.assertEquals(actualNumber, expected, String.format("%s items were added to table", actualNumber));
    }


    @FindBy (css = "input.new-todo")
    private WebElement enterNewTaskField;
    @FindBy (css = "div.view")
    private List<WebElement> items;
    @FindBy (css = "input.toggle-all")
    private WebElement toggleTableItems;
    @FindBy (css = "span.todo-count")
    private WebElement numberOfItemsInTable;
    @FindBy (xpath = "//a[contains(text(),'All')]")
    private WebElement allFilter;
    @FindBy (xpath = "//a[contains(text(),'Active')]")
    private WebElement activeFilter;
    @FindBy (xpath = "//a[contains(text(),'Completed')]")
    private WebElement completedFilter;

}
