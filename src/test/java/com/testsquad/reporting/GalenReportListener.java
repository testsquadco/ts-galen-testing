package com.testsquad.reporting;

import com.galenframework.reports.GalenTestInfo;
import com.galenframework.reports.HtmlReportBuilder;
import com.galenframework.reports.model.LayoutReport;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import java.util.LinkedList;
import java.util.List;
import java.util.Date;

public class GalenReportListener implements TestWatcher {
    private final List<GalenTestInfo> tests = new LinkedList<>();

    @Override
    public void testSuccessful(ExtensionContext context) {
        recordTest(context, null);
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        recordTest(context, cause);
    }

    private void recordTest(ExtensionContext context, Throwable error) {
        GalenTestInfo test = GalenTestInfo.fromString(context.getDisplayName());
        test.setStartedAt(new Date(System.currentTimeMillis()));
        
        if (error != null) {
            test.setException(error);
        }
        
        tests.add(test);
        
        try {
            new HtmlReportBuilder().build(tests, "target/galen-reports");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
} 