package utilities;

import RoomsAndDepartments.Room;
import bills.Bill;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import people.Doctor;
import people.Patient;
import people.Receptionist;

import javax.swing.*;
import java.sql.*;

public class Database {
    static private String URL = "jdbc:mysql://127.0.0.1:3306/hospital_database?serverTimezone=UTC";
    static private String user = "root";
    static private String pass = "q1234567";
    private static Connection connection = null;

    public static < T > void addObject(T object) {
        switch (object.getClass().getName()) {
            case "people.Doctor":
                try {
                    // Add doctor to the database
                    Doctor doctor = Doctor.class.cast(object);

                    System.out.println("Incercam sa adaugam in baza de date: " + doctor.toString());

                    connection = DriverManager.getConnection(URL, user, pass);

                    String querySQL = "INSERT INTO doctors VALUES(?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement statement = connection.prepareStatement(querySQL);
                    statement.setString(1, doctor.getName());
                    statement.setString(2, doctor.getPhoneNumber());
                    statement.setInt(3, doctor.getAge());
                    statement.setInt(4, doctor.getDoctorID());
                    statement.setString(5, doctor.getDepartment().name());
                    statement.setString(6, doctor.getSpecialization().name());
                    statement.setDouble(7, doctor.getSalary());

                    int n = statement.executeUpdate();
                    System.out.println("Modified " + n + " records");
                } catch (SQLException ex) {
                    ex.getErrorCode();
                } finally {
                    try {
                        if (connection != null) {
                            connection.close();
                        }
                    } catch (SQLException ex) {
                        ex.getErrorCode();
                    }
                }
                break;
            case "RoomsAndDepartments.Room":
                try {
                    // Add rooms to database
                    Room room = Room.class.cast(object);

                    System.out.println("Incercam sa adaugam in baza de date: " + room.toString());

                    connection = DriverManager.getConnection(URL, user, pass);

                    String querySQL = "INSERT INTO rooms VALUES(?, ?, ?, ?)";
                    PreparedStatement statement = connection.prepareStatement(querySQL);
                    statement.setString(1, room.getRoom().name());
                    statement.setInt(2, room.getRoomNo());
                    statement.setInt(3, room.getFloor());
                    statement.setBoolean(4, room.isAvailability());

                    int n = statement.executeUpdate();
                    System.out.println("Modified " + n + " records");

                } catch (SQLException ex) {
                    ex.getErrorCode();
                }
                try {
                    if (connection != null) {
                        connection.close();
                    }
                } catch (SQLException ex) {
                    ex.getErrorCode();
                }
                break;
            case "people.Receptionist":
                try {
                    // Add receptionist to database
                    Receptionist receptionist = Receptionist.class.cast(object);

                    System.out.println("Incercam sa adaugam in baza de date: " + receptionist.toString());

                    connection = DriverManager.getConnection(URL, user, pass);

                    String querySQL = "INSERT INTO receptionists VALUES(?, ?, ?, ?, ?)";
                    PreparedStatement statement = connection.prepareStatement(querySQL);
                    statement.setString(1, receptionist.getName());
                    statement.setString(2, receptionist.getPhoneNumber());
                    statement.setInt(3, receptionist.getAge());
                    statement.setInt(4, receptionist.getID());
                    statement.setDouble(5, receptionist.getSalary());

                    int n = statement.executeUpdate();
                    System.out.println("Modified " + n + " records");

                } catch (SQLException ex) {
                    ex.getErrorCode();
                }
                try {
                    if (connection != null) {
                        connection.close();
                    }
                } catch (SQLException ex) {
                    ex.getErrorCode();
                }
                break;
            case "people.Patient":
                try {
                    // Add receptionist to database
                    Patient patient = Patient.class.cast(object);

                    System.out.println("Incercam sa adaugam in baza de date: " + patient.toString());

                    connection = DriverManager.getConnection(URL, user, pass);

                    String querySQL = "INSERT INTO patients VALUES(?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement statement = connection.prepareStatement(querySQL);
                    statement.setString(1, patient.getName());
                    statement.setString(2, patient.getPhoneNumber());
                    statement.setInt(3, patient.getAge());
                    statement.setInt(4, patient.getPatientID());
                    statement.setInt(5, patient.getRoomNo());
                    statement.setString(6, patient.getDisease());
                    statement.setInt(7, patient.getBillToPay().getBillNo());

                    int n = statement.executeUpdate();
                    System.out.println("Modified " + n + " records");

                } catch (SQLException ex) {
                    ex.getErrorCode();
                }
                try {
                    if (connection != null) {
                        connection.close();
                    }
                } catch (SQLException ex) {
                    ex.getErrorCode();
                }
                break;
            case "bills.Bill":
                try {
                    // Add bills to database
                    Bill bill = Bill.class.cast(object);

                    System.out.println("Incercam sa adaugam in baza de date: " + bill.toString());

                    connection = DriverManager.getConnection(URL, user, pass);

                    String querySQL = "INSERT INTO bills VALUES(?, ?, ?, ?, ?)";
                    PreparedStatement statement = connection.prepareStatement(querySQL);
                    statement.setInt(1, bill.getBillNo());
                    statement.setString(2, bill.getPatientName());
                    statement.setInt(3, bill.getPatientID());
                    statement.setDouble(4, bill.getAmount());
                    statement.setString(5, bill.getBillDate().toString());

                    int n = statement.executeUpdate();
                    System.out.println("Modified " + n + " records");

                } catch (SQLException ex) {
                    ex.getErrorCode();
                }
                try {
                    if (connection != null) {
                        connection.close();
                    }
                } catch (SQLException ex) {
                    ex.getErrorCode();
                }
                break;
        }
    }

