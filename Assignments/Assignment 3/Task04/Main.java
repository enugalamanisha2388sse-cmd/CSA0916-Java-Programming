import java.util.*;

class ScannerStation extends Thread {
    private final String station; private final int scripts;
    ScannerStation(String station, int scripts) { super(station); this.station=station; this.scripts=scripts; }
    public void run() {
        try { Thread.sleep(20L + (station.hashCode() & 31)); }
        catch (InterruptedException e) { Thread.currentThread().interrupt(); return; }
        System.out.println(station + " scanned " + scripts + " scripts");
    }
}
class RunnableStation implements Runnable {
    private final String station; private final int scripts;
    RunnableStation(String station, int scripts) { this.station=station; this.scripts=scripts; }
    public void run() { System.out.println(station + " scanned " + scripts + " scripts (Runnable)"); }
}
public class Main {
    public static void main(String[] args) throws Exception {
        ScannerStation[] stations = {
            new ScannerStation("Station-A", 25), new ScannerStation("Station-B", 30),
            new ScannerStation("Station-C", 20), new ScannerStation("Station-D", 25)
        };
        for (ScannerStation s : stations) s.start();
        for (ScannerStation s : stations) s.join();
        System.out.println("All Thread stations completed; alive=" + stations[0].isAlive());
        Thread runnable = new Thread(new RunnableStation("Station-R", 10));
        runnable.start(); runnable.join();
        System.out.println("Total scripts represented: 110");
    }
}