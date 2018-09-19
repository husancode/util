
package net;

import org.apache.commons.net.telnet.TelnetClient;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.SocketException;

/**
 * <p>
 * </p>
 *
 * @author hukajie
 * @version V1.0
 * @modification History=========================逻辑或功能性重大变更记录
 * @modify by user: {修改人}
 * @modify by reason:{方法名}:{原因}
 */
public class TelnetClientCase {

    public static void sentCommand(PrintStream pStream, String command){
        pStream.println(command);
        pStream.flush();
    }

    public static String readMessage(InputStream inputStream, byte[] buffer){
        try {
            int size = inputStream.read(buffer);
            if(-1 != size){
                return new String(buffer,0,size);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args){
        try {
            TelnetClient telnetClient = new TelnetClient("ANSI");  //指明Telnet终端类型
            telnetClient.setDefaultTimeout(10000); //socket延迟时间
            telnetClient.connect("10.82.1.59", 21880);  //建立一个连接
            InputStream inputStream = telnetClient.getInputStream(); //读取命令的流
            PrintStream pStream = new PrintStream(telnetClient.getOutputStream());  //写命令的流
            byte[] b = new byte[1024];

            sentCommand(pStream, "ps");
            String resp1 = readMessage(inputStream, b);
            System.out.println(resp1);
            sentCommand(pStream, "ls");
            resp1 = readMessage(inputStream, b);
            System.err.println(resp1.contains("CloudStorageBusinessService"));
            System.out.println(resp1);
            sentCommand(pStream, "status");
            resp1 = readMessage(inputStream, b);
            System.out.println(resp1);
          /*  String command = "invoke CloudStorageBusinessService.openCloudServiceByBusinessProductCode('b7765f9b899a455d98c25ed93a9b6e9f900112112','YS7_WEB','0','husan','S00000000',1,'1000000012',1,-1,false)";
            sentCommand(pStream, command);
            System.out.println(readMessage(inputStream, b));*/

            if(null != pStream) {
                pStream.close();
            }
            telnetClient.disconnect();
        } catch (SocketException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
