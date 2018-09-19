package btrace;

import java.io.*;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * </p>
 *
 * @author hukajie
 * @version V1.0
 * @Date 2018/5/31
 * @modify by user: {修改人}
 * @modify by reason:{方法名}:{原因}
 */
public class DebugObjectCreate {

    public void connect() throws IOException {
        Socket socket = new Socket("10.82.3.79",8080);
        //构建IO
        InputStream is = socket.getInputStream();
        OutputStream os = socket.getOutputStream();

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
        //向服务器端发送一条消息
        bw.write("测试客户端和服务器通信，服务器接收到消息返回到客户端\n");
        bw.flush();

        //读取服务器返回的消息
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String mess = br.readLine();
        System.out.println("服务器：" +mess);
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        DebugObjectCreate debugObjectCreate = new DebugObjectCreate();
        while(true){
            debugObjectCreate.connect();
            TimeUnit.SECONDS.sleep(5);
        }
    }
}
