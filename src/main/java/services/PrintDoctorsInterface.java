package services;

import hospital.Hospital;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class PrintDoctorsInterface extends JFrame {
    private Hospital hospital;

    JFrame frame;

    public PrintDoctorsInterface(Hospital hospital) {
        this.hospital = hospital;

        frame = new JFrame("Doctors in the hospital");
        frame.setSize(300, 200);

        JPanel middlePanel = new JPanel ();
        middlePanel.setBorder ( new TitledBorder( new EtchedBorder(), "Doctors:" ) );

        // create the middle panel components

        JTextArea display = new JTextArea ( 16, 58 );
        display.setEditable ( false ); // set textArea non-editable
        JScrollPane scroll = new JScrollPane ( display );
        scroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );

        //Add Textarea in to middle panel
        middlePanel.add (scroll);

        String doctors = HospitalSerivces.getDoctors(hospital);

        display.append(doctors);

        frame.add ( middlePanel );
        frame.pack ();
        frame.setLocationRelativeTo ( null );
        frame.setVisible ( true );

        frame.setResizable(false);
    }
}
