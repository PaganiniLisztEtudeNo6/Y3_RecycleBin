import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.lang.management.ThreadMXBean;
import java.util.Date;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class CodeAnalyzer_v2_ClassLoader {
    public static void main(String[] args) {
        String jarFilePath = "RotatingRectangle3D.jar"; // jar ony !!!

        try {
            URLClassLoader classLoader = new URLClassLoader(new URL[] { new URL("file://" + jarFilePath) });

            String className = "RotatingRectangle3D";

            Class<?> clazz = classLoader.loadClass(className);

            Method[] methods = clazz.getDeclaredMethods();

            System.out.println("Methods in class : " + className + ":");
            for (Method method : methods) {
                System.out.print(method.getName() + "\n");
            }

            for (Method method : methods) {
                if (method.getName().equals("main")) {
                    Date startTime = new Date();

                    // argument string[] null
                    String[] mainArgs = new String[] {};
                    method.invoke(null, (Object) mainArgs);

                    Date endTime = new Date();
                    long executionTime = endTime.getTime() - startTime.getTime();

                    System.out.println("Used method : " + method.getName());
                    System.out.println("Time (milliseconds): " + executionTime);
                    System.out.println("---------------");
                }
            }

            // Memory JVM
            MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
            MemoryUsage heapMemoryUsage = memoryMXBean.getHeapMemoryUsage();

            System.out.println("Heap Memory Usage:");
            System.out.println("Used Memory: " + heapMemoryUsage.getUsed() / (1024 * 1024) + " MB");
            System.out.println("Max Memory: " + heapMemoryUsage.getMax() / (1024 * 1024) + " MB");
            System.out.println("---------------");

            // CPU
            ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
            long threadCpuTime = threadMXBean.getCurrentThreadCpuTime();

            System.out.println("Thread CPU Time (nanoseconds): " + threadCpuTime);

            classLoader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
