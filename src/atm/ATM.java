package atm;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class ATM extends JFrame implements ActionListener
{

	private JPanel contentPane,panel,operations,ope,history,transfer,withdraw,setting;
	private CardLayout mnlayout, opelayout;
	private JButton btnhistory,btnsetting,btnquit,btntransfer,btnwithdraw,btnwithdrawok,btnsend,btnsettingok;
	private JTextField txtwithdraw;
	private JTable tbl;
	private JLabel weluser,welbalance;
	private JScrollPane scrollPane;
	private JTextField txtpinuserid;
	private JTextField txtoldpin;
	private JTextField txtnewpin;
	private JTextField txttransuser;
	private JTextField txttransamount;
	private String userid;
	private int userpin;
	private Connection con;
	private JTable table;
	public ATM(String id,int pin) throws Exception
	{
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm","root","**********");
		
		userid = id;
		userpin = pin;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 607, 392);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(0, 0, 591, 353);
		contentPane.add(panel);
		mnlayout = new CardLayout(0,0);
		panel.setLayout(mnlayout);
		
		operations = new JPanel();
		operations.setForeground(new Color(54, 226, 226));
		operations.setBackground(new Color(248, 248, 255));
		panel.add(operations, "operation");
		operations.setLayout(null);
		
		JPanel ope_list = new JPanel();
		ope_list.setBackground(new Color(175, 238, 238));
		ope_list.setBounds(0, 0, 143, 353);
		operations.add(ope_list);
		ope_list.setLayout(null);
		
		btnhistory = new JButton("History");
		btnhistory.setBackground(new Color(153, 50, 204));
		btnhistory.setFont(new Font("Verdana", Font.BOLD, 12));
		btnhistory.setForeground(new Color(248, 248, 255));
		btnhistory.setBounds(21, 44, 99, 32);
		ope_list.add(btnhistory);
		
		btnwithdraw = new JButton("Withdraw");
		btnwithdraw.setBackground(new Color(153, 50, 204));
		btnwithdraw.setFont(new Font("Verdana", Font.BOLD, 12));
		btnwithdraw.setForeground(new Color(248, 248, 255));
		btnwithdraw.setBounds(21, 100, 99, 32);
		ope_list.add(btnwithdraw);
		
		btntransfer = new JButton("Transfer");
		btntransfer.setBackground(new Color(153, 50, 204));
		btntransfer.setFont(new Font("Verdana", Font.BOLD, 12));
		btntransfer.setForeground(new Color(248, 248, 255));
		btntransfer.setBounds(21, 155, 99, 32);
		ope_list.add(btntransfer);
		
		btnquit = new JButton("Quit");
		btnquit.setBackground(new Color(153, 50, 204));
		btnquit.setFont(new Font("Verdana", Font.BOLD, 12));
		btnquit.setForeground(new Color(248, 248, 255));
		btnquit.setBounds(21, 263, 99, 32);
		ope_list.add(btnquit);
		
		btnsetting = new JButton("Setting");
		btnsetting.setBackground(new Color(153, 50, 204));
		btnsetting.setFont(new Font("Verdana", Font.BOLD, 12));
		btnsetting.setForeground(new Color(248, 248, 255));
		btnsetting.setBounds(21, 209, 99, 32);
		ope_list.add(btnsetting);
		
		ope = new JPanel();
		ope.setForeground(new Color(54, 226, 226));
		ope.setBounds(142, 92, 449, 261);
		operations.add(ope);
		opelayout = new CardLayout(0,0);
		ope.setLayout(opelayout);
		
		history = new JPanel();
		history.setBackground(new Color(0, 255, 255));
		ope.add(history, "history");
		history.setLayout(null);
		
		JPanel withdraw = new JPanel();
		withdraw.setBackground(new Color(0, 255, 255));
		ope.add(withdraw, "withdraw");
		withdraw.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Withdraw Money");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Verdana", Font.BOLD, 18));
		lblNewLabel_3.setBounds(79, 36, 202, 43);
		withdraw.add(lblNewLabel_3);
		
		JLabel lblamount = new JLabel("Amount");
		lblamount.setHorizontalAlignment(SwingConstants.CENTER);
		lblamount.setFont(new Font("Verdana", Font.BOLD, 15));
		lblamount.setBounds(55, 102, 86, 28);
		withdraw.add(lblamount);
		
		txtwithdraw = new JTextField();
		txtwithdraw.setColumns(10);
		txtwithdraw.setBounds(169, 104, 165, 28);
		withdraw.add(txtwithdraw);
		
		btnwithdrawok = new JButton("OK");
		btnwithdrawok.setForeground(new Color(248, 248, 255));
		btnwithdrawok.setFont(new Font("Verdana", Font.BOLD, 12));
		btnwithdrawok.setBackground(new Color(153, 50, 204));
		btnwithdrawok.setBounds(134, 180, 99, 32);
		withdraw.add(btnwithdrawok);
		
		setting = new JPanel();
		setting.setBackground(new Color(0, 255, 255));
		ope.add(setting, "setting");
		setting.setLayout(null);
		
		JLabel lblpinuserid = new JLabel("User Id");
		lblpinuserid.setHorizontalAlignment(SwingConstants.CENTER);
		lblpinuserid.setFont(new Font("Verdana", Font.BOLD, 15));
		lblpinuserid.setBounds(48, 77, 86, 28);
		setting.add(lblpinuserid);
		
		txtpinuserid = new JTextField();
		txtpinuserid.setColumns(10);
		txtpinuserid.setBounds(162, 79, 165, 28);
		setting.add(txtpinuserid);
		
		JLabel lblNewLabel_3_1 = new JLabel("Change Pin");
		lblNewLabel_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1.setFont(new Font("Verdana", Font.BOLD, 18));
		lblNewLabel_3_1.setBounds(72, 11, 202, 43);
		setting.add(lblNewLabel_3_1);
		
		btnsettingok = new JButton("OK");
		btnsettingok.setForeground(new Color(248, 248, 255));
		btnsettingok.setFont(new Font("Verdana", Font.BOLD, 12));
		btnsettingok.setBackground(new Color(153, 50, 204));
		btnsettingok.setBounds(114, 207, 99, 32);
		setting.add(btnsettingok);
		
		JLabel lbloldpin = new JLabel("Old Pin");
		lbloldpin.setHorizontalAlignment(SwingConstants.CENTER);
		lbloldpin.setFont(new Font("Verdana", Font.BOLD, 15));
		lbloldpin.setBounds(48, 114, 86, 28);
		setting.add(lbloldpin);
		
		txtoldpin = new JTextField();
		txtoldpin.setColumns(10);
		txtoldpin.setBounds(162, 116, 165, 28);
		setting.add(txtoldpin);
		
		JLabel lblnewpin = new JLabel("New Pin");
		lblnewpin.setHorizontalAlignment(SwingConstants.CENTER);
		lblnewpin.setFont(new Font("Verdana", Font.BOLD, 15));
		lblnewpin.setBounds(48, 153, 86, 28);
		setting.add(lblnewpin);
		
		txtnewpin = new JTextField();
		txtnewpin.setColumns(10);
		txtnewpin.setBounds(162, 155, 165, 28);
		setting.add(txtnewpin);
		
		transfer = new JPanel();
		transfer.setBackground(new Color(0, 255, 255));
		ope.add(transfer, "transfer");
		transfer.setLayout(null);
		
		JLabel lbltransfer = new JLabel("Transfer Money");
		lbltransfer.setHorizontalAlignment(SwingConstants.CENTER);
		lbltransfer.setFont(new Font("Verdana", Font.BOLD, 18));
		lbltransfer.setBounds(63, 22, 202, 43);
		transfer.add(lbltransfer);
		
		JLabel lblRecipientsId = new JLabel("Recipient's Id");
		lblRecipientsId.setHorizontalAlignment(SwingConstants.CENTER);
		lblRecipientsId.setFont(new Font("Verdana", Font.BOLD, 15));
		lblRecipientsId.setBounds(27, 88, 137, 28);
		transfer.add(lblRecipientsId);
		
		txttransuser = new JTextField();
		txttransuser.setColumns(10);
		txttransuser.setBounds(174, 88, 165, 28);
		transfer.add(txttransuser);
		
		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setHorizontalAlignment(SwingConstants.CENTER);
		lblAmount.setFont(new Font("Verdana", Font.BOLD, 15));
		lblAmount.setBounds(37, 123, 86, 28);
		transfer.add(lblAmount);
		
		txttransamount = new JTextField();
		txttransamount.setColumns(10);
		txttransamount.setBounds(174, 125, 165, 28);
		transfer.add(txttransamount);
		
		btnsend = new JButton("Send");
		btnsend.setForeground(new Color(248, 248, 255));
		btnsend.setFont(new Font("Verdana", Font.BOLD, 12));
		btnsend.setBackground(new Color(153, 50, 204));
		btnsend.setBounds(126, 194, 99, 32);
		transfer.add(btnsend);
		
		JPanel welcome = new JPanel();
		welcome.setForeground(new Color(54, 226, 226));
		welcome.setBackground(new Color(0, 255, 255));
		welcome.setBounds(142, 0, 449, 92);
		operations.add(welcome);
		welcome.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("WelCome");
		lblNewLabel.setFont(new Font("Monotype Corsiva", Font.BOLD | Font.ITALIC, 35));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(108, 11, 261, 44);
		welcome.add(lblNewLabel);
		
		weluser = new JLabel("Username");
		weluser.setText(userid);
		weluser.setFont(new Font("Verdana", Font.ITALIC, 18));
		weluser.setHorizontalAlignment(SwingConstants.CENTER);
		weluser.setBounds(0, 66, 169, 25);
		welcome.add(weluser);
		
		welbalance = new JLabel("Balance");
		Statement st = con.createStatement();
		String que = "Select amount from accounts where user_id = '"+userid+"'";
		ResultSet rs = st.executeQuery(que);
		if(rs.next())
		{
			welbalance.setText("Rs "+Integer.toString(rs.getInt(1)));
		}
		welbalance.setFont(new Font("Verdana", Font.ITALIC, 18));
		welbalance.setHorizontalAlignment(SwingConstants.CENTER);
		welbalance.setBounds(277, 64, 151, 28);
		welcome.add(welbalance);
		add_com();
	}
	public void add_com()
	{
		btnhistory.addActionListener(this);
		btnwithdraw.addActionListener(this);
		btntransfer.addActionListener(this);
		btnsetting.addActionListener(this);
		btnquit.addActionListener(this);
		btnwithdrawok.addActionListener(this);
		btnsend.addActionListener(this);
		btnsettingok.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==btnhistory) 
		{
			opelayout.show(ope,"history");
			try
			{
				Statement st = con.createStatement();
				String q1 = "Select * from history where user_id = '"+userid+"'";
				ResultSet rs = st.executeQuery(q1);
				ResultSetMetaData rsmd = rs.getMetaData();
				
				JScrollPane scrollpane = new JScrollPane();
				scrollpane.setBounds(10, 11, 429, 239);
				history.add(scrollpane);
				
				table = new JTable();
//				table.setModel(new DefaultTableModel(
//					new Object[][] {
//					},
//					new String[] {
//					}
//				));
				scrollpane.setViewportView(table);
//				table.setBackground(new Color(, 255, 255));
				
				DefaultTableModel  model = (DefaultTableModel) table.getModel();
				
				String colname[] = {"Date Time","User Id","Operation","Amount"};
				model.setColumnIdentifiers(colname);
				while(rs.next())
				{
					String row[] = {rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)};
					model.addRow(row);	
				}
				
			}
			catch(Exception p) {System.out.println(p);}
			
		}
		else if(e.getSource()==btnwithdraw) opelayout.show(ope, "withdraw");
		else if(e.getSource()==btntransfer) opelayout.show(ope, "transfer");
		else if(e.getSource()==btnsetting)	opelayout.show(ope,"setting");
		else if(e.getSource()==btnquit)
		{
			Login frame = new Login();
			frame.setVisible(true);
			this.dispose();
		}
		else if(e.getSource()==btnwithdrawok)
		{
			try
			{
				String sql = "Select amount from accounts where user_id = '"+userid+"'";
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql);
				rs.next();
				int am = rs.getInt(1);
				{
					if(am >= Integer.parseInt(txtwithdraw.getText()))
					{
						String que = "update accounts set amount = amount - "+txtwithdraw.getText()+" where user_id = '"+userid+"'";
						PreparedStatement pr = con.prepareStatement(que);
						pr.executeUpdate();
						JOptionPane.showMessageDialog(history, "Collect Money");
						
						String que1 = "insert into history values(NOW(),'"+userid+"','Debited',"+txtwithdraw.getText()+")";
						PreparedStatement pr1 = con.prepareStatement(que1);
						pr1.executeUpdate();
					}
					else JOptionPane.showMessageDialog(history, "Insufficient Balance");
				}
			}
			catch(Exception p) {System.out.println(p);}
		}
		else if (e.getSource()== btnsettingok)
		{
			try
			{
				String sql = "Select * from user_info where user_id = '"+userid+"'";
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql);
				rs.next();
				int oldpin = rs.getInt(2);
				if(oldpin == userpin)
				{
					String que = "update user_info set pin ="+txtnewpin.getText()+" where user_id = '"+userid+"'";
					PreparedStatement pr = con.prepareStatement(que);
					pr.executeUpdate();
					JOptionPane.showMessageDialog(setting, "Pin Changed");
				}
				else JOptionPane.showMessageDialog(history, "Incorrect Old pin");
				
			}
			catch(Exception p) {System.out.println(p);}
		}
		else if(e.getSource()==btnsend)
		{
			try
			{
				String sql = "Select * from user_info where user_id = '"+txttransuser.getText()+"'";
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql);
				if(rs.next())
				{
					try
					{
						String sql1 = "Select amount from accounts where user_id = '"+userid+"'";
						String sql2 = "Select * from accounts where user_id = '"+txttransuser.getText()+"'";
						
						Statement st1 = con.createStatement();
						ResultSet rs1 = st.executeQuery(sql1);
						
						rs1.next();
						int am = rs1.getInt(1);
						
						Statement st2 = con.createStatement();
						ResultSet rs2 = st.executeQuery(sql2);
						
						if(am >= Integer.parseInt(txttransamount.getText()) && rs2.next())
						{
							String que1 = "update accounts set amount = amount - "+txttransamount.getText()+" where user_id = '"+userid+"'";
							PreparedStatement pr1 = con.prepareStatement(que1);
							pr1.executeUpdate();
							
							String que2 = "update accounts set amount = amount + "+txttransamount.getText()+" where user_id = '"+txttransuser.getText()+"'";
							PreparedStatement pr2 = con.prepareStatement(que2);
							pr2.executeUpdate();
							
							String que3 = "insert into history values(NOW(),'"+userid+"','Debited',"+txttransamount.getText()+")";
							PreparedStatement pr3 = con.prepareStatement(que3);
							pr3.executeUpdate();
							
							String que4 = "insert into history values(NOW(),'"+txttransuser.getText()+"','Credited',"+txttransamount.getText()+")";
							PreparedStatement pr4 = con.prepareStatement(que4);
							pr4.executeUpdate();
							
							
						}
						else JOptionPane.showMessageDialog(history, "Insufficient Balance");
							
					}
					catch(Exception k) {System.out.println(k);}
					
				}
				else JOptionPane.showMessageDialog(transfer, "Invalid User_Id");	
			}
			catch(Exception p) {System.out.println(p);}
		}
	}
	public static void main(String arg[]) {}
}
