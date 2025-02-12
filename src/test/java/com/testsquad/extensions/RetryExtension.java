package com.testsquad.extensions;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;
import com.testsquad.config.TestConfig;

public class RetryExtension implements TestExecutionExceptionHandler {
    @Override
    public void handleTestExecutionException(ExtensionContext context, Throwable throwable) throws Throwable {
        int maxRetries = Integer.parseInt(TestConfig.getProperty("max.retries", "3"));
        int currentAttempt = getRetryCount(context);

        if (currentAttempt < maxRetries) {
            setRetryCount(context, currentAttempt + 1);
            // Retry the test
            context.getRequiredTestMethod().invoke(context.getRequiredTestInstance());
        } else {
            throw throwable;
        }
    }

    private int getRetryCount(ExtensionContext context) {
        return context.getStore(ExtensionContext.Namespace.create(getClass()))
            .getOrDefault("retryCount", Integer.class, 0);
    }

    private void setRetryCount(ExtensionContext context, int count) {
        context.getStore(ExtensionContext.Namespace.create(getClass()))
            .put("retryCount", count);
    }
} 