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
	public static void main(String[] args) throws DALException, IOException, scaleConnectionException {
		IScaleConnection sc = new ScaleConnection("169.254.2.3");
//
//		IScaleConnection sc = new FakeScaleConnection();
//		ProcedureController p = new ProcedureController(sc, true);
//		p.startScaleProcess();
		
		sc.displayMsg("Test Start");
		sc.setComponentName("Morten");
		sc.setOperatorInitials("MOR");
		sc.setProductBatchID(2);
		sc.setSoftKey();
//		
		sc.waitForAnswer(); // first time
		
		
		sc.removeComponentName();
		sc.removeOperatorInitials();
		sc.removeProductBatchID();
		sc.removeSoftKey();
		sc.setSoftKey();
		sc.waitForAnswer(); // For view
		sc.removeSoftKey();
		int i = sc.getInteger("Test with integer");
		System.out.println(i);
		sc.displayMsg("Enter Tara");
		System.out.println(sc.doTara());
		sc.displayMsg("Enter mass");
		System.out.println(sc.getMass());
		
		
		
//		
//		System.out.println(sc.doTara());
//		sc.displayMsg("Hejmed");
//		int i = sc.getInteger("Hejigen");
////		sc.setProductBatchID(i);
//sc.setComponentName("Hej");
//sc.setSoftKey();
		
		
		
//	
		
	}

}
