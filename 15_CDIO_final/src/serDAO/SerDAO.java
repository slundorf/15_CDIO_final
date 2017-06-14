package serDAO;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.StreamCorruptedException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;

import dto.IngredientBatchDTO;
import dto.ProductBatchDTO;
import interfaces.IIngredientBatchDAO;

public abstract class SerDAO<E> {
	protected final String pathName;
	protected List<E> list = new ArrayList<E>();

	public SerDAO(String pathName) {
		this.pathName = helper(pathName);
	}

	public static String helper(String path) {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		URL t = classLoader.getResource(path);
		String f =t.getPath();
		String res=f.toString();
		res=res.replaceAll("%20", " ");
		String stream = classLoader.getResource(path).getPath().toString().replaceAll("%20", " ");
		System.out.println(stream);
		return stream;
	}

	/**
	 * Loads the data arraylist
	 */
	@SuppressWarnings("unchecked")
	protected void loadInfo() {

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
	 * saves the data arraylist to the .ser file.
	 */
	protected void saveInfo() {
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
