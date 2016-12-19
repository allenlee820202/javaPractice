import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
 
public class SerializationDemo {
 
    public static void main(String args[]) {
        //Object serialization 
        try {
            MyClass object1 = new MyClass("Hello", 32139604, 2.7e10);
            System.out.println("Object 1: " + object1);
            FileOutputStream fos = new FileOutputStream("./Cat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(object1);
            oos.flush();
            oos.close();
        } catch (Exception e) {
        }
        //Object deserialization 
        try {
            MyClass object2;
            FileInputStream fis = new FileInputStream("./Cat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            object2 = (MyClass) ois.readObject();
            ois.close();
            System.out.println("Object 2: " + object2);
        } catch (Exception e) {
        }
    }
}

