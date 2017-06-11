/**
 * 
 */
package testData;

import java.util.List;

import dto.ProductBatchDTO;
import exceptions.DALException;
import interfaces.IProductBatchDAO;

/**
 * @author jonaslarsen
 *
 */
public class FakeProductBatchDAO implements IProductBatchDAO {

	/* (non-Javadoc)
	 * @see interfaces.IProductBatchDAO#getProductBatch(int)
	 */
	@Override
	public ProductBatchDTO getProductBatch(int pbId) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see interfaces.IProductBatchDAO#getProductBatchList()
	 */
	@Override
	public List<ProductBatchDTO> getProductBatchList() throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see interfaces.IProductBatchDAO#createProductBatch(dto.ProductBatchDTO)
	 */
	@Override
	public void createProductBatch(ProductBatchDTO productbatch) throws DALException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see interfaces.IProductBatchDAO#updateProductBatch(dto.ProductBatchDTO)
	 */
	@Override
	public void updateProductBatch(ProductBatchDTO productbatch) throws DALException {
		// TODO Auto-generated method stub

	}

}
