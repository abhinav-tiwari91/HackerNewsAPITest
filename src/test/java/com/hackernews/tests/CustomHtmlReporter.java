package com.hackernews.tests;

import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.xml.XmlSuite;
import org.testng.reporters.EmailableReporter2;

import java.util.List;

public class CustomHtmlReporter extends EmailableReporter2 implements IReporter {

    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        super.generateReport(xmlSuites, suites, outputDirectory);
        System.out.println("Custom HTML report generated at: " + outputDirectory);
    }
}
