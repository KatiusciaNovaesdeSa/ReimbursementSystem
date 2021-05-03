	
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Finance decision page</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<!-- <link href="../css/styles.css" rel="stylesheet" type="text/css"> -->
</head>
<body>
    
   <br><br><br>
   <div align="center" class="container">
     <!--   <table border="1" cellpadding="5" >  --> 
        <c:set var="admin" value=" Hello, admin" />
        <c:out value="${admin}"/>
        <caption><h2>List of Pending Reimbursements </h2></caption>
        <table class="table table-success table-striped table-hover" >
	
            <tr>
                <th scope="col">Username</th>
                <th scope="col">Amount</th>
                <th scope="col">Date Submitted</th>
                <th scope="col">Description</th>
                <th scope="col">Author Name</th>
                <th scope="col">Status</th>
                <th scope="col">Type</th>
                <th scope="col">Approve</th>
                <th scope="col">Deny</th>
            </tr>
            <c:forEach var="reimbursement" items="${reimbursements}">
                <tr>
                    <td><c:out value="${reimbursement.username}" /></td>
                    <td><c:out value="${reimbursement.amount}" /></td>
                    <td><c:out value="${reimbursement.submitted}" /></td>
                    <td><c:out value="${reimbursement.description}" /></td>
                    <td><c:out value="${reimbursement.authorFullName}" /></td>
                    <td><c:out value="${reimbursement.status}" /></td>
                    <td><c:out value="${reimbursement.type}" /></td>
                    <td><a href="<c:url value="/ApprovalServlet">
        						 <c:param name="id" value="${reimbursement.reimid}"/>
      							 </c:url>">Approve</a></td>         
				    <td><a href="<c:url value="/DenyServlet">
        						 <c:param name="id" value="${reimbursement.reimid}"/>
      							 </c:url>">Deny</a></td>
                </tr>
            </c:forEach>
        </table>
        
    </div>
</body>
</html>