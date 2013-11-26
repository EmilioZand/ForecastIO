package test.java;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
		features="src/test/resources/",
		format = {"json:target/integration_cucumber.json"},
		tags = {"@run"})


public class RunBDTTests {
	
}

