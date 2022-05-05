package util;

import lombok.experimental.UtilityClass;
import org.testcontainers.containers.PostgreSQLContainer;

@UtilityClass
public class HinernateTestUtil {
    private static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:14");

    static {
        postgres.start();
    }
}
