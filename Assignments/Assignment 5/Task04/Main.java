import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class Resident implements Serializable {

    private static final long serialVersionUID = 1L;

    String name;
    String registerNo;

    // Sensitive/runtime-only fields are excluded from serialization.
    transient String password;
    transient Object liveConnection;
}

class Room implements Serializable {

    private static final long serialVersionUID = 1L;

    List<Resident> residents = new ArrayList<>();
}

class Hostel implements Serializable {

    private static final long serialVersionUID = 1L;

    List<Room> rooms = new ArrayList<>();
}

public class Main {

    public static void main(String[] args) {
        System.out.println("Password and live Connection are transient; Hostel serialization traverses reachable serializable objects.");
        System.out.println("New phoneNumber field defaults to null when reading older streams; version/migrate.");
    }
}
