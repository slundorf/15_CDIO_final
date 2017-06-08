/**
 * 
 */
package exceptions;

/**
 * @author s136335
 *
 */


public class scaleConnectionException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2775796227918268529L;

	
	public scaleConnectionException(String msg, Throwable e) {
		super(msg,e);
	}

	public scaleConnectionException(String msg) {
		super(msg);
	}
	public scaleConnectionException(Exception e) { 
		super(e); 
	}
}
