package exceptions;

public class InvalidPatient extends Exception {
    private String name;
    private int ID;

    public InvalidPatient(String name) {
        this.name = name;
        this.ID = -1;
    }

    public InvalidPatient(int ID) {
        this.name = "";
        this.ID = ID;
    }

    @Override
    public String toString() {
        if (name.equals("")) {
            return "!!! Exception: Patient with ID: " + ID + " not found!!!";
        }

        return "!!! Exception: Patient: " + name + " not found!!!";
    }
}
