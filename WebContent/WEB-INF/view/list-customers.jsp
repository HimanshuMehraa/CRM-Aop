<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>LIST CUSTOMERS</title>
 
<link type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet" >

</head>
<body>

<div id="wrapper">
  <div id="header">
   <h2>Customer relationship Manager</h2>
  </div>
</div>



<div id= "container">
  <div id= "content">
    <input type="button" value="Add Customer" 
       onClick="window.location.href='showFormForAdd'; return false"
       class= "add-button"/>
   <table>
      <tr>
       <th>First Name</th>
       <th>Last Name</th>
       <th>Email</th>
       <th>Action</th>
      </tr>
      
     <c:forEach  var="tempCustomers" items= "${customers}">
     <!-- Construct an update link with customer id -->
      <c:url var="updateLink" value="/customer/showFormForUpdate">
         <c:param name="customerId" value="${tempCustomers.id}"/>
      </c:url>
      
      <!-- Construct an delete link with customer id -->
      <c:url var="deleteLink" value="/customer/delete">
         <c:param name="customerId" value="${tempCustomers.id}"/>
      </c:url>
      
      
       <tr>
        <td> ${tempCustomers.firstName} </td>
        <td> ${tempCustomers.lastName} </td>
        <td> ${tempCustomers.email} </td>
        <td>
          <a href="${updateLink}">Update</a>
          | <a href="${deleteLink}" 
          onClick= "if(!(confirm('Are you sure you want to delete this customer?'))) return false">Delete</a>
         </td>
       </tr>
     </c:forEach>
      </table>
      
  </div>
</div>


</body>
</html>