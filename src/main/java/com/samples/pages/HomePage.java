package com.samples.pages;

import com.samples.steps.BaseSteps;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{

    public HomePage(BaseSteps baseSteps) {
        super(baseSteps);
    }

    @Override
    public boolean verifyPage() {
        return baseSteps.getPageTitle().equals("ironSource | Turning Apps Into Scalable Businesses");
    }

    public void goToCareersPage(){
        baseSteps.clickOnElement(companyTab);
        baseSteps.clickOnElement(careersOption);
    }



    @FindBy (linkText = "Company")
    private WebElement companyTab;
    @FindBy (linkText = "Careers")
    private WebElement careersOption;
}
