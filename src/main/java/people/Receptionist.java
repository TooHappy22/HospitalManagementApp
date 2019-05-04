package people;

import RoomsAndDepartments.Departments;

public class Receptionist extends Person implements Employee {
    private int ID;
    private final int salary = 1800;

    public Receptionist(String name, String phoneNumber, int age, int ID) {
        super(name, phoneNumber, age);
        this.ID = ID;
    }


    @Override
    public Departments getDepartment() {
        return null;
    }

    @Override
    public double getSalary() {
        return salary;
    }

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void printInformation() {
        System.out.println(ID + " " + name + " " + super.getPhoneNumber() + " " + super.getAge() + " " + salary);
    }
}
