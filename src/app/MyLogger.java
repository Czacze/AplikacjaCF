package app;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

/**
 * Klasa <code>MyLogger</code> pozwala na tworzenie i wyświetlania logów przy pomocy biblioteki log4j
 * Logi zapisywane są do pliku oraz wyświetlane na bieżąco w konsoli
 */
public class MyLogger {

    static final Logger logger = Logger.getLogger("logger");

    public MyLogger() {
        DOMConfigurator.configure("config/log4j-conf.xml");
    }
    public static void writeLog(String level, String message) {
        if(level.equals("DEBUG")) logger.debug(message);
        else if(level.equals("INFO")) logger.info(message);
        else if(level.equals("WARN")) logger.warn(message);
        else if(level.equals("ERROR")) logger.error(message);
        else if(level.equals("FATAL")) logger.fatal(message);
    }
}
