package com.qh.Frame;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.qh.Tools.GetRelativePaths;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Scrollbar;
import java.awt.ScrollPane;

public class AboutUsFrame {

	private JFrame frame;
	private JTextField textField;

	public AboutUsFrame() {
		frame = new JFrame("关于我们");
		frame.setBounds(300, 100, 450, 500);

		ScrollPane scrollPane = new ScrollPane();
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		frame.setVisible(true);
		
		JTextArea textAreaOutput;
		textAreaOutput = new JTextArea("缩略词词典", 14, 20);
		textAreaOutput.setSelectedTextColor(Color.RED);
		textAreaOutput.setLineWrap(true);        //激活自动换行功能 
		textAreaOutput.setWrapStyleWord(true);            // 激活断行不断字功能
		textAreaOutput.setText("    在经济新发展与数字信息化的当下,老人宜居环境要求也不断提升,私立养老院不断涌现,"
				+ "随即产生供需不匹配现象.养老院管理系统的设计与实现旨在打开私立养老院网上门户,"
				+ "提供高质量与高效率的完善管理体系.为养老行业提供更方便、更快捷、更安全、更人性化的服务。由于在养老院住宿老人是一个较为庞大的群体，"
				+ "为了管理的方便与统一，同时也为了老人的安全，所以需要对其进行相关信息的记录。本着对此问题的思考，于是开发出一个简单便利的养老院管理系统。"
				+ "这一系统的研发主要实现四大功能—“CRUD”：“CRUD”分别是英文单词“create,require,update,delete”的缩写,"
				+ "用中文简易地说法即为对信息的创建、查询、更新、删除。"
				+ "“麻雀虽小，可五脏俱全”：此系统的四个功能满足了对养老院信息管理的基本要求。\r\n"
				+ "    本系统主要是针对敬老院工作人员即管理员和员工设计的。敬老院管理系统将IT技术为养老院提供一个接口便于管理信息,存储老人个人信息和其他信息,"
				+ "查找和更新信息的养老院档案,节省了员工的劳动时间,大大降低了成本。\r\n"
				+ "    其主要功能包括：用户管理员、员工登录、接待管理、老人管理、费用管理、 资料管理、统计分析、员工管理和系统设置。"
				+ "以及对这些功能的增、删、改、查处理。");
		textAreaOutput.setFont(new Font("楷体", Font.BOLD, 15));
		scrollPane.add(textAreaOutput, BorderLayout.CENTER);
		
		
		JLabel theold = new JLabel("New label");
		ImageIcon loginIcon=GetRelativePaths.add("Aboutus.jpg");
		theold.setIcon(loginIcon);
		theold.setOpaque(true);
		frame.getContentPane().add(theold, BorderLayout.NORTH);
		frame.setResizable(false);
	}
}
