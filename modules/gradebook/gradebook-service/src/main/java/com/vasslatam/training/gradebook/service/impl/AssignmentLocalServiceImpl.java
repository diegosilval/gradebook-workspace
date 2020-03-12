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

import java.util.List;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.vasslatam.training.gradebook.model.Assignment;
import com.vasslatam.training.gradebook.service.base.AssignmentLocalServiceBaseImpl;

/**
 * The implementation of the assignment local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>com.vasslatam.training.gradebook.service.AssignmentLocalService</code> interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssignmentLocalServiceBaseImpl
 */
@Component(
	property = "model.class.name=com.vasslatam.training.gradebook.model.Assignment",
	service = AopService.class
)
public class AssignmentLocalServiceImpl extends AssignmentLocalServiceBaseImpl {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Use <code>com.vasslatam.training.gradebook.service.AssignmentLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.vasslatam.training.gradebook.service.AssignmentLocalServiceUtil</code>.
	 */
	
	public Assignment addAssignment(long groupId, Map<Locale, String> titleMap, String description, Date dueDate, ServiceContext serviceContext) throws PortalException {
		long assignmentId = counterLocalService.increment(Assignment.class.getName());
		Assignment  assignment = createAssignment(assignmentId);
		assignment.setTitleMap(titleMap);
		assignment.setDescription(description);
		assignment.setDueDate(dueDate);
		assignment.setGroupId(groupId);		
		
		long companyId = serviceContext.getCompanyId();
		assignment.setCompanyId(companyId);
		
		long userId = serviceContext.getUserId();
		assignment.setUserId(userId);
		
		User user=userLocalService.getUserById(userId);
		assignment.setUserName(user.getFullName() );
		
		assignment.setCreateDate(serviceContext.getCreateDate(new Date()));
		
		return addAssignment(assignment);
				
	}
	
	
	public Assignment deleteAssignment(long assignmentId) throws PortalException {
		Assignment assignment = getAssignment(assignmentId);
		return deleteAssignment(assignment);
	}
	
	public List<Assignment> findByGroupId(long groupId){
		return assignmentPersistence.findByGroupId(groupId);
	}
	
	public List<Assignment> findByGroupId(long groupId, int start, int end){
		return assignmentPersistence.findByGroupId(groupId,start,end);
	}
	
	public int countByGroupId(long groupId) {
		return assignmentPersistence.countByGroupId(groupId);
	}
	
	
	public Assignment updateAssignment(long assignmentId, Map<Locale, String> titleMap, String description, Date dueDate, ServiceContext serviceContext) throws PortalException {
		Assignment assignment = getAssignment(assignmentId);
		assignment.setTitleMap(titleMap);
		assignment.setDescription(description);
		assignment.setDueDate(dueDate);
		assignment.setModifiedDate(  serviceContext.getModifiedDate(new Date())  );
		
		return updateAssignment(assignment);
	}
}