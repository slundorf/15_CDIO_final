package test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import businessLayer.BusinessLayerImplementation;
import businessLayer.IBusinessLayer;
import dto.UserDTO;
import exceptions.DALException;
import interfaces.IUserDAO;
import serDAO.SerUserDAO;

public class BusinessTest {

	@Test
	public void test() throws DALException {
		IUserDAO IUD = new FakeUserDAO();
		
		
	}

}
