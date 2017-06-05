package dao;

import java.util.List;
import dto.RecipeDTO;
import exceptions.DALException;
import interfaces.IRecipeDAO;

public class SerRecipeDAO extends SerDAO<RecipeDTO> implements IRecipeDAO{ 
	
	public SerRecipeDAO(){
		super("SerFiles/RecipeDB.ser");
	}
	
	@Override
	public RecipeDTO getRecipe(int recipeID) throws DALException {
		loadInfo();
		if(list.size() == 0)
			throw new DALException("The database is empty.");
		for(int i = 0; i < list.size(); i++) {
			if (list.get(i).getRecipeID() == recipeID) {
				return list.get(i);
			}
		}
		throw new DALException("No recipe has been found with id: " + recipeID);
	}

	@Override
	public List<RecipeDTO> getRecipeList() throws DALException {
		loadInfo();
		if(list.size() == 0)
			throw new DALException("There are no recepies in this database");
		return list;
	}

	@Override
	public void createRecipe(RecipeDTO recipe) throws DALException {
		loadInfo();
		list.add(recipe);
		saveInfo();
	}

	@Override
	public void updateRecipe(RecipeDTO recipe) throws DALException {
		loadInfo();
		for(int i = 0; i< list.size(); i++) {
			if(recipe.getRecipeID() == list.get(i).getRecipeID()) {
				list.remove(i);
				list.add(recipe);
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
