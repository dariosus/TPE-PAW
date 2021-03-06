<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<c:if test="${empty hasPermissions}">
	<p class="alert alert-error">You don't have permissions to view this page</p>
</c:if>



<c:if test="${not empty hasPermissions}">
	<div class="page-header"><h1>
			Add New Room
	</h1></div>
	
	<c:set var="url">
		property/addRoom/${property.id}
	</c:set>
	
	
	<form:form action="${basePath }/${url }" method="post" commandName="roomForm">
		
		<form:errors path="*" cssClass="alert alert-error"/>
	
		<div>
			<label for="name">Name:</label>
			<form:input path="name" />
		</div>
		<br/>
		<div class="row">
			<div class="span3">
				<label for="length">Length:</label>
				<form:input path="length" />
			</div>
			
			<div class="span3 offset1">
				<label for="width">Width:</label>
				<form:input path="width" />
			</div>
		</div>
		
		<br />
		<div>
			<input type="submit" name="submit" value="Submit" class="btn btn-primary"/>
		</div>	
		
	</form:form>
</c:if>