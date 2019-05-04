package utilities;

import RoomsAndDepartments.Departments;
import RoomsAndDepartments.Room;
import RoomsAndDepartments.Rooms;
import RoomsAndDepartments.Specializations;
import people.Doctor;
import people.Patient;
import people.Receptionist;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utilities {
    // region Properties
    // -----------------------------------------------------------------------------------------------------------------

    private static final Rooms[] roomsType = Rooms.values();
    private static final Departments[] departmentsName = Departments.values();
    private static final Specializations[] specializations = Specializations.values();

    private static final String[] names = { "Andreea", "Bianca", "Iulia", "Florica", "Miruna", "Ioana" };
    private static final String[] doctorNames = { "Alex", "Ionut", "Daniela", "Florica", "Costin", "Andrei" };
    private static final String[] patientNames = { "Costi", "Andreea", "Viorel", "Tudor", "Gheorghita", "Vio" };
    private static final String[] phoneNumbers = { "0745463890", "0745623811", "0745623220", "0745463890", "0725623890", "0734523890" };
    private static final String[] diseases = { "kidneys", "heart", "surgery", "brain" };

    // -----------------------------------------------------------------------------------------------------------------
    // endregion

    // region Utility Methods
    // -----------------------------------------------------------------------------------------------------------------

    public static String formatDateAndTime(Date date) {
        DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return sdf.format(date); // 03/04/2019 02:13:11
    }

    public static int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }

    public static Room generateRoomForHospital(int i, int j) {
        Rooms roomType = roomsType[(int)(Math.random() * 4)];

        Room room;
        if (j < 10) {
            room = new Room(roomType, i * 10 + j, i, true);
        } else {
            room = new Room(roomType, i * 100 + j, i, true);
        }

        return room;
    }

    public static Receptionist generateReceptionist() {
        String name = names[(int)(Math.random() * 6)];
        String phoneNumber = phoneNumbers[(int)(Math.random() * 6)];
        int age = (int)(Math.random() * 50 + 18);

        return new Receptionist(name, phoneNumber, age, 1);
    }

    public static Doctor generateDoctor(int id) {
        String name = doctorNames[(int)(Math.random() * 6)];
        String phoneNumber = phoneNumbers[(int)(Math.random() * 6)];
        int age = (int)(Math.random() * 27 + 28);
        Departments department = departmentsName[(int)(Math.random() * 4)];
        Specializations specialization = specializations[department.ordinal()];

        return new Doctor(name, phoneNumber, age, id, department, specialization);
    }

    public static Patient generatePatient() {
        String name = patientNames[(int)(Math.random() * 6)];
        String phoneNumber = phoneNumbers[(int)(Math.random() * 6)];
        int age = (int)(Math.random() * 50 + 18);
        String disease = diseases[(int)(Math.random() * 4)];

        return new Patient(name, phoneNumber,age, disease);
    }

    // -----------------------------------------------------------------------------------------------------------------
    // endregion
}
