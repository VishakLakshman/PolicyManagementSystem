<jsp:include page="../../header.jsp"></jsp:include>
<%
	String path = application.getContextPath() + "/index.jsp";
	response.getWriter().print(
			"<div class=\"display-message\" align=\"center\">" + "<font color=\"black\">"
					+ "Un-authorized access to the page</font></div>");
	response.getWriter().print("You will be redirected Shortly");
	response.getWriter().print(
			"<META http-equiv=\"refresh\" content=\"5;URL=" + path
					+ "\">");
%>