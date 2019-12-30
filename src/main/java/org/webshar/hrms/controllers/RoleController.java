package org.webshar.hrms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.webshar.hrms.dao.exceptions.EntityNotFoundException;
import org.webshar.hrms.response.Response;
import org.webshar.hrms.services.RoleService;

@RestController
@RequestMapping("/")
public class RoleController
{
  @Autowired
  RoleService roleService;


  @RequestMapping(value = "/services/api/web/hrms/roles/{role_guid}", method = RequestMethod.GET)
  @ResponseBody
  public Response getRole(@PathVariable("role_guid") String roleGuid) throws EntityNotFoundException
  {
    Response response = new Response();
    response.setMessage("Temp");
    return  response;
  }

}
