package com.vasslatam.training.gradebook.service.rest.application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.CompanyService;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.util.PortalUtil;
import com.vasslatam.training.gradebook.model.Assignment;
import com.vasslatam.training.gradebook.service.AssignmentService;

/**
 * @author diego
 */
@Component(
	property = {
//		"liferay.auth.verifier=false",
//		"liferay.oauth2=false",
		JaxrsWhiteboardConstants.JAX_RS_APPLICATION_BASE + "=/gradebook-rest",
		JaxrsWhiteboardConstants.JAX_RS_NAME + "=Greetings.Rest"
	},
	service = Application.class
)
public class AssignmentRestApplication extends Application {

	public Set<Object> getSingletons() {
		return Collections.<Object>singleton(this);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/assignments")
	public String getAssignments() {
		List<Assignment> assignments=new ArrayList<	>();
		try {
			Company company = _companyService.getCompanyById(PortalUtil.getDefaultCompanyId());
			List<Group> groups = _groupLocalService.getGroups(company.getCompanyId(), 0, true);
			groups.forEach((group)-> assignments.addAll( _assignmentService.findByGroupId(group.getGroupId())   )  );
			return JSONFactoryUtil.serialize(assignments);
		} catch (PortalException e) {
			_log.error(e);
		}
		return "[]";
		
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/assignment/{assignmentId}")
	public String getAssignment(@PathParam("assignmentId")long assignmentId) {
		//return JSONFactoryUtil.serialize( _assignmentService.getAssignment(assignmentId) );
		try {
			return JSONFactoryUtil.serialize( _assignmentService.getAssignment(assignmentId)) ;
		} catch (Exception e) {
			_log.error(e);
		}
		return "{}";
	}
	

	@GET
	@Produces("text/plain")
	public String working() {
		return "It works!";
	}

	@GET
	@Path("/morning")
	@Produces("text/plain")
	public String hello() {
		return "Good morning!";
	}

	@GET
	@Path("/morning/{name}")
	@Produces("text/plain")
	public String morning(
		@PathParam("name") String name,
		@QueryParam("drink") String drink) {

		String greeting = "Good Morning " + name;

		if (drink != null) {
			greeting += ". Would you like some " + drink + "?";
		}

		return greeting;
	}
	
	@Reference
	private AssignmentService _assignmentService;	
	
	@Reference
	private CompanyService _companyService;
	
	@Reference
	private GroupLocalService _groupLocalService;
	
	private static final Log _log=LogFactoryUtil.getLog(AssignmentRestApplication.class);

}