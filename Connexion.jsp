<!DOCTYPE html>
<html>
<head>
    <title>Connexion</title>
</head>
<%
    String Nom = request.getParameter("Nom");
    String mdp = request.getParameter("mdp");

%>
<body>
    <form>
        <label for="login">Nom</label><br>
        <input type="text" id="lgn" name="login"><br>
        <label for="password">Mot de passe</label><br>
        <input type="text" id="mdp" name="password"><br>
        <input type="submit" value="Submit">
      </form>
</body>
</html>