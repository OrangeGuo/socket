package ui;

import javax.swing.*;
import java.awt.*;

public class StorePanel extends JPanel {
    private JProgressBar jProgressBar=null;
    private int max;
    public StorePanel(int id,int max){

        this.max=max;
        this.setLayout(new GridLayout(1,2));
        this.jProgressBar=new JProgressBar();
        jProgressBar.setStringPainted(true);
        jProgressBar.setFont(new Font("Bitstream Vera Sans", Font.BOLD, 20));
        jProgressBar.setString("0/"+max);
        jProgressBar.setValue(0);
        this.add(new JLabel("host "+(id+1)),0);
        this.add(this.jProgressBar,1);
        this.setBackground(Color.lightGray);
    }
    public void update(int num){
        this.jProgressBar.setString(num+"/"+max);
        this.jProgressBar.setValue(num*100/max);
    }
}
