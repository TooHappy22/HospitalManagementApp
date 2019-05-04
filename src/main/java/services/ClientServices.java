package services;

import exceptions.InvalidPatient;
import hospital.Hospital;

public class ClientServices {
    public static void listOfDepartments(Hospital hospital) {
        System.out.println("List of departments:");
        hospital.listOfDepartments();
    }

    public static void checkIfPatientInHospitalByName(String patientName, Hospital hospital) throws InvalidPatient {
        try {
            hospital.checkIfPatientInHospitalByName(patientName);
        } catch (InvalidPatient ex) {
            throw ex;
        }
    }

    public static void amountToPayForPatientByName(String patientName, Hospital hospital) throws InvalidPatient {
        try {
            hospital.amountToPayForPatientByName(patientName);
        } catch (InvalidPatient ex) {
            throw ex;
        }
    }
}
