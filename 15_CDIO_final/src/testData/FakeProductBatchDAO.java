/**
 * 
 */
package testData;

import java.util.ArrayList;
import java.util.List;

import dto.ProductBatchComponentDTO;
import dto.ProductBatchDTO;
import exceptions.DALException;
import interfaces.IProductBatchDAO;

/**
 * @author jonaslarsen
 *
 */
public class FakeProductBatchDAO implements IProductBatchDAO {

	List<ProductBatchDTO> fakeProductBatchList;
	List<ProductBatchComponentDTO> fakeProductBatchComponentList;
	ProductBatchDTO PB1;
	ProductBatchComponentDTO PBC1;
	ProductBatchComponentDTO PBC2;

	public FakeProductBatchDAO() {
		fakeProductBatchList = new ArrayList<ProductBatchDTO>();
		fakeProductBatchComponentList = new ArrayList<ProductBatchComponentDTO>();
		PBC1 = new ProductBatchComponentDTO(31,1);
		PBC2 = new ProductBatchComponentDTO(31,2);
		fakeProductBatchComponentList.add(PBC1);
		fakeProductBatchComponentList.add(PBC2);
		PB1 = new ProductBatchDTO(41, 31, "090693", "Created", fakeProductBatchComponentList);
		fakeProductBatchList.add(PB1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interfaces.IProductBatchDAO#getProductBatch(int)
	 */
	@Override
	public ProductBatchDTO getProductBatch(int pbId) throws DALException {
		for (int i = 0; i < fakeProductBatchList.size(); i++) {
			if (fakeProductBatchList.get(i).getProductBatchID() == pbId) {
				return fakeProductBatchList.get(i);
			}
		}
		throw new DALException("No product batch has been found with id: " + pbId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interfaces.IProductBatchDAO#getProductBatchList()
	 */
	@Override
	public List<ProductBatchDTO> getProductBatchList() throws DALException {
		return fakeProductBatchList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interfaces.IProductBatchDAO#createProductBatch(dto.ProductBatchDTO)
	 */
	@Override
	public void createProductBatch(ProductBatchDTO productbatch) throws DALException {
		for (int i = 0; i < fakeProductBatchList.size(); i++) {
			if (fakeProductBatchList.get(i).getProductBatchID() == productbatch.getProductBatchID()) {
				throw new DALException(
						"A productbatch with ID: " + productbatch.getProductBatchID() + "already exists");
			}
		}
		fakeProductBatchList.add(productbatch);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interfaces.IProductBatchDAO#updateProductBatch(dto.ProductBatchDTO)
	 */
	@Override
	public void updateProductBatch(ProductBatchDTO productbatch) throws DALException {
		for (int i = 0; i < fakeProductBatchList.size(); i++) {
			if (productbatch.getProductBatchID() == fakeProductBatchList.get(i).getProductBatchID()) {
				fakeProductBatchList.remove(i);
				fakeProductBatchList.add(productbatch);
			}
		}

	}

}
