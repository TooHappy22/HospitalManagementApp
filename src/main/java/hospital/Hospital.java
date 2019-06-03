package hospital;

import RoomsAndDepartments.*;
import bills.Bill;
import exceptions.InvalidPatient;
import exceptions.InvalidRoomNumber;
import exceptions.NoRoomsAvailable;
import people.*;
import services.CSVReaderWriter;
import utilities.Database;
import utilities.Utilities;

import javax.xml.crypto.Data;
import java.io.File;
import java.util.*;

public class Hospital {
    // region Properties
    // -----------------------------------------------------------------------------------------------------------------

    private static Hospital ourInstance = new Hospital();

    private int numberOfPatients = 0;

    private static int numberOfBills = 0;

    private final static int numberOfFloors = 9;
    private final static int numberOfRoomsPerFloor = 15;
    private final static int numberOfEmployees = 35;

    private List<Room> rooms;
    private List<Patient> patients;
    private List<Employee> employees;
    private PriorityQueue<Bill> bills;
    private Map<Departments, Set<Integer>> doctorsForEachDepartment;
    private Map<Patient, Bill> billForPatient;

    // -----------------------------------------------------------------------------------------------------------------
    // endregion

    // region Constructors
    // -----------------------------------------------------------------------------------------------------------------

    private Hospital() {
        rooms = new ArrayList<Room>();
        patients = new ArrayList<Patient>();
        employees = new ArrayList<Employee>();
        bills = new PriorityQueue<Bill>();
        doctorsForEachDepartment = new HashMap<>();
        billForPatient = new HashMap<>();

        initializeHospital();
    }

    // -----------------------------------------------------------------------------------------------------------------
    // endregion

    // region Accessors
    // -----------------------------------------------------------------------------------------------------------------

    public static Hospital getOurInstance() {
        return ourInstance;
    }

