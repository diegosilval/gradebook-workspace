<%@page import="com.vasslatam.training.gradebook.web.internal.security.permission.resource.AssignmentModelPermission"%>
<%@page import="com.vasslatam.training.gradebook.web.internal.security.permission.resource.GradebookPermission"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.liferay.portal.kernel.util.PortalUtil"%>
<%@page import="com.vasslatam.training.gradebook.model.Assignment" %>
<%@page import="com.vasslatam.training.gradebook.web.constants.MVCCommandNames"%>
<%@page import="com.liferay.portal.kernel.util.Constants"%>
<%@page import="java.util.List"%>
<%@page import="com.liferay.portal.kernel.security.permission.ActionKeys"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<liferay-theme:defineObjects />

<portlet:defineObjects /> 

<%
	String currentURL= PortalUtil.getCurrentURL(renderRequest);
	String backURL = ParamUtil.getString(renderRequest,"backURL"); 
%>