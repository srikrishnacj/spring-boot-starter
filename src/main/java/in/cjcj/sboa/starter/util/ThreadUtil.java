package in.cjcj.sboa.starter.util;

public class ThreadUtil {
    /**
     * Utility method to sleep for some amount of time
     *
     * @param sleepInMilliSeconds
     */
    public static void sleep(int sleepInMilliSeconds) {
        try {
            Thread.sleep(sleepInMilliSeconds);
        } catch (InterruptedException e) {

        }
    }
}