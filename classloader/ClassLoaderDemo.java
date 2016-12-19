import java.util.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ClassLoaderDemo {
    public static void main(String[] args) {
        ClassLoaderTest test = new ClassLoaderTest();
 
 		System.out.println(test.getClass());					// getClass(): get name of a class
        ClassLoader loader = test.getClass().getClassLoader();	// [class].getClass().getClassLoader()

        System.out.println(loader);
        System.out.println(loader.getParent());
        System.out.println(loader.getParent().getParent());

		try{
			Class c = Class.forName( "java.util.ArrayList" );
			//Class c2 = Class.forName( "ClassLoaderTest2" );
			List list = (List)c.newInstance();		// create a new instance using loaded class c

			for(int i = 0; i < 5; i++) {
                list.add("element " + i);
            }
            for(Object o: list.toArray()) {
                System.out.println(o);
            }

		}
		catch(ClassNotFoundException e){
			System.out.println("Cannot find Class error: "+ e.toString());
		}
		catch(InstantiationException e){	//must catch for newInstance()
			e.printStackTrace();
		}
		catch(IllegalAccessException e){	//must catch for newInstance()
			e.printStackTrace();
		}
		catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("沒有指定類別載入路徑與名稱");
        }

		try {
            Class c = Class.forName("Student");

            Class[] params = new Class[2];
            params[0] = String.class;
            params[1] = Integer.TYPE;

            Constructor constructor = c.getConstructor(params);

            Object[] argObjs = new Object[2];
            argObjs[0] = "caterpillar";
            argObjs[1] = new Integer(90);

            Object obj = constructor.newInstance(argObjs);
            System.out.println(obj);

			Class[] param1 = {String.class};	// argument type array of target method. e.g. setName(String name) here
			Method setNameMethod = c.getMethod("setName", param1);
			Object[] argObjs1 = {"butterfly"};	// argument value array of target method
			setNameMethod.invoke(obj, argObjs1);

			Class[] param2 = {Integer.TYPE};
			Method setScoreMethod = c.getMethod("setScore", param2);
			Object[] argObjs2 = {80};
			setScoreMethod.invoke(obj, argObjs2);

			System.out.println(obj);
        } catch (ClassNotFoundException e) {
            System.out.println("找不到類別");
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            System.out.println("沒有所指定的方法");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
} 
