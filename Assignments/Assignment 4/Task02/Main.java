import java.util.*;
public class Main {
    public static void main(String[] args) {
        System.out.println("TASK 2 - HOSPITAL APPOINTMENT EVENT DESIGN");
        System.out.println("Department JComboBox -> ItemEvent -> ItemListener -> refresh doctor model.");
        System.out.println("Book JButton -> ActionEvent -> ActionListener -> validate -> check slot -> save -> confirmation.");
        System.out.println("Window -> WindowListener: opened, closing, closed, activated, deactivated, iconified, deiconified.");
        System.out.println("Doctor cache: Map<Department,List<Doctor>> avoids repeated queries; refresh when data changes.");
        System.out.println("Database each change: fresher data but repeated latency. Cache: faster UI, requires invalidation.");
        System.out.println("Window lifecycle is event-driven; applet init/start/paint/stop/destroy are container lifecycle callbacks.");
    }
}
