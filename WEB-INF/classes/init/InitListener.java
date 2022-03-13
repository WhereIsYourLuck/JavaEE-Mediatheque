package init;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class InitListener implements ServletContextListener {
	//Au lancement de Tomcat, appel à cette classe grâce au init listener dans le web.xml
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		try {
			Class.forName("persistance.MediathequeData"); //Créer l'instance mediathequeData
			System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAA"); // Que ce soit clairement visible à la génération du serveur
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
	}
}
