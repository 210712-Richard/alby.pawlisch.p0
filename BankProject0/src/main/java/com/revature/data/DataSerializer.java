package com.revature.data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class DataSerializer<T> {
	
	public List<T> readMoneyFromFile(String filename) {
		List<T> money = null;
		try(ObjectInputStream o = new ObjectInputStream(new FileInputStream(filename));){
			money = (ArrayList<T>) o.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return money;
	}

	public void writeMoneyToFile(List<T> money, String filename) {
		try (ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(filename));){
			o.writeObject(money);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
