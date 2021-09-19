package GeekUniv;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

public class CustomClassLoader extends ClassLoader {
    public static void main(String[] args) throws Exception {

        final String className = "Hello";
        final String methodName = "hello";

        CustomClassLoader loader = new CustomClassLoader();
        Class<?> clazz = loader.loadClass(className);
        Object instance = clazz.getDeclaredConstructor().newInstance();
        Method method = clazz.getMethod(methodName);
        method.invoke(instance);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String resourcePath = name.replace(".", "/");
        final String suffix = ".xlass";
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(resourcePath + suffix);

        try {
            int length = inputStream.available();
            byte[] byteArray = new byte[length];
            inputStream.read(byteArray);
            byte[] classBytes = decode(byteArray);
            return defineClass(name, classBytes, 0, classBytes.length);
        } catch (IOException e) {
            throw new ClassNotFoundException(name, e);
        } finally {
            if(null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    private byte[] decode(byte[] byteArray) {
        byte[] targetArray = new byte[byteArray.length];
        for(int i = 0; i < byteArray.length; i++) {
            targetArray[i] = (byte)(255 - byteArray[i]);
        }
        return targetArray;
    }
}
