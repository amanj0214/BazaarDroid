<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="MyServer" method="post">
           <table>
               <tr><td><input type="text" name="name"/></td></tr>
            <tr><td><input type="text" name="mobNo"/></td></tr>
            <tr><td><input type="submit" value="Sumbmit"/></td></tr>
           </table>
           <%
         /*  String getName = null;
           if(getName!=null)
               {*/
               String  getName = (String)request.getAttribute("sndName");
                String getNum = (String)request.getAttribute("sndmobNum");
                out.print(getName+" and "+getNum);
            //  }

           %>
           <%=getName%>
</form>         
</body>
</html>