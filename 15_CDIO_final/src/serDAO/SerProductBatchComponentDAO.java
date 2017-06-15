package serDAO;

import java.util.ArrayList;
import java.util.List;

import dto.ProductBatchComponentDTO;
import dto.ProductBatchDTO;
import exceptions.DALException;
import interfaces.IProductBatchComponentDAO;

public class SerProductBatchComponentDAO extends SerDAO<ProductBatchDTO> implements IProductBatchComponentDAO {

	public SerProductBatchComponentDAO(String pathName) {
		super(pathName);
	}

	public SerProductBatchComponentDAO() {
		super("ProductBatchDB.ser");
	}

	@Override
	public ProductBatchComponentDTO getProduktBatchComp(int pbId, int ingrId) throws DALException {
		loadInfo();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getProductBatchID() == pbId) {
				for (int j = 0; j < list.get(i).getComponents().size(); j++) {
					if (list.get(i).getComponents().get(j).getIngredientID() == ingrId) {
						return list.get(i).getComponents().get(j);
					}
				}
			}
		}
		throw new DALException(
				"No Product batch found with id = " + pbId + " and ingredient batch id = " + ingrId);
	}

	@Override
	public List<ProductBatchComponentDTO> getProductBatchComponentList(int pbId) throws DALException {
		loadInfo();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getProductBatchID() == pbId) {
				return list.get(i).getComponents();
			}
		}
		throw new DALException("No product batch found with ID = " + pbId);
	}

	@Override
	public List<ProductBatchComponentDTO> getProductBatchComponentList() throws DALException {
		loadInfo();
		List<ProductBatchComponentDTO> returnList = new ArrayList<ProductBatchComponentDTO>();
		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < list.get(i).getComponents().size(); j++) {
				returnList.add(list.get(i).getComponents().get(j));
			}
		}
		return returnList;
	}

	@Override
	public void createProductBatchComponent(ProductBatchComponentDTO productbatchcomponent) throws DALException {
		loadInfo();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getProductBatchID() == productbatchcomponent.getPbId()) {
				if (list.get(i).getComponents() != null) {
					for (int j = 0; j < list.get(i).getComponents().size(); j++) {
						if (list.get(i).getComponents().get(j).getIngredientID() == productbatchcomponent
								.getIngredientID()) {
							throw new DALException("That ingredient is already in that productbatch");
						}
					}
				}
				list.get(i).addComponent(productbatchcomponent);
			}
		}
		saveInfo();
	}

	@Override
	public void updateProductBatchComponent(ProductBatchComponentDTO productbatchComponent) throws DALException {
		loadInfo();
		boolean existed = false;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getProductBatchID() == productbatchComponent.getPbId()) {
				for (int j = 0; j < list.get(i).getComponents().size(); j++) {
					if (list.get(i).getComponents().get(j).getIngredientID() == productbatchComponent
							.getIngredientID()) {
						list.get(i).addComponent(productbatchComponent);
						existed = true;
					}
				}
			}
		}
		if (!existed) {
			throw new DALException("Product batch not found");
		}
		saveInfo();
	}
}
