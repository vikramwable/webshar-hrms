package org.webshar.hrms.request.employee;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.AssertTrue;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.webshar.hrms.constants.ErrorMessageConstants;
import org.webshar.hrms.enums.EmployeeSortBy;
import org.webshar.hrms.enums.Order;

@Data
@ApiModel
public class EmployeeSearchRequest {

  @ApiModelProperty(value = "queryString", example = "xyz")
  private String queryString;
  @ApiModelProperty(value = "perPage", example = "10")
  private Integer perPage;
  @ApiModelProperty(value = "page", example = "1")
  private Integer page;
  @ApiModelProperty(value = "sortBy", example = "EMPLOYEE_ID")
  private String sortBy;
  @ApiModelProperty(value = "order", example = "ASC")
  private String order;
  @ApiModelProperty(value = "active", example = "true")
  private Boolean active;


  @AssertTrue(message = ErrorMessageConstants.EMPLOYEE_INVALID_ORDER_BY_VALUE)
  private boolean isValidOrder() {
    if (StringUtils.isEmpty(order)) {
      setOrder("DESC");
    }

    for (Order o : Order.values()) {
      if (o.name().equals(order)) {
        return true;
      }
    }
    return false;
  }

  @AssertTrue(message = ErrorMessageConstants.EMPLOYEE_INVALID_INVALID_SORT_BY_COLUMN)
  public boolean isValidSortBy() {
    if (StringUtils.isEmpty(sortBy)) {
      setSortBy("EMPLOYEE_ID");
    }
    for (EmployeeSortBy sortParam : EmployeeSortBy.values()) {
      if (sortParam.name().equals(sortBy)) {
        return true;
      }
    }
    return false;
  }

  @AssertTrue(message = ErrorMessageConstants.INVALID_PER_PAGE_VALUE)
  private boolean isValidPerPageParameter() {
    final Set<Integer> perPageParameterSet = new HashSet<>(Arrays.asList(10, 25, 50));
    return getPerPage() == null || perPageParameterSet.contains(getPerPage());
  }

  @AssertTrue(message = ErrorMessageConstants.INVALID_PAGE_PARAMETER_ERROR)
  private boolean isValidPageParameter() {
    return getPage() == null || getPage() >= 1;
  }
}
