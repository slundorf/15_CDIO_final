/**
 * 
 */
package testData;

import java.util.ArrayList;
import java.util.List;

import dto.ProductBatchComponentDTO;
import dto.ProductBatchDTO;
import exceptions.DALException;
import interfaces.IProductBatchComponentDAO;

/**
 * @author s136335
 *
 */
public class FakeProductbatchComponentDAO implements IProductBatchComponentDAO {
	List<ProductBatchComponentDTO> fakeProductBatchComponentList;
	List<ProductBatchDTO> fakeProductBatchList;
	ProductBatchComponentDTO PBC1, PBC2, PBC3, PBC4;
	ProductBatchDTO PB1, PB2, PB3;

	public FakeProductbatchComponentDAO() {
		fakeProductBatchComponentList = new ArrayList<ProductBatchComponentDTO>();
		// PB1
		 PBC1 = new ProductBatchComponentDTO(41, 1);
		 PBC2 = new ProductBatchComponentDTO(41, 2);

		// PB2
		 PBC3 = new ProductBatchComponentDTO(42, 3);

		// PB3
		 PBC4 = new ProductBatchComponentDTO(43, 3);
		 
		 fakeProductBatchList = new ArrayList<ProductBatchDTO>();
		 
		//PB1
			List<ProductBatchComponentDTO> productBatchComponentList1 = new ArrayList<ProductBatchComponentDTO>();
			productBatchComponentList1.add(PBC1);
			productBatchComponentList1.add(PBC2);
			//PB2
			List<ProductBatchComponentDTO> productBatchComponentList2 = new ArrayList<ProductBatchComponentDTO>();
			productBatchComponentList2.add(PBC3);
			//PB3
			List<ProductBatchComponentDTO> productBatchComponentList3 = new ArrayList<ProductBatchComponentDTO>();
			productBatchComponentList3.add(PBC4);
			
			 PB1 = new ProductBatchDTO(41, 31, "090693", "Created", productBatchComponentList1);
			 PB2 = new ProductBatchDTO(42, 32, "100693", "Created", productBatchComponentList2);
			 PB3 = new ProductBatchDTO(43, 32, "110693", "Created", productBatchComponentList3);
			 
			 fakeProductBatchList.add(PB1);
			 fakeProductBatchList.add(PB2);
			 fakeProductBatchList.add(PB3);

		 
		
	}

	@Override
	public ProductBatchComponentDTO getProduktBatchComp(int pbId, int ingrbatchId) throws DALException {
		for (int i = 0; i < fakeProductBatchList.size(); i++) {
			if (fakeProductBatchList.get(i).getProductBatchID() == pbId) {
				for (int j = 0; j < fakeProductBatchList.get(i).getComponents().size(); j++) {
					if (fakeProductBatchList.get(i).getComponents().get(j)
							.getIngredientBatchID() == ingrbatchId) {
						return fakeProductBatchList.get(i).getComponents().get(j);
					}
				}
			}
		}
		throw new DALException(
				"No Product batch found with id = " + pbId + " and ingredient batch id = " + ingrbatchId);
	}

	@Override
	public List<ProductBatchComponentDTO> getProductBatchComponentList(int pbId) throws DALException {
		for (int i = 0; i < fakeProductBatchList.size(); i++) {
			if (fakeProductBatchList.get(i).getProductBatchID() == pbId) {
				return fakeProductBatchList.get(i).getComponents();
			}
		}
		throw new DALException("No product batch found with ID = " + pbId);
	}

	@Override
	public List<ProductBatchComponentDTO> getProductBatchComponentList() throws DALException {
		List<ProductBatchComponentDTO> returnList = new ArrayList<ProductBatchComponentDTO>();
		for (int i = 0; i < fakeProductBatchList.size(); i++) {
			for (int j = 0; j < fakeProductBatchList.get(i).getComponents().size(); j++) {
				returnList.add(fakeProductBatchList.get(i).getComponents().get(j));
			}
		}
		return returnList;
	}

	@Override
	public void createProductBatchComponent(ProductBatchComponentDTO productbatchcomponent) throws DALException {
		boolean existed = false;
		for (int i = 0; i < fakeProductBatchList.size(); i++) {
			if (fakeProductBatchList.get(i).getProductBatchID() == productbatchcomponent.getPbId()) {
				existed = true;
				for (int j = 0; j < fakeProductBatchList.get(i).getComponents().size(); j++) {
					if (fakeProductBatchList.get(i).getComponents().get(j)
							.getIngredientID() == productbatchcomponent.getIngredientID()) {
						throw new DALException("That ingredient is already in that productbatch");
					}
				}
				fakeProductBatchList.get(i).addComponent(productbatchcomponent);
			}
		}
		if (!existed) {
			throw new DALException("Product batch not found");
		}
	}

	@Override
	public void updateProductBatchComponent(ProductBatchComponentDTO productbatchComponent) throws DALException {
		boolean existed = false;
		for (int i = 0; i < fakeProductBatchList.size(); i++) {
			if (fakeProductBatchList.get(i).getProductBatchID() == productbatchComponent.getPbId()) {
				for (int j = 0; j < fakeProductBatchList.get(i).getComponents().size(); j++) {
					if (fakeProductBatchList.get(i).getComponents().get(j)
							.getIngredientID() == productbatchComponent.getIngredientID()) {
						fakeProductBatchList.get(i).addComponent(productbatchComponent);
						existed = true;
					}
				}
			}
		}
		if (!existed) {
			throw new DALException("Product batch not found");
		}
	}
}
