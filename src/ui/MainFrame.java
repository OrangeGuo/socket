package ui;

import backends.Client;
import backends.Server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class MainFrame extends JFrame implements ActionListener {
    StorePanel[] storePanels;

    JMenuBar jMenuBar;
    JMenu setting;
    JMenuItem quit,clientController;
    public MainFrame()throws Exception{

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
        jMenuBar=new JMenuBar();
        setting=new JMenu("设置");

        quit=new JMenuItem("退出");

        clientController=new JMenuItem("启动主机");
        quit.addActionListener(this);

        clientController.addActionListener(this);

        setting.add(clientController);
        setting.add(quit);
        jMenuBar.add(setting);


        this.setJMenuBar(jMenuBar);
    }
    public void update(ArrayList[] arrayList){
        for (int i = 0; i < 4; i++) {
            this.storePanels[i].update(arrayList[i].size());
        }
    }
    public static void main(String[] args) throws Exception{
        MainFrame mainFrame=new MainFrame();
        //mainFrame.server.ServerStart(mainFrame);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource().equals(quit)){
            System.out.println("exit");
            System.exit(0);
        }else{
            for (int i = 0; i < 4; i++) {
                try {
                    new Client(i).start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
