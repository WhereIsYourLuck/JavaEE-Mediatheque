
<%
    String Nom = request.getParameter("login");
    String mdp = request.getParameter("password");
    
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
        <input type="submit" value="Submit">
      </form>
      <p><% int Valeur="Ok";<p>
</body>
</html>