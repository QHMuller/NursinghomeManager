package com.qh.Frame;

import java.awt.*;
import javax.swing.tree.*;
import javax.swing.*;
import javax.swing.event.*;
import net.miginfocom.swing.MigLayout;
import com.jgoodies.forms.layout.*;
import com.qh.Tools.GetRelativePaths;

public class AdminFrame extends JFrame
{
	JTree tree;
	public AdminFrame() 
	{
		setTitle("养老院管理系统");
		setBounds(100, 100, 725, 640);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 228, 196));
		//添加树，设置结点
		tree = new JTree();
		tree.setBackground(new Color(255, 228, 196));
		tree.addTreeSelectionListener(new MyTreeListener_1());
		tree.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode("养老院管理系统") {
				{
					DefaultMutableTreeNode node;
					node = new DefaultMutableTreeNode("老人管理");
					node.add(new DefaultMutableTreeNode("基本信息"));
					node.add(new DefaultMutableTreeNode("住宿管理"));
					add(node);
					node = new DefaultMutableTreeNode("费用管理");
					node.add(new DefaultMutableTreeNode("费用明细"));
					add(node);
					node = new DefaultMutableTreeNode("关于我们");
					node.add(new DefaultMutableTreeNode("相关信息"));
					node.add(new DefaultMutableTreeNode("服务宗旨"));
					add(node);
				}
			}
		));
		 panel_1.add(tree);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 228, 196));
		
		JLabel username = new JLabel("用户名：Qi.H",SwingConstants.LEFT);
		username.setFont(new Font("宋体", Font.BOLD, 21));
		panel.add(username);
		
		JLabel theold = new JLabel("the old");
		ImageIcon loginIcon=GetRelativePaths.add("User.jpg");
		theold.setIcon(loginIcon);
		theold.setOpaque(true);
		
		JLabel text = new JLabel("text");
		text.setVerticalAlignment(SwingConstants.BOTTOM);
		ImageIcon loginIcon1=GetRelativePaths.add("text.jpg");
		text.setIcon(loginIcon1);
		text.setOpaque(true);
		//添加组件
		getContentPane().add(panel_1, BorderLayout.WEST);
		getContentPane().add(panel, BorderLayout.NORTH);
		getContentPane().add(theold, BorderLayout.CENTER);
		getContentPane().add(text, BorderLayout.SOUTH);

		
		
	}
	//定义节点事件
	class MyTreeListener_1 implements TreeSelectionListener
	{
		public void valueChanged(TreeSelectionEvent e)
		{
			if (e.getSource() == tree)
			{
				//定义被选中的节点
				DefaultMutableTreeNode node = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
				if(node.isLeaf()) {
					//获取叶节点所定义的文本信息
					String str = node.toString();
					if (str.equals("住宿管理"))	new StayManageFrame();
					if (str.equals("相关信息"))	new AboutUsFrame();
					if (str.equals("基本信息"))	new BasicManageFrame();
					if (str.equals("费用明细"))	new FeeManageFrame();
					if (str.equals("服务宗旨"))	new ServiceTenetFrame();
				}
			}
		}
	}
	

}