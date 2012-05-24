package ar.edu.itba.paw.grupo1.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.itba.paw.grupo1.model.Picture;
import ar.edu.itba.paw.grupo1.model.Property;

@Repository
public class PictureHibernateDao extends GenericHibernateRepository<Picture> implements
		PictureRepository {

	@Autowired
	public PictureHibernateDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public List<Picture> getPictures(Property property) {
		Criteria criteria = createCriteria()
				.add(Restrictions.eq("property.id", property.getId()));
		
		return criteria.list();
	}

}
