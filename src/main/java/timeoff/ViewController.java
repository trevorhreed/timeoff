package timeoff;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {
	
	@Autowired
	private ApplicationContext ctx;
	
	@RequestMapping("/")
	public ModelAndView home(){
		ModelAndView mav = new ModelAndView("index");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Collection authorities = auth.getAuthorities();
		List<String> roles = new ArrayList<String>();
		boolean first = true;
		for(Object role : authorities){
			if(role == null) continue;
			roles.add(String.valueOf(role).substring(5).toLowerCase());
		}
		UserRepo userRepo = ctx.getBean(UserRepo.class);
		User user = userRepo.findByHandle(auth.getName());
		Map<String, Object> userJson = new HashMap<String, Object>();
		userJson.put("roles", roles);
		userJson.put("handle", user.getHandle());
		userJson.put("firstName", user.getFirstName());
		userJson.put("lastName", user.getLastName());
		userJson.put("group", user.getEmpGroup());
		mav.addObject("user", userJson);
		return mav;
	}

}