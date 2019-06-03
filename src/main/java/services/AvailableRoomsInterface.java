package services;

import hospital.Hospital;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AvailableRoomsInterface extends JFrame {
    private Hospital hospital;

    private JFrame frame;
    private JLabel answer;

    public AvailableRoomsInterface(Hospital hospital) {
        this.hospital = hospital;

        frame = new JFrame("Available Rooms");
        frame.setSize(300, 200);

        JLabel title = new JLabel("Avaialable rooms:");
        title.setForeground(Color.blue);
        title.setFont(new Font("Serif", Font.BOLD, 20));

        answer = HospitalSerivces.JLabelNumberOfAvailableRooms(hospital);

        title.setBounds(0, 0, 200, 30);
        answer.setBounds(100, 0, 50, 50);

        frame.add(title);
        frame.add(answer);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
