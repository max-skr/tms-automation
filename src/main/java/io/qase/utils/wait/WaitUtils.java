package io.qase.utils.wait;

import io.qase.po.pages.Loadable;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.function.Supplier;

public class WaitUtils {

    private static final Duration LOAD_DURATION = Duration.ofSeconds(30);
    private static final Duration DEFAULT_DURATION = Duration.ofSeconds(30);

    public static <T extends Loadable> T waitLoaded(T loadable) {
        return new FluentWait<>(loadable)
                .withMessage("Failed to load: " + loadable.getClass().getSimpleName())
                .withTimeout(LOAD_DURATION)
                .until(component -> component.isLoaded() ? component : null);
    }

    public static void waitFor(Supplier<Boolean> conditionSupplier, String waitName) {
        new FluentWait<>(conditionSupplier)
                .withMessage(waitName)
                .ignoring(StaleElementReferenceException.class, NoSuchElementException.class)
                .withTimeout(DEFAULT_DURATION)
                .until(Supplier::get);
    }

    public static void waitAsserted(Runnable assertion) {
        new FluentWait<>(assertion)
                .withMessage("Waiting for assertion")
                .withTimeout(DEFAULT_DURATION)
                .until(ast -> {
                    try {
                        ast.run();
                        return true;
                    } catch (AssertionError e) {
                        return false;
                    }
                });
    }
}
