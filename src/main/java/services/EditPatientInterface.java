package services;

import exceptions.InvalidPatient;
import exceptions.NoRoomsAvailable;
import hospital.Hospital;
import people.Patient;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditPatientInterface extends JFrame implements ActionListener {
    private Hospital hospital;

    private JFrame frame;
    private JLabel patientID, name, phoneNumber, age, disease;
    private JTextField patientIDTF, nameTF, phoneNumberTF, ageTF, diseaseTF;
    private JButton submit;

    public EditPatientInterface(Hospital hospital) {
        this.hospital = hospital;

        frame = new JFrame("Edit patient");

        patientID = new JLabel("Patient ID");
        name = new JLabel("Name");

        patientIDTF = new JTextField();
        nameTF = new JTextField();

        submit = new JButton("Edit patient");

        patientID.setBounds(80, 30, 200, 30);
        name.setBounds(80, 70, 200, 30);

        patientIDTF.setBounds(240, 30, 200, 30);
        nameTF.setBounds(240, 70, 200, 30);

        submit.setBounds(240, 230, 200, 30);

        frame.add(patientID);
        frame.add(name);

        frame.add(patientIDTF);
        frame.add(nameTF);

        frame.add(submit);

        submit.addActionListener(this);

        frame.setSize(600, 300);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    public void actionPerformed(ActionEvent ae) {
        String inputID = patientIDTF.getText();
        String inputName = nameTF.getText();

        System.out.println("EDIT PATIENT ACTION PERFORMED");

        if(!inputID.equals("") && !inputName.equals("")) {
            try {
                HospitalSerivces.editPatient(Integer.parseInt(inputID), inputName, hospital);
                frame.setVisible(false);
            } catch (InvalidPatient e) {
                e.toString();
            }
        }
        else {
            JOptionPane.showMessageDialog(this,"Incorrect login or password",
                    "Error",JOptionPane.ERROR_MESSAGE);
        }
    }
}
