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
	 * Display the massage on the scale and wait for response
	 * @param msg max. 24 characters
	 * @return answer from the scale
	 * @throws scaleConnectionException
	 */
	String getString(String msg) throws scaleConnectionException;
	/**
	 * Note the current mass and return it.
	 * @param msg
	 * @return netto weight
	 * @throws scaleConnectionException
	 */
	int getMass(String msg) throws scaleConnectionException;

	/** set the scale to zero and return the difference
	 * 
	 * @return tara weight
	 * @throws scaleConnectionException
	 */
	int doTara() throws scaleConnectionException;
	/**
	 * display a message in the bottom line. Max
	 * @param msg max. 30 characters
	 * @throws scaleConnectionException
	 */
	void setBottomMsg(String msg) throws scaleConnectionException;
	/**
	 * remove the message in the bottom line of the scale
	 * @throws scaleConnectionException
	 */
	void removeBottomMsg() throws scaleConnectionException;
	/**
	 * show a temporary message on the display
	 * @param msg max. 24 characters
	 * @throws scaleConnectionException
	 */
	void displayTemporaryMsg(String msg) throws scaleConnectionException;
	
}
