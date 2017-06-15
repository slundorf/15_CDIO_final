package businessLayer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import dto.IngredientBatchDTO;
import dto.IngredientDTO;
import dto.ProductBatchComponentDTO;
import dto.ProductBatchDTO;
import dto.RecipeComponentDTO;
import dto.RecipeDTO;
import dto.RoleDTO;
import dto.UserDTO;
import exceptions.DALException;
import interfaces.IIngredientBatchDAO;
import interfaces.IIngredientDAO;
import interfaces.IProductBatchComponentDAO;
import interfaces.IProductBatchDAO;
import interfaces.IRecipeComponentDAO;
import interfaces.IRecipeDAO;
import interfaces.IRoleDAO;
import interfaces.IUserDAO;

public class BusinessLayerImplementation implements IBusinessLayer, IRoleDAO {
	private final String uLetter = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private final String lLetter = "abcdefghijklmnopqrstuvwxyz";
	private final String number = "0123456789";
	private final String sChars = "!@#$%^&*_=+-/";
	private final int noOfLetters = 1;
	private final int noOfNumbers = 1;
	private final int noOfSChars = 1;
	private final int min = 9;
	private final int max = 12;
	
	private IUserDAO userDAO;
	private IRoleDAO roleDAO;
	private IIngredientDAO ingredientDAO;
	private IIngredientBatchDAO ingredientBatchDAO;
	private IRecipeDAO recipeDAO;
	private IProductBatchDAO productBatchDAO;
	private IProductBatchComponentDAO productBatchComponentDAO;
	private IRecipeComponentDAO recipeComponentDAO;
	
	
	public BusinessLayerImplementation(IUserDAO userDAO, IRoleDAO roleDAO, IIngredientDAO ingredientDAO, IIngredientBatchDAO ingredientBatchDAO, IRecipeDAO recipeDAO, 
			IProductBatchDAO productBatchDAO, IProductBatchComponentDAO productBatchComponentDAO, IRecipeComponentDAO recipeComponentDAO) {
		this.userDAO=userDAO;
		this.roleDAO=roleDAO;
		this.ingredientDAO=ingredientDAO;
		this.ingredientBatchDAO=ingredientBatchDAO;
		this.recipeDAO=recipeDAO;
		this.productBatchDAO=productBatchDAO;
		this.productBatchComponentDAO=productBatchComponentDAO;
		this.recipeComponentDAO=recipeComponentDAO;
	}
	@Override
	public int login(int userID, String password) throws DALException{
		if(userDAO.getUser(userID).getPassword().equals(password)) {
			return userDAO.getUser(userID).getRole().getRoleID();			
		} throw new DALException("Incorrect userID or password.");
	}
	@Override
	public UserDTO getUser(int userID) throws DALException {
		return userDAO.getUser(userID);
	}

	@Override
	public List<UserDTO> getUserList() throws DALException {
		return userDAO.getUserList();
	}

	@Override
	public void createUser(UserDTO user) throws DALException {
		user.setPassword(createPassword());
		user.setStatus(true);
		checkUser(user);
		userDAO.createUser(user);
	}

	@Override
	public void updateUser(UserDTO user) throws DALException {
		checkUpdatedUser(user);
		userDAO.updateUser(user);
	}
	
	/**
	 * check user for creation specs.
	 * @param user
	 * @throws DALException
	 */
	public void checkUser(UserDTO user) throws DALException {
		//Check for ID already taken.
		for (int i = 0; i < userDAO.getUserList().size(); i++) {
			if (userDAO.getUserList().get(i).getUserID() == user.getUserID())
				throw new DALException("UserID already taken.");
		}
		//Check username
		if (user.getUserName().length() > 20 || user.getUserName().length() < 2)
			throw new DALException("Username length must be between 2 and 20 characters");
		/*
		 * Check Initials
		 * tempIni.matches(".*\\d+.*") ---------------------------- To explain:
		 * .* means any character from 0 to infinite occurence, than the \\d+
		 * (double backslash I think is just to escape the second backslash) and
		 * \d+ means a digit from 1 time to infinite.
		 */
		if (user.getIni().matches(".*\\d+.*")) {
			throw new DALException("Initials contains illegal characters");
		}
		if (user.getIni().length() < 2 || user.getIni().length() > 4) {
			throw new DALException("Initials should have a length between [2-4]");
		}
		//Check CPR number
		checkCpr(user.getCpr());

	}

