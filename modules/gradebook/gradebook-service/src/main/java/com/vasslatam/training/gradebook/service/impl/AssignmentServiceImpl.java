/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.vasslatam.training.gradebook.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.vasslatam.training.gradebook.model.Assignment;
import com.vasslatam.training.gradebook.service.base.AssignmentServiceBaseImpl;

/**
 * The implementation of the assignment remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * <code>com.vasslatam.training.gradebook.service.AssignmentService</code>
 * interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have
 * security checks based on the propagated JAAS credentials because this service
 * can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssignmentServiceBaseImpl
 */
@Component(property = { "json.web.service.context.name=gradebook",
		"json.web.service.context.path=Assignment" }, service = AopService.class)
public class AssignmentServiceImpl extends AssignmentServiceBaseImpl {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use
	 * <code>com.vasslatam.training.gradebook.service.AssignmentServiceUtil</code>
	 * to access the assignment remote service.
	 */
	public Assignment addAssignment(long groupId, Map<Locale, String> title, String description, Date dueDate,
			ServiceContext serviceContext) throws PortalException {

	 
		return assignmentLocalService.addAssignment(groupId, title, description, dueDate, serviceContext);
	}

	public Assignment deleteAssignment(long assignmentId) throws PortalException {
		Assignment assignment = assignmentLocalService.getAssignment(assignmentId);

	 
		return assignmentLocalService.deleteAssignment(assignment);
	}

	public Assignment getAssignment(long assignmentId) throws PortalException {
		Assignment assignment = assignmentLocalService.getAssignment(assignmentId);

		 

		return assignment;
	}

	public Assignment updateAssignment(long assignmentId, Map<Locale, String> titleMap, String description,
			Date dueDate, ServiceContext serviceContext) throws PortalException {

		Assignment assignment = assignmentLocalService.getAssignment(assignmentId);
		
		 

		return assignmentLocalService.updateAssignment(assignmentId, titleMap, description, dueDate, serviceContext);
	}

	@Override
	public int countByGroupId(long groupId) {
		return assignmentLocalService.countByGroupId(groupId);
	}

	@Override
	public List<Assignment> findByGroupId(long groupId) {
		return assignmentLocalService.findByGroupId(groupId);
	}

	@Override
	public List<Assignment> findByGroupId(long groupId, int start, int end) {
		return assignmentLocalService.findByGroupId(groupId, start, end);
	}

	 
}