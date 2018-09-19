package javaagent;

import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;

import java.io.IOException;

public class AttachAPI {


    public static void main(String[] args) throws AttachNotSupportedException, IOException, AgentLoadException, AgentInitializationException {

        String processId = "12152";
        String jarFileName = "F:\\workspace\\util\\target\\util-0.0.1-SNAPSHOT.jar";
        VirtualMachine virtualMachine = VirtualMachine.attach(processId);
        try {
            virtualMachine.loadAgent(jarFileName, "World!");
        } finally {
            virtualMachine.detach();
        }
    }

}