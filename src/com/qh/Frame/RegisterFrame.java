package com.qh.Frame;

import java.awt.*;
import javax.swing.*;

import com.qh.Tools.*;

import java.awt.event.*;
/**
 * 
 * @author QH20010724
 * @version 1.0
 */


//我的窗口类
public class RegisterFrame extends JFrame
{
	private static JTextField username,password;
	//构造方法
	public RegisterFrame()
	{
		setResizable(false);
		setTitle("新用户注册");
		setBounds(0,0,380,450);
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//设置组件
		JLabel UserName = new JLabel("请输入用户名：");
		UserName.setFont(new Font("宋体", Font.BOLD | Font.ITALIC, 14));
		UserName.setBounds(20, 299, 120, 31);
		
		JLabel Password = new JLabel("请输入密码：");
		Password.setFont(new Font("宋体", Font.BOLD | Font.ITALIC, 14));
		Password.setBounds(20, 332, 120, 31);
		
		JButton register = new JButton("注   册");
		register.setBackground(Color.WHITE);
		register.setForeground(Color.RED);
		register.setFont(new Font("楷体", Font.BOLD | Font.ITALIC, 14));
		register.setBounds(133, 373, 90, 33);
		register.addActionListener(new RegisterListener());	
		
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
		//最后设置可视化，否则一些组件不可视
		setVisible(true);
	}
	//注册监听
	class RegisterListener implements ActionListener {	
		public void actionPerformed(final ActionEvent e) {
			

			if(username.getText().length()==0){
				JOptionPane.showMessageDialog(null, "用户名不能为空！");
				return;
			}
			if(password.getText().length()==0){
				JOptionPane.showMessageDialog(null, "密码不能为空！");
				return;
			}
			
			String usernames=username.getText().trim();
			String passwords=password.getText().trim();
			
			int i=JDBC.Insertuser(usernames,passwords);
				
			if(i==1){
			
				JOptionPane.showMessageDialog(null, "注册成功");
			}
		}
	}
}