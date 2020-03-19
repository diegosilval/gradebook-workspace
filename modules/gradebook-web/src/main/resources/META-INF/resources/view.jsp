<%@ include file="init.jsp" %>

<liferay-portlet:renderURL var="newAssignmentURL">
	<portlet:param name="mvcRenderCommandName"
				value="<%=MVCCommandNames.EDIT_ASSIGNMENT %>" />
	<portlet:param name="backURL" value="<%= currentURL %>"/>
	<portlet:param name="CMD" value="<%=Constants.ADD %>"/>
</liferay-portlet:renderURL>

<aui:button href="${newAssignmentURL }" value="new"/>  

<liferay-ui:search-container total="${assignmentsCount }"  >
	<liferay-ui:search-container-results results="${assignments}" />
	
	<liferay-ui:search-container-row className="com.vasslatam.training.gradebook.model.Assignment" modelVar="item" >
		<liferay-ui:search-container-column-text name="assignment.title"  >
			<%= item.getTitle(locale) %>
		</liferay-ui:search-container-column-text>
		<liferay-ui:search-container-column-text title="assignment.description" property="description"/>
		<liferay-ui:search-container-column-text>
			<liferay-ui:icon-menu markupView="lexicon">
						<liferay-portlet:renderURL var="editAssignmentURL">
							<portlet:param name="mvcRenderCommandName"
										value="<%=MVCCommandNames.EDIT_ASSIGNMENT %>" />
							<portlet:param name="assignmentId" value="<%= String.valueOf(  item.getAssignmentId() )%>"/>
							<portlet:param name="backURL" value="<%= currentURL %>"/>
							<portlet:param name="CMD" value="<%=Constants.EDIT %>"/>
						</liferay-portlet:renderURL>
						<liferay-ui:icon message="edit" url="${editAssignmentURL }"  />
						
						<c:if test="<%=AssignmentModelPermission.contains(permissionChecker, item.getAssignmentId(), ActionKeys.PERMISSIONS) %>">
						
							<liferay-portlet:actionURL var="deleteAssignmentURL" name="<%=MVCCommandNames.DELETE_ASSIGNMENT %>">
								<portlet:param name="assignmentId" value="<%= String.valueOf(  item.getAssignmentId() )%>"/>
							</liferay-portlet:actionURL>
							<liferay-ui:icon-delete url="${deleteAssignmentURL }"></liferay-ui:icon-delete>
						</c:if>
					</liferay-ui:icon-menu>
		</liferay-ui:search-container-column-text>
	</liferay-ui:search-container-row>
	
	<liferay-ui:search-iterator />
	
</liferay-ui:search-container>
 