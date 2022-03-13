package init;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class InitListener implements ServletContextListener {
	//Au lancement de Tomcat, appel � cette classe gr�ce au init listener dans le web.xml
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		try {
			Class.forName("persistance.MediathequeData"); //Cr�er l'instance mediathequeData
			System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAA"); // Que ce soit clairement visible � la g�n�ration du serveur
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
	}
}
