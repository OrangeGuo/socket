package ui;

import javax.swing.*;

public class MainFrame extends JFrame {
    public MainFrame(){
        this.setTitle("模拟路由器");
        this.setVisible(true);
        this.setResizable(false);
        this.setSize(600,450);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    public static void main(String[] args) {
        MainFrame mainFrame=new MainFrame();
    }
}
