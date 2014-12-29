package mom.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:basic_simulation.properties")
public class Pauser {
    private final long nanoPause;

    public Pauser(@Value("${pauselength}") long nanoPause) {
        this.nanoPause = nanoPause;
    }

    // Busy wait for granularity
    public void pause() {
        long free = System.nanoTime() + nanoPause;
        while (System.nanoTime() < free) {}
    }
}
