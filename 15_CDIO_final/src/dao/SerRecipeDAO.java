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

import dto.RecipeDTO;
import exceptions.DALException;
import interfaces.IRecipeDAO;

public class SerRecipeDAO implements IRecipeDAO{
	
	private List<RecipeDTO> recepies = new ArrayList<RecipeDTO>();
	private final String pathName;

	public SerRecipeDAO(){
		pathName="RecipeDB.ser";
	}
	/**
	 * 
	 * @param recipeID
	 * @return Recipe with specified recipeID
	 * @throws DALException
	 */
	@Override
	public RecipeDTO getRecipe(int recipeID) throws DALException {
		loadInfo();
		if(recepies.size() == 0)
			throw new DALException("The database is empty.");
		for(int i = 0; i < recepies.size(); i++) {
			if (recepies.get(i).getRecipeID() == recipeID) {
				return recepies.get(i);
			}
		}
		throw new DALException("No recipe has been found with id: " + recipeID);
	}
	/**
	 * 
	 * @return A list of all recepies
	 * @throws DALException
	 */
	@Override
	public List<RecipeDTO> getRecipeList() throws DALException {
		loadInfo();
		if(recepies.size() == 0)
			throw new DALException("There are no recepies in this database");
		return recepies;
	}
	/**
	 * Creates the recipe given as parameter.
	 * @param recipe
	 * @throws DALException
	 */
	
	@Override
	public void createRecipe(RecipeDTO recipe) throws DALException {
		loadInfo();
		recepies.add(recipe);
		saveInfo();
	}
	/**
	 * Updates the recipe, given as parameter.
	 * @param recipe
	 * @throws DALException
	 */
	@Override
	public void updateRecipe(RecipeDTO recipe) throws DALException {
		loadInfo();
		for(int i = 0; i< recepies.size(); i++) {
			if(recipe.getRecipeID() == recepies.get(i).getRecipeID()) {
				recepies.remove(i);
				recepies.add(recipe);
			}
		}
	}
	/**
	 * Loads the recipe arraylist
	 */
	@SuppressWarnings("unchecked")
	public void loadInfo() {

		try {
			InputStream file = new FileInputStream(pathName);
			InputStream buffer = new BufferedInputStream(file);
			ObjectInput input = new ObjectInputStream(buffer);
			recepies = (ArrayList<RecipeDTO>) input.readObject();
			if (recepies.equals(null))
				recepies = new ArrayList<RecipeDTO>();
			input.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (EOFException e) {
			recepies = new ArrayList<RecipeDTO>();
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
	 * saves the recipe arraylist to the .ser file.
	 */
	public void saveInfo() {
		try {
			OutputStream file = new FileOutputStream(pathName);
			OutputStream buffer = new BufferedOutputStream(file);
			ObjectOutput output = new ObjectOutputStream(buffer);
			// ObjectOutputStream oos = new ObjectOutputStream(new
			// FileOutputStream(new File("UserInfo.ser")));
			output.writeObject(recepies);
			// close the writing.
			output.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
