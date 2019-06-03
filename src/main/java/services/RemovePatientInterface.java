package services;

import exceptions.InvalidPatient;
import exceptions.NoRoomsAvailable;
import hospital.Hospital;
import people.Patient;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemovePatientInterface extends JFrame implements ActionListener {
    private Hospital hospital;

    private JFrame frame;
    private JLabel id;
    private JTextField idTF;
    private JButton submit;

    public RemovePatientInterface(Hospital hospital) {
        this.hospital = hospital;

        frame = new JFrame("Remove patient");

        id = new JLabel("Patient ID");

        idTF = new JTextField();

        submit = new JButton("Remove patient");

        id.setBounds(80, 70, 200, 30);

        idTF.setBounds(240, 70, 200, 30);

        submit.setBounds(240, 230, 200, 30);

        frame.add(id);

        frame.add(idTF);

        frame.add(submit);

        submit.addActionListener(this);

        frame.setSize(600, 300);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    public void actionPerformed(ActionEvent ae) {
        String inputID = idTF.getText();

        System.out.println("REMOVE PATIENT ACTION PERFORMED");

        if(!inputID.equals("")) {
            try {
                HospitalSerivces.removePatient(Integer.parseInt(inputID), hospital);
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
