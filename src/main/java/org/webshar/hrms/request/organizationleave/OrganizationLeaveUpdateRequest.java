package org.webshar.hrms.request.organizationleave;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrganizationLeaveUpdateRequest {

  private Long leaveTypeId;

  private Long organizationId;

  private Long count;

}
