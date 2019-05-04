package RoomsAndDepartments;

public final class Room {
    private Rooms room;
    private int roomNo;
    private int floor;
    private boolean availability;

    public Room(Rooms room, int roomNo, int floor, boolean availability) {
        this.room = room;
        this.roomNo = roomNo;
        this.floor = floor;
        this.availability = availability;
    }

    public Rooms getRoom() {
        return room;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public int getFloor() {
        return floor;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
}