    public static < T > void removeObject(T object) {
        switch (object.getClass().getName()) {
            case "people.Patient":
                try {
                    // Remove receptionist to database
                    Patient patient = Patient.class.cast(object);

                    System.out.println("Incercam sa stergem din baza de date: " + patient.toString());

                    connection = DriverManager.getConnection(URL, user, pass);

                    String querySQL = "DELETE FROM patients WHERE id = ?";
                    PreparedStatement statement = connection.prepareStatement(querySQL);
                    statement.setInt(1, patient.getPatientID());

                    int n = statement.executeUpdate();
                    System.out.println("Deleted " + n + " patient");

                } catch (SQLException ex) {
                    ex.getErrorCode();
                }
                try {
                    if (connection != null) {
                        connection.close();
                    }
                } catch (SQLException ex) {
                    ex.getErrorCode();
                }
        }
    }

    public static < T > void updateObject(T object) {
        switch (object.getClass().getName()) {
//            case "people.Doctor":
//                try {
//                    // Add doctor to the database
//                    Doctor doctor = Doctor.class.cast(object);
//
//                    System.out.println("Incercam sa adaugam in baza de date: " + doctor.toString());
//
//                    connection = DriverManager.getConnection(URL, user, pass);
//
//                    String querySQL = "INSERT INTO doctors VALUES(?, ?, ?, ?, ?, ?, ?)";
//                    PreparedStatement statement = connection.prepareStatement(querySQL);
//                    statement.setString(1, doctor.getName());
//                    statement.setString(2, doctor.getPhoneNumber());
//                    statement.setInt(3, doctor.getAge());
//                    statement.setInt(4, doctor.getDoctorID());
//                    statement.setString(5, doctor.getDepartment().name());
//                    statement.setString(6, doctor.getSpecialization().name());
//                    statement.setDouble(7, doctor.getSalary());
//
//                    int n = statement.executeUpdate();
//                    System.out.println("Modified " + n + " records");
//                } catch (SQLException ex) {
//                    ex.getErrorCode();
//                } finally {
//                    try {
//                        if (connection != null) {
//                            connection.close();
//                        }
//                    } catch (SQLException ex) {
//                        ex.getErrorCode();
//                    }
//                }
//                break;
            case "RoomsAndDepartments.Room":
                try {
                    // Add rooms to database
                    Room room = Room.class.cast(object);

                    System.out.println("Incercam sa facem UPDATE in baza de date: " + room.toString());

                    connection = DriverManager.getConnection(URL, user, pass);

                    String querySQL = "UPDATE rooms SET availability = ? WHERE room_no = ?";
                    PreparedStatement statement = connection.prepareStatement(querySQL);
                    statement.setBoolean(1, room.isAvailability());
                    statement.setInt(2, room.getRoomNo());

                    int n = statement.executeUpdate();
                    System.out.println("Modified " + n + " records");

                } catch (SQLException ex) {
                    ex.getErrorCode();
                }
                try {
                    if (connection != null) {
                        connection.close();
                    }
                } catch (SQLException ex) {
                    ex.getErrorCode();
                }
                break;
//            case "people.Receptionist":
//                try {
//                    // Add receptionist to database
//                    Receptionist receptionist = Receptionist.class.cast(object);
//
//                    System.out.println("Incercam sa adaugam in baza de date: " + receptionist.toString());
//
//                    connection = DriverManager.getConnection(URL, user, pass);
//
//                    String querySQL = "INSERT INTO receptionists VALUES(?, ?, ?, ?, ?)";
//                    PreparedStatement statement = connection.prepareStatement(querySQL);
//                    statement.setString(1, receptionist.getName());
//                    statement.setString(2, receptionist.getPhoneNumber());
//                    statement.setInt(3, receptionist.getAge());
//                    statement.setInt(4, receptionist.getID());
//                    statement.setDouble(5, receptionist.getSalary());
//
//                    int n = statement.executeUpdate();
//                    System.out.println("Modified " + n + " records");
//
//                } catch (SQLException ex) {
//                    ex.getErrorCode();
//                }
//                try {
//                    if (connection != null) {
//                        connection.close();
//                    }
//                } catch (SQLException ex) {
//                    ex.getErrorCode();
//                }
//                break;
            case "people.Patient":
                try {
                    // Edit patient to database
                    Patient patient = Patient.class.cast(object);

                    System.out.println("Incercam sa editam in baza de date: " + patient.toString());

                    connection = DriverManager.getConnection(URL, user, pass);

                    System.out.println(patient.getDisease());

                    String querySQL = "UPDATE patients SET name = ? WHERE id = ?";
                    PreparedStatement statement = connection.prepareStatement(querySQL);
                    statement.setString(1, patient.getName());
                    statement.setInt(2, patient.getPatientID());

                    int n = statement.executeUpdate();
                    System.out.println("Modified " + n + " records");
                } catch (SQLException ex) {
                    ex.getErrorCode();
                }
                try {
                    if (connection != null) {
                        connection.close();
                    }
                } catch (SQLException ex) {
                    ex.getErrorCode();
                }
//            case "bills.Bill":
//                try {
//                    // Add bills to database
//                    Bill bill = Bill.class.cast(object);
//
//                    System.out.println("Incercam sa adaugam in baza de date: " + bill.toString());
//
//                    connection = DriverManager.getConnection(URL, user, pass);
//
//                    String querySQL = "INSERT INTO bills VALUES(?, ?, ?, ?, ?)";
//                    PreparedStatement statement = connection.prepareStatement(querySQL);
//                    statement.setInt(1, bill.getBillNo());
//                    statement.setString(2, bill.getPatientName());
//                    statement.setInt(3, bill.getPatientID());
//                    statement.setDouble(4, bill.getAmount());
//                    statement.setString(5, bill.getBillDate().toString());
//
//                    int n = statement.executeUpdate();
//                    System.out.println("Modified " + n + " records");
//
//                } catch (SQLException ex) {
//                    ex.getErrorCode();
//                }
//                try {
//                    if (connection != null) {
//                        connection.close();
//                    }
//                } catch (SQLException ex) {
//                    ex.getErrorCode();
//                }
        }
    }

