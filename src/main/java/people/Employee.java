package people;

import RoomsAndDepartments.Departments;

public interface Employee {
    double getSalary();
    void printInformation();
    int getID();
    Departments getDepartment();
}
