/**
 * 
 */
package test;

import java.io.IOException;

import exceptions.DALException;
import exceptions.scaleConnectionException;
import scaleMachine.IScaleConnection;
import scaleMachine.InputException;
import scaleMachine.ProcedureController;
import testData.FakeScaleConnection;

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
	public static void main(String[] args) throws DALException, IOException, InputException, scaleConnectionException {
		IScaleConnection sc = new FakeScaleConnection();
		ProcedureController p = new ProcedureController(sc,true);
		p.startScaleProcess();
	}

}
