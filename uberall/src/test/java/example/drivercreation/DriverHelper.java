package example.drivercreation;

import java.io.PrintWriter;

public class DriverHelper {

    protected static PrintWriter writer;

    public static int timeout;
    public static String browserName;
    public static String hubURL;
    public static String host;

    public static String loggingFileLocation;
    public static PrintWriter getWriter() {
        return writer;
    }
    public static void setWriter(PrintWriter writer) {
        DriverHelper.writer = writer;
    }

}
