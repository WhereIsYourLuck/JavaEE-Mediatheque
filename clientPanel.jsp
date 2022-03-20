<%@page import="mediatek2022.Mediatheque"%>
<%@page import="mediatek2022.Utilisateur"%>
<%@page import="mediatek2022.Document"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%!
public String aRendre(List<Document> docEmpruntes){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < docEmpruntes.size(); i++) {
            Document document = docEmpruntes.get(i);
            String nom = document.toString();
            String[] words = nom.split(" ");
            int num = Integer.parseInt(words[0]);
            sb.append("<tr><td>")
            .append(document.toString())
            .append(" ")
            .append("<a href='./retourDocument.jsp?id=" + num + "'>Rendre</a><br>");
        }
        return sb.toString();
    };
%>

<%!
public String aEmprunter(List<Document> docEmpruntes){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < docEmpruntes.size(); i++) {
            Document document = docEmpruntes.get(i);
            String nom = document.toString();
            String[] words = nom.split(" ");
            int num = Integer.parseInt(words[0]);
            sb.append("<tr><td>")
            .append(document.toString())
            .append(" ")
            .append("<a href='./empruntDocument.jsp?id=" + num + "'>Emprunter</a><br>");
        }
        return sb.toString();
    };
%>

<%
    Utilisateur user = (Utilisateur) session.getAttribute("user");
    if(user == null || user.isBibliothecaire()){
        response.sendRedirect("index.jsp");
    }
    List<Document> docsLibres = Mediatheque.getInstance().tousLesDocumentsDisponibles();
    Object[] donneesUser = user.data();
    List<Document> docEmpruntes = (List) donneesUser[3];

    String DocsEmprunts = aRendre(docEmpruntes);
    String DocsDispo = aEmprunter(docsLibres);
   
%>
<!DOCTYPE html>
<html>
<head>
    <title>Interface Client</title>
</head>

<body>
    <h3> Bienvenue, <%=user.name()%> !</h3>
    <p> Grâce à votre interface client, vous pouvez organiser la gestion de vos documents. </p>
    <h4>Les documents disponibles à l'emprunt : </h4>
    <%=DocsDispo%>
    <h4> Vos documents actuels : </h4>
    <%=DocsEmprunts%>
    <a href="deconnexion.jsp">Se déconnecter</a>
</body>
</html>