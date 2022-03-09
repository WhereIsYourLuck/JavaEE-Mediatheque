<%@page import="mediatek2022.Utilisateur"%>
<%@page import="mediatek2022.Mediatheque"%>
<%
    Utilisateur user = null;
    user = (Utilisateur)session.getAttribute("user");
    System.out.println(user);
    String ok = "";
    if(user == null || user.isBibliothecaire()){
        response.sendRedirect("http://localhost:8080/JaveEEMediatheque");
    }
    
%>
<!DOCTYPE html>
<html>
    <meta charset="UTF-8">
    <title>Interface client</title>
<body>
<p><%=ok%></p>
    


</body>
</html>