	
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Finance decision page</title>
</head>
<body>
    
     
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of pending reimbursements</h2></caption>
	
            <tr>
                <th>Username</th>
                <th>Amount</th>
                <th>Date submitted</th>
                <th>Description</th>
                <th>Author full name</th>
                <th>Status</th>
                <th>Type</th>
                <th>Approve</th>
                <th>Deny</th>
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