    public static void readObjects(String fromTable) {
        switch (fromTable) {
            case "doctors":
                try {
                    System.out.println("AFISAREA tuturor doctorilor din baza de date:");

                    connection = DriverManager.getConnection(URL, user, pass);

                    String querySQL = "SELECT * FROM doctors";
                    Statement statement = connection.createStatement();

                    ResultSet rs = statement.executeQuery(querySQL);
                    while(rs.next()) {
                        System.out.println(rs.getInt("id") + " " +
                                rs.getString("name") + " " +
                                rs.getString("phone_number") + " " +
                                rs.getInt("age") + " " +
                                rs.getString("departament") + " " +
                                rs.getString("specialization") + " " +
                                rs.getDouble("salary"));
                    }
                } catch (SQLException ex) {
                    ex.getErrorCode();
                } finally {
                    try {
                        if (connection != null) {
                            connection.close();
                        }
                    } catch (SQLException ex) {
                        ex.getErrorCode();
                    }
                }
                break;
            case "rooms":
                try {
                    System.out.println("AFISAREA tuturor camerelor libere din baza de date:");

                    connection = DriverManager.getConnection(URL, user, pass);

                    String querySQL = "SELECT COUNT(*) AS count FROM rooms WHERE availability = 1";
                    Statement statement = connection.createStatement();

                    ResultSet rs = statement.executeQuery(querySQL);
                    while(rs.next()) {
                        System.out.println(rs.getInt("count"));
                    }
                } catch (SQLException ex) {
                    ex.getErrorCode();
                } finally {
                    try {
                        if (connection != null) {
                            connection.close();
                        }
                    } catch (SQLException ex) {
                        ex.getErrorCode();
                    }
                }
                break;
            case "patients":
                try {
                    System.out.println("AFISAREA tuturor pacientilor din baza de date:");

                    connection = DriverManager.getConnection(URL, user, pass);

                    String querySQL = "SELECT * FROM patients";
                    Statement statement = connection.createStatement();

                    ResultSet rs = statement.executeQuery(querySQL);
                    while(rs.next()) {
                        System.out.println(rs.getInt("id") + " " +
                                rs.getString("name") + " " +
                                rs.getString("phone_number") + " " +
                                rs.getInt("age") + " " +
                                rs.getInt("room") + " " +
                                rs.getString("disease") + " "  +
                                rs.getInt("bill_no"));
                    }
                } catch (SQLException ex) {
                    ex.getErrorCode();
                } finally {
                    try {
                        if (connection != null) {
                            connection.close();
                        }
                    } catch (SQLException ex) {
                        ex.getErrorCode();
                    }
                }
                break;
            case "bills":
                try {
                    System.out.println("AFISAREA tuturor chitantelor din baza de date:");

                    connection = DriverManager.getConnection(URL, user, pass);

                    String querySQL = "SELECT * FROM bills";
                    Statement statement = connection.createStatement();

                    ResultSet rs = statement.executeQuery(querySQL);
                    while(rs.next()) {
                        System.out.println(rs.getInt("bill_no") + " " +
                                rs.getString("patient_name") + " " +
                                rs.getInt("patient_id") + " " +
                                rs.getDouble("amount") + " " +
                                rs.getString("date"));
                    }
                } catch (SQLException ex) {
                    ex.getErrorCode();
                } finally {
                    try {
                        if (connection != null) {
                            connection.close();
                        }
                    } catch (SQLException ex) {
                        ex.getErrorCode();
                    }
                }
                break;
        }
    }

