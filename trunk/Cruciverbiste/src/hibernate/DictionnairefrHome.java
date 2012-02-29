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
 * Home object for domain model class Dictionnairefr.
 * @see hibernate.Dictionnairefr
 * @author Hibernate Tools
 */
public class DictionnairefrHome {

	private static final Log log = LogFactory.getLog(DictionnairefrHome.class);

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

	public void persist(Dictionnairefr transientInstance) {
		log.debug("persisting Dictionnairefr instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Dictionnairefr instance) {
		log.debug("attaching dirty Dictionnairefr instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Dictionnairefr instance) {
		log.debug("attaching clean Dictionnairefr instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Dictionnairefr persistentInstance) {
		log.debug("deleting Dictionnairefr instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Dictionnairefr merge(Dictionnairefr detachedInstance) {
		log.debug("merging Dictionnairefr instance");
		try {
			Dictionnairefr result = (Dictionnairefr) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Dictionnairefr findById(int id) {
		log.debug("getting Dictionnairefr instance with id: " + id);
		try {
			Dictionnairefr instance = (Dictionnairefr) sessionFactory
					.getCurrentSession().get("hibernate.Dictionnairefr", id);
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

	public List findByExample(Dictionnairefr instance) {
		log.debug("finding Dictionnairefr instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("hibernate.Dictionnairefr")
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
