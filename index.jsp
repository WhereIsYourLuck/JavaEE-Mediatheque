<%@page import="mediatek2022.Mediatheque"%>
<%@page import="mediatek2022.Utilisateur"%>
<%
    String username = request.getParameter("login");
    String password = request.getParameter("password");
    Utilisateur user = Mediatheque.getInstance().getUser(username, password);
    
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
      </form>
</body>
</html>