package com.samples.pages;

import com.samples.enums.Sites;
import com.samples.steps.BaseSteps;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class CareersPage extends BasePage{

    public CareersPage(BaseSteps baseSteps) {
        super(baseSteps);
    }

    @Override
    public boolean verifyPage() {
        return baseSteps.getPageTitle().equals("Careers | ironSource");
    }

    public void verifyNumberOfOpenPositions(String expectedNumberOfOpenPositions){
        String[] split = baseSteps.getText(openPositions).split(" ");
        String actualNumberOfOpenPositions = split[0];
        Assert.assertEquals(actualNumberOfOpenPositions, expectedNumberOfOpenPositions, String.format("number of open positions is %s and not %s as expected", actualNumberOfOpenPositions, expectedNumberOfOpenPositions));
    }

    public void selectSite(Sites site) {
        switch (site){
            case ISRAEL:
                baseSteps.clickOnElement(israelCareerSite);
                break;
        }
    }

    @FindBy(partialLinkText = "Yalla")
    private WebElement israelCareerSite;
    @FindBy(css = ".small > div.col-md-4")
    private WebElement openPositions;
}
