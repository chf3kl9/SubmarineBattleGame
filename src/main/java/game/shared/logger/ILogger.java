package game.shared.logger;

import java.util.ArrayList;

public interface ILogger {

    void log(String message , LogLevel logLevel);
    void log(Exception ex);
    LogMessage getLastLog();
    ArrayList<LogMessage> getMessages();
}
