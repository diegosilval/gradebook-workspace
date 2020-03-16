<%@ include file="../init.jsp" %>
<%
Assignment assignment=null; 
%>

<liferay-portlet:actionURL var="addAssignmentURL" name="<%= MVCCommandNames.EDIT_ASSIGNMENT %>">
</liferay-portlet:actionURL>
<aui:form action="${addAssignmentURL}">

	<aui:model-context bean="<%= assignment %>" model="<%=Assignment.class %>"></aui:model-context>

	<aui:input  name="title" />
	<aui:input  name="description"/>
	<aui:input  name="dueDate"/>
	
	<aui:button type="submit" value="save"/>
</aui:form>