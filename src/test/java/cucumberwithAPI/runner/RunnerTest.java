package cucumberwithAPI.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features={"features"},
		glue="stepdefs",
		plugin =
			{
					"pretty" , 
					"json:target/cucumber.json",
					"html:target/cucumber"
				
			}
		
		)
public class RunnerTest {

}
