package com.samples.enums;

import com.samples.pages.BasePage;
import com.samples.pages.HomePage;
import com.samples.steps.BaseSteps;

public enum Pages {

    HOME_PAGE(){
        @Override
        public BasePage getPage(BaseSteps baseSteps) {
            return new HomePage(baseSteps);
        }
    };

    public abstract BasePage getPage(BaseSteps baseSteps);
}
