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

import dto.IngredientBatchDTO;
import exceptions.DALException;
import interfaces.IIngredientBatchDAO;

public class SerIngredientBatchDAO implements IIngredientBatchDAO {
	
	
	private List<IngredientBatchDTO> ingrBatches = new ArrayList<IngredientBatchDTO>();
	private final String pathName;
	
	public SerIngredientBatchDAO(){
		pathName="IngredientBatchDB.ser";
	}

	@Override
	public IngredientBatchDTO getIngredientBatch(int ibId) throws DALException {
		loadInfo();
		if (ingrBatches.size() == 0)
			throw new DALException("The database is empty.");
		for (int i = 0; i < ingrBatches.size(); i++) {
			if (ingrBatches.get(i).getIngredientBatchID() == ibId) {
				return ingrBatches.get(i);
			}
		}
		throw new DALException("No Ingredient Batch has been found with id: " + ibId);
	}

	@Override
	public List<IngredientBatchDTO> getIngredientBatchList() throws DALException {
		loadInfo();
		if (ingrBatches.size() == 0)
			throw new DALException("There are no Ingredient batches in the database.");
		return ingrBatches;
	}

	@Override
	public List<IngredientBatchDTO> getIngredientBatchList(int ingredientId) throws DALException {
		loadInfo();
		List<IngredientBatchDTO> returnList = new ArrayList<IngredientBatchDTO>();
		if (ingrBatches.size() == 0)
			throw new DALException("There are no Ingredient batches in the database.");
		for (int i = 0; i < ingrBatches.size(); i++){
			if (ingredientId == ingrBatches.get(i).getIngredientID()){
				returnList.add(ingrBatches.get(i));
			}
			else 
				throw new DALException("No Ingredient Batches has been found containing ingredient with id: " + ingredientId);
		}
		return returnList;
	}

	@Override
	public void createIngredientBatch(IngredientBatchDTO ingredientBatch) throws DALException {
		loadInfo();
		ingrBatches.add(ingredientBatch);
		saveInfo();
	}

	@Override
	public void updateRaavareBatch(IngredientBatchDTO ingredientBatch) throws DALException {
		loadInfo();
		for (int i = 0; i < ingrBatches.size(); i++) {
			if (ingredientBatch.getIngredientBatchID() == ingrBatches.get(i).getIngredientBatchID()) {
				ingrBatches.remove(i);
				ingrBatches.add(ingredientBatch);
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
			ingrBatches = (ArrayList<IngredientBatchDTO>) input.readObject();
			if (ingrBatches.equals(null))
				ingrBatches = new ArrayList<IngredientBatchDTO>();
			input.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (EOFException e) {
			ingrBatches = new ArrayList<IngredientBatchDTO>();
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
			output.writeObject(ingrBatches);
			// close the writing.
			output.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
