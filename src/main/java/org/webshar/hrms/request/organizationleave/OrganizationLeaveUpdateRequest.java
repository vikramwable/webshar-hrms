package org.webshar.hrms.request.organizationleave;

import lombok.Data;

@Data
public class OrganizationLeaveUpdateRequest {

  private Long leaveTypeId;

  private Long organizationId;

  private Long count;

}
