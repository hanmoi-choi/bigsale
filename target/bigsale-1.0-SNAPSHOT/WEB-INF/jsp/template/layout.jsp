<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<link type="text/css" rel="stylesheet"
      href="/bigsale/resources/css/main.css"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><tiles:insertAttribute name="title" ignore="true" /></title>
</head>
<body>
<table border="1" cellpadding="2" cellspacing="2" align="center" width="1280" height="720">
	<tr>
		<td id="header" height="30" colspan="2"><tiles:insertAttribute name="header" />
		</td>
	</tr>
	<tr>
		<td id="menu" width="200"><tiles:insertAttribute name="menu" /></td>
		<td id="body"><tiles:insertAttribute name="body" /></td>
	</tr>
	<tr>
		<td id="footer" height="30" colspan="2"><tiles:insertAttribute name="footer" />
		</td>
	</tr>
</table>
</body>
</html>
