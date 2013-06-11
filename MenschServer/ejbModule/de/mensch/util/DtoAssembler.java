package de.mensch.util;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import de.mensch.dto.AccountTO;
import de.mensch.dto.CustomerTO;
import de.mensch.dto.DiceResponse;
import de.mensch.dto.DiceTO;
import de.mensch.entities.*;

@Stateless
public class DtoAssembler {
	
  public CustomerTO makeDTO(CustomerTO customer) {
	  CustomerTO dto = new CustomerTO();
	  dto.setId(customer.getId());
	  dto.setPassword(customer.getPassword());
	  dto.setUserName(customer.getUserName());
	  return dto;
  }
}
