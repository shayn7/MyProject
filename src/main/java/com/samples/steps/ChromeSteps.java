package com.samples.steps;

import com.samples.enums.Environments;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;

public class ChromeSteps extends BaseSteps{

    public ChromeSteps(Environments environment) {
        super(environment);
    }

    @Override
    protected MutableCapabilities initCapabilities() {
        ChromeOptions options = new ChromeOptions();
        options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
        //options.addArguments("--headless");
        return options;
    }
}
