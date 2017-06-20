package scaleMachine;

import java.io.IOException;

import exceptions.DALException;
import exceptions.scaleConnectionException;
import test.FakeScaleConnection;

public class ScaleRunnable implements Runnable{
	IScaleConnection sc;
	ProcedureController p;
	String ip;
	@Override
	public void run() {
		if(ip==null){
			sc = new FakeScaleConnection();
		}else{
			sc= new ScaleConnection(ip);
		}
		
		p = new ProcedureController(sc, false);
		try {
			p.startScaleProcess();
		} catch (DALException | scaleConnectionException e) {
			e.printStackTrace();
		}
	}
	public void setIP(String IP){
		if(IP.length()>10){
			this.ip=IP;
		}
	}
}