	/**
	 * check user for update specs
	 * @param user
	 * @throws DALException
	 */
	public void checkUpdatedUser(UserDTO user) throws DALException {
		
		//Check username
		if (user.getUserName().length() > 20 || user.getUserName().length() < 2)
			throw new DALException("Username length must be between 2 and 20 characters");

		/*
		 * Check Initials
		 * tempIni.matches(".*\\d+.*") ---------------------------- To explain:
		 * .* means any character from 0 to infinite occurence, than the \\d+
		 * (double backslash I think is just to escape the second backslash) and
		 * \d+ means a digit from 1 time to infinite.
		 */
		if (user.getIni().matches(".*\\d+.*")) {
			throw new DALException("Initials contains illegal characters");
		}
		if (user.getIni().length() < 2 || user.getIni().length() > 4) {
			throw new DALException("Initials should have a length between [2-4]");
		}

		for (int i = 0; i < userDAO.getUserList().size(); i++) {
			if (user.getIni().equals(userDAO.getUserList().get(i).getIni())) {
				if (!(userDAO.getUserList().get(i).getUserID() == user.getUserID())) {
					throw new DALException("Initials already taken"); }
			}
		}
		
		//Check CPR number
		checkCpr(user.getCpr());
		for (int i = 0; i < userDAO.getUserList().size(); i++) {
			if (!(user.getUserID() == userDAO.getUserList().get(i).getUserID())) {
				if (user.getCpr().equals(userDAO.getUserList().get(i).getCpr())) {
					throw new DALException("Invalid CPR number. This CPR number is already taken.");
				}
			}
		}
		
		//check password
		checkPassword(user.getPassword());

	}

	/**
	 * Method to create a new password.
	 * 
	 * @param psw
	 * @return finalPassword
	 * @throws DALException
	 */
	public String createPassword() throws DALException {
		Random random = new Random();
		int length = random.nextInt(max - min + 1) + min;
		char[] password = new char[length];
		int index = 0;
		for (int i = 0; i < noOfLetters; i++) {
			index = getNI(random, length, password);
			password[index] = uLetter.charAt(random.nextInt(uLetter.length()));
		}
		for (int i = 0; i < noOfNumbers; i++) {
			index = getNI(random, length, password);
			password[index] = number.charAt(random.nextInt(number.length()));
		}
		for (int i = 0; i < noOfSChars; i++) {
			index = getNI(random, length, password);
			password[index] = sChars.charAt(random.nextInt(sChars.length()));
		}
		for (int i = 0; i < noOfNumbers; i++) {
			if (password[i] == 0) {
				password[i] = lLetter.charAt(random.nextInt(lLetter.length()));
			}
		}
		String finalPassword = "";
		for (int i = 0; i < password.length; i++) {
			finalPassword += password[i];
		}
		return finalPassword;
	}

	/**
	 * Supportive method, used to generate a password.
	 * 
	 * @param random
	 * @param length
	 * @param password
	 * @return index
	 */
	public int getNI(Random random, int length, char[] password) {
		int index = random.nextInt(length);
		while (password[index = random.nextInt(length)] != 0)
			;
		return index;
	}

	/**
	 * Method to validate the entered password.
	 * 
	 * @param password
	 * @throws DALException
	 */
	public void checkPassword(String password) throws DALException {
		if (password.length() > max) {
			throw new DALException("Password is too long");
		}
		if (password.length() < min) {
			throw new DALException("Password is too short");
		}
		int noCAPS = 0;
		int noSChars = 0;
		int noDigits = 0;

		for (int i = 0; i < password.length(); i++) {
			if (Character.isUpperCase(password.charAt(i))) {
				noCAPS++;
			} else if (Character.isDigit(password.charAt(i))) {
				noDigits++;
			} else if (!password.matches("[^A-Za-z0-9 ]")) {
				noSChars++;
			}
		}
		if (noCAPS < noOfLetters) {
			throw new DALException("Password must contain at least " + noOfLetters + " upper case characters.");
		}
		if (noSChars < noOfSChars) {
			throw new DALException(
					"Password must contain at least " + noOfSChars + " special characters [!@#$%^&*_=+-/].");
		}
		if (noDigits < noOfNumbers) {
			throw new DALException("Password must contain at least " + noOfNumbers + " digits.");
		}

	}

