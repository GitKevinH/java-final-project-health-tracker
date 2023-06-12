package org.ctac.java103.models;
import java.util.Date;

public class Sleep {
    private Date sleepTime;
    private Date wakeTime;

    public static void main(String[] args) {

    }

    public Sleep(Date sleepTime, Date wakeTime) {
        this.sleepTime = sleepTime;
        this.wakeTime = wakeTime;
    }

    public Sleep() {
        this.sleepTime = null;
        this.wakeTime = null;
    }


    public Date getSleepTime() {
        return sleepTime;
    }

    public void setSleepTime(Date sleepTime) {
        this.sleepTime = sleepTime;
    }

    public Date getWakeTime() {
        return wakeTime;
    }

    public void setWakeTime(Date wakeTime) {
        this.wakeTime = wakeTime;
    }

    public boolean hasValidSleepAndWakeTime() {
        return sleepTime != null && wakeTime != null && wakeTime.after(sleepTime);
    }

    public double getTotalSleepHours() {
        if (hasValidSleepAndWakeTime()) {
            long sleepDurationInMillis = getWakeTime().getTime() - getSleepTime().getTime();
            return sleepDurationInMillis / (1000.0 * 60.0 * 60.0);
        } else {
            return 0;
        }
    }
}
