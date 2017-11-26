<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Attendance Tracker</title>
</head>
<style>
.textbox {
    width: 100px;
    height: 20px;
    box-sizing: border-box;
    border: 2px solid #ccc;
    background-color: #f8f8f8;
}
.image{
    background-image: url(../images/attendance.png);
    background-position: center top;
    background-size: 350px 230px;
    background-repeat: no-repeat;
}
.thisdiv{
	height:200px;
	width:450px;
	text-align:center;
	background-color:#BEBEBE;
	padding-top:120px;
	margin:200px 0 0 450px;
	border:2px solid red;
	}
</style>
<body class="image">
   <div class="thisdiv">
    <form action="QuickServlet" method="post">
        <label style="font-weight: bold;">Key: </label><input type="text" size="5" name="Key" class="textbox"/>
        &nbsp;&nbsp;
        <label style="font-weight: bold;">Student Id:</label> <input type="number" size="5" name="StudentId" class="textbox"/>
        &nbsp;&nbsp;
        <input type="submit" value="submit" />
    </form>
   </div>
</body>
</html>