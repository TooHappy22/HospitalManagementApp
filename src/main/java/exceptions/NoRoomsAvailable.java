package exceptions;

public class NoRoomsAvailable extends Exception {
    public String toString() {
        return "!!! Exception: No Rooms available in the hospital !!!";
    }
}
