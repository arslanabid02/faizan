
package bank.management.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*; 
import java.time.LocalDate;


public class fastcash extends JFrame implements ActionListener {
    JButton back,deposit,withdraw,fastcash,ministatement,pinchange,balanceinquiry;
    String PinNumber,CardNumber;
fastcash( String CardNumber,String PinNumber){
    this.PinNumber = PinNumber;
    this.CardNumber = CardNumber;
getContentPane().setBackground(Color.WHITE);
//
//ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpeg"));
//Image i2 = i1.getImage().getScaledInstance(1280,720,Image.SCALE_DEFAULT);
//ImageIcon i3 = new ImageIcon(i2);
//JLabel image = new JLabel(i3);
//image.setBounds(0,0,1280,720);
//add(image);

JLabel topText = new JLabel("PLEASE SELECT YOUR Fastcash Amount");
topText.setFont(new Font("Courier",Font.BOLD,35));
topText.setBounds(500,40,500,50);
topText.setForeground(Color.BLACK);
add(topText);


ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/logo.jpeg"));
        Image i12 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i13 = new ImageIcon(i12);
        JLabel label = new JLabel(i13);
        label.setBounds(50,10,100,100);
        add(label);
        
         JLabel bName = new JLabel("Trust Bank");
        bName.setBounds(150,40,400,40);//3d Option for Text Length
        bName.setFont(new Font("GothamBlack", Font.BOLD, 30));
        add(bName);

 deposit = new JButton("Rs 100");
deposit.setBounds(355,155,200,40);
deposit.setBackground(Color.BLACK);
deposit.setForeground(Color.WHITE);
deposit.setFont(new Font("ariel",Font.BOLD,20));
deposit.addActionListener(this);
add(deposit);

 withdraw = new JButton("Rs 500");
withdraw.setBounds(655,155,200,40);
withdraw.setBackground(Color.BLACK);
withdraw.setForeground(Color.WHITE);
withdraw.setFont(new Font("ariel",Font.BOLD,20));
withdraw.addActionListener(this);
add(withdraw);

 fastcash = new JButton("Rs 1000");
fastcash.setBounds(355,215,200,40);
fastcash.setBackground(Color.BLACK);
fastcash.setForeground(Color.WHITE);
fastcash.setFont(new Font("ariel",Font.BOLD,20));
fastcash.addActionListener(this);
add(fastcash);

 ministatement = new JButton("Rs 5000");
ministatement.setBounds(655,215,200,40);
ministatement.setBackground(Color.BLACK);
ministatement.setForeground(Color.WHITE);
ministatement.setFont(new Font("ariel",Font.BOLD,20));
ministatement.addActionListener(this);
add(ministatement);

 pinchange = new JButton("Rs 10,000");
pinchange.setBounds(355,275,200,40);
pinchange.setBackground(Color.BLACK);
pinchange.setForeground(Color.WHITE);
pinchange.setFont(new Font("ariel",Font.BOLD,20));
pinchange.addActionListener(this);
add(pinchange);

 balanceinquiry = new JButton("Rs 20000");
balanceinquiry.setBounds(655,275,200,40);
balanceinquiry.setBackground(Color.BLACK);
balanceinquiry.setForeground(Color.WHITE);
balanceinquiry.setFont(new Font("ariel",Font.BOLD,20));
balanceinquiry.addActionListener(this);
add(balanceinquiry);

back = new JButton("Back");
back.setBounds(355,335,200,40);
back.setBackground(Color.BLACK);
back.setForeground(Color.WHITE);
back.setFont(new Font("ariel",Font.BOLD,20));
add(back);
back.addActionListener(this);

    setLayout(null);
    setSize(900,750);
    setLocation(250,0);
    setVisible(true);
    

}


   public void actionPerformed(ActionEvent ae){
     
    if(ae.getSource()==back){
       setVisible(false);
       new Transactions(CardNumber,PinNumber).setVisible(true);
    }
    else{
        String amount = ((JButton)ae.getSource()).getText().substring(3);
        Conn c = new Conn();
    try{
        ResultSet rs = c.s.executeQuery("select * from bank where CardNumber = '"+CardNumber+"' ");
   int balance = 0;
   while(rs.next()){
       if(rs.getString("type").equals("Deposit")){
           balance += Integer.parseInt(rs.getString("amount"));
       }
       else{
           balance-=Integer.parseInt(rs.getString("amount"));
       }
   }
   if(ae.getSource()!=back && balance<Integer.parseInt(amount)){
       JOptionPane.showMessageDialog(null,"Insufficient Balance");
    return;   
   }
  java.util.Date date = new java.util.Date(); 
    String query = "insert into bank values ('"+CardNumber+"','"+date+"','Deposit','"+amount+"')";
    c.s.executeUpdate(query);
           JOptionPane.showMessageDialog(null,"Rs "+amount+" Withdrawed from "+CardNumber);
            setVisible(false);
       new Transactions(CardNumber,PinNumber).setVisible(true);
    }
    catch(Exception e){
        System.out.print(e);
    }
    }
}  
    public static void main(String args[]) {
 new fastcash("","");
    }
}

