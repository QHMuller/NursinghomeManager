package com.qh.Frame;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;
import net.miginfocom.swing.MigLayout;
import com.jgoodies.forms.layout.*;
import com.qh.Tools.GetRelativePaths;

public class UserFrame extends JFrame
{
	JTree tree;
	public UserFrame() 
	{
		setTitle("养老院系统用户界面");
		setBounds(100, 100, 725, 640);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 228, 196));
		getContentPane().add(panel_1, BorderLayout.WEST);
		
		tree = new JTree();
		tree.setBackground(new Color(255, 228, 196));
		tree.addTreeSelectionListener(new MyTreeListener_1());
		tree.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode("养老院用户界面") {
				{
					DefaultMutableTreeNode node_1;
					node_1 = new DefaultMutableTreeNode("信息管理");
					node_1.add(new DefaultMutableTreeNode("个人信息"));
					node_1.add(new DefaultMutableTreeNode("住宿信息"));
					add(node_1);
					node_1 = new DefaultMutableTreeNode("费用查询");
					node_1.add(new DefaultMutableTreeNode("收费明细"));
					add(node_1);
					node_1 = new DefaultMutableTreeNode("密码管理");
					node_1.add(new DefaultMutableTreeNode("修改密码"));
					add(node_1);
					node_1 = new DefaultMutableTreeNode("关于我们");
					node_1.add(new DefaultMutableTreeNode("相关信息"));
					node_1.add(new DefaultMutableTreeNode("服务宗旨"));
					add(node_1);
				}
			}
		));
		panel_1.add(tree);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 228, 196));
		getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("愿每一位老人身体健康！",SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("宋体", Font.BOLD, 21));
		panel.add(lblNewLabel);
		
		JLabel theold = new JLabel("the old");
		ImageIcon loginIcon=GetRelativePaths.add("User.jpg");
		theold.setIcon(loginIcon);
		theold.setOpaque(true);
		
		JLabel text = new JLabel("text");
		text.setVerticalAlignment(SwingConstants.BOTTOM);
		ImageIcon loginIcon1=GetRelativePaths.add("text.jpg");
		text.setIcon(loginIcon1);
		text.setOpaque(true);
		
		getContentPane().add(theold, BorderLayout.CENTER);
		getContentPane().add(text, BorderLayout.SOUTH);
		setVisible(true);
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
					if (str.equals("个人信息"))	new PriBasicFrame();
					if (str.equals("住宿信息"))	new PriStayFrame();
					if (str.equals("收费明细"))	new PriFeeFrame();
					if (str.equals("修改密码"))	new PassModiFrame();
					if (str.equals("相关信息"))	new AboutUsFrame();
					if (str.equals("服务宗旨"))	new ServiceTenetFrame();
				}
			}
		}
	}
	
}
