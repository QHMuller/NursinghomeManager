package com.qh.Frame;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import com.qh.Modal.MyTableModel;
import com.qh.Tools.JDBC;
import com.qh.Tools.GetRelativePaths;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class StayEditorFrame {

	private JFrame frame;
	private JTextField textField,textField_1,textField_2,textField_3,textField_4;
	private JTextField textField_5;

	

	
	public StayEditorFrame(int selectedRow,MyTableModel model)
	{
		frame = new JFrame("住宿信息修改");
		frame.setResizable(false);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(Color.pink);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("姓   名：");
		lblNewLabel.setBounds(21, 10, 66, 25);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(110, 12, 81, 25);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("性   别：");
		lblNewLabel_1.setBounds(21, 54, 66, 25);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(110, 54, 81, 25);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("房 间 号：");
		lblNewLabel_2.setBounds(21, 97, 66, 25);
		frame.getContentPane().add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		textField_2.setBounds(110, 98, 81, 24);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("床 位 号：");
		lblNewLabel_3.setBounds(21, 132, 66, 23);
		frame.getContentPane().add(lblNewLabel_3);
		
		textField_3 = new JTextField();
		textField_3.setBounds(110, 132, 81, 25);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("电   话：");
		lblNewLabel_4.setBounds(21, 175, 66, 25);
		frame.getContentPane().add(lblNewLabel_4);
		
		textField_4 = new JTextField();
		textField_4.setBounds(110, 175, 81, 25);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("状   态：");
		lblNewLabel_5.setBounds(21, 218, 66, 25);
		frame.getContentPane().add(lblNewLabel_5);
		
		textField_5 = new JTextField();
		textField_5.setBounds(110, 219, 81, 25);
		frame.getContentPane().add(textField_5);
		textField_5.setColumns(10);
		
		JLabel image = new JLabel("image");
		ImageIcon loginIcon=GetRelativePaths.add("Editor.jpg");
		image.setIcon(loginIcon);
		image.setOpaque(true);
		image.setBounds(255, 0, 183,132);
		frame.getContentPane().add(image);
		
		JButton btnNewButton = new JButton("确   认");
		btnNewButton.setBounds(301, 157, 93, 34);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("取   消");
		btnNewButton_1.setBounds(301, 201, 93, 34);
		frame.getContentPane().add(btnNewButton_1);
		
		frame.setDefaultCloseOperation(frame.HIDE_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
		
		//连接数据库
		JDBC jdbcConnection=new JDBC();
		
		//导出所选行的信息
		textField.setText((String) model.getValueAt(selectedRow,1));
		textField.setEditable(false);
		textField_1.setText((String) model.getValueAt(selectedRow, 2));
		textField_2.setText((String) model.getValueAt(selectedRow, 3));
		textField_3.setText((String) model.getValueAt(selectedRow, 4));
		textField_4.setText((String) model.getValueAt(selectedRow, 5));
		textField_5.setText((String) model.getValueAt(selectedRow, 6));
		
		
		
		//修改功能
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					String name=textField.getText();
					String sex=textField_1.getText();
					String age=textField_2.getText();
					String job=textField_3.getText();
					String state=textField_4.getText();
					String states=textField_5.getText();
					String sql="update ZhuSuXinXi set sex='"+sex+"',roomnum='"+age+"',bednum='"+job+"',tel='"
									+state+",state='"+states+"'where name='"+name+"';";
					jdbcConnection.executeUpdate(sql);
					//修改对应行的数据，序号和编号不需要更新
					model.setValueAt(name, selectedRow, 1);
					model.setValueAt(sex, selectedRow, 2);
					model.setValueAt(age, selectedRow, 3);
					model.setValueAt(job, selectedRow, 4);
					model.setValueAt(state, selectedRow, 5);
					model.setValueAt(states, selectedRow, 6);
					JOptionPane.showMessageDialog(frame, "修改住宿信息成功","消息",JOptionPane.PLAIN_MESSAGE);
					frame.dispose();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		//取消功能
		btnNewButton_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				frame.setVisible(false);
			}
		});
		
	}
}
