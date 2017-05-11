package entity;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class GEModel {
	
	public static Object read(String fileName) throws IOException, ClassNotFoundException {
		FileInputStream fileInputStream = new FileInputStream(fileName);   //os에 요청
		BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream); 
		ObjectInputStream objectInputStream = new ObjectInputStream(bufferedInputStream); 
		Object object = objectInputStream.readObject();
		objectInputStream.close();
		return object;
	}
	
	public static void save(String fileName, Object object) throws IOException {
		FileOutputStream fileOutputStream = new FileOutputStream(fileName);   //os에 요청
		BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream); 
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(bufferedOutputStream); 
		objectOutputStream.writeObject(object);
		objectOutputStream.close();
	}
}
