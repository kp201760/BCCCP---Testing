package bcccp.tickets.season;

public class UsageRecord implements IUsageRecord {
	
	String ticketId;
	long startDateTime;
	long endDateTime;
	
	
	
	public UsageRecord(String ticketId, long startDateTime) throws Exception{
		if(ticketId==null || ticketId=="")
			throw Exception("invalid ticket id")
			this.ticketId = ticketId;
			
		if(startDateTime <= 0)
			throw Exception("invalid time")
		    this.startDateTime = startDateTime;
	}
	
	
	
	public void finalise(long endDateTime) throws Exception {
		if(endDateTime<=0)
			throw Exception("invalid end time")
		    this.endDateTime = endDateTime;
	}
	
	
	
	@Override
	public long getStartTime() {
		return startDateTime;
	}



	@Override
	public long getEndTime() {
		return endDateTime;
	}



	@Override
	public String getSeasonTicketId() {
		return ticketId;
	}

	
	
	public String toString() {
		return ("Usage : startDateTime : " + startDateTime + ", endDateTime: " + endDateTime);
	}




}
