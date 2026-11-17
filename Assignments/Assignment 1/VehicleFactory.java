/** Factory used to validate and construct supported vehicle categories. */
public final class VehicleFactory {
    private VehicleFactory() { }

    public static Vehicle create(String type, String registration) {
        if (type == null) throw new IllegalArgumentException("Vehicle type is required.");
        return switch (type.trim().toLowerCase()) {
            case "two-wheeler", "twowheeler" -> new TwoWheeler(registration);
            case "car" -> new Car(registration);
            case "electric-car", "electriccar", "ev" -> new ElectricCar(registration);
            case "e-bike", "ebike" -> new EBike(registration);
            case "delivery-van", "deliveryvan", "van" -> new DeliveryVan(registration);
            default -> throw new IllegalArgumentException("Unknown vehicle type: " + type);
        };
    }
}
