import exceptions.InvalidPatient;
import exceptions.InvalidRoomNumber;
import exceptions.NoRoomsAvailable;
import hospital.Hospital;
import people.Patient;
import services.ClientServices;
import services.HospitalSerivces;
import services.MyLogger;
import services.Services;
import utilities.Utilities;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Logger;

public class Main {
    public static Logger logger = null;
    public static void main(String[] args) {
        Hospital hospital = Hospital.getOurInstance();
        try {
            logger = MyLogger.getInstance();
        } catch (IOException ex) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            String sStackTrace = sw.toString();

            logger.severe("Program termination with stack trace:\n" + sStackTrace);
            ex.printStackTrace();
        }

        logger.info("PROGRAM STARTED");

        boolean foundException = false;

        int numberOfPatients = Utilities.randBetween(10, 20);
        for (int i = 1; i <= numberOfPatients; ++i) {
            Patient patient = Utilities.generatePatient();

            try {
                logger.info("Add patient method called");
                HospitalSerivces.addPatient(patient, hospital);
            } catch (NoRoomsAvailable ex) {
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                ex.printStackTrace(pw);
                String sStackTrace = sw.toString();

                logger.severe("Program termination with stack trace:\n" + sStackTrace);
                ex.printStackTrace();

                foundException = true;
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                e.printStackTrace(pw);
                String sStackTrace = sw.toString();

                logger.severe("Program termination with stack trace:\n" + sStackTrace);
                e.printStackTrace();

                foundException = true;
            }
        }

        logger.info("Print patients method called");
        HospitalSerivces.printPatients(hospital);
//        System.out.println();

        try {
            logger.info("Remove patient method called");
            HospitalSerivces.removePatient(3, hospital);
        } catch (InvalidPatient ex) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            String sStackTrace = sw.toString();

            logger.severe("Program termination with stack trace:\n" + sStackTrace);
            ex.printStackTrace();

            foundException = true;
        }

        try {
            logger.info("Remove patient method called");
            HospitalSerivces.removePatient(5, hospital);
        } catch (InvalidPatient ex) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            String sStackTrace = sw.toString();

            logger.severe("Program termination with stack trace:\n" + sStackTrace);
            ex.printStackTrace();

            foundException = true;
        }

        try {
            logger.info("Check if patient in hospital by ID method called");
            HospitalSerivces.checkIfPatientInHospitalByID(11, hospital);
            System.out.println();
        } catch (InvalidPatient ex) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            String sStackTrace = sw.toString();

            logger.severe("Program termination with stack trace:\n" + sStackTrace);
            ex.printStackTrace();

            foundException = true;
        }

        try {
            logger.info("Check if patient in hospital by name method called");
            ClientServices.checkIfPatientInHospitalByName("XXX", hospital);
            System.out.println();
        } catch (InvalidPatient ex) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            String sStackTrace = sw.toString();

            logger.severe("Program termination with stack trace:\n" + sStackTrace);
            ex.printStackTrace();

            foundException = true;
        }

        try {
            logger.info("Get amount to pay for patient by ID method called");
            HospitalSerivces.amountToPayForPatientByID(4, hospital);
            System.out.println();
        } catch (InvalidPatient ex) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            String sStackTrace = sw.toString();

            logger.severe("Program termination with stack trace:\n" + sStackTrace);
            ex.printStackTrace();

            foundException = true;
        }

        try {
            logger.info("Get amount to pay for patient by name method called");
            ClientServices.amountToPayForPatientByName("Andrei", hospital);
            System.out.println();
        } catch (InvalidPatient ex) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            String sStackTrace = sw.toString();

            logger.severe("Program termination with stack trace:\n" + sStackTrace);
            ex.printStackTrace();

            foundException = true;
        }

        try {
            logger.info("Get floor number for specific room method called");
            HospitalSerivces.getFloorNumberForRoom(312, hospital);
        } catch (InvalidRoomNumber ex) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            String sStackTrace = sw.toString();

            logger.severe("Program termination with stack trace:\n" + sStackTrace);
            ex.printStackTrace();

            foundException = true;
        }

        logger.info("Print patients method called");
        HospitalSerivces.printPatients(hospital);
        System.out.println();

        logger.info("Print number of available method called");
        HospitalSerivces.numberOfAvailableRooms(hospital);
        System.out.println();

        logger.info("Print list of departments method called");
        ClientServices.listOfDepartments(hospital);
        System.out.println();

        logger.info("Print list of doctors method called");
        Services.listOfDoctors(hospital);
        System.out.println();

        logger.info("Print doctors for each department method called");
        Services.printDoctorsForEachDepartment(hospital);
        System.out.println();

        logger.info("Print bills ascending method called");
        HospitalSerivces.printBillsAscending(hospital);
        System.out.println();

        logger.info("Print bills descending method called");
        HospitalSerivces.printBillsDescending(hospital);
        System.out.println();

        if (!foundException) {
            logger.info("PROGRAM ENDED WITH NO EXCEPTIONS");
        }
    }
}
