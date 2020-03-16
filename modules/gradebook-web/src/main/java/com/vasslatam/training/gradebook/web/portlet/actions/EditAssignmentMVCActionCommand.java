package com.vasslatam.training.gradebook.web.portlet.actions;

import java.util.Date;
import java.util.Locale;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.vasslatam.training.gradebook.service.AssignmentService;
import com.vasslatam.training.gradebook.web.constants.GradebookPortletKeys;
import com.vasslatam.training.gradebook.web.constants.MVCCommandNames;
@Component(
		immediate = true,
		property = {
			 	
			"javax.portlet.name=" + GradebookPortletKeys.GRADEBOOK ,
			"mvc.command.name=" + MVCCommandNames.EDIT_ASSIGNMENT
		},
		service = MVCActionCommand.class
	)
public class EditAssignmentMVCActionCommand implements MVCActionCommand {

	@Override
	public boolean processAction(ActionRequest actionRequest, ActionResponse actionResponse) throws PortletException {
		
		try {
			Map<Locale, String> title = LocalizationUtil.getLocalizationMap(actionRequest, "title");
			String description=ParamUtil.getString(actionRequest, "description");
			Date dueDate = ParamUtil.getDate(
					actionRequest, "dueDate",
					DateFormatFactoryUtil.getDate(actionRequest.getLocale()));
			ThemeDisplay themeDisplay=(ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
			//long userId = themeDisplay.getUserId();
			long groupId = themeDisplay.getScopeGroupId();
			ServiceContext serviceContext = ServiceContextFactory.getInstance(actionRequest);
			
			_assignmentService.addAssignment(groupId, title, description, dueDate, serviceContext);
			
			
		} catch (PortalException e) {
			_log.error(e);
			return false;
		}
		return true;
	}
	
	private static final Log _log=LogFactoryUtil.getLog(EditAssignmentMVCActionCommand.class);
	
	@Reference
	private AssignmentService _assignmentService;

}
