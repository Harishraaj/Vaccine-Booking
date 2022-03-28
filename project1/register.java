package project1;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.JTextComponent;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneLayout;
import java.awt.Dimension;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class register {
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
	   //		Database Connection
		String url = "jdbc:mysql://localhost/vaccine";
		String uname = "root";
		String pass = "H@rish57";
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, uname, pass);
		
		JFrame f=new JFrame();
		JPanel j=new JPanel();	
		j.setSize(400,500); 
		
		
		
	//	JLabel lblNewLabel = new JLabel("");
	//    lblNewLabel.setIcon(new ImageIcon("C:\\Users\\harish\\Documents\\vac.jpeg"));
	//    lblNewLabel.setBounds(175, -12, 1084, 721);
	//    j.add(lblNewLabel);
	    
		
		
		JPanel log=new JPanel();
		log.setSize(400,500);
		log.setBackground(Color.GRAY);
		JPanel d=new JPanel();	
		d.setSize(400,500);
		
		JTabbedPane tp=new JTabbedPane();  
		tp.setBounds(0,0,200,200);  
		
		tp.add("Login",log);  
	   // tp.add("Register",j);  
		//tp.add("Details",d);   
		f.add(tp);  
		
		//login
		log.setLayout(null);
		JTextField g1;
		JLabel b1=new JLabel("USERNAME *"); 
		b1.setForeground(Color.white);
		b1.setBounds(10, 100, 200, 30);
        g1=new JTextField();
        g1.setToolTipText("Enter your username");
        g1.setBounds(150, 100, 200, 30);
        log.add(b1);
        log.add(g1);
        
        JLabel b2=new JLabel("PASSWORD *");
        b2.setBounds(10,150, 200, 30);
        JPasswordField g2=new JPasswordField();
        b2.setForeground(Color.white);
        g2.setToolTipText("Enter your password");
        g2.setBounds(150, 150, 200, 30);
		log.add(b2);
		log.add(g2);
		
		//CREATE ID 
		JButton create=new JButton("CREATE ID");
		create.setBounds(300, 250, 100, 30);
		log.add(create);	
		create.addActionListener(new ActionListener() {
	
			@Override
			public void actionPerformed(ActionEvent e) {
				
				JFrame adframe=new JFrame();
				
				JPanel panel = new JPanel();
				panel.setBackground(new Color(0, 139, 139));
				adframe.getContentPane().add(panel, BorderLayout.CENTER);
				panel.setLayout(null);
				
				JLabel lblNewLabel = new JLabel("USERNAME*");
				lblNewLabel.setBounds(28, 51, 78, 19);
				panel.add(lblNewLabel);
				
				JLabel lblNewLabel_1 = new JLabel("PASSWORD*");
				lblNewLabel_1.setBounds(28, 96, 78, 13);
				panel.add(lblNewLabel_1);
				
				JTextField textPane = new JTextField();
				textPane.setToolTipText("type your username");
				textPane.setBounds(116, 51, 137, 19);
				panel.add(textPane);
				
				JPasswordField passwordField = new JPasswordField();
				passwordField.setToolTipText("type your password");
				passwordField.setBounds(116, 93, 137, 19);
				panel.add(passwordField);
				
				JButton btnNewButton = new JButton("CREATE");
				btnNewButton.setBounds(135, 145, 85, 21);
				panel.add(btnNewButton);
				
				JPanel panel_1 = new JPanel();
				panel_1.setBackground(new Color(0, 206,209));
				panel_1.setBounds(10, 10, 389, 28);
				panel.add(panel_1);
				panel_1.setLayout(null);
				
				JLabel lblNewLabel_2 = new JLabel("SIGN UP ");
				lblNewLabel_2.setBounds(148, 0, 92, 28);
				panel_1.add(lblNewLabel_2);
				adframe.setVisible(true);
				
				btnNewButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						if(textPane.getText().trim().equals("") &&passwordField.getText().trim().equals(""))
						{
							JOptionPane.showMessageDialog(f,"PLEASE ENTER THE USERID AND PASSWORD");
						} 
						else
						{
						String query = "insert into signup (USERNAME,PWD)values(?,?);";
						PreparedStatement ps;
						try {
							ps = con.prepareStatement(query);
							ps.setString(1, textPane.getText());
							ps.setString(2,passwordField.getText());
							ps.executeUpdate();
							adframe.setVisible(false);
							JOptionPane.showMessageDialog(f,"USER ID CREATED SUCCESSFULLY"); 
							
						} catch (Exception e1) {
							System.out.println(e1);
						}finally {
							
						}
						}
						
					}
				} );
				
				
				
			}
		});
		
		
        //SEARCH
		
	    JPanel ad=new JPanel();
		ad.setSize(400,500);
		ad.setBackground(Color.gray);
		
		JLabel se=new JLabel("AADHAR NO* ");
		se.setBounds(10,100, 200,30);
		JTextField t21=new JTextField();  
		t21.setToolTipText("Enter your aadhar no");
		t21.setBounds(150,100, 200,30); 
		
		ad.add(se);
		ad.add(t21);
	//	tp.add("SEARCH ",ad);
		ad.setLayout(null);
		ad.setVisible(false);
		
		JButton check=new JButton("CHECK");  
	    check.setBounds(180,250,100,30);
		ad.add(check);
		check.addActionListener(new ActionListener() {
			
			@Override 
			public void actionPerformed(ActionEvent e) {
				
				String aadhar=t21.getText().toString(); 
				String sql ="SELECT * FROM reg WHERE AADHAR_NO=?";
				PreparedStatement pst;
				try {
					pst=con.prepareStatement(sql);
					pst.setString(1, aadhar);
					ResultSet rs=pst.executeQuery();
					int i=0;
					System.out.println("here1");
					while(rs.next())
					{
						String name=rs.getString("NAME");
						String age=rs.getString("AGE");
						String aadhar_no=rs.getString("AADHAR_NO");
						String mobile_no=rs.getString("MOBILE_NO");
						String vaccine=rs.getString("VACCINE");
						String dose=rs.getString("DOSE");
						String address=rs.getString("ADDRESS");
						String gender=rs.getString("GENDER");
						i++;
						String[][] sea={ {"NAME",name},    
		                          {"AGE",age}, 
		                          {"AADHAR NO",aadhar_no},
		                          {"MOBILE NO",mobile_no},
		                          {"DOSE",dose},
		                          {"VACCINE",vaccine},
		                          {"ADDRESS",address},
		                          {"GENDER",gender}
		                          };   
						  String column[]={"Key","Value"};         
						  JTable jsea=new JTable(sea,column); 
						  jsea.setBounds(30,400,300,200);         
						  ad.add(jsea); 
						  tp.repaint();
					}
					if(i<1)
					{
						System.out.println("RECORD NOT FOUND");
						JOptionPane.showMessageDialog(f,"Record NOT found");  
						
					}
					
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
				
		
			}
		});	
		
		
		
		
		JTree adtree = new JTree();
		adtree.setBackground(new Color(135, 206, 250));
		adtree.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode("ADMIN") {
				{
					DefaultMutableTreeNode node_1;
					node_1 = new DefaultMutableTreeNode("LOGIN");
						node_1.add(new DefaultMutableTreeNode("signin"));
					add(node_1);
					node_1 = new DefaultMutableTreeNode("SEARCH");
						node_1.add(new DefaultMutableTreeNode("record"));
					add(node_1);
				}
			}
		));
		adtree.setBounds(600, 150, 150, 130);
		log.add(adtree);
		

		adtree.addTreeSelectionListener(new TreeSelectionListener() {
			
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				// TODO Auto-generated method stub
				 DefaultMutableTreeNode node = (DefaultMutableTreeNode)adtree.getLastSelectedPathComponent();
				 String selected=node.toString();
				 if(selected.equals("signin"))
				 {
					 //JOptionPane.showMessageDialog(f,"hi");
					 JTable table;
					JTextField textField;
					 JFrame adminframe=new JFrame();	
						JTabbedPane adtpad=new JTabbedPane();  
						adtpad.setBounds(0,0,200,200);    
						adminframe.getContentPane().add(adtpad);  
						JPanel adlog=new JPanel();
						adlog.setBackground(new Color(173, 216, 230));
						adtpad.add("ADMIN",adlog);
						//login
						JTextField adg1;
						JLabel adb1=new JLabel("USERNAME *");
						adb1.setBounds(48, 53, 80, 13);
				        adg1=new JTextField();
				        adg1.setBounds(136, 50, 135, 19);
				        adg1.setToolTipText("Enter your username");
				        adlog.setLayout(null);
				        adlog.add(adb1);
				        adlog.add(adg1);
				        
				        JLabel adb2=new JLabel("PASSWORD *");
				        adb2.setBounds(48, 86, 88, 13);
				        JPasswordField adg2=new JPasswordField();
				        adg2.setBounds(136, 83, 135, 19);
				       // b2.setForeground(Color.white);
				        adg2.setToolTipText("Enter your password");
						adlog.add(adb2);
						adlog.add(adg2);
						
						//ADMIN LOGIN 
						JButton adlogin=new JButton("LOGIN");
						adlogin.setBounds(149, 147, 88, 21);
						adlog.add(adlogin);	
						
						JPanel adpanel = new JPanel();
						adpanel.setBounds(23, 0, 340, 28);
						adpanel.setBackground(new Color(147, 112, 219));
						adlog.add(adpanel);
						adpanel.setLayout(null);
						
						JLabel adlblNewLabel = new JLabel("ADMIN LOGIN");
						adlblNewLabel.setBounds(115, 5, 107, 13);
						adpanel.add(adlblNewLabel);
						
						

						adminframe.setVisible(true);
						JPanel details=new JPanel();
						details.setBackground(new Color(173, 216, 230));
						
						details.setLayout(null);
						
						//INSERT
						JButton btnNewButton = new JButton("Insert");
						btnNewButton.setBounds(31, 41, 85, 21);
						details.add(btnNewButton);
						btnNewButton.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								// TODO Auto-generated method stub
								tp.add("Register",j); 
								tp.setSelectedIndex(1);
								//tp.add("Details",d); 
								f.add(tp);
								f.setVisible(true);
							}
						});
						
						DefaultTableModel model = new DefaultTableModel();
						table = new JTable(model);
						table.setBounds(31, 163, 700, 180);
						model.addColumn("NAME");
				        model.addColumn("AGE");
				        model.addColumn("AADHAR_NO");
				        model.addColumn("MOBILE_NO");
				        model.addColumn("VACCINE");
				        model.addColumn("DOSE");
				        model.addColumn("ADDRESS");
				        model.addColumn("GENDER");
				        String query="SELECT * FROM reg;";
				        PreparedStatement pstm;
				        try {
				        	
							pstm=con.prepareStatement(query);
							 ResultSet Rs = pstm.executeQuery();
					            while(Rs.next()){
					                model.addRow(new Object[]{Rs.getString(1), Rs.getString(2),Rs.getString(3),Rs.getString(4),Rs.getString(5), Rs.getString(6),Rs.getString(7),Rs.getString(8)});
					            }
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
			           
				      //  JScrollPane pg = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
				        details.add(table);
				       // pg.setEnabled(true);
				      //  pg.setVisible(true);
				        
				        
				        
				        
					  
						textField = new JTextField();
						textField.setBounds(411, 90, 181, 19);
						details.add(textField);
						textField.setColumns(10);
						
						JLabel lblNewLabel = new JLabel("AADHAR NO*");
						lblNewLabel.setBounds(325, 93, 132, 13);
						details.add(lblNewLabel);
						
						
						//DELETE RECORD
						JButton btnNewButton_1 = new JButton("Delete");
						btnNewButton_1.setBounds(31, 85, 85, 21);
						details.add(btnNewButton_1);
						
						btnNewButton_1.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								String aadharno=textField.getText().toString();
								String sql ="DELETE FROM reg WHERE AADHAR_NO=?;";
								PreparedStatement pst;
								try {
									
									pst=con.prepareStatement(sql);
									pst.setString(1,aadharno);
									pst.executeUpdate();
									System.out.println("here1");
									JOptionPane.showMessageDialog(adminframe,"RECORD DELETED");
									
								} catch (Exception e2) {
									// TODO: handle exception
								}
							}
						});
						
						
						
						
						JPanel panel = new JPanel();
						panel.setBackground(new Color(25, 25, 112));
						panel.setBounds(178, 10, 414, 52);
						details.add(panel);
						panel.setLayout(null);
						
						JLabel lblNewLabel_1 = new JLabel("PATIENTS RECORDS");
						lblNewLabel_1.setForeground(new Color(255, 255, 255));
						lblNewLabel_1.setBounds(133, 0, 246, 52);
						panel.add(lblNewLabel_1);
						lblNewLabel_1.setBackground(new Color(255, 255, 255));
						//adtpad.setSelectedIndex(1);
						details.setVisible(false);
						adlogin.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								// TODO Auto-generated method stub
								if(adg1.getText().equals("hari") && adg2.getText().equals("123"))
								{
									//JPanel details=new JPanel();
									//adtpad.add("DETAILS",details);
									
									adtpad.add("DETAILS",details);
									adtpad.setSelectedIndex(1);
									details.setVisible(true);
								}
								else
								{
									JOptionPane.showMessageDialog(adminframe,"invalid username or password");
								}
							}
						});
						

				 }
				 else if(selected.equals("record"))
				 {
					 ad.setVisible(true);
					 tp.add("SEARCH ",ad);
					 tp.setSelectedIndex(1);
					 
				 }
				 else
				 {
					
				 }
			}
		});
		//USER
		
		JButton x=new JButton("LOGIN");  
	    x.setBounds(180,250,100,30);
		log.add(x);
		
		 JLabel lbNewLabel = new JLabel("");
		    lbNewLabel.setBackground(Color.white);
		    lbNewLabel.setIcon(new ImageIcon("C:\\Users\\harish\\Documents\\lgnew.jpg"));
		    lbNewLabel.setBounds(0, 0, 1500, 800);
		    log.add(lbNewLabel);
		    
		x.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String loguser=g1.getText().toString();
				String logpass=g2.getText().toString(); 
				String query="select * from signup where USERNAME=? && PWD=?;";
	            PreparedStatement ps;
	            try {
					ps = con.prepareStatement(query);
					ps.setString(1, loguser);
					ps.setString(2,logpass);
					ResultSet rs=ps.executeQuery();
					if(rs.next())
					{
						tp.add("Register",j); 
						tp.setSelectedIndex(1);
						//tp.add("Details",d); 
						f.add(tp);
					}
					else
					{
						JOptionPane.showMessageDialog(f,"invalid username or password");
					}
					
					//LOGIN DETAILS
					String query1 = "insert into logdet (USERID,PASSWD,LOG_TIME)values(?,?,?);";
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
					LocalDateTime now = LocalDateTime.now();  
					String date=dtf.format(now);
					try {
						ps=con.prepareStatement(query1);
						ps.setString(1, loguser);
						ps.setString(2,logpass);
					    ps.setString(3, date);
					    ps.executeUpdate();
					    
					} catch (Exception e2) {
						// TODO: handle exception
					}
					
				} catch (Exception e1) {
					System.out.println(e1);
					
				}finally {
					
				}
				
			
			/*	if(g1.getText().equals("hari") && g2.getText().equals("123"))
				{
					tp.add("Register",j); 
					tp.setSelectedIndex(1);
					//tp.add("Details",d); 
					f.add(tp);
				}
				else
				{
					JOptionPane.showMessageDialog(f,"invalid username or password");
				}*/
			}
		});
        
   
		
		
		//ADMIN
	//	JButton admin=new JButton("ADMIN");
	//	admin.setBounds(10, 250, 100, 30);
	//	log.add(admin);	 
		
		
		

        
		JLabel l1=new JLabel("COVID-19 VACCINE REGISTRATION",JLabel.RIGHT);
		l1.setForeground(Color.yellow);
		l1.setBounds(0,25,800,100);
		l1.setFont(new Font(null, Font.BOLD, 24));
		j.add(l1);
		j.setLayout(null);
		
	    
		JTextField t1,t2,t3,t4;

		JLabel l3=new JLabel("NAME *");
		l3.setForeground(Color.white);
		l3.setBounds(10,100,200,30);
		t1=new JTextField(); 
		t1.setToolTipText("Enter your name");
		t1.setBounds(150,100, 200,30); 
		j.add(l3);
		j.add(t1);
			
		
		JLabel l4=new JLabel("AGE *");
		l4.setForeground(Color.white);
		l4.setBounds(10,150,200,30);
		t2=new JTextField();  
		t2.setToolTipText("Enter your age");
		t2.setBounds(150,150, 200,30); 
		j.add(l4);
		j.add(t2);  

		JLabel l5=new JLabel("AADHAR NO *");
		l5.setForeground(Color.white);
		l5.setBounds(10,200, 200,30);
		t3=new JTextField();
		t3.setToolTipText("Enter your aadhar no");
		t3.setBounds(150,200, 200,30);
		j.add(t3);
		j.add(l5);
		
		
		JLabel l6=new JLabel("MOBILE NO *");
		l6.setForeground(Color.white);
		l6.setBounds(10,250,200,30);
		t4=new JTextField();
		t4.setToolTipText("Enter your mobile no");
		t4.setBounds(150,250, 200,30);
		j.add(l6);
		j.add(t4);
		
		
		JLabel l10=new JLabel("ADDRESS *");
		l10.setForeground(Color.white);
	    l10.setBounds(500,200,200,30);
	    j.add(l10);
	    JTextField tex=new JTextField();
	    tex.setToolTipText("Enter your address");
	    tex.setBounds(600,200,200,30);
	    j.add(tex);
	    
		
		
		JLabel l7=new JLabel("VACCINE *");
		l7.setForeground(Color.white);
		l7.setBounds(10,300,200,30);
		String vac[]={"COVAXIN","COVISHIELD","SPUTNIC"}; 
		JComboBox cb=new JComboBox(vac);
		cb.setBounds(150,300,200,30);   
		j.add(l7);
		j.add(cb);
       
		JLabel l8=new JLabel("DOSE *");
		l8.setForeground(Color.white);
		l8.setBounds(10,400,200,30);
		JRadioButton c3=new JRadioButton("FIRST");
		c3.setActionCommand("FIRST");
		JRadioButton c4=new JRadioButton("SECOND");
		c4.setActionCommand("SECOND");
		ButtonGroup cg=new ButtonGroup();
		cg.add(c4);
		cg.add(c3);
		c3.setBounds(150,400, 200,30);
		c4.setBounds(150,450, 200,30);
		j.add(l8);
        j.add(c3);
        j.add(c4);
        
        
        
        JLabel l9=new JLabel("GENDER *");
        l9.setForeground(Color.white);
	    l9.setBounds(500,300,200,30);
	    j.add(l9);
	    JRadioButton c5=new JRadioButton("MALE");
	    c5.setActionCommand("Male");
	    JRadioButton c6=new JRadioButton("FEMALE");
	    c6.setActionCommand("FEMALE");
	    JRadioButton c7=new JRadioButton("OTHER");
	    c7.setActionCommand("OTHER");
	    ButtonGroup dg=new ButtonGroup();
	    dg.add(c5);
	    dg.add(c6);
	    dg.add(c7);
	    c5.setBounds(600,300,200,30);
	    c6.setBounds(600,350,200,30);
	    c7.setBounds(600,400,200,30);
	    j.add(c5);
	    j.add(c6);
	    j.add(c7);
	    
	    JCheckBox chckbxNewCheckBox = new JCheckBox("I agree the terms");
	    chckbxNewCheckBox.setBounds(600, 504, 167, 39);
	    j.add(chckbxNewCheckBox);
	    
	    JLabel lblNewLabel_2 = new JLabel("Note*");
	    lblNewLabel_2.setForeground(Color.yellow);
	    lblNewLabel_2.setBounds(500, 477, 45, 13);
	    j.add(lblNewLabel_2);
	    
	    JLabel lblNewLabel_3 = new JLabel("keep wearing your mask ");
	    lblNewLabel_3.setForeground(Color.CYAN);
	    lblNewLabel_3.setBounds(600, 468, 170, 30);
	    j.add(lblNewLabel_3);
	    
        
        JPanel p=new JPanel();
	    p.setLayout(null);
	    p.setBounds(0, 0, 100, 100);
		 
		JButton b=new JButton("REGISTER");  
	    b.setBounds(500,678,100,30); 
	    
	    b.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(t1.getText().trim().equals(""))
				{
				  JOptionPane.showMessageDialog(f,"Please enter your NAME");  
				}
				else if(t2.getText().trim().equals(""))
				{
				  JOptionPane.showMessageDialog(f,"Please enter your AGE");  
				}
				else if(t3.getText().trim().equals(""))
				{
				  JOptionPane.showMessageDialog(f,"Please enter your AADHAR N0");  
				}
				else if(t4.getText().trim().equals(""))
				{
				  JOptionPane.showMessageDialog(f,"Please enter your MOBILE N0");  
				}
				else if(tex.getText().trim().equals(""))
				{
				  JOptionPane.showMessageDialog(f,"Please enter your ADDRESS");  
				}
				else if(cg.isSelected(null))
				{
					JOptionPane.showMessageDialog(f, "please choose DOSE");
				}
				else if(dg.isSelected(null))
				{
					JOptionPane.showMessageDialog(f, "please choose GENDER");
				}
				else
				{	
				  JOptionPane.showMessageDialog(f,"Registration Successfull");
				  tp.add("Details",d); 
				  f.add(tp);
				  tp.setSelectedIndex(2);
			      f.setVisible(true);
			      d.removeAll();
			      
				  String[][] dis={ {"NAME",t1.getText()},    
                          {"AGE",t2.getText()}, 
                          {"AADHAR NO",t3.getText()},
                          {"MOBILE NO",t4.getText()},
                          {"DOSE",cg.getSelection().getActionCommand()},
                          {"VACCINE",cb.getSelectedItem().toString()},
                          {"ADDRESS",tex.getText()},
                          {"GENDER",dg.getSelection().getActionCommand()}
                          };   
				  String column[]={" "," "};         
				  JTable jt=new JTable(dis,column); 
				  jt.setBounds(10,20,100,100);          
				  JScrollPane sp=new JScrollPane(jt);    
				  d.add(sp);      
				  
				  JLabel lbd = new JLabel("");
			    d.setBackground(Color.lightGray);
			    lbd.setIcon(new ImageIcon("C:\\Users\\harish\\Documents\\details.png"));
			    lbd.setBounds(0, 0, 800, 800);
			    d.add(lbd);
	
			    	String query = "insert into reg (NAME,AGE,AADHAR_NO,MOBILE_NO,VACCINE,DOSE,ADDRESS,GENDER)values(?,?,?,?,?,?,?,?);";
					PreparedStatement ps;
					try {
						ps = con.prepareStatement(query);
						ps.setString(1, t1.getText());
						ps.setString(2,t2.getText());
						ps.setString(3,t3.getText() );
						ps.setString(4,t4.getText());
						ps.setString(5,cb.getSelectedItem().toString());
						ps.setString(6, cg.getSelection().getActionCommand());
						ps.setString(7,tex.getText());
						ps.setString(8,dg.getSelection().getActionCommand());
						ps.executeUpdate();
						
					} catch (Exception e1) {
						System.out.println(e1);
					}finally {
						
					}
	  
			    
				}
				
			}
		});
			
	    j.add(b);  
	 
	    JButton c=new JButton("CANCEL");  
	    c.setBounds(667,678,100,30); 
	    c.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			   JOptionPane.showMessageDialog(f,"Registration Cancelled");  
		       f.setVisible(true);
			}
		});
	    j.add(c);  
	    
	   
	    
	    
	    JLabel lblNewLabel = new JLabel("");
	    j.setBackground(Color.black);
	    lblNewLabel.setIcon(new ImageIcon("C:\\Users\\harish\\Documents\\vaccine.jpg"));
	    lblNewLabel.setBounds(500, 150, 1900, 700);
	    j.add(lblNewLabel);
	    
	    
	    JTree tree = new JTree();
	    tree.setModel(new DefaultTreeModel(
	    	new DefaultMutableTreeNode("vaccines available") {
	    		{
	    			DefaultMutableTreeNode node_1;
	    			node_1 = new DefaultMutableTreeNode("India");
	    				node_1.add(new DefaultMutableTreeNode("covaxin"));
	    				node_1.add(new DefaultMutableTreeNode("covishield"));
	    			add(node_1);
	    			node_1 = new DefaultMutableTreeNode("cannada");
	    				node_1.add(new DefaultMutableTreeNode("pfizer"));
	    				node_1.add(new DefaultMutableTreeNode("moderna"));
	    				node_1.add(new DefaultMutableTreeNode("covishield"));
	    			add(node_1);
	    			node_1 = new DefaultMutableTreeNode("upcoming ");
    				node_1.add(new DefaultMutableTreeNode("pfizer"));
    				node_1.add(new DefaultMutableTreeNode("moderna"));
    				node_1.add(new DefaultMutableTreeNode("sputnic V"));
    			add(node_1);
	    		}
	    	}
	    ));
	    tree.setBounds(891, 200, 200, 200);
	    j.add(tree);
	    
		
       f.setVisible(true);
       f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
	}

}
