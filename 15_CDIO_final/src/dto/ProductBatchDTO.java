package dto;

import java.awt.Component;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProductBatchDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2239800485607431112L;
	private int productBatchID;
	private int recipeID;
	private String createdDate;
	private String status;
	private List<ProductBatchComponentDTO> components;
	
	
	public ProductBatchDTO(){}
	public ProductBatchDTO(int productBatchID, int recipeID, String createdDate,String status,List<ProductBatchComponentDTO> components){
		this.setProductBatchID(productBatchID);
		this.setRecipeID(recipeID);
		this.setCreatedDate(createdDate);
		this.setStatus(status);
		this.setComponents(components);
	
	}
	public ProductBatchDTO(int productBatchID, int recipeID, String createdDate, String status) {
		this.setProductBatchID(productBatchID);
		this.setRecipeID(recipeID);
		this.setCreatedDate(createdDate);
		this.setStatus(status);
		this.setComponents(new ArrayList<ProductBatchComponentDTO>());
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
	public int getRecipeID() {
		return recipeID;
	}
	/**
	 * @return the components
	 */
	public List<ProductBatchComponentDTO> getComponents() {
		return components;
	}
	/**
	 * @param components the components to set
	 */
	public void setComponents(List<ProductBatchComponentDTO> components) {
		this.components = components;
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
	public void setRecipeID(int recipeID) {
		this.recipeID = recipeID;
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
//	public int getUserId() {
//		return UserId;
//	}
//	public void setUserId(int userId) {
//		UserId = userId;
//	}
	
	
}