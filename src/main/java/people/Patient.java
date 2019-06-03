package people;

import bills.Bill;

public class Patient extends Person {
    // region Properties
    // -----------------------------------------------------------------------------------------------------------------

    private int patientID;
    private int roomNo;
    private String disease;
    private Bill billToPay;

    // -----------------------------------------------------------------------------------------------------------------
    // endregion

    // region Constructors
    // -----------------------------------------------------------------------------------------------------------------

    public Patient(String name, String phoneNumber, int age, String disease) {
        super(name, phoneNumber, age);
        this.disease = disease;
    }

    public Patient(String name, String phoneNumber, int age, int patientID, String disease) {
        super(name, phoneNumber, age);
        this.patientID = patientID;
        this.disease = disease;
    }

// -----------------------------------------------------------------------------------------------------------------
    // endregion

    // region Accessors
    // -----------------------------------------------------------------------------------------------------------------

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }

    public String getDisease() {
        return disease;
    }

    public Bill getBillToPay() {
        return billToPay;
    }

    public void setBillToPay(Bill billToPay) {
        this.billToPay = billToPay;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // endregion

    // region Utility Methods
    // -----------------------------------------------------------------------------------------------------------------

    public void printInformation() {
//        System.out.println(patientID + " " + name + " " + super.getPhoneNumber() + " " + super.getAge() + " " + disease + " " + roomNo + " " + billToPay.getAmount());
        System.out.println(patientID + " " + name + " " + super.getPhoneNumber() + " " + super.getAge() + " " + disease + " " + roomNo);
    }

    // -----------------------------------------------------------------------------------------------------------------
    // endregion
}
