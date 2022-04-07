package com.samples.factories;

import com.samples.enums.Environments;
import com.samples.steps.BaseSteps;
import com.samples.steps.ChromeSteps;
import com.samples.steps.FirefoxSteps;
import static com.samples.enums.Environments.CHROME;
import static com.samples.enums.Environments.FIREFOX;

public class EnvironmentFactory {

    public static synchronized BaseSteps getEnvironment(String environment){
        switch (environment){
            case "chrome":
                return new ChromeSteps(CHROME);
            case "firefox":
                return new FirefoxSteps(FIREFOX);
            case "remote":
                return new ChromeSteps(Environments.REMOTE);

            default: throw new RuntimeException("environment not supported!!");
        }
    }
}
