package com.vasslatam.training.gradebook.web.portlet.actions;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.vasslatam.training.gradebook.service.AssignmentService;
import com.vasslatam.training.gradebook.web.constants.GradebookPortletKeys;
import com.vasslatam.training.gradebook.web.constants.MVCCommandNames;
@Component(
		immediate = true,
		property = {
			 	
			"javax.portlet.name=" + GradebookPortletKeys.GRADEBOOK ,
			"mvc.command.name=" + MVCCommandNames.DELETE_ASSIGNMENT
		},
		service = MVCActionCommand.class
	)
public class DeleteAssignmentMVCActionCommand implements MVCActionCommand {

	@Override
	public boolean processAction(ActionRequest actionRequest, ActionResponse actionResponse) throws PortletException {
		long assignmentId=ParamUtil.getLong(actionRequest, "assignmentId");
		
		try {
			_assignmentService.deleteAssignment(assignmentId);
			return true;
		} catch (PortalException e) {
			_log.error(e);
		}
		
		return false;
	}
	@Reference
	private AssignmentService _assignmentService;
	
	private static final Log _log=LogFactoryUtil.getLog(DeleteAssignmentMVCActionCommand.class);
}
