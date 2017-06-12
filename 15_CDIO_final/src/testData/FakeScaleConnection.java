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
		System.out.println(msg);
		int integer = input.nextInt();
		return integer;
	}

	@Override
	public double getMass() throws scaleConnectionException {
		System.out.println("Enter fake mass");
		double mass = input.nextDouble();
		return mass;
	}

	@Override
	public double doTara() throws scaleConnectionException {
		System.out.println("Enter fake tara");
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
		System.out.println(msg);
		input.next();
		System.out.println("DisplayMsg done");
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

}
