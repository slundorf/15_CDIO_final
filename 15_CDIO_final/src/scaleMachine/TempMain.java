package scaleMachine;

import exceptions.scaleConnectionException;

public class TempMain {

	public static void main(String[] args) throws scaleConnectionException {
		ScaleConnection con = new ScaleConnection("169.254.2.3");
		con.displayMsg("mathias");
		System.out.println(con.doTara());
		System.out.println(con.getInteger("Skriv et tal"));
		con.setOperatorName("Mathias");
		con.setProductBatchName("Sæd");
		con.setComponentName("Jerk off");
		con.displayMsg("Gør klar til afvejning");
		System.out.println(con.getMass());

	}

}
