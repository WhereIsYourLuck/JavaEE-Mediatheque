import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class InitListener implements ServletContextListener {

/* mettre dans le web.xml
<listener>
    <listener-class>InitListener</listener-class>
</listener>
*/

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		Class.forName("persistance.MediathequeData");
		System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
	}

}
