<%@ include file="../init.jsp" %>
<%
Assignment assignment=(Assignment)renderRequest.getAttribute("assignment") ; 
%>

<liferay-ui:header title="assginment-edit" backURL="<%=backURL %>">
</liferay-ui:header>

<liferay-portlet:actionURL var="addAssignmentURL" name="<%= MVCCommandNames.EDIT_ASSIGNMENT %>">
</liferay-portlet:actionURL>
<aui:form action="${addAssignmentURL}">

	<aui:model-context bean="<%= assignment %>" model="<%=Assignment.class %>"></aui:model-context>

	<aui:input  name="assignmentId" type="hidden" />
	<aui:input name="CMD" value="<%= assignment==null? Constants.ADD:Constants.EDIT   %>" type="hidden"/>
	<aui:input  name="title" />
	<aui:input  name="description"/>
	<aui:input  name="dueDate"/>
	
	<aui:button-row>
		<aui:button type="submit" value="save"/>
		<aui:button type="cancel" value="cancel" href="<%=backURL %>"/>
	</aui:button-row>
</aui:form>