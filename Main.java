
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    private Scanner sc = new Scanner(System.in);
    private Validation validation = new Validation();
    private ParcelManager manager = ParcelManager.getInstance();

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

    public void run() {
        boolean loop = true;

        while (loop) {
            System.out.println("\n==============================================");
            System.out.println("       PARCEL DELIVERY MANAGEMENT SYSTEM");
            System.out.println("==============================================");
            System.out.println("1. Add Parcel");
            System.out.println("2. View All Parcels");
            System.out.println("3. Change Delivery Service");
            System.out.println("4. Mark as Delivered");
            System.out.println("5. Exit");

            int choice = validation.getMenuChoice(sc);

            switch (choice) {
                case 1:
                    addParcel();
                    break;
                case 2:
                    viewAllParcels();
                    break;
                case 3:
                    changeDeliveryService();
                    break;
                case 4:
                    markAsDelivered();
                    break;
                case 5:
                    loop = false;
                    System.out.println("Thank you for using the system!");
                    break;
            }
        }
    }

    private void addParcel() {
        System.out.println("\n==============================================");
        System.out.println("                 ADD PARCEL");
        System.out.println("==============================================");

        String type = validation.getParcelType(sc);

        String trackingNumber = "";
        boolean unique = false;

        while (!unique) {
            trackingNumber = validation.getTrackingNumber(sc);

            if (manager.trackingNumberExists(trackingNumber)) {
                System.out.println("Tracking number already exists!");
            } else {
                unique = true;
            }
        }

        String receiverName = validation.getReceiverName(sc);

        double maximumWeight;

        if (type.equals("Document")) {
            maximumWeight = 0.5;
        } else if (type.equals("Box")) {
            maximumWeight = 50.0;
        } else {
            maximumWeight = 30.0;
        }

        double weight = validation.getWeight(sc, maximumWeight);

        Parcel parcel = null;

        if (type.equals("Document")) {
            parcel = new Document(
                trackingNumber, receiverName, weight, "Standard"
            );
        } else if (type.equals("Box")) {
            int length = validation.getPositiveInt(
                sc, "Enter length in cm: ", 1, 100000
            );
            int width = validation.getPositiveInt(
                sc, "Enter width in cm: ", 1, 100000
            );
            int height = validation.getPositiveInt(
                sc, "Enter height in cm: ", 1, 100000
            );

            parcel = new Box(
                trackingNumber, receiverName, weight,
                "Standard", length, width, height
            );
        } else if (type.equals("Fragile")) {
            int length = validation.getPositiveInt(
                sc, "Enter length in cm: ", 1, 100000
            );
            int width = validation.getPositiveInt(
                sc, "Enter width in cm: ", 1, 100000
            );
            int height = validation.getPositiveInt(
                sc, "Enter height in cm: ", 1, 100000
            );

            String handlingNote = validation.getHandlingNote(sc);

            parcel = new Fragile(
                trackingNumber, receiverName, weight,
                "Standard", length, width, height, handlingNote
            );
        }

        boolean validService = false;

        while (!validService) {
            String service = validation.getDeliveryService(sc);

            if (service.equals("Same Day")
                    && parcel.getChargeableWeight() > 10) {
                System.out.println(
                    "Same Day delivery is only for parcels up to 10 kg!"
                );
            } else {
                parcel.setDeliveryService(service);
                validService = true;
            }
        }

        manager.addParcel(parcel);

        System.out.println("Parcel added successfully!");
        System.out.println("Tracking Number: " + parcel.getTrackingNumber());
        System.out.printf(
            "Chargeable Weight: %.2f kg%n",
            parcel.getChargeableWeight()
        );
        System.out.printf(
            "Delivery Fee: Php %.2f%n",
            manager.calculateDeliveryFee(parcel)
        );
    }

    private void viewAllParcels() {
        System.out.println("\n==============================================");
        System.out.println("               ALL PARCELS");
        System.out.println("==============================================");

        if (manager.isEmpty()) {
            System.out.println("No parcels yet.");
            return;
        }

        System.out.printf(
            "%-12s %-12s %-20s %12s %-12s %14s %-12s%n",
            "Tracking No.", "Type", "Receiver",
            "Weight (kg)", "Service", "Fee (Php)", "Status"
        );

        System.out.println(
            "-----------------------------------------------------------------------------------------------"
        );

        ArrayList<Parcel> parcels = manager.getParcels();

        for (Parcel parcel : parcels) {
            System.out.printf(
                "%-12s %-12s %-20s %12.2f %-12s %14.2f %-12s%n",
                parcel.getTrackingNumber(),
                parcel.getParcelType(),
                parcel.getReceiverName(),
                parcel.getChargeableWeight(),
                parcel.getDeliveryService(),
                manager.calculateDeliveryFee(parcel),
                parcel.getStatus()
            );
        }
    }

    private void changeDeliveryService() {
        System.out.println("\n==============================================");
        System.out.println("          CHANGE DELIVERY SERVICE");
        System.out.println("==============================================");

        System.out.print("Enter tracking number: ");
        String trackingNumber = sc.nextLine();

        Parcel parcel = manager.findParcel(trackingNumber);

        if (parcel == null) {
            System.out.println("Parcel not found!");
            return;
        }

        if (parcel.getStatus().equals("Delivered")) {
            System.out.println("Parcel already delivered!");
            return;
        }

        System.out.println(
            "Current service: " + parcel.getDeliveryService()
        );

        String newService = validation.getDeliveryService(sc);

        if (newService.equals("Same Day")
                && parcel.getChargeableWeight() > 10) {
            System.out.println(
                "Same Day delivery is only for parcels up to 10 kg!"
            );
            return;
        }

        parcel.setDeliveryService(newService);

        System.out.println("Delivery service updated successfully!");
        System.out.println("New service: " + parcel.getDeliveryService());
        System.out.printf(
            "New delivery fee: Php %.2f%n",
            manager.calculateDeliveryFee(parcel)
        );
    }

    private void markAsDelivered() {
        System.out.println("\n==============================================");
        System.out.println("             MARK AS DELIVERED");
        System.out.println("==============================================");

        System.out.print("Enter tracking number: ");
        String trackingNumber = sc.nextLine();

        Parcel parcel = manager.findParcel(trackingNumber);

        if (parcel == null) {
            System.out.println("Parcel not found!");
            return;
        }

        if (parcel.getStatus().equals("Delivered")) {
            System.out.println("Parcel already delivered!");
            return;
        }

        parcel.setStatus("Delivered");

        System.out.println("Parcel marked as delivered successfully!");
    }
}
