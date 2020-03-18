<%@page import="java.util.List"%>
<%@ include file="init.jsp" %>

<liferay-portlet:renderURL var="newAssignmentURL">
	<portlet:param name="mvcRenderCommandName"
				value="<%=MVCCommandNames.EDIT_ASSIGNMENT %>" />
</liferay-portlet:renderURL>

<aui:button href="${newAssignmentURL }" value="new"/>

<% List<Assignment> assignments=(List<Assignment>)renderRequest.getAttribute("assignments");  %>

<table>
	<thead>
	</thead>
	<tbody>
		<%for(Assignment item:assignments){ %>
			<tr>
				<td><%= item.getTitle(locale) %> </td> 
				<td><%=item.getDescription() %></td>
			</tr>
		<%} %>
	</tbody>
</table>