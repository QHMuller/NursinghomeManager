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


public class StayManageFrame
{
	private JTable Table;
	private List list;
	private JComboBox comboBox,comboBox_2;
	//存放列名
	private Vector<String> titles=new Vector();
	//存放数据
	private Vector<Vector<Object>> data=new Vector<>();
	JFrame frame =new JFrame("住宿管理");
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JScrollBar scrollBar;
	
	public StayManageFrame()
	{
		
		
		//表格的相关操作
		//初始化列名
		titles.addElement("序号");
		titles.addElement("姓名");
		titles.addElement("性别");
		titles.addElement("房间号");
		titles.addElement("床位号");
		titles.addElement("电话");
		titles.addElement("状态");
		
		//连接数据库
		JDBC jdbcConnection=new JDBC();
		
		//获取所有老人信息
		try {
			String sql="select * from ZhuSuXinXi;";
			ResultSet res=jdbcConnection.executeQuery(sql);
			int count=1;											//序号
			while(res.next())
			{
				Vector<Object> dataRow=new Vector<>();				//一行数据
				dataRow.add(count++);
				dataRow.add(res.getString("name"));
				dataRow.add(res.getString("sex"));
				dataRow.add(res.getString("roomnum"));
				dataRow.add(res.getString("bednum"));
				dataRow.add(res.getString("tel"));
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
		TableColumn columnTyp=Table.getColumn(titles.get(4));
		TableColumn columnTe=Table.getColumn(titles.get(5));
		columnCount.setMaxWidth(40);											//调整其最大宽度
		columnNo.setMaxWidth(80);
		columnType.setMaxWidth(40);
		columnName.setMaxWidth(80);
		columnTe.setMinWidth(100);
		columnTyp.setMaxWidth(40);

		frame.setBounds(100, 100, 533, 345);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		JScrollPane scrollPane = new JScrollPane(Table);
		scrollPane.setBounds(0, 0, 521, 143);
		frame.getContentPane().add(scrollPane);
		
		scrollBar = new JScrollBar();
		scrollPane.setRowHeaderView(scrollBar);
		
		btnNewButton = new JButton("点  击  添  加");
		btnNewButton.setBounds(313, 153, 155, 31);
		btnNewButton.setFont(new Font("楷体", Font.BOLD | Font.ITALIC, 15));
		frame.getContentPane().add(btnNewButton);
		
		btnNewButton_1 = new JButton("点  击  删  除");
		btnNewButton_1.setBounds(313, 202, 155, 31);
		btnNewButton_1.setFont(new Font("楷体", Font.BOLD | Font.ITALIC, 15));
		frame.getContentPane().add(btnNewButton_1);
		
		btnNewButton_2 = new JButton("点  击  修  改");
		btnNewButton_2.setBounds(313, 254, 155, 31);
		btnNewButton_2.setFont(new Font("楷体", Font.BOLD | Font.ITALIC, 15));
		frame.getContentPane().add(btnNewButton_2);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(0, 141, 242, 179);
		ImageIcon loginIcon=GetRelativePaths.add("Every.jpg");
		lblNewLabel.setIcon(loginIcon);
		lblNewLabel.setOpaque(true);
		frame.getContentPane().add(lblNewLabel);
		
		
		frame.setDefaultCloseOperation(frame.HIDE_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.validate();
		//删除功能
		btnNewButton_1.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try {
					int result=JOptionPane.showConfirmDialog(frame, "确定删除该老人的住宿信息？","提示",JOptionPane.YES_NO_OPTION);
					if(result==JOptionPane.YES_OPTION)
					{
						int selectedRow=Table.getSelectedRow();
						//在数据库中删除这条数据
						String oldNo=(String) model.getValueAt(selectedRow, 1);
						String sql="delete from ZhuSuXinXi where name='"+oldNo+"'";
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
		//修改功能
		btnNewButton_2.addActionListener(new ActionListener()
		{	
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					StayEditorFrame stayeditorFrame=new StayEditorFrame(Table.getSelectedRow(),model);
				}catch(Exception e){
					
					JOptionPane.showMessageDialog(null, "请选择需要修改的信息！");
				}
			
			}
		});
		//添加功能
		btnNewButton.addActionListener(new ActionListener()
		{	
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
			StayAddFrame stayaddFrame=new StayAddFrame();
			}
		});
	}
}