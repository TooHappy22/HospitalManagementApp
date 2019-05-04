package exceptions;

public class InvalidRoomNumber extends Exception {
    private int roomNo;

    public InvalidRoomNumber(int roomNo) {
        this.roomNo = roomNo;
    }

    @Override
    public String toString() {
        return "!!! Exception: Room number: " + roomNo + " is not valid!!!";
    }
}
