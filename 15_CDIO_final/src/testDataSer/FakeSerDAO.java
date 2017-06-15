package testDataSer;

import java.util.ArrayList;
import java.util.List;

import dto.IngredientBatchDTO;
import dto.IngredientDTO;
import dto.ProductBatchComponentDTO;
import dto.ProductBatchDTO;
import dto.RecipeComponentDTO;
import dto.RecipeDTO;
import dto.RoleDTO;
import dto.UserDTO;
import exceptions.DALException;

public abstract class FakeSerDAO<E> {
	protected String pathName;
	protected List<E> list = new ArrayList<E>();
	protected List<E> writeObjectList = new ArrayList<E>();
	protected static List<IngredientBatchDTO> ingredientBatchList = new ArrayList<IngredientBatchDTO>();
	protected static List<IngredientDTO> ingredientList = new ArrayList<IngredientDTO>();
	protected static List<ProductBatchDTO> productBatchList = new ArrayList<ProductBatchDTO>();
	protected static List<RecipeDTO> recipeList = new ArrayList<RecipeDTO>();
	protected static List<RoleDTO> roleList = new ArrayList<RoleDTO>();
	protected static List<UserDTO> userList = new ArrayList<UserDTO>();

	static {

		// IngredientBatch data

		IngredientBatchDTO IB1 = new IngredientBatchDTO(20, 1, 25);
		IngredientBatchDTO IB2 = new IngredientBatchDTO(21, 2, 50);
		IngredientBatchDTO IB3 = new IngredientBatchDTO(22, 2, 60);
		IngredientBatchDTO IB4 = new IngredientBatchDTO(23, 3, 10);

		// Saves it on DAO

		ingredientBatchList.add(IB1);
		ingredientBatchList.add(IB2);
		ingredientBatchList.add(IB3);
		ingredientBatchList.add(IB4);

		// Ingredients data

		IngredientDTO I1 = new IngredientDTO(1, "Salt", "supplier1");
		IngredientDTO I2 = new IngredientDTO(2, "Water", "supplier2");
		IngredientDTO I3 = new IngredientDTO(3, "Lemon", "supplier3");

		// Saves it on DAO

		ingredientList.add(I1);
		ingredientList.add(I2);
		ingredientList.add(I3);

		// Create productbatchComponents first then creates productbatches with
		// the productBatchComponentList inside.

		// ProductBatchcomponent
		ProductBatchComponentDTO PBC1 = new ProductBatchComponentDTO(41, 1);
		ProductBatchComponentDTO PBC2 = new ProductBatchComponentDTO(41, 2);
		ProductBatchComponentDTO PBC3 = new ProductBatchComponentDTO(42, 3);
		ProductBatchComponentDTO PBC4 = new ProductBatchComponentDTO(43, 3);

		// List with productbatchcomponents
		List<ProductBatchComponentDTO> tempPBCList1 = new ArrayList<ProductBatchComponentDTO>();
		List<ProductBatchComponentDTO> tempPBCList2 = new ArrayList<ProductBatchComponentDTO>();
		List<ProductBatchComponentDTO> tempPBCList3 = new ArrayList<ProductBatchComponentDTO>();

		tempPBCList1.add(PBC1);
		tempPBCList1.add(PBC2);
		tempPBCList2.add(PBC3);
		tempPBCList3.add(PBC4);

		// Create productbatch
		ProductBatchDTO PB1 = new ProductBatchDTO(41, 31, "090693", "Created", tempPBCList1);
		ProductBatchDTO PB2 = new ProductBatchDTO(42, 32, "100693", "Created", tempPBCList2);
		ProductBatchDTO PB3 = new ProductBatchDTO(43, 32, "110693", "Created", tempPBCList3);

		// Saves it on DAO
		productBatchList.add(PB1);
		productBatchList.add(PB2);
		productBatchList.add(PB3);

		// Create recipeComponents first then creates recipes with the
		// recipecomponentList inside.

		// Create RecipeComponent
		RecipeComponentDTO RC1 = new RecipeComponentDTO(31, 1, 0.2, 0.1);
		RecipeComponentDTO RC2 = new RecipeComponentDTO(31, 2, 2, 0.1);
		RecipeComponentDTO RC3 = new RecipeComponentDTO(32, 3, 1, 0.5);

		// List with RecipeComponent
		List<RecipeComponentDTO> tempRCList1 = new ArrayList<RecipeComponentDTO>();
		List<RecipeComponentDTO> tempRCList2 = new ArrayList<RecipeComponentDTO>();

		tempRCList1.add(RC1);
		tempRCList1.add(RC2);
		tempRCList2.add(RC3);

		// Create Recipes
		RecipeDTO R1 = new RecipeDTO(31, "SaltWater", tempRCList1);
		RecipeDTO R2 = new RecipeDTO(32, "Lemon", tempRCList2);

		// Saves it on DAO
		recipeList.add(R1);
		recipeList.add(R2);

		// Create Roles
		RoleDTO role1 = new RoleDTO(1, "Administator");
		RoleDTO role2 = new RoleDTO(2, "Pharmacist");
		RoleDTO role3 = new RoleDTO(3, "Foreman");
		RoleDTO role4 = new RoleDTO(4, "Operator");

		// Saves it on DAO
		roleList.add(role1);
		roleList.add(role2);
		roleList.add(role3);
		roleList.add(role4);
		
		// Create Users
		UserDTO user1 = new UserDTO(11, "Steve", "STV", "101011-1234", "e6+Easdasd1", role4, true);
		UserDTO user2 = new UserDTO(12, "Kurt", "Kru", "101112-1234", "e6+Easdasd2", role1, true);
		UserDTO user3 = new UserDTO(13, "Lis", "LIS", "101113-1234", "e6+Easdasd3", role2, false);

		// Saves it on DAO
		userList.add(user1);
		userList.add(user2);
		userList.add(user3);
	}

