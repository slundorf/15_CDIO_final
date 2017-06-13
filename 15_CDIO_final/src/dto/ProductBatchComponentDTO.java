package dto;

import java.io.Serializable;

public class ProductBatchComponentDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8505275367940949745L;

	private Double tara;
	private int pbcId;
	private Double netto;
	private Integer ingredientBatchID;
	private Integer ingredientID;
	private Integer userId;
	private Integer pbId;
	

	
	public ProductBatchComponentDTO(){}
	public ProductBatchComponentDTO( int pbcId, Double tara, Double netto, Integer ingredientBatchID, Integer UserId){
		this.setPbId(pbId);
		this.setTara(tara);
		this.setPbcId(pbcId);
		this.setNetto(netto);
		this.setIngredientBatchID(ingredientBatchID);
		this.setUserId(userId);
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
	 * @return the pbcId
	 */
	public int getPbcId() {
		return pbcId;
	}
	/**
	 * @param pbcId the pbcId to set
	 */
	public void setPbcId(int pbcId) {
		this.pbcId = pbcId;
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