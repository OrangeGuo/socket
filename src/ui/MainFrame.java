package ui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainFrame extends JFrame {
    StorePanel[] storePanels;
    public MainFrame(){
        this.storePanels=new StorePanel[4];
        this.setTitle("模拟路由器");
        this.setVisible(true);
        this.setResizable(false);
        this.setSize(600,450);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel jPanel=new JPanel();
        jPanel.setBackground(Color.white);
        jPanel.setLayout(new GridLayout(4,1));
        for (int i = 0; i < 4; i++) {
            this.storePanels[i]=new StorePanel(i,50);
            jPanel.add(this.storePanels[i]);
        }

        this.add(jPanel);
    }
    public void update(ArrayList[] arrayList){
        for (int i = 0; i < 4; i++) {
            this.storePanels[i].update(arrayList[i].size());
        }
    }
    public static void main(String[] args) {
        MainFrame mainFrame=new MainFrame();
    }
}
