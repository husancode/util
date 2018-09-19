package javaagent;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

public class RunningProcess {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        System.out.println("Current processid = " + getCurrentThreadID());

        while(true) {
            try {
                System.out.println("I'm living...");
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }


    }

    /**
     * 获取当前进程ID
     */
    private static Integer getCurrentThreadID()
    {
        RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();
        String name = runtime.getName();
        return Integer.parseInt(name.substring(0, name.indexOf("@")));
    }

}