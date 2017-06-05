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
import dto.ProductBatchDTO;
import exceptions.DALException;
import interfaces.IProductBatchDAO;

public class SerProductBatchDAO implements IProductBatchDAO {
	
	private List<ProductBatchDTO> pbs = new ArrayList<ProductBatchDTO>();
	private final String pathName;
	
	public SerProductBatchDAO(){
		pathName="productBatchDB.ser";
	}

	@Override
	public ProductBatchDTO getProductBatch(int pbId) throws DALException {
		loadInfo();
		if (pbs.size() == 0)
			throw new DALException("The database is empty.");
		for (int i = 0; i < pbs.size(); i++) {
			if (pbs.get(i).getProductBatchID() == pbId) {
				return pbs.get(i);
			}
		}
		throw new DALException("No product batch has been found with id: " + pbId);
	}

	@Override
	public List<ProductBatchDTO> getProductBatchList() throws DALException {
		loadInfo();
		if (pbs.size() == 0)
			throw new DALException("There are no product batches in the database.");
		return pbs;
	}

	@Override
	public void createProductBatch(ProductBatchDTO productbatch) throws DALException {
		loadInfo();
		pbs.add(productbatch);
		saveInfo();// TODO Auto-generated method stub
		
	}

	@Override
	public void updateProductBatch(ProductBatchDTO productbatch) throws DALException {
		loadInfo();
		for (int i = 0; i < pbs.size(); i++) {
			if (productbatch.getProductBatchID() == pbs.get(i).getProductBatchID()) {
				pbs.remove(i);
				pbs.add(productbatch);
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
			pbs = (ArrayList<ProductBatchDTO>) input.readObject();
			if (pbs.equals(null))
				pbs = new ArrayList<ProductBatchDTO>();
			input.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (EOFException e) {
			pbs = new ArrayList<ProductBatchDTO>();
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
			output.writeObject(pbs);
			// close the writing.
			output.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