    public static void setOurInstance(Hospital ourInstance) {
        Hospital.ourInstance = ourInstance;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // endregion

    // region Utility Methods
    // -----------------------------------------------------------------------------------------------------------------

    private void initializeHospital() {
        File file = new File("./src/main/resources/patients.csv");
        file.delete();

        file = new File("./src/main/resources/bills.csv");
        file.delete();

        file = new File("./src/main/resources/logging.txt");
        file.delete();

        Database.cleareDatabases();

        Departments[] departmentsName = Departments.values();

        rooms = CSVReaderWriter.csvReader("room");

//        Generate rooms in hospital
//        for (int i = 0; i <= numberOfFloors; ++i) {
//            for (int j = 1; j <= numberOfRoomsPerFloor; ++j) {
//                Room newRoom = Utilities.generateRoomForHospital(i, j);
//                rooms.add(newRoom);
//            }
//        }
//        CSVReaderWriter.csvWriter(rooms);

        // Set departments as keys in the HashSet.
        for (Departments dep: departmentsName) {
            doctorsForEachDepartment.put(dep, new HashSet<Integer>());
        }


        employees = CSVReaderWriter.csvReader("doctor");
        for (int i = 1; i <= numberOfEmployees; ++i) {
            doctorsForEachDepartment.get(employees.get(i - 1).getDepartment()).add(i);
        }

//        Generate doctors in hospital.
//        for (int i = 1; i <= numberOfEmployees; ++i) {
//            Doctor newDoctor = Utilities.generateDoctor(i);
//            employees.add(newDoctor);
//
//            // Add doctor's id to the specific department.
//            doctorsForEachDepartment.get(newDoctor.getDepartment()).add(i);
//        }
//        CSVReaderWriter.csvWriter(employees);

        employees.add((Receptionist)CSVReaderWriter.csvReader("receptionist").get(0));

//        Add receptionist.
//        Receptionist receptionist = Utilities.generateReceptionist();
//        employees.add(receptionist);
//
//        CSVReaderWriter.csvWriter(receptionist);
    }

    private int getFreeRoomNumberForDisease(String disease) {
        for (Room room : rooms) {
            if (room.isAvailability()) {
                if (disease.equals("kidneys") && room.getRoom() == Rooms.INTERNAL_MEDICINE) {
                    room.setAvailability(false);
                    rooms = CSVReaderWriter.csvReader("room");
                    Database.updateObject(room);
                    return room.getRoomNo();
                }
                if (disease.equals("heart") && room.getRoom() == Rooms.CARDIOLOGY) {
                    room.setAvailability(false);
                    rooms = CSVReaderWriter.csvReader("room");
                    Database.updateObject(room);
                    return room.getRoomNo();
                }
                if (disease.equals("surgery") && room.getRoom() == Rooms.GENERAL_SURGERY) {
                    room.setAvailability(false);
                    rooms = CSVReaderWriter.csvReader("room");
                    Database.updateObject(room);
                    return room.getRoomNo();
                }
                if (disease.equals("brain") && room.getRoom() == Rooms.NEUROLOGY) {
                    room.setAvailability(false);
                    rooms = CSVReaderWriter.csvReader("room");
                    Database.updateObject(room);
                    return room.getRoomNo();
                }
            }
        }

        return -1;
    }

    private Bill calcuateBillForDisease(String patientName, int patientID, String disease) {
        double amount;
        amount = (Math.random() * 3000) + 2400;

        switch(disease) {
            case "kidneys":
                break;
            case "heart":
                amount = amount + 499.99;
                break;
            case "surgery":
                amount = amount + 299.99;
                break;
            case "brain":
                amount = amount + 1099.99;
                break;
        }

        return new Bill(numberOfBills, patientName, patientID, amount, new Date());
    }

    // -----------------------------------------------------------------------------------------------------------------
    // endregion

    // region Services Implementation
    // -----------------------------------------------------------------------------------------------------------------

    public void addPatient(Patient patient) throws NoRoomsAvailable { // Add new patient in the system.
        numberOfPatients += 11; // increment number of patients.
        numberOfBills += 11; // increment number of bills;

        int roomNo = getFreeRoomNumberForDisease(patient.getDisease()); // get a free room for the patient.

        if (roomNo == -1) {
            throw new NoRoomsAvailable();
        }

        patient.setPatientID(numberOfPatients);
        patient.setRoomNo(roomNo);

        Bill bill = calcuateBillForDisease(patient.getName(), patient.getPatientID(), patient.getDisease()); // calculate the bill to pay.
        patient.setBillToPay(bill);

        patients.add(patient);
        bills.add(bill);
        billForPatient.put(patient, bill);

        CSVReaderWriter.csvWriter(patient);
        CSVReaderWriter.csvWriter(bill);
    }

    public void removePatient(int patientNo) throws InvalidPatient { // Remove patient from the system.
//        numberOfPatients--; // decrement the number of patients.

        System.out.println("Stergere" + patientNo);

        for (Patient patient : patients) {
            if (patient.getPatientID() == patientNo) {
                int roomNo = patient.getRoomNo(); // get the number of the room of the patient.

                for (Room room : rooms) {
                    if (room.getRoomNo() == roomNo) {
                        room.setAvailability(true); // set the room to be available
                        break;
                    }
                }

                billForPatient.remove(patient, patient.getBillToPay()); // remove the pair (patient, bill) from the HashMap.
                patients.remove(patient); // remove patient from the list of patients.

                CSVReaderWriter.csvWriter(patients);

                System.out.println("PATIENT FOUND FOR DELETION");

                Database.removeObject(patient);

                return;
            }
        }

        throw  new InvalidPatient(patientNo);
    }

    public int numberOfAvailableRooms() {
        int cnt = 0;

        for (Room room : rooms) {
            cnt = cnt + (room.isAvailability() ? 1 : 0);
        }

        return cnt;
    }

    public int getFloorNumberForRoom(int roomNo) throws InvalidRoomNumber {
        for (Room room : rooms) {
            if (room.getRoomNo() == roomNo) {
                return room.getFloor();
            }
        }

        throw new InvalidRoomNumber(roomNo);
    }

    public void listOfDepartments() {
        Departments[] departmentsName = Departments.values();

        for (Departments dep: departmentsName) {
            System.out.println(dep.name());
        }
    }

    public void listOfDoctors() {
        // employees[0] is the receptionist.
        Database.readObjects("doctors");

//        for (int i = 1; i < numberOfEmployees; ++i) {
//            employees.get(i).printInformation();
//        }
    }

    public void printDoctorsForEachDepartment() {
        for (Map.Entry<Departments, Set<Integer>> it : doctorsForEachDepartment.entrySet()) {
            System.out.println(it.getKey() + ": ");

            for (Integer idx : it.getValue()) {
                for (Employee empl : employees) {
                    if (empl.getID() == idx) {
                        empl.printInformation();
                    }
                }
            }

            System.out.println();
        }
    }

    public void checkIfPatientInHospitalByID(int patientID) throws InvalidPatient {
        for (Patient patient : patients) {
            if (patient.getPatientID() == patientID) {
                System.out.println("Patient: " + patient.getName() + " with id: " + patientID + " found!");
                return;
            }
        }

        throw new InvalidPatient(patientID);
    }

    public void checkIfPatientInHospitalByName(String patientName) throws InvalidPatient {
        for (Patient patient : patients) {
            if (patient.getName().equals(patientName)) {
                System.out.println("Patient: " + patientName+ " found!");
                return;
            }
        }

        throw new InvalidPatient(patientName);
    }

    public void amountToPayForPatientByID(int patientID) throws InvalidPatient {
        for (Patient patient : patients) {
            if (patient.getPatientID() == patientID) {
                System.out.println("Patient: " + patient.getName() + " with id: " + patientID + " has to pay: " + billForPatient.get(patient).getAmount());
                return;
            }
        }

        throw new InvalidPatient(patientID);
    }

    public void amountToPayForPatientByName(String patientName) throws InvalidPatient{
        for (Patient patient : patients) {
            if (patient.getName().equals(patientName)) {
                System.out.println("Patient: " + patientName+ " has to pay: " + billForPatient.get(patient).getAmount());
                return;
            }
        }

        throw new InvalidPatient(patientName);
    }

    public void printBillsAscending() {
        System.out.println("Bills (ascending): ");

        for (Bill bill : bills) {
            bill.printBill();
        }
    }

    public void printBillsDescending() {
        System.out.println("Bills (descending): ");

        List<Bill> billsTemp = new ArrayList<>(bills);
        for (int i = billsTemp.size() - 1; i >= 0; --i) {
            billsTemp.get(i).printBill();
        }
    }

    public void printPatients() {
        System.out.println("List of patients: ");
        for (Patient patient: patients) {
            patient.printInformation();
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    // endregion
}
