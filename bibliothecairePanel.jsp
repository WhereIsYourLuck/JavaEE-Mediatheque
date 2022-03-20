<%@page import="mediatek2022.Mediatheque"%>
<%@page import="mediatek2022.Utilisateur"%>
<%@page import="mediatek2022.Document"%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    //On commence par vérifier que l'utilisateur est connecté et qu'il s'agisse bien d'un bibliothécaire
    Utilisateur user = (Utilisateur) session.getAttribute("user");
    if(user == null || !user.isBibliothecaire()){
        response.sendRedirect("index.jsp");
    }

    
%>

<!DOCTYPE html>
<html>
<head>
    <title>Interface bibliothécaire</title>
</head>

<body>
<h3>Bienvenue <%=user.name()%>, Vous pouvez ajouter des documents depuis cette interface.</h3>
    <form action="bibliothecairePanel.jsp" method="post">
        <label for="nomDoc">Nom du document :</label>
        <input type="text" name="nomDoc" id="nomDoc"><br>

        <label for="auteurDoc">Auteur :</label>
        <input type="text" name="auteurDoc" id="auteurDoc"><br>

        <label for="typeDoc"></label>
        <select id="type-document" name="type-document">
        <option value="CD">CD</option>
        <option value="DVD">DVD</option>
        <option value="Livre">Livre</option>
        </select><br>

        <input type="submit" value="Ajouter le document"><br><br>
    
<a href="deconnexion.jsp">Se déconnecter</a>
</body>
</html>