import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.*; 

public class SerializationDemo {
 
    public static void main(String args[]) {
        //Object serialization 
        try {
            Class myclass1 = Class.forName("MyClass");
            System.out.println("myclass 1: " + myclass1.getName());
            FileOutputStream fos = new FileOutputStream("./Cat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(myclass1);
            oos.flush();
            oos.close();
        } catch (Exception e) {
			System.out.println("Serialization failed: " + e.toString());
        }
        //Object deserialization 
        try {
            Class myclass2;
            FileInputStream fis = new FileInputStream("./Cat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            myclass2 = (Class) ois.readObject();
            ois.close();
            System.out.println("myclass 2: " + myclass2.getName());
			Class[] params = new Class[3];
			params[0] = String.class;
			params[1] = Integer.TYPE;
			params[2] = Double.TYPE;

			Constructor constructor = myclass2.getConstructor(params);
			
			Object[] argObjs = new Object[3];
			argObjs[0] = "Mary";
			argObjs[1] = new Integer(10);
			argObjs[2] = new Double(2.0);

			Object obj = constructor.newInstance(argObjs);

			System.out.println(obj);
		
			Class[] funcParam = {String.class};
			Method setNameMethod = myclass2.getMethod("setName", funcParam);
			Object[] funcArgObjs = {"John"};
			setNameMethod.invoke(obj, funcArgObjs);
			System.out.println(obj);
			
			Method setMyValueMethod = myclass2.getDeclaredMethod("setMyValue", null);
			setMyValueMethod.setAccessible(true);
			setMyValueMethod.invoke(obj, null);

			System.out.println(obj);

			Method getMyValueMethod = myclass2.getMethod("getMyValue", null);
			Object ret = getMyValueMethod.invoke(obj);
			double retVal = (Double) ret;
			System.out.println("Return value: "+ retVal);
			// MyClass myclass = (MyClass) myclass2.newInstance();
        } catch (ClassNotFoundException e) {
            System.out.println("Cannot find class");
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            System.out.println("No such method");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (Exception e) {
			System.out.println("Deserialization failed: " + e.toString());
        }
    }
}

