import classes.*;
import java.io.File;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
/**
 *
 * @author Stettinger
 *
 */

public class DatabaseConstruction {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Start");

		File configFile = new File("hibernate.cfg.xml");

		Configuration configuration = new Configuration();

		//TODO add classes
		configuration.addAnnotatedClass(StdUser.class);
		configuration.addAnnotatedClass(StudentUser.class);
		configuration.addAnnotatedClass(TeacherUser.class);
		configuration.addAnnotatedClass(AdminUser.class);
		configuration.addAnnotatedClass(Happening.class);
		configuration.addAnnotatedClass(Course.class);
		configuration.addAnnotatedClass(Exam.class);
		configuration.addAnnotatedClass(Event.class);
		configuration.addAnnotatedClass(Place.class);
		configuration.addAnnotatedClass(Time.class);

		configuration.configure(configFile);

		new SchemaExport(configuration).create(true, true);

		System.out.println("Finished");
	}
}
