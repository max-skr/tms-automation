package io.qase.utils.wait;

import io.qase.po.pages.Loadable;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

public class WaitUtils {

    private static final Duration LOAD_DURATION = Duration.ofSeconds(30);

    public static <T extends Loadable> T waitLoaded(T loadable) {
        return new FluentWait<>(loadable)
                .withMessage("Failed to load: " + loadable.getClass().getSimpleName())
                .withTimeout(LOAD_DURATION)
                .until(component -> component.isLoaded() ? component : null);
    }

}
