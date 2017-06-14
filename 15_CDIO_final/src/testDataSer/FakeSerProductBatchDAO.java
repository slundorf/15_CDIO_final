package testDataSer;

import java.util.List;
import dto.ProductBatchDTO;
import exceptions.DALException;
import interfaces.IProductBatchDAO;

public class FakeSerProductBatchDAO extends FakeSerDAO<ProductBatchDTO>implements IProductBatchDAO {
	
	public FakeSerProductBatchDAO(String pathName){
		super(pathName);
	}
	
	public FakeSerProductBatchDAO(){
		super("ProductBatchDB.ser");
	}

	/**
	 * 
	 * @param pbId (productBatch id)
	 * @return Product Batch with specified id
	 * @throws DALException
	 */
	@Override
	public ProductBatchDTO getProductBatch(int pbId) throws DALException {
		loadInfo();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getProductBatchID() == pbId) {
				return list.get(i);
			}
		}
		throw new DALException("No product batch has been found with id: " + pbId);
	}

	/**
	 * 
	 * @return A list of all product batches
	 * @throws DALException
	 */
	@Override
	public List<ProductBatchDTO> getProductBatchList() throws DALException {
		loadInfo();
		return list;
	}

	/**
	 * Creates the Product Batch given as parameter.
	 * @param productbatch
	 * @throws DALException
	 */
	@Override
	public void createProductBatch(ProductBatchDTO productbatch) throws DALException {
		loadInfo();
		for(int i =0;i<list.size();i++){
			if(list.get(i).getProductBatchID()==productbatch.getProductBatchID()){
				throw new DALException("A productbatch with ID: " +productbatch.getProductBatchID()+"already exists");
			}
		}
		list.add(productbatch);
		saveInfo();
	}

	/**
	 * Updates the product batch, given as parameter.
	 * @param productbatch
	 * @throws DALException
	 */
	@Override
	public void updateProductBatch(ProductBatchDTO productbatch) throws DALException {
		loadInfo();
		for (int i = 0; i < list.size(); i++) {
			if (productbatch.getProductBatchID() == list.get(i).getProductBatchID()) {
				list.remove(i);
				list.add(productbatch);
			}
		}
		saveInfo();
	}
}
