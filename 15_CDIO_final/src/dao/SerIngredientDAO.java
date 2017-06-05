package dao;

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

import dto.IngredientDTO;
import exeptions.DALException;
import interfaces.IIngredientDAO;

public class SerIngredientDAO implements IIngredientDAO {
	
	private List<IngredientDTO> ingredients = new ArrayList<IngredientDTO>();
	private final String pathName;
	
	public SerIngredientDAO(){
		pathName="IngredientDB.ser";
	}
	
	@Override
	public IngredientDTO getIngredient(int ingredientID) throws DALException {
			loadInfo();
			if (ingredients.size() == 0)
				throw new DALException("The database is empty.");
			for (int i = 0; i < ingredients.size(); i++) {
				if (ingredients.get(i).getIngredientID() == ingredientID) {
					return ingredients.get(i);
				}
			}
			throw new DALException("No ingredient has been found with id: " + ingredientID);
		}

	

	@Override
	public List<IngredientDTO> getIngredientList() throws DALException {
		loadInfo();
		if (ingredients.size() == 0)
			throw new DALException("There are no ingredients in the database.");
		return ingredients;
	}
	

	@Override
	public void createIngredient(IngredientDTO ingredient) throws DALException {
		loadInfo();
		ingredients.add(ingredient);
		saveInfo();
	}

	@Override
	public void updateIngredient(IngredientDTO ingredient) throws DALException {
		loadInfo();
		for (int i = 0; i < ingredients.size(); i++) {
			if (ingredient.getIngredientID() == ingredients.get(i).getIngredientID()) {
				ingredients.remove(i);
				ingredients.add(ingredient);
			}
		}
		saveInfo();
	}
		
	
	@SuppressWarnings("unchecked")
	public void loadInfo() {

		try {
			InputStream file = new FileInputStream(pathName);
			InputStream buffer = new BufferedInputStream(file);
			ObjectInput input = new ObjectInputStream(buffer);
			ingredients = (ArrayList<IngredientDTO>) input.readObject();
			if (ingredients.equals(null))
				ingredients = new ArrayList<IngredientDTO>();
			input.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (EOFException e) {
			ingredients = new ArrayList<IngredientDTO>();
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
	public void saveInfo() {
		try {
			OutputStream file = new FileOutputStream(pathName);
			OutputStream buffer = new BufferedOutputStream(file);
			ObjectOutput output = new ObjectOutputStream(buffer);
			// ObjectOutputStream oos = new ObjectOutputStream(new
			// FileOutputStream(new File("UserInfo.ser")));
			output.writeObject(ingredients);
			// close the writing.
			output.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
