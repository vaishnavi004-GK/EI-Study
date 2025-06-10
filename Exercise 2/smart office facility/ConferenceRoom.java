import java.util.Timer;
import java.util.TimerTask;

public class ConferenceRoom {
    private final String name;
    private boolean booked = false;
    private boolean occupied = false;
    private final ControlSystem controlSystem = new ControlSystem();
    private Timer releaseTimer;
    
    public ConferenceRoom(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public boolean isBooked() {
        return booked;
    }
    
    public void book() {
        booked = true;
        controlSystem.turnOnLights(name);
        controlSystem.turnOnAC(name);
        resetReleaseTimer();
    }
    
    public void cancelBooking() {
        booked = false;
        controlSystem.turnOffLights(name);
        controlSystem.turnOffAC(name);
        if (releaseTimer != null) {
            releaseTimer.cancel();
        }
    }
    
    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
        if (occupied) {
            resetReleaseTimer();
        } else {
            // Release booking if room is unoccupied for 5 minutes
            releaseTimer = new Timer();
            releaseTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    if (!occupied) {
                        cancelBooking();
                    }
                }
            }, 300000); // 5 minutes
        }
    }
    
    private void resetReleaseTimer() {
        if (releaseTimer != null) {
            releaseTimer.cancel();
        }
        releaseTimer = new Timer();
        releaseTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (!occupied) {
                    cancelBooking();
                }
            }
        }, 300000); // 5 minutes
    }
}
