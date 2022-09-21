package com.qh.Frame;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap.KeySetView;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.table.*;
import javax.swing.tree.*;

import com.qh.Modal.BasicInfo;
import com.qh.Modal.LoginIofo;
import com.qh.Modal.MyTableModel;
import com.qh.Tools.JDBC;


public class PriBasicFrame
{
	public JTable Table;
	private List list;
	private JComboBox comboBox,comboBox_2;
	//存放列名
	private Vector<String> titles=new Vector();
	//存放数据
	private Vector<Vector<Object>> data=new Vector<>();
	JFrame frame =new JFrame("个人信息查询");
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	
	public PriBasicFrame()
	{
		
		
		//表格的相关操作
		//初始化列名
		titles.addElement("序号");
		titles.addElement("姓名");
		titles.addElement("性别");
		titles.addElement("年龄");
		titles.addElement("职业");
		titles.addElement("状态");
		
		//连接数据库
		JDBC jdbcConnection=new JDBC();
		
		//获取所有老人信息
		try {
			String sql="select * from JiBenXinXi where name='厉致诚';";
			ResultSet res=jdbcConnection.executeQuery(sql);
			int count=1;											//序号
			while(res.next())
			{
				Vector<Object> dataRow=new Vector<>();				//一行数据
				dataRow.add(count++);
				dataRow.add(res.getString("name"));
				dataRow.add(res.getString("sex"));
				dataRow.add(res.getString("age"));
				dataRow.add(res.getString("job"));
				dataRow.add(res.getString("state"));
				data.add(dataRow);
			}
//			System.out.println(data);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//用列名和数据创建表格模型
		MyTableModel model=new MyTableModel(data,titles);
		
		//用模型来初始化表格
		Table=new JTable(model);
		
		//获取表格头
		JTableHeader departmentHeader=Table.getTableHeader();
		//获取表格体
		Table.setFont(new Font(null,Font.PLAIN,12));			
		Table.setForeground(Color.BLACK);						
		Table.setGridColor(Color.BLACK);						
		Table.setRowHeight(20);								
		Table.setSelectionBackground(Color.yellow);			
		Table.setSelectionForeground(Color.red);	
		Table.setBounds(0,10,200,100);
		
		//设置单行选择
		Table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		//调整表格
		TableColumn columnCount=Table.getColumn(titles.get(0));		//获取表格列
		TableColumn columnNo=Table.getColumn(titles.get(1));	
		TableColumn columnName=Table.getColumn(titles.get(2));
		TableColumn columnType=Table.getColumn(titles.get(3));
		columnCount.setMaxWidth(40);											//调整其最大宽度
		columnNo.setMinWidth(80);
		columnType.setMaxWidth(40);
		columnName.setMaxWidth(80);
		

		frame.setBounds(100, 100, 533, 250);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		this.Table.setVisible(false);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		JScrollPane scrollPane = new JScrollPane(Table);
		scrollPane.setBounds(0, 0, 521, 94);
		frame.getContentPane().add(scrollPane);
		
		btnNewButton = new JButton("点 击 查 询");
		btnNewButton.setBounds(55, 136, 146, 31);
		btnNewButton.setForeground(Color.red);
		btnNewButton.setFont(new Font("楷体", Font.BOLD | Font.ITALIC, 15));
		frame.getContentPane().add(btnNewButton);
		
		btnNewButton_1 = new JButton("取      消");
		btnNewButton_1.setBounds(306, 136, 139, 31);
		btnNewButton_1.setForeground(Color.red);
		btnNewButton_1.setFont(new Font("楷体", Font.BOLD | Font.ITALIC, 15));
		frame.getContentPane().add(btnNewButton_1);
		
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				PriBasicFrame.this.Table.setVisible(true);
			}
		});
		btnNewButton_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				frame.setVisible(false);
			}
		});
		frame.setDefaultCloseOperation(frame.HIDE_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.validate();
		
	}
}