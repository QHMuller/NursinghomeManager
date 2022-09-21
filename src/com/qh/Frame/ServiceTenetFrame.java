package com.qh.Frame;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.qh.Tools.GetRelativePaths;

public class ServiceTenetFrame {

	private JFrame frame;

	public ServiceTenetFrame() {
		frame = new JFrame("·þÎñ×ÚÖ¼");
		frame.setBounds(100, 100, 600, 435);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		ImageIcon loginIcon=GetRelativePaths.add("Tenet1.jpg");
		lblNewLabel.setIcon(loginIcon);
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBounds(0, 0, 400, 400);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		ImageIcon loginIcon1=GetRelativePaths.add("Tenet2.jpg");
		lblNewLabel_1.setIcon(loginIcon1);
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setBounds(400, 0, 189, 400);
		frame.getContentPane().add(lblNewLabel_1);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(frame.HIDE_ON_CLOSE);
		frame.setVisible(true);
	}

}
