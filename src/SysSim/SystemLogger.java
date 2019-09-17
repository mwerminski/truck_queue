package SysSim;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public final class SystemLogger {
    private static Logger log;
    private static FileHandler fh;

    private SystemLogger() {
        log = Logger.getLogger(SystemLogger.class.getName());
        try {
            fh = new FileHandler("SysSim_" + getCurrentTimeStamp() + ".log");
        }
        catch (IOException e){
            e.printStackTrace();
        }
        fh.setFormatter(new SimpleFormatter());
        log.addHandler(fh);
    }

    public static final void logException(Exception e){
        getLogger().log(Level.SEVERE, e.toString(), e);
    }

    public static final void logInfo(String info){
        getLogger().log(Level.INFO, info);
    }

    private static final String getCurrentTimeStamp() {
        return new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_SSS").format(new Date());
    }
    private static final Logger getLogger(){
        if(log == null) new SystemLogger();
        return log;
    }
}
