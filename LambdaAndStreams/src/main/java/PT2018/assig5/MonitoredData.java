package PT2018.assig5;

import java.util.*;
import org.joda.time.DateTime;
import org.joda.time.Duration;



public class MonitoredData {

	private Date startTime;
	private Date endTime;
	private String label;
	
	MonitoredData() {}
	
	public Date getStartTime() {
		return startTime;
	}
	
	@SuppressWarnings("deprecation")
	public int getStartTimeDay() {
		return startTime.getDate();
	}
	
	@SuppressWarnings("deprecation")
	public int getEndTimeDay() {
		return endTime.getDate();
	}
	
	public long getEndTimeInt() {
		return endTime.getTime();
	}
	public long getStartTimeInt() {
		return startTime.getTime();
	}

	public Long getDuration(){
		
		DateTime x = new DateTime(this.getEndTime());
		DateTime y = new DateTime(this.getStartTime());
		
		Duration z = new Duration(y,x);
		
		return Math.abs(z.getMillis());
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}


	public Date getEndTime() {
		return endTime;
	}


	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}


	public String getLabel() {
		return label;
	}


	public void setLabel(String label) {
		this.label = label;
	}
}
