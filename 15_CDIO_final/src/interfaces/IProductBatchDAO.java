package interfaces;

import java.util.List;

import dto.ProductBatchDTO;
import exceptions.DALException;


public interface IProductBatchDAO {
	    ProductBatchDTO getProductBatch(int pbId) throws DALException;
	    List<ProductBatchDTO> getProductBatchList() throws DALException;
	    void createProductBatch(ProductBatchDTO productbatch) throws DALException;
	    void updateProductBatch(ProductBatchDTO productbatch) throws DALException;
	}
