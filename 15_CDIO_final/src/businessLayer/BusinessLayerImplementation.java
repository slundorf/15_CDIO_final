package businessLayer;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.text.SimpleDateFormat;

import dto.RoleDTO;
import dto.UserDTO;
import exceptions.DALException;
import interfaces.*;


public class BusinessLayerImplementation implements IBusinessLayer, IRoleDAO{
	private final String uLetter = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private final String lLetter = "abcdefghijklmnopqrstuvwxyz";
	private final String number = "0123456789";
	private final String sChars = "!@#$%^&*_=+-/";
	private final int noOfLetters = 1;
	private final int noOfNumbers = 1;
	private final int noOfSChars = 1;
	private final int min = 9;
	private final int max = 12;
	private final int productBatchMin = 300;
	private final int productBatchMax = 399;
	private IUserDAO userDAO;
	private IRoleDAO roleDAO;
	private IIngredientDAO ingredientDAO;
	private IRecipeDAO recipeDAO;
	private IProductBatchDAO productBatchDAO;

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
		String cpr = user.getCpr();
		Date date;
		try {
			String[] parts = cpr.split("-");
			String dateNumber = parts[0];
			String number = parts[1];
				if(dateNumber.length() == 6 && number.length() == 4) {
					try {
						SimpleDateFormat sdf = new SimpleDateFormat("ddMMyy");
						date = sdf.parse(dateNumber);
						if (!dateNumber.equals(sdf.format(date))) {
							throw new DALException("invalid date");
						} else {
							userDAO.createUser(user);
							}
					} catch (ParseException ex) {
						throw new DALException("parsing error");
						}
					} else
						throw new DALException("Invalid CPR length");
				} catch(ArrayIndexOutOfBoundsException e) {
		throw new DALException("Invalid CPR from (missing -)");
		}
	}

	@Override
	public void updateUser(UserDTO user) throws DALException {
		userDAO.updateUser(user);
		
	}
	
	/**
	 * Method to create a new password. 
	 * @param psw
	 * @return finalPassword
	 * @throws DALException
	 */

	@Override
	public String createPassword(UserDTO pwg) throws DALException {
		Random random = new Random();
		int length = random.nextInt(max-min+1)+min;
		char[] password = new char[length];
		int index = 0;
		for(int i = 0; i < noOfLetters; i++) {
			index = getNI(random,length, password);
			password[index] = uLetter.charAt(random.nextInt(uLetter.length()));
		}
		for(int i = 0; i < noOfNumbers; i++) {
			index = getNI(random, length, password);
			password[index]= number.charAt(random.nextInt(number.length()));
		}
		for(int i = 0; i < noOfSChars; i++) {
			index = getNI(random, length, password);
			password[index]= sChars.charAt(random.nextInt(sChars.length()));
		}
		for(int i = 0; i < noOfNumbers; i++) {
			if(password[i] == 0) {
				password[i] = lLetter.charAt(random.nextInt(lLetter.length()));
			}
		}
		String finalPassword = "";
		for(int i = 0; i < password.length; i++) {
			finalPassword += password[i];
		}
		return finalPassword;
	}
	
	/**
	 * Supportive method, used to generate a password.
	 * @param random
	 * @param length
	 * @param password
	 * @return index
	 */
	
	private int getNI(Random random, int length, char[] password) {
		int index = random.nextInt(length);
		while (password[index = random.nextInt(length)] != 0);
		return index;
	}

	/**
	 * Method to validate the entered password.
	 * @param password
	 * @throws DALException
	 */
	
	@Override
	public void checkPassword(String password) throws DALException {
		if(password.length() > max) {
			throw new DALException("Password is too long");
		}
		if(password.length() < min) {
			throw new DALException("Password is too short");
		}
		int noCAPS = 0;
		int noSChars = 0;
		int noDigits = 0;
		
		for(int i = 0; i < password.length(); i++) {
			if(Character.isUpperCase(password.charAt(i))) {
				noCAPS++;
			} else if(Character.isDigit(password.charAt(i))) {
				noDigits++;
			} else if(!password.matches("[^A-Za-z0-9 ]")) {
				noSChars++;
			}
		}
		if(noCAPS < noOfLetters) {
			throw new DALException("Password must contain at least " + noOfLetters + " upper case characters.");
		}
		if(noSChars < noOfSChars) {
			throw new DALException("Password must contain at least " + noOfSChars + " special characters [!@#$%^&*_=+-/].");
		}
		if(noDigits < noOfNumbers) {
			throw new DALException("Password must contain at least " + noOfNumbers + " digits.");
		}
		
	}
	
	/**
	 * Method to validate the entered CPR number.
	 * @param cpr
	 * @return true, false
	 * @throws DALException
	 */
	
	@Override
	public boolean checkCpr(String cpr) throws DALException {
		Date date = null;
		//First try and catch for "-" error
		try {
			String[] parts = cpr.split("-");
			String dateNumber = parts[0];
			String number = parts[1];
			
			if(dateNumber.length() == 6 && number.length() == 4) {
				try {
					SimpleDateFormat  sdf = new SimpleDateFormat("ddMMyy");
					date = sdf.parse(dateNumber);
					if(!dateNumber.equals(sdf.format(date))) {
						return false;
					} else {
						return true;
					}
				} catch (ParseException ex) { 
					return false;
				}
			} else {
				return false;
			} 
		} catch (ArrayIndexOutOfBoundsException e) {
				return false;
			}
	}

	/**
	 * Method to generate an ID for the product batch-
	 * @return prodBatchID
	 */    
	
	@Override
	public int productBatchIDGenerator() throws DALException {
		int prodBatchID = productBatchMin;
		for(int i = 0; i < 100; i++) {
			prodBatchID++;
		}
		if(prodBatchID > productBatchMax) {
			prodBatchID = 0;
		}
		return prodBatchID;
	}
	
	@Override
	public void checkID(int ID) throws DALException {
		//role ID, User ID, Ingredient ID, recipe ID, productbatchID
		if(ID > 0 && ID < 6) {
			roleDAO.getRole(ID);
		} else if(ID > 10 && ID < 100) {
			userDAO.getUser(ID);
		} else if(ID > 99 && ID < 200) {
			ingredientDAO.getIngredient(ID);
		} else if(ID > 199 && ID < 300) {
			recipeDAO.getRecipe(ID);
		} else if(ID > 299 && ID < 400) {
			productBatchDAO.getProductBatch(ID);
		} else 
			throw new DALException("Not a valid ID");
		
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
		roleDAO.createRole(role);
	}

	@Override
	public void updateRole(RoleDTO role) throws DALException {
		roleDAO.updateRole(role);;
		
	}

	

	}

