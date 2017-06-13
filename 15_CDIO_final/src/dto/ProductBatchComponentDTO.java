package dto;

import java.io.Serializable;

public class ProductBatchComponentDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8505275367940949745L;

	private Double tara;
	private Double netto;
	private Integer ingredientBatchID;
	private Integer ingredientID;
	private Integer userId;
	private Integer pbId;
	

	
	public ProductBatchComponentDTO(){}
	public ProductBatchComponentDTO( int pbId,int ingredientID){
		this.setPbId(pbId);
		this.setIngredientID(ingredientID);
	}
	
	public void setPbId(int pbId) {
		this.pbId =pbId;
	}
	
	public int getPbId() {
		return pbId;
	}
	
	public Double getTara() {
		return tara;
	}
	/**
	 * @param tara the tara to set
	 */
	public void setTara(Double tara) {
		this.tara = tara;
	}
	/**
	 * @return the netto
	 */
	public Double getNetto() {
		return netto;
	}
	/**
	 * @param netto the netto to set
	 */
	public void setNetto(Double netto) {
		this.netto = netto;
	}
	/**
	 * @return the ingredientBatchID
	 */
	public Integer getIngredientBatchID() {
		return ingredientBatchID;
	}
	/**
	 * @param ingredientBatchID the ingredientBatchID to set
	 */
	public void setIngredientBatchID(Integer ingredientBatchID) {
		this.ingredientBatchID = ingredientBatchID;
	}
	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	/**
	 * @return the ingredientID
	 */
	public Integer getIngredientID() {
		return ingredientID;
	}
	/**
	 * @param ingredientID the ingredientID to set
	 */
	public void setIngredientID(Integer ingredientID) {
		this.ingredientID = ingredientID;
	}
	
}