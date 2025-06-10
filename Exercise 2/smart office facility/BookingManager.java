public class BookingManager {
    
    public void bookRoom(String roomName) {
        ConferenceRoom room = Office.getInstance().getRoom(roomName);
        if (room != null) {
            if (!room.isBooked()) {
                room.book();
                System.out.println(roomName + " booked successfully.");
            } else {
                System.out.println(roomName + " is already booked.");
            }
        } else {
            System.out.println("Room " + roomName + " does not exist.");
        }
    }
    
    public void cancelBooking(String roomName) {
        ConferenceRoom room = Office.getInstance().getRoom(roomName);
        if (room != null) {
            if (room.isBooked()) {
                room.cancelBooking();
                System.out.println(roomName + " booking cancelled.");
            } else {
                System.out.println(roomName + " is not booked.");
            }
        } else {
            System.out.println("Room " + roomName + " does not exist.");
        }
    }
}