	/**
	 * Method to validate the entered CPR number.
	 * 
	 * @param cpr
	 * @return true, false
	 * @throws DALException
	 */
	private boolean checkCpr(String cpr) throws DALException {
		Date date = null;
		// First try and catch for "-" error
		for(int i=0;i<userDAO.getUserList().size();i++){
			if(userDAO.getUserList().get(i).getCpr().equals(cpr)){
				throw new DALException("Another user has the CPR: "+cpr);
			}
		}
		try {
			String[] parts = cpr.split("-");
			String dateNumber = parts[0];
			String number = parts[1];
			if (dateNumber.length() == 6 && number.length() == 4) {
				try {
					SimpleDateFormat sdf = new SimpleDateFormat("ddMMyy");
					date = sdf.parse(dateNumber);
					if (!dateNumber.equals(sdf.format(date))) {
						throw new DALException("invalid date format");
					} else {
						return true;
					}
				} catch (ParseException ex) {
					throw new DALException("parsing error");
				}
			} else
				throw new DALException("Invalid CPR length");
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new DALException("Invalid CPR from (missing -)");
		}
		
	}

	@Override
	public RoleDTO getRole(int roleID) throws DALException {
		return roleDAO.getRole(roleID);
	}

	@Override
	public List<RoleDTO> getRoleList() throws DALException {
		return roleDAO.getRoleList();
	}

	@Override
	public void createRole(RoleDTO role) throws DALException {
		if(role.getRoleID()<0 || role.getRoleID()>9)
			throw new DALException("Invalid ID");
		for(int i =0;i<roleDAO.getRoleList().size();i++){
			if(roleDAO.getRole(i).getRoleID()==role.getRoleID()){
				throw new DALException("ID already taken.");
			}
		}
		roleDAO.createRole(role);
	}

	@Override
	public void updateRole(RoleDTO role) throws DALException {
		roleDAO.updateRole(role);
	}

	public IngredientDTO getIngredient(int ingredientID) throws DALException{
		return ingredientDAO.getIngredient(ingredientID);
	}
	public List<IngredientDTO> getIngredientList() throws DALException{
		return ingredientDAO.getIngredientList();
	}
	public void createIngredient(IngredientDTO ingredient) throws DALException{
		if(ingredient.getIngredientID()<100 || ingredient.getIngredientID()>199){
			throw new DALException("Invalid ID");
		}
		for(int i=0;i<ingredientDAO.getIngredientList().size();i++){
			if(ingredientDAO.getIngredientList().get(i).getIngredientID()==ingredient.getIngredientID()){
				throw new DALException("ID already taken");
			}else if(ingredientDAO.getIngredientList().get(i).getIngredientName().equals(ingredient.getIngredientName()) 
					&& ingredientDAO.getIngredientList().get(i).getSupplier().equals(ingredient.getSupplier())){
				throw new DALException("This combination of ingredient and Supplier already exists.");
			}
		}
		ingredientDAO.createIngredient(ingredient);
	}
	public void updateIngredient(IngredientDTO ingredient) throws DALException{
		for(int i=0;i<ingredientDAO.getIngredientList().size();i++){
			if(ingredientDAO.getIngredientList().get(i).getIngredientName().equals(ingredient.getIngredientName()) 
					&& ingredientDAO.getIngredientList().get(i).getSupplier().equals(ingredient.getSupplier())){
				throw new DALException("This combination of ingredient and Supplier already exists.");
			}
		}
		ingredientDAO.updateIngredient(ingredient);
	}
	public ProductBatchComponentDTO getProductBatchComponent(int pbId, int ingrbatchId) throws DALException {
		return productBatchComponentDAO.getProduktBatchComp(pbId, ingrbatchId);
	}
	
	public List<ProductBatchComponentDTO> getProductBatchComponentList(int pbId) throws DALException {
		return productBatchComponentDAO.getProductBatchComponentList(pbId);
	}
	
	public List<ProductBatchComponentDTO> getProductBatchComponentList() throws DALException {
		return productBatchComponentDAO.getProductBatchComponentList();
	}
	
	public void updateProductBatchComponent(ProductBatchComponentDTO productbatchComponent) throws DALException {
		productBatchComponentDAO.updateProductBatchComponent(productbatchComponent);
	}
	
