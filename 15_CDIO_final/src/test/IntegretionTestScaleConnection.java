/**
 * 
 */
package test;

import java.io.IOException;

import exceptions.DALException;
import exceptions.scaleConnectionException;
import scaleMachine.IScaleConnection;
import scaleMachine.ScaleConnection;

/**
 * @author s136335
 *
 */
public class IntegretionTestScaleConnection {

	/**
	 * @param args
	 */
	public static void main(String[] args)  throws DALException, IOException, scaleConnectionException{
		// TODO Auto-generated method stub
				IScaleConnection sc = new ScaleConnection("169.254.2.3");
		
//				IScaleConnection sc = new FakeScaleConnection(); test FakeConnection
				
				
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

	}

}
