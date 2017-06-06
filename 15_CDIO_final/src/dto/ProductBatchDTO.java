package dto;

import java.io.Serializable;
import java.util.List;

public class ProductBatchDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2239800485607431112L;
	private int productBatchID;
	private String recipeID;
	private String productBatchName;
	private String createdDate;
	private String status;
	private List<ProductBatchComponentDTO> components;
	
	public ProductBatchDTO(){}
	public ProductBatchDTO(int productBatchID, String recipeID, String productBatchName, String createdDate,String status){
		this.setProductBatchID(productBatchID);
		this.setRecipeID(recipeID);
		this.setProductBatchName(productBatchName);
		this.setCreatedDate(createdDate);
		this.setStatus(status);
	}
	/**
	 * @return the productBatchID
	 */
	public int getProductBatchID() {
		return productBatchID;
	}
	/**
	 * @param productBatchID the productBatchID to set
	 */
	public void setProductBatchID(int productBatchID) {
		this.productBatchID = productBatchID;
	}
	/**
	 * @return the recipeID
	 */
	public String getRecipeID() {
		return recipeID;
	}
	/**
	 * @return the components
	 */
	public List<ProductBatchComponentDTO> getComponents() {
		return components;
	}
	/**
	 * 
	 * @param component adds a component to the current list of components
	 */
	public void addComponent(ProductBatchComponentDTO component){
		components.add(component);
	}
	/**
	 * @param recipeID the recipeID to set
	 */
	public void setRecipeID(String recipeID) {
		this.recipeID = recipeID;
	}
	/**
	 * @return the productName
	 */
	public String getProductBatchName() {
		return productBatchName;
	}
	/**
	 * @param productBatchName the productName to set
	 */
	public void setProductBatchName(String productBatchName) {
		this.productBatchName = productBatchName;
	}
	/**
	 * @return the createdDate
	 */
	public String getCreatedDate() {
		return createdDate;
	}
	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}