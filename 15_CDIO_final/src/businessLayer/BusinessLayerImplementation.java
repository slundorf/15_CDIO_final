package businessLayer;

import java.text.ParseException;
import java.util.Date;
import java.util.Random;
import java.text.SimpleDateFormat;

import exceptions.DALException;

public class BusinessLayerImplementation implements IBusinessLayer{
	
	private final String uLetter = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private final String lLetter = "abcdefghijklmnopqrstuvwxyz";
	private final String number = "0123456789";
	private final String sChars = "!@#$%^&*_=+-/";
	private final int noOfLetters = 1;
	private final int noOfNumbers = 1;
	private final int noOfSChars = 1;
	private final int min = 9;
	private final int max = 12;
	private final int productBatchMin = 10000;
	private final int productBatchMax = 10100;
	
	
	private String createPassword(BusinessLayerImplementation psw) throws DALException {
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
	private int getNI(Random random, int length, char[] password) {
		int index = random.nextInt(length);
		while (password[index = random.nextInt(length)] != 0);
		return index;
	}

	
	private void checkPassword(String password) throws DALException {
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
	public boolean checkCPR(String cpr) throws DALException {
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
	/*public int productBatchNumberGenerator() {
		int prodBatchNumber = productBatchMin;
		for(int i = 0; i < 100; i++) {
			
		}
		return prodBatchNumber;
	}*/
	}

