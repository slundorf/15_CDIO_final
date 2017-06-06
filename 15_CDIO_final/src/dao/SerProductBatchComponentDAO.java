package dao;

import java.util.List;

import dto.ProductBatchComponentDTO;
import exceptions.DALException;
import interfaces.IProductBatchComponentDAO;

public class SerProductBatchComponentDAO extends SerDAO<ProductBatchComponentDTO> implements IProductBatchComponentDAO {
	
	public SerProductBatchComponentDAO(){
		super("SerFiles/productBatchComponentDB.ser");
	}

	@Override
	public ProductBatchComponentDTO getProduktBatchComp(int pbId, int ingrbatchId) throws DALException {
	return null;
	}

	@Override
	public List<ProductBatchComponentDTO> getProductBatchComponentList(int pbId) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductBatchComponentDTO> getProductBatchComponentList() throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createProductBatchComponent(ProductBatchComponentDTO productbatchcomponent) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateProductBatchComponent(ProductBatchComponentDTO productbatchComponent) throws DALException {
		// TODO Auto-generated method stub
		
	}
}
