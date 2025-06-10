import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Office {
    private static Office instance;
    private final Map<String, ConferenceRoom> rooms = new HashMap<>();
    private final BookingManager bookingManager = new BookingManager();
    
    private Office() {}
    
    public static Office getInstance() {
        if (instance == null) {
            instance = new Office();
        }
        return instance;
    }
    
    public void configureRooms(int numberOfRooms) {
        for (int i = 1; i <= numberOfRooms; i++) {
            String roomName = "Room" + i;
            rooms.put(roomName, new ConferenceRoom(roomName));
        }
        System.out.println("Configured " + numberOfRooms + " rooms.");
    }
    
    public ConferenceRoom getRoom(String roomName) {
        return rooms.get(roomName);
    }
    
    public BookingManager getBookingManager() {
        return bookingManager;
    }
    
    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Configure Rooms");
            System.out.println("2. Book Room");
            System.out.println("3. Cancel Booking");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    System.out.print("Enter number of rooms: ");
                    int numRooms = scanner.nextInt();
                    configureRooms(numRooms);
                    break;
                case 2:
                    System.out.print("Enter room name to book: ");
                    String bookRoom = scanner.nextLine();
                    bookingManager.bookRoom(bookRoom);
                    break;
                case 3:
                    System.out.print("Enter room name to cancel booking: ");
                    String cancelRoom = scanner.nextLine();
                    bookingManager.cancelBooking(cancelRoom);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    
    public static void main(String[] args) {
        Office office = Office.getInstance();
        office.start();
    }
}
