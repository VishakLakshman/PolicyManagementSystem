<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<!-- <script type="text/javascript">
	function loadAll() {
		var url = window.location.pathname;
		var filename = url.substring(url.lastIndexOf('/') + 1);
		if (filename != "register.jsp") {
			checkuser();
			if (filename == "userpage.jsp")
				getPolicyId();
			if (filename == "adminpage.jsp")
				getThings();
		}
	}
</script> -->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">


<!-- DON'T CHANGE ORDER OF FILES -->
<!-- JQUERY -->
<script src="<%=request.getContextPath()%>/js/jquery 1.12.0.min.js"></script>

<!-- js fileS -->
<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/js/formValidation.js"></script>
<script
	src="<%=request.getContextPath()%>/js/bootstrapFormValidation.js"></script>
<script src="<%=request.getContextPath()%>/js/clickFunctions.js"></script>
<script src="<%=request.getContextPath()%>/js/validate.js"></script>

<!-- CSS files -->
<link href="<%=request.getContextPath()%>/css/custom css.css"
	type="text/css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/css/bootstrap.css"
	type="text/css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/css/formValidation.css"
	type="text/css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/css/creative 1.0.3.css"
	type="text/css" rel="stylesheet">
<!-- Custom Fonts -->
<link href="<%=request.getContextPath()%>/css/fonts.css"
	rel='stylesheet' type='text/css'>
<link href="<%=request.getContextPath()%>/css/fonts2.css"
	rel='stylesheet' type='text/css'>
<link href="<%=request.getContextPath()%>/css/fonts3.css"
	rel="stylesheet" type="text/css">

<!-- Plugin CSS -->
<%-- <link href="<%=request.getContextPath()%>/css/animate.css" rel="stylesheet" type="text/css">
 --%>

<%-- <title><%= session.getAttribute("title")%> | Policy Management System</title> --%>

<!-- Additional  -->

<!-- Bootstrap Core CSS -->
<!-- <link rel="stylesheet" href="css/backroundfixed.css" type="text/css">  -->
<%-- <link href="<%=request.getContextPath()%>/css/bootstrap.min.css" type="text/css" rel="stylesheet"> --%>
<!-- Custom CSS -->
<%-- <link rel="stylesheet" href="<%=request.getContextPath()%>/css/fonts5.css" type="text/css"> --%>

</head>