package services;

import exceptions.InvalidPatient;
import exceptions.InvalidRoomNumber;
import exceptions.NoRoomsAvailable;
import hospital.Hospital;
import people.Patient;

public class HospitalSerivces {
    public static void addPatient(Patient patient, Hospital hospital) throws NoRoomsAvailable {
        try {
            hospital.addPatient(patient);
        } catch (NoRoomsAvailable ex) {
            throw ex;
        }
    }

    public static void removePatient(int patientNo, Hospital hospital) throws InvalidPatient {
        try {
            hospital.removePatient(patientNo);
        } catch (InvalidPatient ex) {
            throw ex;
        }
    }

    public static void checkIfPatientInHospitalByID(int patientID, Hospital hospital) throws InvalidPatient {
        try {
            hospital.checkIfPatientInHospitalByID(patientID);
        } catch (InvalidPatient ex) {
            throw ex;
        }
    }

    public static void amountToPayForPatientByID(int patientID,  Hospital hospital) throws InvalidPatient {
        try {
            hospital.amountToPayForPatientByID(patientID);
        } catch (InvalidPatient ex) {
            throw ex;
        }
    }

    public static void numberOfAvailableRooms(Hospital hospital) {
        System.out.println("Number of available rooms: " + hospital.numberOfAvailableRooms());
    }

    public static void getFloorNumberForRoom(int roomNo, Hospital hospital) throws InvalidRoomNumber {
        try {
            System.out.println("Room number: " + roomNo + " is at floor: " + hospital.getFloorNumberForRoom(roomNo));
        } catch (InvalidRoomNumber ex) {
            throw ex;
        }
    }

    public static void printBillsAscending(Hospital hospital) {
        hospital.printBillsAscending();
    }

    public static void printBillsDescending(Hospital hospital) {
        hospital.printBillsDescending();
    }

    public static void printPatients(Hospital hospital) {
        hospital.printPatients();
    }
}
