package backends;

import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client extends JFrame implements Runnable {

    private int id;//进程ID
    private Socket socket;
    private OutputStream outputStream;
    private Thread thread;
    private MyLabel myLabel;

    public static void main(String[] args) {
        int id=0;
        Client client= null;
        try {
            client = new Client(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        client.start();
    }

    public Client(int id) throws Exception {
        this.id = id+1;
        this.myLabel=new MyLabel();
        this.setTitle("主机"+this.id);
        this.setVisible(true);
        this.setResizable(false);
        this.setSize(600,100);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel jPanel=new JPanel();
        jPanel.add(myLabel,BorderLayout.CENTER);
        this.add(jPanel);
    }



    public void run() {
        for (int i = 0; i < 100; i++) {
            String content = "";
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                this.socket = new Socket("127.0.0.1", 8080);
                OutputStream outputStream = socket.getOutputStream();
                int address = (int) (4 * Math.random());
                String message = address + ":" + this.id + ":" + this.socket.getLocalPort() + ":" + (i + 1) + ":161530313";
                socket.getOutputStream().write(message.getBytes("UTF-8"));
                //通过shutdownOutput高速服务器已经发送完数据，后续只能接受数据
                socket.shutdownOutput();
                InputStream inputStream = socket.getInputStream();
                byte[] bytes = new byte[1024];
                int len;
                StringBuilder sb = new StringBuilder();
                while ((len = inputStream.read(bytes)) != -1) {
                    //注意指定编码格式，发送方和接收方一定要统一，建议使用UTF-8
                    sb.append(new String(bytes, 0, len, "UTF-8"));
                }
//                System.out.println("host " + this.id);
//                System.out.println(sb);
                content = new String(sb);
                if (content.length() != 0)
                    this.myLabel.receive++;
                inputStream.close();
                outputStream.close();
                socket.close();
                this.myLabel.send++;


            } catch (IOException e) {
                e.printStackTrace();
            }
            FileWriter fileWriter = null;
            try {
                fileWriter = new FileWriter("host" + this.id + ".txt", true);
                fileWriter.write(content);
                if (fileWriter != null)
                    fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


            this.myLabel.display(content);

        }

    }

    public void start() {
        if (thread == null) {
            thread = new Thread(this);
            thread.start();
        }
    }
}
