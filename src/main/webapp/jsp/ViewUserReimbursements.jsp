<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User reimbursements</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<br><br>
 <div align="center" class="container">
       <!-- <table border="1" cellpadding="5">  --> 
        <caption><h2>List of All Reimbursements</h2></caption>      
        <table class="table table-striped table-success table-hover">
            
            <tr>
                <th scope="col">Amount</th>
                <th scope="col">Date submitted</th>
                <th scope="col">Description</th>
                <th scope="col">Author Name</th>
                <th scope="col">Status</th>
                <th scope="col">Type</th>
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