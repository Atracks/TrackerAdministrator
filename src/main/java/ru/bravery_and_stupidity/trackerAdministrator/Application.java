package ru.bravery_and_stupidity.trackerAdministrator;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.bravery_and_stupidity.trackerAdministrator.controllers.ProjectController;

@SpringBootApplication
public class Application {
    private static final Logger logger = Logger.getLogger(ProjectController.class);

    public static void main(String[] args) {
        BasicConfigurator.configure();
        SpringApplication.run(Application.class, args);
        logger.info("runing app");
    }
}
