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
 * Home object for domain model class Orientationmots.
 * @see hibernate.Orientationmots
 * @author Hibernate Tools
 */
public class OrientationmotsHome {

	private static final Log log = LogFactory.getLog(OrientationmotsHome.class);

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

	public void persist(Orientationmots transientInstance) {
		log.debug("persisting Orientationmots instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Orientationmots instance) {
		log.debug("attaching dirty Orientationmots instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Orientationmots instance) {
		log.debug("attaching clean Orientationmots instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Orientationmots persistentInstance) {
		log.debug("deleting Orientationmots instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Orientationmots merge(Orientationmots detachedInstance) {
		log.debug("merging Orientationmots instance");
		try {
			Orientationmots result = (Orientationmots) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Orientationmots findById(java.lang.Integer id) {
		log.debug("getting Orientationmots instance with id: " + id);
		try {
			Orientationmots instance = (Orientationmots) sessionFactory
					.getCurrentSession().get("hibernate.Orientationmots", id);
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

	public List findByExample(Orientationmots instance) {
		log.debug("finding Orientationmots instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("hibernate.Orientationmots")
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
