package ui;

import javax.swing.*;
import java.awt.*;

public class StorePanel extends JPanel {
    private JProgressBar jProgressBar=null;
    private JLabel jLabel=null;
    private int max;
    public StorePanel(int id,int max,MyLabel myLabel){

        this.max=max;
        //this.setLayout(new GridLayout(1,2));
        this.jProgressBar=new JProgressBar();
        this.jProgressBar.setPreferredSize(new Dimension(500,20));

        jProgressBar.setStringPainted(true);
        jProgressBar.setFont(new Font("Bitstream Vera Sans", Font.BOLD, 20));
        jProgressBar.setString("0/"+max);
        jProgressBar.setValue(0);
        this.jLabel=new JLabel("端口 "+(id+1)+"存储器");
        this.jLabel.setForeground(Color.blue);
        this.jLabel.setFont(new Font("Bitstream Vera Sans", Font.BOLD, 20));
        this.add(this.jLabel,BorderLayout.NORTH);
        this.add(this.jProgressBar);
        this.add(myLabel,BorderLayout.SOUTH);

    }
    public void update(int num){
        this.jProgressBar.setString(num+"/"+max);
        this.jProgressBar.setValue(num*100/max);
    }
}
