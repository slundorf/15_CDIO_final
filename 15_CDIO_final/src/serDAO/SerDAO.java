package serDAO;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.List;


public abstract class SerDAO<E> {
	protected final String pathName;
	protected List<E> list = new ArrayList<E>();
	public SerDAO(String pathName){
		this.pathName=pathName;
	}
	
	
	/**
	 * Loads the user arraylist
	 */
	@SuppressWarnings("unchecked")
	public void loadInfo() {

		try {
			InputStream file = new FileInputStream(pathName);
			InputStream buffer = new BufferedInputStream(file);
			ObjectInput input = new ObjectInputStream(buffer);
			list = (ArrayList<E>) input.readObject();
			if (list.equals(null))
				list = new ArrayList<E>();
			input.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (EOFException e) {
			list = new ArrayList<E>();
		} catch (StreamCorruptedException e) {
			System.out.println("The file is currupted.");
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * saves the user arraylist to the .ser file.
	 */
	public void saveInfo() {
		try {
			OutputStream file = new FileOutputStream(pathName);
			OutputStream buffer = new BufferedOutputStream(file);
			ObjectOutput output = new ObjectOutputStream(buffer);
			// ObjectOutputStream oos = new ObjectOutputStream(new
			// FileOutputStream(new File("UserInfo.ser")));
			output.writeObject(list);
			// close the writer.
			output.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}
