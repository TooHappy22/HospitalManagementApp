package bills;

import utilities.Utilities;

import java.util.Date;

public class Bill implements Comparable<Bill> {
    // region Properties
    // -----------------------------------------------------------------------------------------------------------------

    private int billNo;
    private String patientName;
    private int patientID;
    private double amount;
    private Date billDate;

    // -----------------------------------------------------------------------------------------------------------------
    // endregion

    // region Constructors
    // -----------------------------------------------------------------------------------------------------------------

    public Bill(int billNo, String patientName, int patientID, double amount, Date billDate) {
        this.billNo = billNo;
        this.patientName = patientName;
        this.patientID = patientID;
        this.amount = amount;
        this.billDate = billDate;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // endregion

    // Accessors
    // -----------------------------------------------------------------------------------------------------------------

    public double getAmount() {
        return amount;
    }

    public Date getBillDate() {
        return billDate;
    }

    public int getBillNo() {
        return billNo;
    }

    public String getPatientName() {
        return patientName;
    }

    public int getPatientID() {
        return patientID;
    }
// -----------------------------------------------------------------------------------------------------------------
    // endregion

    // region Utility Methods
    // -----------------------------------------------------------------------------------------------------------------

    @Override
    public int compareTo(Bill obj) {
        return Long.compare(billDate.getTime(), obj.getBillDate().getTime());
    }

    public void printBill() {
        System.out.println("Bill number: " +
                        billNo + " -> " +
                        patientID + " " +
                        patientName + ". Amount to pay: " +
                        amount + ". Billed at: " +
                        Utilities.formatDateAndTime(billDate));
    }

    // -----------------------------------------------------------------------------------------------------------------
    // endregion
}
