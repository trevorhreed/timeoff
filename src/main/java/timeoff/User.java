package timeoff;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String handle;
	private String firstName;
	private String lastName;
	private String empGroup;
	
	protected User() {}
	
	public User(String handle, String firstName, String lastName, String empGroup){
		this.handle = handle;
		this.firstName = firstName;
		this.lastName = lastName;
		this.empGroup = empGroup;
	}
	
	public long getId(){
		return id;
	}
	public String getHandle(){
		return this.handle;
	}
	public String getFirstName(){
		return this.firstName;
	}
	public String getLastName(){
		return this.lastName;
	}
	public String getEmpGroup(){
		return this.empGroup;
	}
	
	@Override
	public String toString(){
		return handle;	
	}
}