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
import com.qh.Tools.GetRelativePaths;


public class FeeManageFrame
{
	private JTable Table;
	private List list;
	private JComboBox comboBox,comboBox_2;
	//存放列名
	private Vector<String> titles=new Vector();
	//存放数据
	private Vector<Vector<Object>> data=new Vector<>();
	JFrame frame =new JFrame("费用明细");
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JScrollBar scrollBar;
	
	public FeeManageFrame()
	{
		
		
		//表格的相关操作
		//初始化列名
		titles.addElement("序号");
		titles.addElement("姓名");
		titles.addElement("年份");
		titles.addElement("月份");
		titles.addElement("日期");
		titles.addElement("事项");
		titles.addElement("收支");
		//连接数据库
		JDBC jdbcConnection=new JDBC();
		
		//获取所有老人信息
		try {
			String sql="select * from Feedetail;";
			ResultSet res=jdbcConnection.executeQuery(sql);
			int count=1;											//序号
			while(res.next())
			{
				Vector<Object> dataRow=new Vector<>();				//一行数据
				dataRow.add(count++);
				dataRow.add(res.getString("name"));
				dataRow.add(res.getString("year"));
				dataRow.add(res.getString("month"));
				dataRow.add(res.getString("day"));
				dataRow.add(res.getString("event"));
				dataRow.add(res.getString("price"));
				data.add(dataRow);
			}
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
		TableColumn columnPrice=Table.getColumn(titles.get(4));
		columnCount.setMaxWidth(40);											//调整其最大宽度
		columnNo.setMinWidth(80);
		columnType.setMaxWidth(40);
		columnName.setMaxWidth(80);
		columnPrice.setMaxWidth(40);
		

		frame.setBounds(100, 100, 533, 345);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		JScrollPane scrollPane = new JScrollPane(Table);
		scrollPane.setBounds(0, 0, 521, 141);
		frame.getContentPane().add(scrollPane);
		
		scrollBar = new JScrollBar();
		scrollPane.setRowHeaderView(scrollBar);
		
		btnNewButton = new JButton("添   加");
		btnNewButton.setBounds(343, 159, 93, 31);
		btnNewButton.setFont(new Font("楷体", Font.BOLD | Font.ITALIC, 15));
		frame.getContentPane().add(btnNewButton);
		
		btnNewButton_1 = new JButton("删   除");
		btnNewButton_1.setBounds(343, 212, 93, 31);
		btnNewButton_1.setFont(new Font("楷体", Font.BOLD | Font.ITALIC, 15));
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_3 = new JButton("取   消");
		btnNewButton_3.setBounds(343, 264, 93, 31);
		btnNewButton_3.setFont(new Font("楷体", Font.BOLD | Font.ITALIC, 15));
		frame.getContentPane().add(btnNewButton_3);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(0, 141, 242, 179);
		ImageIcon loginIcon=GetRelativePaths.add("Every.jpg");
		lblNewLabel.setIcon(loginIcon);
		lblNewLabel.setOpaque(true);
		frame.getContentPane().add(lblNewLabel);
		
		frame.setResizable(false);
		frame.setDefaultCloseOperation(frame.HIDE_ON_CLOSE);
		frame.setVisible(true);
		frame.validate();
		//删除功能
		btnNewButton_1.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try {
					int result=JOptionPane.showConfirmDialog(frame, "确定删除该费用信息？","提示",JOptionPane.YES_NO_OPTION);
					if(result==JOptionPane.YES_OPTION)
					{
						int selectedRow=Table.getSelectedRow();
						//在数据库中删除这条数据
						String oldNo=(String) model.getValueAt(selectedRow, 1);
						String sql="delete from Feedetail where name='"+oldNo+"'";
						jdbcConnection.executeUpdate(sql);
						int alterRow=selectedRow+1;
						for(int i=alterRow;i<model.getRowCount();i++)
						{
							model.setValueAt(alterRow++, i, 0);			//序号更改
						}
						model.removeRow(selectedRow);					//在模型中移除该列
					}
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null, "请选择需要删除的信息！");
				}
				
			}
		});
		//添加功能
		btnNewButton.addActionListener(new ActionListener()
		{	
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
			FeeAddFrame feeaddFrame=new FeeAddFrame();
			}
		});
		//取消功能
		btnNewButton_3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				frame.setVisible(false);
			}
		});
	}
}