	public ProductBatchDTO getProductBatch(int pbId) throws DALException {
		return productBatchDAO.getProductBatch(pbId);
	}
	
	public List<ProductBatchDTO> getProductBatchList() throws DALException {
		return productBatchDAO.getProductBatchList();
	}
	
	public void createProductBatch(ProductBatchDTO productbatch) throws DALException {
		for(int i=0;i<productBatchDAO.getProductBatchList().size();i++) {
			if(productBatchDAO.getProductBatch(i).getProductBatchID()==productbatch.getProductBatchID()) {
				throw new DALException("ID already taken.");
			} 
		}
		
		for(int i = 0; i<recipeDAO.getRecipe(productbatch.getRecipeID()).getComponents().size();i++){
			productbatch.addComponent(new ProductBatchComponentDTO(productbatch.getProductBatchID(),
					recipeDAO.getRecipe(productbatch.getRecipeID()).getComponents().get(i).getIngredientID()));
		}
		productBatchDAO.createProductBatch(productbatch);
	}
	
	public void updateProductBatch(ProductBatchDTO productbatch) throws DALException {
		productBatchDAO.updateProductBatch(productbatch);
	}
	
	public RecipeDTO getRecipe(int recipeID) throws DALException {
		return recipeDAO.getRecipe(recipeID);
	}
	
	public List<RecipeDTO> getRecipeList() throws DALException {
		return recipeDAO.getRecipeList();
	}
	
	public void createRecipe(RecipeDTO recipe) throws DALException {
		for(int i=0;i<recipeDAO.getRecipeList().size();i++) {
			if(recipeDAO.getRecipeList().get(i).getRecipeID()==recipe.getRecipeID()) {
				throw new DALException("ID already taken.");
			} 
		}
		recipeDAO.createRecipe(recipe);
	}
	
	public void updateRecipe(RecipeDTO recipe) throws DALException {
		recipeDAO.updateRecipe(recipe);
		//////////
	}
	
	public List<RecipeComponentDTO> getRecipeComponentList() throws DALException {
		return recipeComponentDAO.getRecipeComponentList();
	}
	
	
	public void updateRecipeComponent(RecipeComponentDTO recipeComponent) throws DALException {
		recipeComponentDAO.updateRecipeComponent(recipeComponent);
		////////////
	}
	public IngredientBatchDTO getIngredientBatch(int ibId) throws DALException{
		return ingredientBatchDAO.getIngredientBatch(ibId);
	}
	public List<IngredientBatchDTO> getIngredientBatchList() throws DALException{
		return ingredientBatchDAO.getIngredientBatchList();
	}
	public void createIngredientBatch(IngredientBatchDTO ingredientBatch) throws DALException{
		for(int i =0; i< ingredientBatchDAO.getIngredientBatchList().size();i++){
			if(ingredientBatchDAO.getIngredientBatchList().get(i).getIngredientBatchID()==ingredientBatch.getIngredientBatchID()){
				throw new DALException("IngredientBatch ID already exists.");
			}
		}
		ingredientBatchDAO.createIngredientBatch(ingredientBatch);
	}
	public void updateIngredientBatch(IngredientBatchDTO ingredientBatch) throws DALException {
		ingredientBatchDAO.updateIngredientBatch(ingredientBatch);
	}
	@Override
	public void createRecipeComponent(RecipeComponentDTO recipeComponent) throws DALException {
		for(int i=0;i<recipeComponentDAO.getRecipeComponentList().size(); i++) {
			if(recipeComponentDAO.getRecipeComponentList().get(i).getRecipeID()==recipeComponent.getRecipeID()
					&& recipeComponentDAO.getRecipeComponentList().get(i).getRecipeID()==recipeComponent.getIngredientID()) {
				throw new DALException("Recipe "+recipeComponent.getRecipeID()+" already has ingredient "+recipeComponent.getIngredientID()+" in a component.");				
			} 
		}

		recipeComponentDAO.createRecipeComponent(recipeComponent);
	}
	@Override
	public List<RecipeComponentDTO> getRecipeComponentList(int recipeID) throws DALException {
		return recipeComponentDAO.getRecipeComponentList(recipeID);
	}
	@Override
	public RecipeComponentDTO getRecipeComponent(int recipeID, int ingredientID) throws DALException {
		return recipeComponentDAO.getRecipeComponent(recipeID, ingredientID);
	}
	
}

