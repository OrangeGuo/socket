package backends;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client implements Runnable {

    private int id;//进程ID
    private Socket socket;
    private OutputStream outputStream;
    private Thread thread;

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 4; i++) {
            new Client(i).start();
        }
    }

    public Client(int id) throws Exception {
        this.id = id;
    }

    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                this.socket = new Socket("127.0.0.1", 8080);
                OutputStream outputStream = socket.getOutputStream();
                int address = (int) (4 * Math.random());
                String message = address + ":" + this.id + ":" + this.socket.getLocalPort() + ":" + (i + 1) + "161530313";
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
                System.out.println("host " + this.id);
                System.out.println(sb);

                inputStream.close();
                outputStream.close();
                socket.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void start() {
        if (thread == null) {
            thread = new Thread(this);
            thread.start();
        }
    }
}