	public FakeSerDAO(String pathName) {
		this.pathName = pathName;
	}

	/**
	 * Loads the data arraylist
	 * 
	 * @throws DALException
	 */
	@SuppressWarnings("unchecked")
	protected void loadInfo() {

		// InputStream file = new FileInputStream(pathName);
		// InputStream buffer = new BufferedInputStream(file);
		// ObjectInput input = new ObjectInputStream(buffer);

		if (pathName.equals("UserDB.ser")) {
			list = (ArrayList<E>) userList;

		} else if (pathName.equals("IngredientBatchDB.ser")) {

			list = (ArrayList<E>) ingredientBatchList;

		} else if (pathName.equals("IngredientDB.ser")) {

			list = (ArrayList<E>) ingredientList;

		} else if (pathName.equals("ProductBatchDB.ser")) {

			list = (ArrayList<E>) productBatchList;

		} else if (pathName.equals("RecipeDB.ser")) {

			list = (ArrayList<E>) recipeList;

		} else if (pathName.equals("RoleDB.ser")) {

			list = (ArrayList<E>) roleList;

		}

		if (list.equals(null)) {
			list = new ArrayList<E>();
		}

	}

	/**
	 * saves the data arraylist to the .ser file.
	 */
	protected void saveInfo() {

		if (pathName.equals("UserDB.ser")) {
			userList = (List<UserDTO>) list;

		} else if (pathName.equals("IngredientBatchDB.ser")) {

			ingredientBatchList = (List<IngredientBatchDTO>) list;

		} else if (pathName.equals("IngredientDB.ser")) {

			ingredientList = (List<IngredientDTO>) list;
		} else if (pathName.equals("ProductBatchDB.ser")) {

			productBatchList = (List<ProductBatchDTO>) list;

		} else if (pathName.equals("RecipeDB.ser")) {

			recipeList = (List<RecipeDTO>) list;

		} else if (pathName.equals("RoleDB.ser")) {

			roleList = (List<RoleDTO>) list;

		}

	}

}
