package app;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

/**
 * Klasa <code>MyLogger</code> pozwala na tworzenie i wyświetlania logów przy pomocy biblioteki log4j
 * Logi zapisywane są do pliku oraz wyświetlane na bieżąco w konsoli
 */
public class MyLogger {

    public static final Logger log = Logger.getLogger("logger");

    public MyLogger() {
        DOMConfigurator.configure("config/log4j-conf.xml");
    }
}
