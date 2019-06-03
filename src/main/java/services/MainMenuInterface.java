package services;

import hospital.Hospital;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuInterface extends JFrame implements ActionListener {
    private Hospital hospital;

    private JFrame frame;
    private JButton availableRoomsButton, printBillsButton, printDoctorsButton, printPatientsButton, garbageButton, addPatientButton, removePatientButton, editPatientButton;

    public MainMenuInterface(Hospital hospital) {
        this.hospital = hospital;

        frame = new JFrame("Main Menu");

        frame.setSize(600, 300);

        availableRoomsButton = new JButton("Available rooms");
        printBillsButton = new JButton("Print bills");
        printDoctorsButton = new JButton("Print doctors");
        printPatientsButton = new JButton("Print patients");
        addPatientButton = new JButton("Add patient");
        removePatientButton = new JButton("Remove patient");
        editPatientButton = new JButton("Edit patient");
        garbageButton = new JButton("");

        availableRoomsButton.setBounds(220, 50, 200, 30);
        printBillsButton.setBounds(220, 80, 200, 30);
        printDoctorsButton.setBounds(220, 110, 200, 30);
        printPatientsButton.setBounds(220, 140, 200, 30);
        addPatientButton.setBounds(220, 170, 200, 30);
        removePatientButton.setBounds(220, 200, 200, 30);
        editPatientButton.setBounds(220, 230, 200, 30);
        garbageButton.setBounds(220, 120, 200, 30);

        frame.add(availableRoomsButton);
        frame.add(printBillsButton);
        frame.add(printDoctorsButton);
        frame.add(printPatientsButton);
        frame.add(addPatientButton);
        frame.add(removePatientButton);
        frame.add(editPatientButton);
        frame.add(garbageButton);

        availableRoomsButton.addActionListener(this);
        printBillsButton.addActionListener(this);
        printDoctorsButton.addActionListener(this);
        printPatientsButton.addActionListener(this);
        addPatientButton.addActionListener(this);
        removePatientButton.addActionListener(this);
        editPatientButton.addActionListener(this);


        frame.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    public void actionPerformed(ActionEvent ae) {
        System.out.println("Main menu action");

        switch (ae.getActionCommand()) {
            case "Available rooms":
                AvailableRoomsInterface availableRoomsInterface = new AvailableRoomsInterface(hospital);
                break;
            case "Print bills":
                PrintBillsInterface printBillsInterface = new PrintBillsInterface(hospital);
                break;
            case "Print patients":
                PrintPatientsInterface printPatientsInterface = new PrintPatientsInterface(hospital);
                break;
            case "Print doctors":
                PrintDoctorsInterface printDoctorsInterface = new PrintDoctorsInterface(hospital);
                break;
            case "Add patient":
                AddPatientInterface addPatientInterface = new AddPatientInterface(hospital);
                break;
            case "Remove patient":
                RemovePatientInterface removePatientInterface = new RemovePatientInterface(hospital);
                break;
            case "Edit patient":
                EditPatientInterface editPatientInterface = new EditPatientInterface(hospital);
                break;
        }
    }
}
