<%@page import="mediatek2022.Mediatheque"%>
<%@page import="mediatek2022.Utilisateur"%>
<%@page import="mediatek2022.Document"%>
<%
Utilisateur user = (Utilisateur) session.getAttribute("user");
    if(user == null || user.isBibliothecaire()) {
        response.sendRedirect("./index.jsp");
    }
    int numDoc = Integer.parseInt(request.getParameter("id"));
    Document aSup = (Document) Mediatheque.getInstance().getDocument(numDoc);
    aSup.emprunt(user);

    String nom = (String)user.data()[0];
    String password = (String)user.data()[1];
    session.removeAttribute("user");
    Utilisateur utilisateurMaj = Mediatheque.getInstance().getUser(nom, password);
    session.setAttribute("user", utilisateurMaj);
    response.sendRedirect("clientPanel.jsp");
%>