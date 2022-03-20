<%@page import="mediatek2022.Mediatheque"%>
<%@page import="mediatek2022.Utilisateur"%>
<%@page import="mediatek2022.Document"%>
<%@ page import="java.util.List" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%

    String username = request.getParameter("login");
    String password = request.getParameter("password");
    Utilisateur user = Mediatheque.getInstance().getUser(username, password);
    //On vérifie l'utilisateur pour savoir où le rediriger et gérer sa variable session
    if(user != null){
        if(user.isBibliothecaire()){
            session.setAttribute("user", user);
            response.sendRedirect("./bibliothecairePanel.jsp");  
        } else {
            session.setAttribute("user", user);
            response.sendRedirect("./clientPanel.jsp");
        }
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Connexion</title>
</head>

<body>
    <h1> Bienvenue sur la mediatek2022</h1>
    <p> Connectez-vous pour continuer</p>
    <form action="index.jsp" method="post">
        <label for="login">Nom</label><br>
        <input type="text" id="lgn" name="login"><br>
        <label for="password">Mot de passe</label><br>
        <input type="text" id="mdp" name="password"><br>
        <input type="submit" value="Se connecter"><br>
      </form>
</body>
</html>