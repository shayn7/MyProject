package tests;

import com.samples.factories.EnvironmentFactory;
import com.samples.steps.BaseSteps;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

    @Listeners({listeners.Listeners.class})
    public class TestSetup {

        protected BaseSteps baseSteps;
        protected String endpoint;
        protected static final String BASE_URL = "https://zalmoxisus.github.io/examples/todomvc/";

        public BaseSteps getBaseSteps(){
            return baseSteps;
        }

        @BeforeSuite
        public void setup(){
            endpoint = System.getProperty("endpoint", BASE_URL);
            String environmentToUse = System.getProperty("environment", "chrome");
            this.baseSteps = EnvironmentFactory.getEnvironment(environmentToUse);
        }

        @AfterMethod(alwaysRun = true)
        public void tearDown(){
            if (baseSteps != null) this.baseSteps.tearDown();
        }

    /*
    maven command: mvn clean test -Dendpoint=${endpoint} -DsuiteXmlFile=${suiteXmlFile} -Denvironment=${environment} -Dhub=${hub}
    allure report command: allure serve allure-results
     */
}
