<%@page import="persistance.MediathequeData" %>
<%
    String pseudo = request.getParameter("login");
    String mdp = request.getParameter("password");
    this.getUser(pseudo, mdp);
    String notok = "";

%>
<!DOCTYPE html>
<html>
<head>
    <title>Connexion</title>
</head>

<body>
    <form>
        <label for="login">Nom</label><br>
        <input type="text" id="lgn" name="login"><br>
        <label for="password">Mot de passe</label><br>
        <input type="text" id="mdp" name="password"><br>
        <input type="submit" value="Submit"><br>
        <%=notok%>
      </form>
</body>
</html>