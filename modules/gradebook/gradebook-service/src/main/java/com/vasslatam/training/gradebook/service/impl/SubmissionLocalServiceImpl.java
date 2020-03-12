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

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.vasslatam.training.gradebook.model.Assignment;
import com.vasslatam.training.gradebook.model.Submission;
import com.vasslatam.training.gradebook.service.base.SubmissionLocalServiceBaseImpl;

/**
 * The implementation of the submission local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * <code>com.vasslatam.training.gradebook.service.SubmissionLocalService</code>
 * interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan	
 * @see SubmissionLocalServiceBaseImpl
 */
@Component(property = "model.class.name=com.vasslatam.training.gradebook.model.Submission", service = AopService.class)
public class SubmissionLocalServiceImpl extends SubmissionLocalServiceBaseImpl {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Use
	 * <code>com.vasslatam.training.gradebook.service.SubmissionLocalService</code>
	 * via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use
	 * <code>com.vasslatam.training.gradebook.service.SubmissionLocalServiceUtil</
	 * code>.
	 */
	public Submission addSubmission(long assignmentId, long studentId, String submissionText,
			ServiceContext serviceContext) throws PortalException {
		User student = userLocalService.getUserById(studentId);
		Assignment assignment = assignmentPersistence.findByPrimaryKey(assignmentId);
		long submissionId = counterLocalService.increment(Submission.class.getName());
		Submission submission = createSubmission(submissionId);
		submission.setAssignmentId(assignmentId);
		submission.setStudentId(studentId);
		submission.setSubmissionText(submissionText);
		submission.setCompanyId(assignment.getCompanyId());
		submission.setGroupId(assignment.getGroupId());
		submission.setCreateDate(serviceContext.getCreateDate(new Date()));
		long userId = serviceContext.getUserId();

		submission.setUserId(userId);
		submission.setGrade(-1);

		return addSubmission(submission);

	}

	public List<Submission> getSubmissionsByAssignment(long groupId, long assignmentId) {
		return submissionPersistence.findByG_A(groupId, assignmentId);

	}

	public List<Submission> getSubmissionsByAssignment(long groupId, long assignmentId, int start, int end) {
		return submissionPersistence.findByG_A(groupId, assignmentId, start, end);
	}

	public int getSubmissionsCountByAssignment(long groupId, long assignmentId) {
		return submissionPersistence.countByG_A(groupId, assignmentId);

	}

	public Submission gradeAndCommentSubmission(long submissionId, int grade, String comment) throws PortalException {
		Submission submission = getSubmission(submissionId);
		submission.setGrade(grade);
		submission.setComment(comment);
		submission.setModifiedDate(new Date());
		return updateSubmission(submission);

	}

	public Submission gradeSubmission(long submissionId, int grade) throws PortalException {
		Submission submission = getSubmission(submissionId);
		submission.setGrade(grade);

		submission.setModifiedDate(new Date());
		return updateSubmission(submission);

	}

	public Submission updateSubmission(long submissionId, String submissionText, ServiceContext serviceContext)
			throws PortalException {
		Submission submission = getSubmission(submissionId);
		submission.setSubmissionText(submissionText);

		submission.setModifiedDate(new Date());
		return updateSubmission(submission);

	}
}