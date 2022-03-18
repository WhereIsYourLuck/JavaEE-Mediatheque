<%@page import="mediatek2022.Mediatheque"%>
<%@page import="mediatek2022.Utilisateur"%>
<%@page import="mediatek2022.Document"%>
<%@ page import="java.util.List" %>
<%
    String username = request.getParameter("login");
    String password = request.getParameter("password");
    //Utilisateur user = Mediatheque.getInstance().getUser(username, password);
    //Document doc = Mediatheque.getInstance().getDocument(1);
    List<Document> laListe = Mediatheque.getInstance().tousLesDocumentsDisponibles();
    
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
      <%=laListe%>
</body>
</html>