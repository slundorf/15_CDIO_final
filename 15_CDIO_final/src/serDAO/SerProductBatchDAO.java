package serDAO;

import java.util.List;
import dto.ProductBatchDTO;
import exceptions.DALException;
import interfaces.IProductBatchDAO;

public class SerProductBatchDAO extends SerDAO<ProductBatchDTO>implements IProductBatchDAO {
	
	public SerProductBatchDAO(){
		super("SerFiles/ProductBatchDB.ser");
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
		if (list.size() == 0)
			throw new DALException("The database is empty.");
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
		if (list.size() == 0)
			throw new DALException("There are no product batches in the database.");
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
