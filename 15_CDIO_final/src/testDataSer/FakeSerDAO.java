package testDataSer;

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

import javax.management.relation.Role;
import javax.servlet.Servlet;
import javax.servlet.ServletContext;

import dto.IngredientBatchDTO;
import dto.IngredientDTO;
import dto.ProductBatchComponentDTO;
import dto.ProductBatchDTO;
import dto.RecipeComponentDTO;
import dto.RecipeDTO;
import dto.RoleDTO;
import dto.UserDTO;
import interfaces.IIngredientBatchDAO;
import interfaces.IIngredientDAO;
import interfaces.IProductBatchComponentDAO;
import interfaces.IProductBatchDAO;
import interfaces.IRecipeComponentDAO;
import interfaces.IRecipeDAO;
import interfaces.IRoleDAO;
import interfaces.IUserDAO;
import serDAO.SerIngredientBatchDAO;
import serDAO.SerIngredientDAO;
import serDAO.SerProductBatchComponentDAO;
import serDAO.SerProductBatchDAO;
import serDAO.SerRecipeComponentDAO;
import serDAO.SerRecipeDAO;
import serDAO.SerRoleDAO;
import serDAO.SerUserDAO;

public abstract class FakeSerDAO<E> {
	protected final String pathName;
	protected List<E> list = new ArrayList<E>();
	protected List<IngredientBatchDTO> ingredientBatchList;
	protected List<IngredientDTO> ingredientList;
	protected List<ProductBatchDTO> productBatchList;
	protected List<RecipeDTO> recipeList;
	protected List<RoleDTO> roleList;
	protected List<UserDTO> userList;
	
	

	public FakeSerDAO(String pathName) {
		this.pathName = helper(pathName);
		
		

		IngredientBatchDTO IB1 = new IngredientBatchDTO(20, 1, 25);
		IngredientBatchDTO IB2 = new IngredientBatchDTO(21, 2, 50);
		IngredientBatchDTO IB3 = new IngredientBatchDTO(22, 2, 60);
		IngredientBatchDTO IB4 = new IngredientBatchDTO(23, 3, 10);

		ingredientBatchList.add(IB1);
		ingredientBatchList.add(IB2);
		ingredientBatchList.add(IB3);
		ingredientBatchList.add(IB4);
		
		

		IngredientDTO I1 = new IngredientDTO(1, "Salt", "supplier1");
		IngredientDTO I2 = new IngredientDTO(2, "Water", "supplier2");
		IngredientDTO I3 = new IngredientDTO(3, "Lemon", "supplier3");
		
		ingredientList.add(I1);
		ingredientList.add(I2);
		ingredientList.add(I3);
		
		ProductBatchDTO PB1 = new ProductBatchDTO(41, 31, "090693", "Created");
		ProductBatchDTO PB2 = new ProductBatchDTO(42, 32, "100693", "Created");
		ProductBatchDTO PB3 = new ProductBatchDTO(43, 32, "110693", "Created");
		
		productBatchList.add(PB1);
		productBatchList.add(PB2);
		productBatchList.add(PB3);
		
	

		// PB1
		ProductBatchComponentDTO PBC1 = new ProductBatchComponentDTO(41, 1);
		ProductBatchComponentDTO PBC2 = new ProductBatchComponentDTO(41, 2);

		// PB2
		ProductBatchComponentDTO PBC3 = new ProductBatchComponentDTO(42, 3);

		// PB3
		ProductBatchComponentDTO PBC4 = new ProductBatchComponentDTO(43, 3);

		productBatchComponentList.add(PBC1);
		productBatchComponentList.add(PBC2);
		productBatchComponentList.add(PBC3);
		productBatchComponentList.add(PBC4);
	
		
		
		RecipeComponentDTO RC1 = new RecipeComponentDTO(31,51,1, 0.2, 0.1);
		RecipeComponentDTO RC2 = new RecipeComponentDTO(31,52,2, 2, 0.1);
		RecipeComponentDTO RC3 = new RecipeComponentDTO(32,53,3,1,0.5);
		
		recipeComponentList.add(RC1);
		recipeComponentList.add(RC2);
		recipeComponentList.add(RC3);
		
		
		RecipeDTO R1 = new RecipeDTO(31, "SaltWater");
		RecipeDTO R2 = new RecipeDTO(32, "Lemon");
		
		recipeList.add(R1);
		recipeList.add(R2);
		
		
		
		RoleDTO role1 = new RoleDTO(1, "Administator");
		RoleDTO role2 = new RoleDTO(2, "Pharmacist");
		RoleDTO role3 = new RoleDTO(3, "Foreman");
		RoleDTO role4 = new RoleDTO(4, "Operator");
		
		roleList.add(role1);
		roleList.add(role2);
		roleList.add(role3);
		roleList.add(role4);
		
	
		
		
		UserDTO user1 = new UserDTO(11, "Steve", "STV", "101010-1234","wally", role4, true);
		UserDTO user2 = new UserDTO(12, "Kurt", "Kru", "101110-1234","wally2", role1, true);
		UserDTO user3 = new UserDTO(13, "Lis", "LIS", "101110-1234","wally3", role2, false);
		
		userList.add(user1);
		userList.add(user2);
		userList.add(user3);
		
	}

	public static String helper(String path) {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		String stream = classLoader.getResource(path).getPath().toString().replaceAll("%20", " ");
		return stream;
	}

	/**
	 * Loads the data arraylist
	 */
	@SuppressWarnings("unchecked")
	protected void loadInfo() {

		
//			InputStream file = new FileInputStream(pathName);
//			InputStream buffer = new BufferedInputStream(file);
//			ObjectInput input = new ObjectInputStream(buffer);
		
		if(pathName.equals("UserDB.ser")){
			list = (ArrayList<E>) userList;
			
		}else if(pathName.equals("IngredientBatchDB.ser")){
			
			list = (ArrayList<E>) ingredientBatchList;
			
		}else if(pathName.equals("IngredientDB.ser")){
			
			list = (ArrayList<E>) ingredientList;
		}else if(pathName.equals("ProductBatchDB.ser")){
			
			list = (ArrayList<E>) productBatchList;
			
		}else if(pathName.equals("RecipeDB.ser")){
			
			list = (ArrayList<E>) recipeList;
			
		}else if(pathName.equals("RoleDB.ser")){
			
			list = (ArrayList<E>) roleList;
		
		}
			
			list = (ArrayList<E>) userList;

			if (list.equals(null))
				list = new ArrayList<E>();
//			input.close();
	
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