    public static JLabel JLabelReadObject(String fromTable) {
        JLabel result = new JLabel();
        switch (fromTable) {
            case "rooms":
                try {
                    System.out.println("AFISAREA tuturor camerelor libere din baza de date:");

                    connection = DriverManager.getConnection(URL, user, pass);

                    String querySQL = "SELECT COUNT(*) AS count FROM rooms WHERE availability = 1";
                    Statement statement = connection.createStatement();

                    ResultSet rs = statement.executeQuery(querySQL);
                    while(rs.next()) {
                        String res = Integer.toString(rs.getInt("count"));
                        result.setText(res);
                    }
                } catch (SQLException ex) {
                    ex.getErrorCode();
                } finally {
                    try {
                        if (connection != null) {
                            connection.close();
                        }
                    } catch (SQLException ex) {
                        ex.getErrorCode();
                    }
                }
                break;
        }

        return result;
    }

    public static String readObjectsToString(String fromTable) {
        switch (fromTable) {
            case "doctors":
                try {
                    System.out.println("AFISAREA tuturor doctorilor din baza de date:");

                    connection = DriverManager.getConnection(URL, user, pass);

                    String querySQL = "SELECT * FROM doctors";
                    Statement statement = connection.createStatement();

                    ResultSet rs = statement.executeQuery(querySQL);
                    String result = "";
                    while(rs.next()) {
                        result += (rs.getInt("id") + " " +
                                rs.getString("name") + " " +
                                rs.getString("phone_number") + " " +
                                rs.getInt("age") + " " +
                                rs.getString("departament") + " " +
                                rs.getString("specialization") + " " +
                                rs.getDouble("salary") + "\n");
                    }

                    return result;
                } catch (SQLException ex) {
                    ex.getErrorCode();
                } finally {
                    try {
                        if (connection != null) {
                            connection.close();
                        }
                    } catch (SQLException ex) {
                        ex.getErrorCode();
                    }
                }
                break;
            case "rooms":
                try {
                    System.out.println("AFISAREA tuturor camerelor libere din baza de date:");

                    connection = DriverManager.getConnection(URL, user, pass);

                    String querySQL = "SELECT COUNT(*) AS count FROM rooms WHERE availability = 1";
                    Statement statement = connection.createStatement();

                    ResultSet rs = statement.executeQuery(querySQL);
                    while(rs.next()) {
                        String res = Integer.toString(rs.getInt("count"));
//                        return new JLabel(res);
                    }
                } catch (SQLException ex) {
                    ex.getErrorCode();
                } finally {
                    try {
                        if (connection != null) {
                            connection.close();
                        }
                    } catch (SQLException ex) {
                        ex.getErrorCode();
                    }
                }
                break;
            case "patients":
                try {
                    System.out.println("AFISAREA tuturor pacientilor din baza de date:");

                    connection = DriverManager.getConnection(URL, user, pass);

                    String querySQL = "SELECT * FROM patients";
                    Statement statement = connection.createStatement();

                    ResultSet rs = statement.executeQuery(querySQL);
                    String result = "";
                    while(rs.next()) {
                        result += (rs.getInt("id") + " " +
                                rs.getString("name") + " " +
                                rs.getString("phone_number") + " " +
                                rs.getInt("age") + " " +
                                rs.getInt("room") + " " +
                                rs.getString("disease") + " "  +
                                rs.getInt("bill_no") + "\n");
                    }

                    return result;
                } catch (SQLException ex) {
                    ex.getErrorCode();
                } finally {
                    try {
                        if (connection != null) {
                            connection.close();
                        }
                    } catch (SQLException ex) {
                        ex.getErrorCode();
                    }
                }
                break;
            case "bills":
                try {
                    System.out.println("AFISAREA tuturor chitantelor din baza de date:");

                    connection = DriverManager.getConnection(URL, user, pass);

                    String querySQL = "SELECT * FROM bills";
                    Statement statement = connection.createStatement();

                    ResultSet rs = statement.executeQuery(querySQL);
                    String result = "";
                    while(rs.next()) {
                        result += (rs.getInt("bill_no") + " " +
                                rs.getString("patient_name") + " " +
                                rs.getInt("patient_id") + " " +
                                rs.getDouble("amount") + " " +
                                rs.getString("date") + "\n");
                    }

                    return result;
                } catch (SQLException ex) {
                    ex.getErrorCode();
                } finally {
                    try {
                        if (connection != null) {
                            connection.close();
                        }
                    } catch (SQLException ex) {
                        ex.getErrorCode();
                    }
                }
                break;
        }

        return "TEAPAAAAAA!";
    }

