package ui;

import backends.MyLabel;
import org.omg.SendingContext.RunTime;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class MainFrame extends JFrame implements ActionListener {
    StorePanel[] storePanels;

    JMenuBar jMenuBar;
    JMenu setting;
    JMenuItem quit,clientController;

    MyLabel[] labels;
    public MainFrame()throws Exception{

        this.storePanels=new StorePanel[4];
        labels=new MyLabel[4];
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
            labels[i]=new MyLabel();
            this.storePanels[i]=new StorePanel(i,50,labels[i]);
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
                   // new Client(i).start();
                    Runtime runtime=Runtime.getRuntime();
                    System.out.println(System.getProperty("user.dir"));
                    //Process p=runtime.exec("vi");
                    Process p=Runtime.getRuntime().exec(new String[]{"/bin/sh","-c","cd /home/orange/IdeaProjects/socket/src  && java backends.Client "+i});
                    //Process p =runtime.exec("bash -c cd /home/orange/IdeaProjects/socket/src \n & java backends.Client "+i+ "\n");
                    //ProcessBuilder processBuilder=new ProcessBuilder("java","backends.Client "+i);
                    //processBuilder.directory(new File("/home/orange/IdeaProjects/socket/src"));
                    //Process process=processBuilder.stcart();
                    //System.out.println("000");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
