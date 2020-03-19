package com.vasslatam.training.gradebook.web.portlet.actions;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.vasslatam.training.gradebook.model.Assignment;
import com.vasslatam.training.gradebook.service.AssignmentService;
import com.vasslatam.training.gradebook.web.constants.GradebookPortletKeys;
import com.vasslatam.training.gradebook.web.constants.MVCCommandNames;
@Component(
		immediate = true,
		property = {
			 	
			"javax.portlet.name=" + GradebookPortletKeys.GRADEBOOK ,
			"mvc.command.name=" + MVCCommandNames.EDIT_ASSIGNMENT 
		},
		service = MVCRenderCommand.class
	)
public class EditAssignmentMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		String cmd=ParamUtil.getString(renderRequest, "CMD");
		if (Constants.EDIT.equals(cmd)) {
			try {
				long assignmentId=ParamUtil.getLong(renderRequest, "assignmentId");
				Assignment assignment = _assignmentService.getAssignment(assignmentId);
				
				renderRequest.setAttribute("assignment", assignment);
				
			} catch (PortalException e) {
				_log.error(e);
			}
					
		}
		return "/assignment/edit_assignment.jsp";
	}
	@Reference
	private AssignmentService _assignmentService;
	private static final Log _log=LogFactoryUtil.getLog(EditAssignmentMVCRenderCommand.class);
}
