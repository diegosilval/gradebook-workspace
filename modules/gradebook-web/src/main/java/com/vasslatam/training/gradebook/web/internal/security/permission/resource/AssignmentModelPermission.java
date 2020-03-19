package com.vasslatam.training.gradebook.web.internal.security.permission.resource;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.vasslatam.training.gradebook.model.Assignment;

@Component(immediate = true)
public class AssignmentModelPermission {
	public static boolean contains(PermissionChecker permissionChecker, Assignment assignment, String actionId)
			throws PortalException {

		return _assignmentModelResourcePermission.contains(permissionChecker, assignment, actionId);
	}

	public static boolean contains(PermissionChecker permissionChecker, long guestbookId, String actionId)
			throws PortalException {

		return _assignmentModelResourcePermission.contains(permissionChecker, guestbookId, actionId);
	}

	@Reference(target = "(model.class.name=com.vasslatam.training.gradebook.model.Assignment)", unbind = "-")
	protected void setEntryModelPermission(ModelResourcePermission<Assignment> modelResourcePermission) {

		_assignmentModelResourcePermission = modelResourcePermission;
	}

	private static ModelResourcePermission<Assignment> _assignmentModelResourcePermission;

}
