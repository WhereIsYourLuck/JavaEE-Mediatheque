<%@page import="mediatek2022.Utilisateur"%>
<%@page import="mediatek2022.Mediatheque"%>
<%
    Utilisateur user = null;
    user = (Utilisateur)session.getAttribute("user");
    String ok = "";
    if(user == null || !user.isBibliothecaire()){
        response.sendRedirect("http://localhost:8080/JaveEEMediatheque");
    }
    
%>
<!DOCTYPE html>
<html>
    <title>Interface bibliothécaire</title>
    <meta charset="UTF-8">
<body>
<p>ok</p>
</body>
</html>