/**
 * 
 */
package test;


import java.io.IOException;

import exceptions.DALException;
import exceptions.scaleConnectionException;
import scaleMachine.IScaleConnection;
import scaleMachine.ProcedureController;
import scaleMachine.ScaleConnection;

/**
 * @author jonaslarsen
 *
 */
public class IntegrationTestProcedureController {

	/**
	 * @param args
	 * @throws scaleConnectionException
	 * @throws InputException
	 * @throws IOException
	 * @throws DALException
	 */
	public static void main(String[] args) throws DALException, IOException, scaleConnectionException {
		IScaleConnection sc = new ScaleConnection("169.254.2.3");
//
		//IScaleConnection sc = new FakeScaleConnection();
		
		ProcedureController p = new ProcedureController(sc, true);
		
		p.startScaleProcess();
		

		
	}

	

}
