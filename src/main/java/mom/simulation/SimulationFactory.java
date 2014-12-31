package mom.simulation;

import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public class SimulationFactory {
    private final static Logger logger = LoggerFactory.getLogger(SimulationFactory.class);
    private final ApplicationContext ctx;

    @Autowired
    public SimulationFactory(final ApplicationContext ctx) {
        logger.info("wiring context");
        this.ctx = ctx;
    }

    public Simulation simulation() {
        logger.info("serving {}", Simulation.class);
        Simulation s = ctx.getBean(Simulation.class);
        return s;
    }

    public Simulator simulator() {
        logger.info("serving {}", Simulator.class);
        return ctx.getBean(Simulator.class);
    }

    @Bean(name = "newListener")
    public Listener newListener() {
        logger.info("serving {}", Listener.class);
        return ctx.getBean(Listener.class);
    }

    @Bean(name = "simulators")
    @Scope("singleton")
    @Lazy
    public Set<Simulator> simulators() {
        logger.info("serving simulators", Set.class);
        return simulators;
    }

    @Bean(name = "listeners")
    @Scope("singleton")
    @Lazy
    public Set<Listener> listeners() {
        logger.info("serving listeners", Set.class);
        return listeners;
    }

    // @Bean
    // @Lazy
    // @Scope("prototype")
    // public Subscriber subscriber() {
    // logger.info("serving ", Subscriber.class);
    // return ctx.getBean(Subscriber.class);
    // }
}
