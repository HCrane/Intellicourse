package classes;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.SQLQuery;

import classes.*;

/**
 * This Class handles everything for communicating with the database
 *
 * @author Stettinger
 *
 */

public class HibernateSupport {

	private static SessionFactory sessionFactory;

	// static {
	// 	//System.out.println("HibernateSupport: Constructor");
	// 	init();
	// }

	public static void create(){
		// function is not necessary it only activates the static construction above
	}

	public static void deinit()
	{
		System.out.println("Close Hibernate Session.");
		getCurrentSession().close();
	}

	public static void init() {
		System.out.println("Start Hibernate Session.");
		File configFile = new File("hibernate.cfg.xml");

		Configuration configuration = new Configuration();

		//TODO add all classes you want to annotate
		configuration.addAnnotatedClass(StdUser.class);
		configuration.addAnnotatedClass(StudentUser.class);
		configuration.addAnnotatedClass(Happening.class);
		configuration.addAnnotatedClass(Course.class);

		configuration.configure(configFile);

		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	}

	private static Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}


	public static void beginTransaction() {
		getCurrentSession().beginTransaction();
	}

	public static void commitTransaction() {
		getCurrentSession().getTransaction().commit();
	}

	public static boolean commit(Object obj) {
		try {
			getCurrentSession().saveOrUpdate(obj);
		}
		catch (HibernateException e) {
			System.out.println(e);
			return false;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	public static <T> List<T> readMoreObjects(Class<?> classToRetrieve, List<Criterion> criterions) {
		beginTransaction();
		Criteria criteria = getCurrentSession().createCriteria(classToRetrieve).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		if (criterions != null) {
			for(Criterion criterion: criterions) {
				criteria.add(criterion);
			}
		}
		List<T> result = criteria.list();
		commitTransaction();

		return result;
	}

	@SuppressWarnings("unchecked")
	public static <T> List<T> readMoreObjectswithSQL(Class<?> classToRetrieve, String sql) {
		beginTransaction();
		if (sql == "") {
			return null;
		}
		SQLQuery query = getCurrentSession().createSQLQuery(sql);
		query.addEntity(classToRetrieve);
		List<T> result = query.list();
		commitTransaction();
		return result;
	}

	public static <T> T readOneObject(Class<?> classToRetrieve, List<Criterion> criterions) {
		List<T> result = readMoreObjects(classToRetrieve, criterions);
		return (result.size() > 0) ? (result.get(0)):(null);
	}

	public static <T> T readOneObjectByID(Class<?> classToRetrieve, int id) {
		List<Criterion> criterions = new ArrayList<Criterion>();
		criterions.add(Restrictions.idEq(id));
		T result = readOneObject(classToRetrieve, criterions);
		return result;
	}

	public static <T> void deleteObject(T objectToDelete) {
		getCurrentSession().delete(objectToDelete);
	}

}
