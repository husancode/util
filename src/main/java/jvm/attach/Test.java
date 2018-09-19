package jvm.attach;
import java.util.List;

import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;
/**
 * tools.jar needs to be added to the IDE's library path and the program's classpath.
 * The tools.jar file is found in the JDK's lib directory.
 * it provider the AttachProvider 的windows实现sun.tools.attach.WindowsAttachProvider
 */
public class Test
{

    public static void main(String[] args)
    {
        List<VirtualMachineDescriptor> list = VirtualMachine.list();
        for (VirtualMachineDescriptor vmd : list)
        {
            System.out.println("pid:" + vmd.id() + ":" + vmd.displayName());
        }
    }

}
