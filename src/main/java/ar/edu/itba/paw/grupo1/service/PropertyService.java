package ar.edu.itba.paw.grupo1.service;

import java.util.List;

import ar.edu.itba.paw.grupo1.model.Property;
import ar.edu.itba.paw.grupo1.model.User;

public interface PropertyService {

	public List<Property> getProperties(int userId);

	public Property getById(int id);

	public void save(Property property, User user);
}
