package TestRunners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
      // features = {"src/test/java/features/Accounts","src/test/java/features/maps","src/test/java/features/WeatherPage"},
        features = {"src/test/java/features/Accounts"},
        glue = {
                "support",
                "stepDefinitions"
        },
        plugin = {
                "de.monochromata.cucumber.report.PrettyReports:reporting/pretty/Sanity",
                "html:reporting/CucumberHTMLReports/Sanity",
                "json:reporting/cucumberOtherreports/Sanity.json",
                "junit:reporting/cucumber-reports/Sanity.xml",
                "rerun:target/rerun.txt" //Creates a text file with failed scenarios
        },
        strict = true,
        tags = "@sanity"
)

public class Sanity {
}