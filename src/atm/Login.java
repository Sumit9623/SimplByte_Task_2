package atm;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
public class Login extends JFrame implements ActionListener
{

	private JPanel contentPane;
	private JTextField txtuserid,txtpin;
	private JButton btnlogin;

	public static void main(String[] args) 
	{
		try 
		{
			Login frame = new Login();
			frame.setVisible(true);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	public Login() 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 354, 366);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbluserid = new JLabel("User Id :");
		lbluserid.setFont(new Font("Verdana", Font.PLAIN, 13));
		lbluserid.setBounds(59, 134, 78, 29);
		contentPane.add(lbluserid);
		
		txtuserid = new JTextField();
		txtuserid.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtuserid.setBounds(147, 134, 142, 29);
		contentPane.add(txtuserid);
		txtuserid.setColumns(10);
		
		JLabel iblpin = new JLabel("Pin :");
		iblpin.setFont(new Font("Verdana", Font.PLAIN, 13));
		iblpin.setBounds(59, 188, 78, 26);
		contentPane.add(iblpin);
		
		txtpin = new JTextField();
		txtpin.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtpin.setBounds(147, 185, 142, 29);
		contentPane.add(txtpin);
		txtpin.setColumns(10);
		
		btnlogin = new JButton("Login");
		btnlogin.setBackground(new Color(190, 245, 250));
		btnlogin.setFont(new Font("Verdana", Font.PLAIN, 13));
		btnlogin.setBounds(104, 234, 109, 29);
		contentPane.add(btnlogin);
		
		JLabel hii = new JLabel("Hii There");
		hii.setFont(new Font("Verdana", Font.PLAIN, 16));
		hii.setHorizontalAlignment(SwingConstants.CENTER);
		hii.setBounds(10, 29, 304, 34);
		contentPane.add(hii);
		
		JLabel please = new JLabel("Please Login to Operate");
		please.setFont(new Font("Verdana", Font.PLAIN, 16));
		please.setHorizontalAlignment(SwingConstants.CENTER);
		please.setBounds(10, 63, 304, 29);
		contentPane.add(please);
		
		btnlogin.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource()==btnlogin)
		{
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm","root","Sumit@9623");
				Statement st = con.createStatement();
				String sql = "select * from user_info where user_id = '"+txtuserid.getText()+"' and pin = '"+Integer.parseInt(txtpin.getText())+"'";
				ResultSet rs= st.executeQuery(sql);
				if(rs.next()) 
					{
						ATM frame = new ATM(txtuserid.getText(),Integer.parseInt(txtpin.getText()));
						frame.setVisible(true);
						dispose();
					}
				else  JOptionPane.showMessageDialog(null,"Incorrect Credentials");
				con.close();
			}
			catch(Exception p) 
			{
				System.out.println(p);
			}
		}
		
	}
}
