package backends;


import ui.MainFrame;

import java.io.IOException;
import java.io.InputStream;

import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private int max_num=400;
    private  ServerSocket server;
    private ExecutorService threadPool;
    private ArrayList[] cache;
    private MainFrame mainFrame;
    Table table;
    public Server()throws Exception{

        table=new Table();
        cache = new ArrayList[4];
        for (int i = 0; i < 4; i++) {
            cache[i] = new ArrayList<String>();
        }
        // 监听指定的端口
        int port = 8080;
        server = new ServerSocket(port);
        // server将一直等待连接的到来


        //如果使用多线程，那就需要线程池，防止并发过高时创建过多线程耗尽资源
        threadPool = Executors.newFixedThreadPool(20);
        this.mainFrame=new MainFrame();
    }
    public void ServerStart() throws Exception{
        System.out.println("路由器已经启动！");
        while (true) {


            Socket socket = server.accept();



            Runnable runnable=()->{
                try {
                    // 建立好连接后，从socket中获取输入流，并建立缓冲区进行读取
                    InputStream inputStream = socket.getInputStream();
                    byte[] bytes = new byte[1024];
                    int len;
                    StringBuilder sb = new StringBuilder();
                    while ((len = inputStream.read(bytes)) != -1) {
                        // 注意指定编码格式，发送方和接收方一定要统一，建议使用UTF-8
                        sb.append(new String(bytes, 0, len, "UTF-8"));
                    }
                    String content = new String(sb);

                    int target = content.charAt(0) - 48;
                    if(cache[target].size()<50)cache[target].add(content.substring(2));
                    //System.out.println(content.charAt(2)-48);
                    //System.out.println(content.substring(2));
                    String message = "";
                    target = content.charAt(2) - 49;
                    if (cache[target].size() > 0&&table.queryTable(target)) {
                        message += cache[target].get(0);
                        message += "\n";
                        cache[target].remove(0);
                    }
                    OutputStream outputStream = socket.getOutputStream();
                    outputStream.write(message.getBytes("UTF-8"));
                    inputStream.close();
                    outputStream.close();
                    socket.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            };
            threadPool.submit(runnable);

            max_num--;
            //if(max_num%40==0){
                mainFrame.update(cache);
            //}
        }

    }
    public void serverClose() throws Exception{
        this.server.close();
    }

    public static void main(String[] args) throws Exception{
        Server server=new Server();
        server.ServerStart();
    }
}
