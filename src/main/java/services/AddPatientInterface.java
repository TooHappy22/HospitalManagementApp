package services;

import exceptions.NoRoomsAvailable;
import hospital.Hospital;
import people.Patient;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddPatientInterface extends JFrame implements ActionListener {
    private Hospital hospital;

    private JFrame frame;
    private JLabel name, phoneNumber, age, disease;
    private JTextField nameTF, phoneNumberTF, ageTF, diseaseTF;
    private JButton submit;

    public AddPatientInterface(Hospital hospital) {
        this.hospital = hospital;

        frame = new JFrame("Add new patient");

        name = new JLabel("Name");
        phoneNumber = new JLabel("Phone number");
        age = new JLabel("Age");
        disease = new JLabel("Disease");

        nameTF = new JTextField();
        phoneNumberTF = new JTextField();
        ageTF = new JTextField();
        diseaseTF = new JTextField();

        submit = new JButton("Add patient");

        name.setBounds(80, 70, 200, 30);
        phoneNumber.setBounds(80, 110, 200, 30);
        age.setBounds(80, 150, 200, 30);
        disease.setBounds(80, 190, 200, 30);

        nameTF.setBounds(240, 70, 200, 30);
        phoneNumberTF.setBounds(240, 110, 200, 30);
        ageTF.setBounds(240, 150, 200, 30);
        diseaseTF.setBounds(240, 190, 200, 30);

        submit.setBounds(240, 230, 200, 30);

        frame.add(name);
        frame.add(phoneNumber);
        frame.add(age);
        frame.add(disease);

        frame.add(nameTF);
        frame.add(phoneNumberTF);
        frame.add(ageTF);
        frame.add(diseaseTF);

        frame.add(submit);

        submit.addActionListener(this);

        frame.setSize(600, 300);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    public void actionPerformed(ActionEvent ae) {
        String inputName = nameTF.getText();
        String inputPhoneNumber = phoneNumberTF.getText();
        String inputAge = ageTF.getText();
        String inputDisease = diseaseTF.getText();

        System.out.println("ADD PATIENT ACTION PERFORMED");

        if(!inputName.equals("") && !inputPhoneNumber.equals("") && !inputAge.equals("") && !inputDisease.equals("")) {
            try {
                HospitalSerivces.addPatient(new Patient(inputName, inputPhoneNumber, Integer.parseInt(inputAge), inputDisease), hospital);
                frame.setVisible(false);
            } catch (NoRoomsAvailable e) {
                e.toString();
            }
        }
        else {
            JOptionPane.showMessageDialog(this,"Incorrect login or password",
                    "Error",JOptionPane.ERROR_MESSAGE);
        }
    }
}
