package logger;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

public class Log {
  public Logger logger = LogManager.getLogger(Log.class);//loger objesi kullanmak için gerekli

    public Log() {

        DOMConfigurator.configure("log4j.xml");//log4j.xml e ulaşmak için kullanılır

    }


    public void info(String message){
        logger.info(message);
    }
    public void warn(String message){
        logger.warn(message);
    }
    public void error(String message){
        logger.error(message);
    }

}
