package interfaces;

import java.util.List;

import dto.ProductBatchComponentDTO;
import exceptions.DALException;

public interface IProductBatchComponentDAO {
	/**
	 * 
	 * @param pbId (productbatch id)
	 * @param ingrbatchId (ingredientbatch id)
	 * @return ProductBatchComponent
	 * @throws DALException
	 */
	ProductBatchComponentDTO getProduktBatchComp(int pbId, int ingrbatchId) throws DALException;

	/**
	 * 
	 * @param pbId (producbatch id)
	 * @return List of productbatch with specified receptID
	 * @throws DALException
	 */
	List<ProductBatchComponentDTO> getProductBatchComponentList(int pbId) throws DALException;
	
	/**
	 * 
	 * @return all ProductBatchComponents.
	 * @throws DALException
	 */
	List<ProductBatchComponentDTO> getProductBatchComponentList() throws DALException;

	/**
	 * @param productbatchcomponent
	 * @throws DALException
	 */
	void createProductBatchComponent(ProductBatchComponentDTO productbatchcomponent) throws DALException;
	
	/**
	 * 
	 * @param productbatchComponent
	 * @throws DALException
	 */
	void updateProductBatchComponent(ProductBatchComponentDTO productbatchComponent) throws DALException;

}
