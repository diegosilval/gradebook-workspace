package com.vasslatam.training.gradebook.web.portlet.actions;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
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
		// TODO Auto-generated method stub
		return "/assignment/edit_assignment.jsp";
	}

}
