public class Process {
    
    private String name;
    private int burst;
    private int enteringTime;
    private int turnAroundTime;
    private int waitingTime;
    private int lastTurnTime;
    
    public Process(String name, int burst, int enteringTime) {
        this.name = name;
        this.burst = burst;
        this.enteringTime = enteringTime;
        this.lastTurnTime = enteringTime;
    }

    public String getName() {
        return this.name;
    }
    public int getBurst() {
        return this.burst;
    }
    public void setBurst(int remaingBurst) {
        this.burst = remaingBurst;
    }
    public int getEnteringTime() {
        return this.enteringTime;
    }
    public int getTurnAroundTime() {
        return this.turnAroundTime;
    }
    public void setTurnAroundTime(int time) {
        this.turnAroundTime = time;
    }
    public int getWaitingTime() {
        return this.waitingTime;
    }
    public void setWaitingTime(int time) {
        this.waitingTime = time;
    }
    public int getLastTurnTime() {
        return this.lastTurnTime;
    }
    public void setLastTurnTime(int time) {
        this.lastTurnTime = time;
    }
}