package TestRunners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/features",
        glue = {
                "support",
                "stepDefinitions"
        },
        plugin = {
                "de.monochromata.cucumber.report.PrettyReports:reporting/pretty/Seasons",
                "html:reporting/CucumberHTMLReports/Seasons",
                "json:reporting/cucumberOtherreports/Seasons.json",
                "junit:reporting/cucumber-reports/Seasons.xml",
                "rerun:target/rerun.txt" //Creates a text file with failed scenarios
        },
        strict = true,
        tags = "@Performance"
)

public class TestRunnerPerformance {
}