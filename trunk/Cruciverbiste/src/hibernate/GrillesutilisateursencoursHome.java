package hibernate;

// Generated 23 févr. 2012 22:45:54 by Hibernate Tools 3.4.0.CR1

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

/**
 * Home object for domain model class Grillesutilisateursencours.
 * @see hibernate.Grillesutilisateursencours
 * @author Hibernate Tools
 */
public class GrillesutilisateursencoursHome {

	private static final Log log = LogFactory
			.getLog(GrillesutilisateursencoursHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext()
					.lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException(
					"Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(Grillesutilisateursencours transientInstance) {
		log.debug("persisting Grillesutilisateursencours instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Grillesutilisateursencours instance) {
		log.debug("attaching dirty Grillesutilisateursencours instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Grillesutilisateursencours instance) {
		log.debug("attaching clean Grillesutilisateursencours instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Grillesutilisateursencours persistentInstance) {
		log.debug("deleting Grillesutilisateursencours instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Grillesutilisateursencours merge(
			Grillesutilisateursencours detachedInstance) {
		log.debug("merging Grillesutilisateursencours instance");
		try {
			Grillesutilisateursencours result = (Grillesutilisateursencours) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Grillesutilisateursencours findById(
			hibernate.GrillesutilisateursencoursId id) {
		log.debug("getting Grillesutilisateursencours instance with id: " + id);
		try {
			Grillesutilisateursencours instance = (Grillesutilisateursencours) sessionFactory
					.getCurrentSession().get(
							"hibernate.Grillesutilisateursencours", id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Grillesutilisateursencours instance) {
		log.debug("finding Grillesutilisateursencours instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("hibernate.Grillesutilisateursencours")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
