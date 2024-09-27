import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Class for Room
class Room {
    int roomNumber;
    String roomType;
    double pricePerNight;
    boolean isAvailable;

    public Room(int roomNumber, String roomType, double pricePerNight, boolean isAvailable) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.pricePerNight = pricePerNight;
        this.isAvailable = isAvailable;
    }

    @Override
    public String toString() {
        return "Room " + roomNumber + " [" + roomType + "] - $" + pricePerNight + " per night | Available: "
                + isAvailable;
    }
}

// Class for Reservation
class Reservation {
    Room room;
    String guestName;
    String checkInDate;
    String checkOutDate;
    double totalCost;

    public Reservation(Room room, String guestName, String checkInDate, String checkOutDate, double totalCost) {
        this.room = room;
        this.guestName = guestName;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.totalCost = totalCost;
    }

    @Override
    public String toString() {
        return "Reservation for " + guestName + " in Room " + room.roomNumber +
                " [" + room.roomType + "] from " + checkInDate + " to " + checkOutDate +
                ". Total Cost: $" + totalCost;
    }
}

// Main class for Hotel Reservation System
public class HotelReservationSystem {
    // List to store room and reservation data
    static List<Room> rooms = new ArrayList<>();
    static List<Reservation> reservations = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Initialize some dummy rooms
        rooms.add(new Room(101, "Single", 100, true));
        rooms.add(new Room(102, "Double", 150, true));
        rooms.add(new Room(103, "Suite", 250, true));
        rooms.add(new Room(104, "Single", 100, false)); // Booked room
        rooms.add(new Room(105, "Suite", 250, true));

        while (true) {
            System.out.println("\n----- Hotel Reservation System -----");
            System.out.println("1. Search for Available Rooms");
            System.out.println("2. Make a Reservation");
            System.out.println("3. View All Reservations");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    searchAvailableRooms(scanner);
                    break;
                case 2:
                    makeReservation(scanner);
                    break;
                case 3:
                    viewReservations();
                    break;
                case 4:
                    System.out.println("Exiting... Thank you!");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // Search for available rooms by room type
    public static void searchAvailableRooms(Scanner scanner) {
        System.out.print("Enter room type to search (Single, Double, Suite): ");
        String roomType = scanner.nextLine();

        System.out.println("\nAvailable " + roomType + " Rooms:");
        boolean found = false;
        for (Room room : rooms) {
            if (room.roomType.equalsIgnoreCase(roomType) && room.isAvailable) {
                System.out.println(room);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No available rooms of type " + roomType);
        }
    }

    // Make a reservation
    public static void makeReservation(Scanner scanner) {
        System.out.print("Enter room number to book: ");
        int roomNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Room selectedRoom = null;
        for (Room room : rooms) {
            if (room.roomNumber == roomNumber && room.isAvailable) {
                selectedRoom = room;
                break;
            }
        }

        if (selectedRoom == null) {
            System.out.println("Room not available or does not exist.");
            return;
        }

        System.out.print("Enter guest name: ");
        String guestName = scanner.nextLine();

        System.out.print("Enter check-in date (YYYY-MM-DD): ");
        String checkInDate = scanner.nextLine();

        System.out.print("Enter check-out date (YYYY-MM-DD): ");
        String checkOutDate = scanner.nextLine();

        // Calculate total cost (assuming 1 night for simplicity)
        double totalCost = selectedRoom.pricePerNight;

        // Create reservation and mark room as unavailable
        Reservation reservation = new Reservation(selectedRoom, guestName, checkInDate, checkOutDate, totalCost);
        reservations.add(reservation);
        selectedRoom.isAvailable = false;

        System.out.println("Reservation confirmed!");
        System.out.println(reservation);
    }

    // View all reservations
    public static void viewReservations() {
        if (reservations.isEmpty()) {
            System.out.println("No reservations found.");
        } else {
            System.out.println("\nAll Reservations:");
            for (Reservation reservation : reservations) {
                System.out.println(reservation);
            }
        }
    }
}