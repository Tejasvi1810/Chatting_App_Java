import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

import java.awt.event.*;
import java.io.*;
import java.sql.*;
import java.util.*;
import java.text.*;
import java.net.*;

// win1 server ki tarah act krega...
public class win1  implements ActionListener
{
	JTextField text;
	JPanel a1;
	static DataOutputStream dout;
	static Box vertical = Box.createVerticalBox();
	static JFrame f = new JFrame();
	
	win1()
	{
		f.setLayout(null);
		JPanel p1 = new JPanel();
		p1.setBackground(new Color(7,94,84));
		p1.setLayout(null);
		p1.setBounds(0,0,450,70);
		f.add(p1);
		
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("3.png"));
		Image i2 = i1.getImage().getScaledInstance(15,15,Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
		 JLabel back = new JLabel(i3);
		back.setBounds(15,20,25,25);
		p1.add(back);
		
		back.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent ae) {
				System.exit(0);
			}
		});
		
		ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("passport pic.jpg"));
		Image i5 = i4.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT);
		ImageIcon i6 = new ImageIcon(i5);
		 JLabel profile = new JLabel(i6);
		profile.setBounds(50,15,50,50);
		p1.add(profile);
		
		ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("video.png"));
		Image i8 = i7.getImage().getScaledInstance(25,25,Image.SCALE_DEFAULT);
		ImageIcon i9 = new ImageIcon(i8);
		 JLabel video = new JLabel(i9);
		video.setBounds(300,20,35,35);
		p1.add(video);
		
		
		
		ImageIcon i10 = new ImageIcon(ClassLoader.getSystemResource("phone.png"));
		Image i11 = i10.getImage().getScaledInstance(25,25,Image.SCALE_DEFAULT);
		ImageIcon i12 = new ImageIcon(i11);
		 JLabel phone = new JLabel(i12);
		phone.setBounds(350,20,35,35);
		p1.add(phone);
		
		ImageIcon i13 = new ImageIcon(ClassLoader.getSystemResource("3icon.png"));
		Image i14 = i13.getImage().getScaledInstance(10,25,Image.SCALE_DEFAULT);
		ImageIcon i15 = new ImageIcon(i14);
		 JLabel more = new JLabel(i15);
		more.setBounds(400,25,10,25);
		
		p1.add(more);
		
		JLabel name = new JLabel("Tejasvi");
		name.setBounds(110,15,100,25);
		name.setForeground(Color.WHITE);
		name.setFont(new Font("SAN_SERIF",Font.BOLD,18));
		p1.add(name);
		
		JLabel status = new JLabel("Active Now");
		status.setBounds(110,35,100,25);
		status.setForeground(Color.WHITE);
		status.setFont(new Font("SAN_SERIF",Font.BOLD,14));
		p1.add(status);
		a1 = new JPanel();
		a1.setBounds(5,75,440,570);
		f.add(a1);
		
		text = new JTextField();
		text.setBounds(5,520,300,40);
		text.setFont(new Font("SAN_SERIF",Font.PLAIN,16));
		f.add(text);
		
		JButton send = new JButton("Send");
		send.setBounds(320,510,100,30);
		send.setForeground(Color.WHITE);
		send.setBackground(new Color(7,94,84));
		send.addActionListener(this);
		f.add(send);
		
		
		
		
		
		
		
		
		
		
		
		
		
		f.setSize(450,600);
		f.setLocation(50,50);
		f.getContentPane().setBackground(Color.WHITE);
		// System.out.println("hii");
		f.setVisible(true);
		//f.setDefaultCloseOperation(EXIT_ON_CLOSE);
		  // JScrollPane textt = new JScrollPane(text);  
		// textt.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		// f.add(textt);

	}
	
	public static void main(String str[])
	{
		new win1();
		
		// server bna re hai 
		try
		{
			
			ServerSocket skt = new ServerSocket(6001);
			while(true)
			{
				Socket s = skt.accept(); // ye msg ko accept krke store krega 
				DataInputStream din = new DataInputStream(s.getInputStream());
				dout = new DataOutputStream(s.getOutputStream());
				
				while(true)
				{
					String msg = din.readUTF();
					JPanel panel = formatLabel(msg);
					
					JPanel left = new JPanel(new BorderLayout());
					left.add(panel,BorderLayout.LINE_START);
					vertical.add(left);
					f.validate();
					
			
				}
			}
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
	
		}
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		try{
		String out = text.getText();
		
		a1.setLayout(new BorderLayout());
		
		JPanel p2 = formatLabel(out);
		//p2.add(output);
		a1.setLayout(new BorderLayout());
		JPanel right = new JPanel(new BorderLayout());
		right.add(p2,BorderLayout.LINE_END);
		vertical.add(right);
		vertical.add(Box.createVerticalStrut(15));
		
		a1.add(vertical,BorderLayout.PAGE_START);
		
		dout.writeUTF(out);
		text.setText("");
		
		f.repaint();
		f.invalidate();
		f.validate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static JPanel formatLabel(String out)
	{
		JPanel panel = new JPanel();
		
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		JLabel output = new JLabel(out);
		output.setFont(new Font("Tahoma",Font.PLAIN,16));
		output.setBackground(new Color(37,211,102));
		output.setOpaque(true);
		output.setBorder(new EmptyBorder(15,15,15,50));
		
		panel.add(output);
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		
		JLabel time = new JLabel();
		time.setText(sdf.format(cal.getTime()));
		panel.add(time);
		
		return panel;
		
		
	}
		
}


