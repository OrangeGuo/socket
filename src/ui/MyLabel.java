package ui;

import javax.swing.*;
import java.awt.*;

public class MyLabel extends JLabel {
    public int receive,send;
    public MyLabel(){
        receive=0;
        send=0;
        this.display("0");
    }

    public void display(String content){
        this.setText("接收:"+receive+" 发送:"+send+" 当前接收:"+content);
        this.setFont(new Font("Bitstream Vera Sans", Font.BOLD, 15));
    }
}
