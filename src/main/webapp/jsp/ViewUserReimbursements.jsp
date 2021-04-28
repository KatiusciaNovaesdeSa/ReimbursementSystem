<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User reimbursements</title>
</head>
<body>
 <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of all Reimbursements <c:out value="${reimbursement.authorFullName}" /></h2></caption>
           
            <tr>
                <th>Amount</th>
                <th>Date submitted</th>
                <th>Description</th>
                <th>Author full name</th>
                <th>Status</th>
                <th>Type</th>
            </tr>
            <c:forEach var="reimbursement" items="${reimbursements}">
                <tr>
                    <td><c:out value="${reimbursement.amount}" /></td>
                    <td><c:out value="${reimbursement.submitted}" /></td>
                    <td><c:out value="${reimbursement.description}" /></td>
                    <td><c:out value="${reimbursement.authorFullName}" /></td>
                    <td><c:out value="${reimbursement.status}" /></td>
                    <td><c:out value="${reimbursement.type}" /></td>
                </tr>
            </c:forEach>
        </table>
    </div>

</body>
</html>