package timeoff;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Request {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String user;
	private long startdate;
	private long enddate;
	private String type;
	private int status;
	
	protected Request() {}
	
	public Request(String user, long startdate, long enddate, String type, int status){
		this.user = user;
		this.startdate = startdate;
		this.enddate = enddate;
		this.type = type;
		this.status = status;
	}
	
	public long getId(){
		return id;
	}
	public void setId(long id){
		this.id = id;
	}
	
	public String getUser(){
		return this.user;
	}
	public void setUser(String user){
		this.user = user;
	}
	
	public long getStartdate(){
		return this.startdate;
	}
	public void setStartdate(long startdate){
		this.startdate = startdate;
	}
	
	public long getEnddate(){
		return this.enddate;
	}
	public void setEnddate(long enddate){
		this.enddate = enddate;
	}
	
	public String getType(){
		return this.type;
	}
	public void setType(String type){
		this.type = type;
	}
	
	public int getStatus(){
		return this.status;
	}
	public void setStatus(int status){
		this.status = status;
	}
	
	@Override
	public String toString(){
		return this.user;	
	}
}