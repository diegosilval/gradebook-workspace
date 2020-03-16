<%@ include file="init.jsp" %>

<liferay-portlet:renderURL var="newAssignmentURL">
	<portlet:param name="mvcRenderCommandName"
				value="<%=MVCCommandNames.EDIT_ASSIGNMENT %>" />
</liferay-portlet:renderURL>

<aui:button href="${newAssignmentURL }" value="new">
</aui:button>