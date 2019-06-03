package services;

import exceptions.InvalidPatient;
import exceptions.InvalidRoomNumber;
import exceptions.NoRoomsAvailable;
import hospital.Hospital;
import people.Patient;
import utilities.Database;

import javax.swing.*;

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
        Database.readObjects("rooms");
//        System.out.println("Number of available rooms: " + hospital.numberOfAvailableRooms());
    }

    public static JLabel JLabelNumberOfAvailableRooms(Hospital hospital) {
        return Database.JLabelReadObject("rooms");
//        System.out.println("Number of available rooms: " + hospital.numberOfAvailableRooms());
    }

    public static void getFloorNumberForRoom(int roomNo, Hospital hospital) throws InvalidRoomNumber {
        try {
            System.out.println("Room number: " + roomNo + " is at floor: " + hospital.getFloorNumberForRoom(roomNo));
        } catch (InvalidRoomNumber ex) {
            throw ex;
        }
    }

    public static void printBillsAscending(Hospital hospital) {
        Database.readObjects("bills");
//        hospital.printBillsAscending();
    }

    public static void printBillsDescending(Hospital hospital) {
        Database.readObjects("bills");
//        hospital.printBillsDescending();
    }

    public static void printPatients(Hospital hospital) {
        Database.readObjects("patients");
//        hospital.printPatients();
    }

    public static String getPatients(Hospital hospital) {
        return Database.readObjectsToString("patients");
//        hospital.printPatients();
    }

    public static String getDoctors(Hospital hospital) {
        return Database.readObjectsToString("doctors");
//        hospital.printPatients();
    }

    public static String getBills(Hospital hospital) {
        return Database.readObjectsToString("bills");
//        hospital.printPatients();
    }
}
