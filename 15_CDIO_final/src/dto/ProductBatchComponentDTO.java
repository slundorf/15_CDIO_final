package dto;

import java.io.Serializable;

public class ProductBatchComponentDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8505275367940949745L;
	private int ingredientID;
	private String ingredientName;
	private double amount;
	private double tolerance;
	private double tara;
	private double netto;
	private int ingredientBatchID;
	
	public ProductBatchComponentDTO(){}
	public ProductBatchComponentDTO(int ingredientID, String ingredientName
			, double amount, double tolerance, double tara, double netto, int ingredientBatchID){
		this.setIngredientID(ingredientID);
		this.setIngredientName(ingredientName);
		this.setAmount(amount);
		this.setTolerance(tolerance);
		this.setTara(tara);
		this.setNetto(netto);
		this.setIngredientBatchID(ingredientBatchID);
	}
	/**
	 * @return the ingredientID
	 */
	public int getIngredientID() {
		return ingredientID;
	}
	/**
	 * @param ingredientID the ingredientID to set
	 */
	public void setIngredientID(int ingredientID) {
		this.ingredientID = ingredientID;
	}
	/**
	 * @return the ingredientName
	 */
	public String getIngredientName() {
		return ingredientName;
	}
	/**
	 * @param ingredientName the ingredientName to set
	 */
	public void setIngredientName(String ingredientName) {
		this.ingredientName = ingredientName;
	}
	/**
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}
	/**
	 * @return the tolerance
	 */
	public double getTolerance() {
		return tolerance;
	}
	/**
	 * @param tolerance the tolerance to set
	 */
	public void setTolerance(double tolerance) {
		this.tolerance = tolerance;
	}
	/**
	 * @return the tara
	 */
	public double getTara() {
		return tara;
	}
	/**
	 * @param tara the tara to set
	 */
	public void setTara(double tara) {
		this.tara = tara;
	}
	/**
	 * @return the netto
	 */
	public double getNetto() {
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
	public int getIngredientBatchID() {
		return ingredientBatchID;
	}
	/**
	 * @param ingredientBatchID the ingredientBatchID to set
	 */
	public void setIngredientBatchID(int ingredientBatchID) {
		this.ingredientBatchID = ingredientBatchID;
	}
}