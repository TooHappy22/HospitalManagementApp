package services;

import RoomsAndDepartments.Departments;
import RoomsAndDepartments.Room;
import RoomsAndDepartments.Rooms;
import RoomsAndDepartments.Specializations;
import bills.Bill;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import people.Doctor;
import people.Patient;
import people.Receptionist;
import utilities.Database;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CSVReaderWriter {
    private static OpenOption[] openOptions = {StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE};
    private static OpenOption[] openOptions1 = {StandardOpenOption.APPEND, StandardOpenOption.CREATE};


    public static < T > void csvWriter(List<T> object) {
        switch (object.get(0).getClass().getName()) {
            case "people.Doctor":
                try {
                    Writer writer = Files.newBufferedWriter(Paths.get("./src/main/resources/doctors.csv"), Charset.forName("UTF-8"), openOptions);

                    CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("Doctor Name", "Phone number", "age", "ID", "department", "specialization"));

                    //Writing records in the generated CSV file
                    for (T obj : object) {
                        Doctor doctor = Doctor.class.cast(obj);
                        csvPrinter.printRecord(doctor.getName(), doctor.getPhoneNumber(), doctor.getAge(), doctor.getDoctorID(), doctor.getDepartment(), doctor.getSpecialization());
                    }

                    csvPrinter.flush();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                break;
            case "RoomsAndDepartments.Room":
                try {
                    Writer writer = Files.newBufferedWriter(Paths.get("./src/main/resources/rooms.csv"), Charset.forName("UTF-8"), openOptions);

                    CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("Room", "Number", "Floor", "Availability"));

                    //Writing records in the generated CSV file
                    for (T obj : object) {
                        Room room = Room.class.cast(obj);
                        csvPrinter.printRecord(room.getRoom(), room.getRoomNo(), room.getFloor(), room.isAvailability() ? "available" : "not available");
                    }

                    csvPrinter.flush();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                break;
            case "people.Receptionist":
                try {
                    Writer writer = Files.newBufferedWriter(Paths.get("./src/main/resources/receptionist.csv"), Charset.forName("UTF-8"), openOptions);

                    CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("Name", "Phone number", "age", "ID"));

                    //Writing records in the generated CSV file
                    for (T obj : object) {
                        Receptionist receptionist = Receptionist.class.cast(obj);
                        csvPrinter.printRecord(receptionist.getName(), receptionist.getPhoneNumber(), receptionist.getAge(), receptionist.getID());
                    }

                    csvPrinter.flush();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                break;
            case "people.Patient":
                try {
                    Writer writer = Files.newBufferedWriter(Paths.get("./src/main/resources/patients.csv"), Charset.forName("UTF-8"), openOptions);

                    CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);

                    //Writing records in the generated CSV file
                    for (T obj : object) {
                        Patient patient = Patient.class.cast(obj);
                        csvPrinter.printRecord(patient.getName(), patient.getPhoneNumber(), patient.getAge(), patient.getDisease());
                    }

                    csvPrinter.flush();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                break;
            case "bills.Bill":
                try {
                    Writer writer = Files.newBufferedWriter(Paths.get("./src/main/resources/bills.csv"), Charset.forName("UTF-8"), openOptions);

                    CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);

                    //Writing records in the generated CSV file
                    for (T obj : object) {
                        Bill bill = Bill.class.cast(obj);
                        csvPrinter.printRecord(bill.getBillNo(), bill.getPatientName(), bill.getPatientID(), bill.getAmount(), bill.getBillDate());
                    }

                    csvPrinter.flush();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                break;
        }
    }

    public static < T > void csvWriter(T object) {
        switch (object.getClass().getName()) {
            case "people.Receptionist":
                try {
                    Writer writer = Files.newBufferedWriter(Paths.get("./src/main/resources/receptionist.csv"), Charset.forName("UTF-8"), openOptions);

                    CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("Name", "Phone number", "age", "ID"));

                    //Writing records in the generated CSV file
                    Receptionist receptionist = Receptionist.class.cast(object);
                    csvPrinter.printRecord(receptionist.getName(), receptionist.getPhoneNumber(), receptionist.getAge(), receptionist.getID());

                    csvPrinter.flush();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                break;
            case "people.Doctor":
                try {
                    Writer writer = Files.newBufferedWriter(Paths.get("./src/main/resources/doctors.csv"), Charset.forName("UTF-8"), openOptions1);

                    CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);

                    //Writing records in the generated CSV file
                    Doctor doctor = Doctor.class.cast(object);
                    csvPrinter.printRecord(doctor.getName(), doctor.getPhoneNumber(), doctor.getAge(), doctor.getDoctorID(), doctor.getDepartment(), doctor.getSpecialization());

                    csvPrinter.flush();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                break;
            case "people.Patient":
                try {
                    Writer writer = Files.newBufferedWriter(Paths.get("./src/main/resources/patients.csv"), Charset.forName("UTF-8"), openOptions1);

                    CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);

                    //Writing records in the generated CSV file
                    Patient patient = Patient.class.cast(object);
                    csvPrinter.printRecord(patient.getName(), patient.getPhoneNumber(), patient.getAge(), patient.getDisease());

                    Database.addObject((T) patient);

                    csvPrinter.flush();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                break;
            case "bills.Bill":
                try {
                    Writer writer = Files.newBufferedWriter(Paths.get("./src/main/resources/bills.csv"), Charset.forName("UTF-8"), openOptions1);

                    CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);

                    //Writing records in the generated CSV file
                    Bill bill = Bill.class.cast(object);
                    csvPrinter.printRecord(bill.getBillNo(), bill.getPatientName(), bill.getPatientID(), bill.getAmount(), bill.getBillDate());

                    Database.addObject((T) bill);

                    csvPrinter.flush();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                break;
        }
    }

    public static < T > List<T> csvReader(String what) {
        List<T> result = new ArrayList<>();

        switch (what) {
            case "doctor":
                try {
                    BufferedReader reader = Files.newBufferedReader(Paths.get("./src/main/resources/doctors.csv"));
                    CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader("Doctor Name", "Phone number", "age", "ID", "department", "specialization").withFirstRecordAsHeader());

                    for (CSVRecord csvRecord: csvParser) {
                        //Accessing the values by column header name
                        Doctor newDoctor = new Doctor(csvRecord.get("Doctor Name"), csvRecord.get("Phone number"), Integer.parseInt(csvRecord.get("age")), Integer.parseInt(csvRecord.get("ID")), Departments.valueOf(csvRecord.get("department")), Specializations.valueOf(csvRecord.get("specialization")));

                        Database.addObject((T) newDoctor);

                        result.add((T) newDoctor);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                break;
            case "receptionist":
                try {
                    BufferedReader reader = Files.newBufferedReader(Paths.get("./src/main/resources/receptionist.csv"));
                    CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader("Name", "Phone number", "age", "ID").withFirstRecordAsHeader());

                    for (CSVRecord csvRecord: csvParser) {
                        //Accessing the values by column header name
                        Receptionist newReceptionist = new Receptionist(csvRecord.get("Name"), csvRecord.get("Phone number"), Integer.parseInt(csvRecord.get("age")), Integer.parseInt(csvRecord.get("ID")));

                        Database.addObject((T) newReceptionist);

                        result.add((T) newReceptionist);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                break;
            case "patient":
                try {
                    BufferedReader reader = Files.newBufferedReader(Paths.get("./src/main/resources/patients.csv"));
                    CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);

                    for (CSVRecord csvRecord: csvParser) {
                        //Accessing the values by column header name
                        Patient newPatient = new Patient(csvRecord.get(0), csvRecord.get(1), Integer.parseInt(csvRecord.get(2)), csvRecord.get(3));
                        result.add((T) newPatient);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                break;
            case "room":
                try {
                    BufferedReader reader = Files.newBufferedReader(Paths.get("./src/main/resources/rooms.csv"));
                    CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader("Room", "Number", "Floor", "Availability").withFirstRecordAsHeader());

                    for (CSVRecord csvRecord: csvParser) {
                        //Accessing the values by column header name
                        Room newRoom = new Room(Rooms.valueOf(csvRecord.get("Room")), Integer.parseInt(csvRecord.get("Number")), Integer.parseInt(csvRecord.get("Floor")), csvRecord.get("Availability").equals("available") ? true : false);

                        Database.addObject((T) newRoom);

                        result.add((T) newRoom);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                break;
            case "bill":
                try {
                    BufferedReader reader = Files.newBufferedReader(Paths.get("./src/main/resources/bills.csv"));
                    CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);

                    for (CSVRecord csvRecord: csvParser) {
                        //Accessing the values by column header name
                        Bill newBill = new Bill(Integer.parseInt(csvRecord.get(0)), csvRecord.get(1), Integer.parseInt(csvRecord.get(2)), Double.parseDouble(csvRecord.get(3)), new SimpleDateFormat("dd/MM/yyyy").parse(csvRecord.get(4)));
                        result.add((T) newBill);
                    }
                } catch (IOException | ParseException ex) {
                    ex.printStackTrace();
                }
                break;
        }

        return result;
    }
}
