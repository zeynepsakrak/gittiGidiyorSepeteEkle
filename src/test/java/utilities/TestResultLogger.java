package utilities;

import logger.Log;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import java.util.Optional;

public class TestResultLogger implements TestWatcher {//testinizi takip eden IPA

    Log log = new Log();

    @Override
    public void testSuccessful(ExtensionContext context) {//başarılı olursa
        String testName = context.getDisplayName();
        log.info(testName + " PASSED");
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        String testName = context.getDisplayName();
        String testFailCause = cause.getMessage() ;
        log.error(testName + " FAILED with cause : " + testFailCause);


    }

    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
//
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        String testName = context.getDisplayName();
        String testAbordCause = cause.getMessage() ;
        log.warn(testName + " FAILED with cause : " +testAbordCause);
    }
}
