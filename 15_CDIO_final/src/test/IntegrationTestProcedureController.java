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
public class IntegrationTestProcedureController implements Runnable{

	/**
	 * @param args
	 * @throws scaleConnectionException
	 * @throws InputException
	 * @throws IOException
	 * @throws DALException
	 */
//	public static void main(String[] args) throws DALException, IOException, scaleConnectionException {
//	//	IScaleConnection sc = new ScaleConnection("169.254.2.3");
////
//
//		
//	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		IScaleConnection sc = new FakeScaleConnection();
		System.out.println("nu launcher daoerne");
		ProcedureController p = new ProcedureController(sc, false);
		System.out.println("nu er daoerne launched");
		try {
			p.startScaleProcess();
		} catch (DALException | IOException | scaleConnectionException e) {
			e.printStackTrace();
		}
	}

}
