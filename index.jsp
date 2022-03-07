<%@page import="mediatek2022.Mediatheque"%>
<%@page import="mediatek2022.Utilisateur"%>
<%
    String username = request.getParameter("login");
    String password = request.getParameter("password");
    Utilisateur user = Mediatheque.getInstance().getUser(username, password);
    String notok = "";
    if(user != null){
        if(user.isBibliothecaire()){
            response.sendRedirect("http://localhost:8080/JaveEEMediatheque/bibliothecairePanel.jsp");  
        } else {
            response.sendRedirect("http://localhost:8080/JaveEEMediatheque/clientPanel.jsp");
        }
    }
    
%>
<!DOCTYPE html>
<html>
<head>
    <title>Connexion</title>
</head>

<body>
    <form action="index.jsp" method="post">
        <label for="login">Nom</label><br>
        <input type="text" id="lgn" name="login"><br>
        <label for="password">Mot de passe</label><br>
        <input type="text" id="mdp" name="password"><br>
        <input type="submit" value="Submit"><br>
        <%=notok%>
      </form>
</body>
</html>