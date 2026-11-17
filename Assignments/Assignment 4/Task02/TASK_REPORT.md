# Task 2 – Requirement to Event-Driven Design

## Event table
| Event source | Event object | Listener | Action |
|---|---|---|---|
| Department JComboBox | ItemEvent | ItemListener | Refresh doctor list for selected department |
| Doctor JComboBox | ItemEvent | ItemListener | Update selected doctor details |
| Date field / spinner | ChangeEvent or ActionEvent | ChangeListener / ActionListener | Validate date |
| Book JButton | ActionEvent | ActionListener | Validate, check availability, persist booking |
| Reset JButton | ActionEvent | ActionListener | Clear form |
| JFrame | WindowEvent | WindowListener / WindowAdapter | Handle open, close, activate and state changes |

## Component containment tree
```
JFrame AppointmentWindow (BorderLayout)
  JPanel form (GridBagLayout)
    JLabel + JTextField patient name
    JLabel + JComboBox department
    JLabel + JComboBox doctor
    JLabel + JSpinner appointment date/time
    JButton Book | JButton Reset
  JPanel status (FlowLayout) -> JLabel
```

## Pseudocode
Department change: get selected department; retrieve doctors from cache or repository; replace doctor combo model; select no doctor; display empty-state message if none.
Booking: validate patient name, department, doctor and future appointment time; disable Book; perform availability check and insert in background; show confirmation or clear error; re-enable button in completion callback.

## Method trace
Create components -> register listeners -> pack -> set visible -> user events dispatch to listeners -> closing event prompts/cleans up -> dispose frame. Use WindowAdapter when only selected window methods are needed.

## Lifecycle comparison
A window receives user-driven WindowEvents and can be disposed/reopened. Applet methods init(), start(), paint(), stop(), destroy() are lifecycle callbacks controlled by an applet container; they are not replacements for Swing window events.

## Doctor loading alternatives
Querying on every department change gives fresher data but adds latency and database load. Loading once into Map<Department,List<Doctor>> makes switching fast (average map lookup O(1)), but requires cache refresh/invalidation when doctors change.
