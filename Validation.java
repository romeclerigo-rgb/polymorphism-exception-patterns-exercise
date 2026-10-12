
import java.util.Scanner;

public class Validation {

    public int getMenuChoice(Scanner sc) {

        boolean valid = false;
        int choice = 0;

        while (!valid) {
            System.out.println("======================================");
            System.out.print("Enter your choice: ");
            String input = sc.nextLine();

            if (input.matches("[1-5]")) {
                try {
                    choice = Integer.parseInt(input);
                    valid = true;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid menu choice!");
                }
            } else {
                System.out.println(
                    "Invalid input! Please enter a number from 1 to 5 only."
                );
            }
        }

        return choice;
    }

    public String getTrackingNumber(Scanner sc) {

        boolean valid = false;
        String trackingNumber = "";

        while (!valid) {
            System.out.print("Enter tracking number (ABC0001): ");
            String input = sc.nextLine();

            if (input.isEmpty()) {
                System.out.println("Tracking number cannot be empty!");

            } else if (!input.matches("ABC\\d{4}")) {
                System.out.println(
                    "Invalid tracking number! Format must be ABC followed by 4 digits."
                );

            } else {
                trackingNumber = input;
                valid = true;
            }
        }

        return trackingNumber;
    }

    public String getReceiverName(Scanner sc) {

        boolean valid = false;
        String receiverName = "";

        while (!valid) {
            System.out.print("Enter receiver name: ");
            String input = sc.nextLine();

            if (input.trim().isEmpty()) {
                System.out.println("Receiver name cannot be empty!");

            } else if (input.length() > 50) {
                System.out.println(
                    "Invalid name! Maximum length is 50 characters."
                );

            } else {
                receiverName = input;
                valid = true;
            }
        }

        return receiverName;
    }

    public double getWeight(Scanner sc, double maximumWeight) {

        boolean valid = false;
        double weight = 0;

        while (!valid) {
            System.out.print("Enter weight in kg: ");
            String input = sc.nextLine();

            if (input.isEmpty()) {
                System.out.println("Weight cannot be empty!");

            } else if (!input.matches(
                    "(?:0|[1-9]\\d*)(?:\\.\\d+)?")) {
                System.out.println(
                    "Invalid weight! Please enter a valid positive number."
                );

            } else {
                try {
                    weight = Double.parseDouble(input);

                    if (Double.isInfinite(weight)
                            || Double.isNaN(weight)
                            || weight <= 0) {
                        System.out.println(
                            "Invalid weight! Weight must be greater than 0."
                        );

                    } else if (weight > maximumWeight) {
                        System.out.println(
                            "Invalid weight! Maximum weight is "
                            + maximumWeight + " kg."
                        );

                    } else {
                        valid = true;
                    }

                } catch (NumberFormatException e) {
                    System.out.println(
                        "Invalid weight! Please enter a valid number."
                    );
                }
            }
        }

        return weight;
    }

    public int getPositiveInt(
            Scanner sc, String prompt, int minimum, int maximum) {

        boolean valid = false;
        int number = 0;

        while (!valid) {
            System.out.print(prompt);
            String input = sc.nextLine();

            if (input.isEmpty()) {
                System.out.println("Input cannot be empty!");

            } else if (!input.matches("[1-9]\\d*")) {
                System.out.println(
                    "Invalid input! Please enter a positive whole number."
                );

            } else {
                try {
                    number = Integer.parseInt(input);

                    if (number < minimum || number > maximum) {
                        System.out.println(
                            "Invalid input! Enter a number from "
                            + minimum + " to " + maximum + "."
                        );
                    } else {
                        valid = true;
                    }

                } catch (NumberFormatException e) {
                    System.out.println(
                        "Invalid input! The number is too large."
                    );
                }
            }
        }

        return number;
    }

    public String getHandlingNote(Scanner sc) {

        boolean valid = false;
        String note = "";

        while (!valid) {
            System.out.print("Enter handling note: ");
            String input = sc.nextLine();

            if (input.trim().isEmpty()) {
                System.out.println("Handling note cannot be empty!");

            } else if (input.length() > 100) {
                System.out.println(
                    "Invalid note! Maximum length is 100 characters."
                );

            } else {
                note = input;
                valid = true;
            }
        }

        return note;
    }

    public String getParcelType(Scanner sc) {

        boolean valid = false;
        String type = "";

        while (!valid) {
            System.out.println("\nParcel Types");
            System.out.println("1. Document");
            System.out.println("2. Box");
            System.out.println("3. Fragile");
            System.out.print("Enter parcel type: ");

            String input = sc.nextLine();

            if (input.matches("[1-3]")) {
                switch (input) {
                    case "1":
                        type = "Document";
                        break;
                    case "2":
                        type = "Box";
                        break;
                    case "3":
                        type = "Fragile";
                        break;
                }

                valid = true;

            } else {
                System.out.println(
                    "Invalid input! Please enter 1, 2, or 3 only."
                );
            }
        }

        return type;
    }

    public String getDeliveryService(Scanner sc) {

        boolean valid = false;
        String service = "";

        while (!valid) {
            System.out.println("\nDelivery Services");
            System.out.println("1. Standard");
            System.out.println("2. Express");
            System.out.println("3. Same Day");
            System.out.print("Enter delivery service: ");

            String input = sc.nextLine();

            if (input.matches("[1-3]")) {
                switch (input) {
                    case "1":
                        service = "Standard";
                        break;
                    case "2":
                        service = "Express";
                        break;
                    case "3":
                        service = "Same Day";
                        break;
                }

                valid = true;

            } else {
                System.out.println(
                    "Invalid input! Please enter 1, 2, or 3 only."
                );
            }
        }

        return service;
    }
}
