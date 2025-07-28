package ch.alni.balanza;

import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static org.slf4j.LoggerFactory.getLogger;

@SpringBootApplication
public class BalanzaApp {
    private static final Logger LOG = getLogger(BalanzaApp.class);

    public static void main(String[] args) {
        LOG.info("Starting BalanzaApp");
        SpringApplication.run(BalanzaApp.class, args);
    }
}