    public static void cleareDatabases() {
        try {
            int n = 0;
            connection = DriverManager.getConnection(URL, user, pass);

            String rmBillsQuery = "DELETE FROM bills WHERE bill_no > ?";
            String rmDoctorsQuery = "DELETE FROM doctors WHERE id > ?";
            String rmPatientsQuery = "DELETE FROM patients WHERE id > ?";
            String rmReceptionistsQuery = "DELETE FROM receptionists WHERE id > ?";
            String rmRoomsQuery = "DELETE FROM rooms WHERE room_no > ?";

            PreparedStatement statement = connection.prepareStatement(rmBillsQuery);
            statement.setInt(1, -100);

            n = statement.executeUpdate();
            System.out.println("Deleted " + n + " bills");

            statement = connection.prepareStatement(rmDoctorsQuery);
            statement.setInt(1, -100);

            n = statement.executeUpdate();
            System.out.println("Deleted " + n + " doctors");

            statement = connection.prepareStatement(rmPatientsQuery);
            statement.setInt(1, -100);

            n = statement.executeUpdate();
            System.out.println("Deleted " + n + " patients");

            statement = connection.prepareStatement(rmReceptionistsQuery);
            statement.setInt(1, -100);

            n = statement.executeUpdate();
            System.out.println("Deleted " + n + " receptionist");

            statement = connection.prepareStatement(rmRoomsQuery);
            statement.setInt(1, -100);

            n = statement.executeUpdate();
            System.out.println("Deleted " + n + " rooms");
        } catch (SQLException ex) {
            ex.getErrorCode();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.getErrorCode();
            }
        }
    }
}
