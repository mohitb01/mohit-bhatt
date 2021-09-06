package com.automation.runnerFile;

import org.junit.runner.RunWith;

import com.automation.base.SerenityBase;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;

@RunWith(CucumberWithSerenity.class)

@CucumberOptions(strict = false, features = "src/test/resources/features/",
        glue = { "com.automation.stepdefinitions" }, tags = { "@AddPets" },
        plugin = { "pretty", "html:target/cucumber",
                "json:target/cucumber.json", "junit:target/cucumber.xml" })

public class RunnerCukes extends SerenityBase {
}
