package timeoff;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api")
public class ApiController {

	@Autowired
	private ApplicationContext ctx;
	
	@RequestMapping("/ping")
	public Map hello() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Collection authorities = auth.getAuthorities();
		List<String> roles = new ArrayList<String>();
		for(Object role : authorities){
			if(role == null) continue;
			roles.add(String.valueOf(role).substring(5));
		}
		Map<String, Object> json = new HashMap<String, Object>();
		json.put("status", "secure");
		json.put("roles", roles);
		return json;
	}
	
	@RequestMapping(value="/requests", method=RequestMethod.GET)
	public List<Request> getRequest() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		RequestRepo requestRepo = ctx.getBean(RequestRepo.class);
		return requestRepo.findByUser(auth.getName());
	}
	@RequestMapping(value="/requests", method=RequestMethod.POST)
	public Map postRequest(@RequestBody Request request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		RequestRepo requestRepo = ctx.getBean(RequestRepo.class);
		request.setUser(auth.getName());
		Request newRequest = requestRepo.save(request);
		Map<String, Object> json = new HashMap<String, Object>();
		json.put("message", "failure");
		json.put("entity", newRequest);
		return json;
	}
	@RequestMapping(value="/requests/{id}", method=RequestMethod.PUT)
	public Map putRequest(@RequestBody Request request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		RequestRepo requestRepo = ctx.getBean(RequestRepo.class);
		request.setUser(auth.getName());
		Request updatedRequest = requestRepo.save(request);
		Map<String, Object> json = new HashMap<String, Object>();
		json.put("message", "failure");
		json.put("entity", updatedRequest);
		return json;
	}
	@RequestMapping(value="/requests/{id}", method=RequestMethod.DELETE)
	public Map deleteRequest(@PathVariable long id) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		RequestRepo requestRepo = ctx.getBean(RequestRepo.class);
		Request request = requestRepo.findOne(id);
		Map<String, Object> json = new HashMap<String, Object>();
		if(request.getUser().equals(auth.getName())){
			requestRepo.delete(id);
			json.put("message", "success");
		}else{
			json.put("message", "failure");
		}
		return json;
	}

	@RequestMapping(value="/groups/{groupId}/requests", method=RequestMethod.GET)
	public List<Request> getGroupRequests(@PathVariable String groupId){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserRepo userRepo = ctx.getBean(UserRepo.class);
		User supervisor = userRepo.findByHandle(auth.getName());
		List<User> groupUsers = userRepo.findByEmpGroup(supervisor.getEmpGroup());
		List<Request> requests = new ArrayList<Request>();
		RequestRepo requestRepo = ctx.getBean(RequestRepo.class);
		for(User user : groupUsers){
			requests.addAll(requestRepo.findByUser(user.getHandle()));
		}
		Map<String, Object> json = new HashMap<String, Object>();
		json.put("supervisor", supervisor);
		json.put("group", supervisor.getEmpGroup());
		json.put("groupUsers", groupUsers);
		json.put("requests", requests);
		
		return requests;
	}
}