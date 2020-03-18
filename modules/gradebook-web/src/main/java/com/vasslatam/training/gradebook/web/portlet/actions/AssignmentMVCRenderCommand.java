package com.vasslatam.training.gradebook.web.portlet.actions;

import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.vasslatam.training.gradebook.model.Assignment;
import com.vasslatam.training.gradebook.service.AssignmentService;
import com.vasslatam.training.gradebook.web.constants.GradebookPortletKeys;
@Component(
		immediate = true,
		property = {
			 	
			"javax.portlet.name=" + GradebookPortletKeys.GRADEBOOK ,
			"mvc.command.name=/" 
		},
		service = MVCRenderCommand.class
	)
public class AssignmentMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		ThemeDisplay themeDisplay=(ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		long groupId=themeDisplay.getScopeGroupId();
		
		int currentPage = ParamUtil.getInteger(
			renderRequest, SearchContainer.DEFAULT_CUR_PARAM,
			SearchContainer.DEFAULT_CUR);

		int delta = ParamUtil.getInteger(
			renderRequest, SearchContainer.DEFAULT_DELTA_PARAM,
			SearchContainer.DEFAULT_DELTA);

		int start = ((currentPage > 0) ? (currentPage - 1) : 0) * delta;
		int end = start + delta;	 	
		
		List<Assignment> assignments = _assignmentService.findByGroupId(groupId,start,end);
		renderRequest.setAttribute("assignments", assignments);
		
		int assignmentsCount=_assignmentService.countByGroupId(groupId);
		renderRequest.setAttribute("assignmentsCount", assignmentsCount);
		
		return "/view.jsp";
	}
	
	@Reference
	private AssignmentService _assignmentService;

}
