/**
 * 
 */
package scaleMachine;

import exceptions.scaleConnectionException;
/**
 * @author s136335
 *
 */
public interface IScaleConnection {
	
	/**
	 * Display the massage on the scale and wait for numeric response
	 * @param msg max. 24 characters
	 * @return answer from the scale
	 * @throws scaleConnectionException
	 */
	int getInteger(String msg) throws scaleConnectionException;
	/**
	 * Note the current mass and return it.
	 * @return netto weight
	 * @throws scaleConnectionException
	 */
	double getMass() throws scaleConnectionException;

	/** set the scale to zero and return the difference
	 * 
	 * @return tara weight
	 * @throws scaleConnectionException
	 */
	double doTara() throws scaleConnectionException;
	/**
	 * display a message in the bottom line. Max
	 * @param msg max. 30 characters
	 * @throws scaleConnectionException
	 */
	void setComponentName(String msg) throws scaleConnectionException;
	/**
	 * remove the message in the bottom line of the scale
	 * @throws scaleConnectionException
	 */
	void removeComponentName() throws scaleConnectionException;
	/**
	 * show a temporary message on the display
	 * @param msg max. 24 characters
	 * @throws scaleConnectionException
	 */
	void displayMsg(String msg) throws scaleConnectionException;
	
	void setOperatorInitials(String operatorInitials) throws scaleConnectionException;
	
	void removeOperatorInitials() throws scaleConnectionException;
	
	void setProductBatchID(int productBatchID) throws scaleConnectionException;
	
	void removeProductBatchID() throws scaleConnectionException;
	
	void waitForAnswer() throws scaleConnectionException;
	
	void setSoftKey() throws scaleConnectionException; 
	
	
	 
}