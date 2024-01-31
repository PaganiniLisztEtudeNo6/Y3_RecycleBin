import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import javax.management.MalformedObjectNameException;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanException;

public class JavaApplicationMonitor {

    public static void main(String[] args) throws IOException {
        JMXServiceURL jmxServiceURL = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:9999/jmxrmi");

        JMXConnector jmxConnector = JMXConnectorFactory.connect(jmxServiceURL);

        MBeanServerConnection mBeanServerConnection = jmxConnector.getMBeanServerConnection();

        System.out.println("app Java comp: " + mBeanServerConnection.getDefaultDomain());

        try {
            ObjectName objectName = new ObjectName("java.lang:type=OperatingSystem");
            String version = (String) mBeanServerConnection.getMBeanInfo(objectName).getDescriptor()
                    .getFieldValue("version");
            System.out.println("app Java ver: " + version);
        } catch (MalformedObjectNameException e) {
            e.printStackTrace();
        } catch (InstanceNotFoundException e) {
            e.printStackTrace();
        } catch (MBeanException e) {
            e.printStackTrace();
        }

        // Set objectName for querying threadNames
        ObjectName threadObjectName = new ObjectName("java.lang:type=Threading");

        Set<ObjectName> threadNames = mBeanServerConnection.queryNames(threadObjectName, null);
        List<ObjectName> threadNamesList = new ArrayList<>(threadNames);

        for (ObjectName threadName : threadNamesList) {
            try {
                String threadNameStr = threadName.toString();
                String threadState = (String) mBeanServerConnection.getAttribute(threadName, "ThreadState");
                System.out.println("status effect : " + threadNameStr + " (status: " + threadState + ")");
            } catch (InstanceNotFoundException e) {
                e.printStackTrace();
            } catch (MBeanException e) {
                e.printStackTrace();
            }
        }

        // Set objectName for CPU and network usage
        ObjectName usageObjectName = new ObjectName("java.lang:type=OperatingSystem");

        try {
            double cpuUsage = (double) mBeanServerConnection.getAttribute(usageObjectName, "ProcessCpuLoad");
            System.out.println("CPU use : " + cpuUsage);

            long bytesSent = (long) mBeanServerConnection.getAttribute(usageObjectName, "BytesSent");
            long bytesReceived = (long) mBeanServerConnection.getAttribute(usageObjectName, "BytesReceived");
            System.out.println("network use: " + bytesSent + " bytes out , " + bytesReceived + " bytes in ");
        } catch (MalformedObjectNameException e) {
            e.printStackTrace();
        } catch (InstanceNotFoundException e) {
            e.printStackTrace();
        } catch (MBeanException e) {
            e.printStackTrace();
        }

        jmxConnector.close();
    }
}
