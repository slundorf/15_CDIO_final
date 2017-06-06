package interfaces;

import java.util.List;

import dto.ProductBatchComponentDTO;
import exceptions.DALException;

public interface IProductBatchComponentDAO {
	    ProductBatchComponentDTO getProduktBatchComp(int pbId, int ingrbatchId) throws DALException;
	    List<ProductBatchComponentDTO> getProductBatchComponentList(int pbId) throws DALException;
	    List<ProductBatchComponentDTO> getProductBatchComponentList() throws DALException;
	    void createProductBatchComponent(ProductBatchComponentDTO productbatchcomponent) throws DALException;
	    void updateProductBatchComponent(ProductBatchComponentDTO productbatchComponent) throws DALException;

}
