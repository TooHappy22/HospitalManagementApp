package services;

import hospital.Hospital;

public class Services {
    public static void printDoctorsForEachDepartment(Hospital hospital) {
        hospital.printDoctorsForEachDepartment();
    }

    public static void listOfDoctors(Hospital hospital) {
        System.out.println("List of doctors:");
        hospital.listOfDoctors();
    }
}
