package com.qh.Frame;

import java.awt.*;
import javax.swing.*;

import com.qh.Modal.LoginIofo;
import com.qh.Tools.*;

import java.awt.event.*;
import java.sql.SQLException;
/**
 * 
 * @author QH20010724
 * @version 1.0
 */


//我的窗口类
public class PassModiFrame extends JFrame
{
	private static JTextField username,password;
	private JTextField textField;
	//构造方法
	public PassModiFrame()
	{
		setResizable(false);
		setTitle("密码修改");
		setBounds(200,100,380,500);
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		//设置组件
		JLabel UserName = new JLabel("请输入用户名：");
		UserName.setFont(new Font("宋体", Font.BOLD | Font.ITALIC, 14));
		UserName.setBounds(20, 299, 120, 31);
		
		JLabel Password = new JLabel("请输入原密码：");
		Password.setFont(new Font("宋体", Font.BOLD | Font.ITALIC, 14));
		Password.setBounds(20, 332, 120, 31);
		
		JButton register = new JButton("修   改");
		register.setBackground(Color.WHITE);
		register.setForeground(Color.RED);
		register.setFont(new Font("楷体", Font.BOLD | Font.ITALIC, 14));
		register.setBounds(81, 405, 90, 33);
		register.addActionListener(new PassmodiListener());	
		
		JLabel image = new JLabel("image");
		ImageIcon loginIcon=GetRelativePaths.add("register.jpg");
		image.setIcon(loginIcon);
		image.setOpaque(true);
		image.setBounds(0, 0, 381,300);
		
		username = new JTextField();
		username.setBounds(150, 300, 203, 31);
		username.setColumns(10);
		
		password = new JTextField();
		password.setBounds(150, 332, 203, 31);
		password.setColumns(10);
		password.setColumns(10);

		//添加组件
		getContentPane().add(UserName);
		getContentPane().add(Password);
		getContentPane().add(image);
		getContentPane().add(username);
		getContentPane().add(password);
		getContentPane().add(register);
		
		JButton btnNewButton = new JButton("取   消");
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setForeground(Color.RED);
		btnNewButton.setFont(new Font("楷体", Font.BOLD | Font.ITALIC, 14));
		btnNewButton.setBounds(231, 406, 90, 31);
		getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("请输入新密码：");
		lblNewLabel.setFont(new Font("宋体", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel.setBounds(20, 361, 120, 33);
		getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(150, 364, 203, 31);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				PassModiFrame.this.setVisible(false);
			}
		});
		//最后设置可视化，否则一些组件不可视
		setVisible(true);
	}
	//修改密码监听器
	class PassmodiListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			String usernames=username.getText();
			String newPass=textField.getText();
			LoginIofo user = JDBC.checkuser(username.getText(), password.getText());
			if (user.getName() != null) {

				try {
					String sql="update Password set password='"+newPass+"'where name='"+usernames+"';";
					int i=JDBC.executeUpdate(sql);
					JOptionPane.showMessageDialog(null, "密码修改成功！");
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(null, "原密码错误，请重新输入！");
				username.setText("");
				password.setText("");
			}
		}
	}
}