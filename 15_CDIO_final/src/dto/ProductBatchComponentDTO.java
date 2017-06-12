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
	private Integer userId;
	
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
	public ProductBatchComponentDTO(){}
	public ProductBatchComponentDTO(Double tara, Double netto, Integer ingredientBatchID, Integer UserId){
		this.setTara(tara);
		this.setNetto(netto);
		this.setIngredientBatchID(ingredientBatchID);
		this.setUserId(userId);
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
	public void setNetto(double netto) {
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
	public void setIngredientBatchID(int ingredientBatchID) {
		this.ingredientBatchID = ingredientBatchID;
	}
}