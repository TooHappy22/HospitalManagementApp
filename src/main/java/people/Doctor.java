package people;

import RoomsAndDepartments.Departments;
import RoomsAndDepartments.Specializations;

public class Doctor extends Person implements Employee {
    // region Properties
    // -----------------------------------------------------------------------------------------------------------------

    private int doctorID;
    private Departments department;
    private Specializations specialization;
    private final int salary = 6600;

    // -----------------------------------------------------------------------------------------------------------------
    // endregion

    // region Constructors
    // -----------------------------------------------------------------------------------------------------------------

    public Doctor(String name, String phoneNumber, int age, int doctorID, Departments department, Specializations specialization) {
        super(name, phoneNumber, age);
        this.doctorID = doctorID;
        this.department = department;
        this.specialization = specialization;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // endregion


    public int getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(int doctorID) {
        this.doctorID = doctorID;
    }

    public void setDepartment(Departments department) {
        this.department = department;
    }

    public Specializations getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specializations specialization) {
        this.specialization = specialization;
    }

    @Override
    public Departments getDepartment() {
        return department;
    }

    @Override
    public double getSalary() {
        if (department == Departments.CARDIOLOGY) {
            return salary + 800;
        }

        if (department == Departments.NEUROLOGY) {
            return salary + 1100;
        }

        if (department == Departments.GENERAL_SURGERY) {
            return salary + 1800;
        }

        return salary;
    }

    @Override
    public int getID() {
        return doctorID;
    }

    @Override
    public void printInformation() {
        System.out.println(doctorID + " " + name + " " + super.getPhoneNumber() + " " + super.getAge() + " " + department + " " + specialization + " " + getSalary());
    }
}
