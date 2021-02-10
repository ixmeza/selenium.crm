package com.selenium.demo.utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log {

    private static Log instanceOfLogClass = null;

    private static Logger Log;

    private Log()
    {
        Log = LogManager.getLogger(Log.class.getName());
    }

    public static synchronized Log getInstance(){
        if(instanceOfLogClass == null){
            instanceOfLogClass = new Log();
        }
        return instanceOfLogClass;
    }

    public org.apache.logging.log4j.Logger getLogger(){
        return Log;
    }

    // This is to print log for the beginning of the test case, as we usually run so many test cases as a test suite
    public static void startTestCase(String sTestCaseName){
        Log.info(sTestCaseName);
    }
    //This is to print log for the ending of the test case
    public static void endTestCase(String sTestCaseName){
        Log.info(sTestCaseName + "-E---N---D-");
    }

    // Need to create these methods, so that they can be called
    public static void info(String message) {
        Log.info(message);
    }

    public static void warn(String message) {
        Log.warn(message);
    }

    public static void error(String message) {
        Log.error(message);
    }

    public static void fatal(String message) {
        Log.fatal(message);
    }

    public static void debug(String message) {
        Log.debug(message);
    }

}
