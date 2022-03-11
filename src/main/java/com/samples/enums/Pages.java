package com.samples.enums;

import com.samples.pages.BasePage;
import com.samples.pages.CareersPage;
import com.samples.pages.HomePage;
import com.samples.steps.BaseSteps;

public enum Pages {


    CAREERS_PAGE(){
        @Override
        public BasePage getPage(BaseSteps baseSteps) {
            return new CareersPage(baseSteps);
        }
    },
    HOME_PAGE(){
        @Override
        public BasePage getPage(BaseSteps baseSteps) {
            return new HomePage(baseSteps);
        }
    };

    public abstract BasePage getPage(BaseSteps baseSteps);
}
