package testData;

import java.util.Scanner;

import exceptions.scaleConnectionException;
import scaleMachine.IScaleConnection;

public class FakeScaleConnection implements IScaleConnection {
	Scanner input;

	public FakeScaleConnection() {
		input = new Scanner(System.in);
	}

	@Override
	public int getInteger(String msg) throws scaleConnectionException {
		if(msg.length()>24){
			throw new scaleConnectionException("msg too long. DisplayMsg");
		}
		System.out.println(msg);
		int integer = input.nextInt();
		return integer;
	}

	@Override
	public double getMass() throws scaleConnectionException {
		System.out.println("Enter fake mass (comma separated)");
		double mass = input.nextDouble();
		return mass;
	}

	@Override
	public double doTara() throws scaleConnectionException {
		System.out.println("Enter fake tara (comma separated)");
		double mass = input.nextDouble();
		return mass;
	}

	@Override
	public void setComponentName(String msg) throws scaleConnectionException {
		System.out.println("setComponentName to: " + msg);
	}

	@Override
	public void removeComponentName() throws scaleConnectionException {
		System.out.println("removeComponentName");
	}

	@Override
	public void displayMsg(String msg) throws scaleConnectionException {
		if(msg.length()>24){
			throw new scaleConnectionException("msg too long. DisplayMsg");
		}
		System.out.println(msg+" (Enter something)");
		input.next();
//		System.out.println("DisplayMsg done");
	}

	@Override
	public void setOperatorInitials(String operatorInitials) throws scaleConnectionException {
		System.out.println("setOperatorInitials to: " + operatorInitials);
	}

	@Override
	public void removeOperatorInitials() throws scaleConnectionException {
		System.out.println("removeOperatorInitials");
	}

	@Override
	public void setProductBatchID(int productBatchID) throws scaleConnectionException {
		System.out.println("SetProductBatchId to: " + productBatchID);
	}

	@Override
	public void removeProductBatchID() throws scaleConnectionException {
		System.out.println("removeProductBatchID");
	}

	@Override
	public void waitForAnswer() throws scaleConnectionException {
		System.out.println("Wait for fake botton pressed (Enter something)");
		input.next();		
	}

	@Override
	public void setSoftKey() throws scaleConnectionException {
		System.out.println("setSoftKey to Ok");
		
	}

	@Override
	public void removeSoftKey() throws scaleConnectionException {
		// TODO Auto-generated method stub
		System.out.println("RemoveSoftKey");
		
	}

	
}
