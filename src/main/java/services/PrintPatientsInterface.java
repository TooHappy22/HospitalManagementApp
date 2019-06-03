package services;

import hospital.Hospital;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class PrintPatientsInterface extends JFrame {
    private Hospital hospital;

    JFrame frame;

    public PrintPatientsInterface(Hospital hospital) {
        this.hospital = hospital;

        frame = new JFrame("Patients in the hospital");
        frame.setSize(300, 200);

        JPanel middlePanel = new JPanel ();
        middlePanel.setBorder ( new TitledBorder( new EtchedBorder(), "Patients:" ) );

        // create the middle panel components

        JTextArea display = new JTextArea ( 16, 58 );
        display.setEditable (false);
        JScrollPane scroll = new JScrollPane ( display );
        scroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );

        middlePanel.add (scroll);

        String patients = HospitalSerivces.getPatients(hospital);

        display.append(patients);

        frame.add ( middlePanel );
        frame.pack ();
        frame.setLocationRelativeTo (null);
        frame.setVisible (true);

        frame.setResizable(false);
    }
}
