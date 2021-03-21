package jvm.week01;

import java.io.File;
import java.io.FileInputStream;
import java.rmi.server.ExportException;

/**
 * Created at 2021-03-21 09:52
 * <p>
 * Description:
 * HelloXClassLoader
 *
 * @author hmxiao
 * @version 1.0
 */
public class HelloXClassLoader extends ClassLoader {
    public static void main(String[] args) {
        try {
            new HelloXClassLoader().findClass("Hello").newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }




    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            File f = new File("/Users/hmxiao/Documents/FWCode/javatrain/resource/Hello.xlass");
            int length = (int) f.length();
            byte[] data = new byte[length];
            new FileInputStream(f).read(data);

            for (int i = 0; i < data.length; i++) {
                data[i] = (byte) (255 - data[i]);
            }
            return defineClass(name, data, 0, data.length);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



}
