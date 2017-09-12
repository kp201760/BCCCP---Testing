package bcccp.carpark;

import java.util.ArrayList;
import java.util.List;

import bcccp.tickets.adhoc.IAdhocTicket;
import bcccp.tickets.adhoc.IAdhocTicketDAO;
import bcccp.tickets.season.ISeasonTicket;
import bcccp.tickets.season.ISeasonTicketDAO;

public class Carpark implements ICarpark {
	
	private List<ICarparkObserver> observers;
	private String carparkId;
	private int capacity;
	private int nParked;
	private IAdhocTicketDAO adhocTicketDAO;
	private ISeasonTicketDAO seasonTicketDAO;
	
	
	
	public Carpark(String name, int capacity, 
			IAdhocTicketDAO adhocTicketDAO, 
			ISeasonTicketDAO seasonTicketDAO) throws Exception {
		if(carparkID=null){
			throw new Exception("Invalid Carpark ID");
			this.carparkId = name;
		}
		if(capacity<=0){
			throw new Exception("Full");
			this.capacity = capacity;
		}
		observers = new ArrayList<>();
		this.adhocTicketDAO = adhocTicketDAO;
		if(getNumberOfTickets()-10/100*getNumberOfTickets()<=0) throws Exception {
			throw new Exception("Space is not available");
			this.seasonTicketDAO = seasonTicketDAO;
		}
	}

	
	
	@Override
	public void register(ICarparkObserver observer) {
		if (!observers.contains(observer)) {
			observers.add(observer);
			notifyObservers();
		}
	}

	
	
	@Override
	public void deregister(ICarparkObserver observer) {
		if (observers.contains(observer)) {
			observers.remove(observer);
			notifyObservers();
		}
	}
	
	
	
	private void notifyObservers() {
		for (ICarparkObserver observer : observers) {
			observer.notifyCarparkEvent();
		}
	}

	
	
	@Override
	public String getName() {
		return carparkId;
	}
	
	
	
	@Override
	public boolean isFull() {
		int status=nParked + seasonTicketDAO.getNumberOfTickets();
		if(status==capacity){
			return true;
		}
		else{
			return false;
		}
	}
	
	
	
	@Override
	public IAdhocTicket issueAdhocTicket() {
		if (isFull==true) throw new RuntimeException("issueAdhocTicket: Full ");
		return adhocTicketDAO.createTicket(carparkId);
	}
	
	
	@Override
	public IAdhocTicket getAdhocTicket(String barcode) {
		if(barcode==null) 
		{
			throw new RuntimeException("Invalid Barcode");
		}
		else
		{	
			if(adhocTicketDAO.findTicketByBarcode(barcode)==null)throw new RuntimeException("Invalid Ticket");
			return adhocTicketDAO.findTicketByBarcode(barcode);
		}
	}
	
	
		
	@Override
	public float calculateAddHocTicketCharge(long entryDateTime) {
		float charge=0;
		float OOH_Rate=2.0;
		float BH_Rate=5.0;
		long startTime = entryDateTime;  
		String curDay = startTime.Day;
		String endDay = endTime.Day;  
		curStartTime = startTime;
		public float calcDayCharge(startTime, endTime, day){
			float dayCharge = 0;
			if isBusinessDay(day){ 
			if (endTime <= startBH){
				dayCharge = (endTime - startTime) * OOH_Rate ;
			}
			else if (startTime >= endBH) {
				dayCharge = (endTime - startTime) * OOH_Rate;
			}
			else if (startTime >= startBH and endTime <= endBH){
				dayCharge = (endTime - startTime) * BH_Rate;
			}				
			else if (startTime < startBH and endTime <= endBH){
				dayCharge = (startBH - startTime) * OOH_Rate;
				dayCharge += (endTime - startBH) * BH_Rate; 
			}
			else if (startTime >= startBH and startTime < endBH and endTime > endBH){
				dayCharge = (endBH - startTime) * BH_Rate;
				dayCharge += (endTime - endBH) * OOH_Rate ;
			}				
			else if (startTime < startBH and endTime > endBH){
				dayCharge = (startBH - startTime) * OOH_Rate;
				dayCharge += (endBH - startBH) * BH_Rate;
				dayCharge += (endTime - endBH) * OOH_Rate;
			}
			else  { 
				return "Error";
			}
			else{
				dayCharge = (endTime - startTime) * OOH_Rate;
			}
			return dayCharge; 
			}
		}
		while (curDay != endDay) {  
			curEndTime = curDay.midnight;
			charge += calcDayCharge(curStartTime, curEndTime, curDay);
			curStartTime = curEndTime;
			curDay = curDay.nextDay;
		}			
		charge += calcDayCharge(curStartTime, endTime, endDay);
		return charge.(format, .3f);     
			
	}

	
	
	@Override
	public boolean isSeasonTicketValid(String barcode) {		
		ISeasonTicket ticket = seasonTicketDAO.findTicketById(barcode);
		if (ticket == null) throw new RuntimeException("recordSeasonTicketExit: invalid ticketId - " + ticketId);
		long statrtTime= ticket.getStartValidPeriod(ticketID);
		long endTime= ticket.getEndValidPeriod(ticketID);
		if(startTime>=7 && endTime<=19)
		{
			return true;
		}else
		{ 
			return false;
		}
	}

	
	
	@Override
	public void registerSeasonTicket(ISeasonTicket seasonTicket) {
		if(seasonTicket!=getName(seasonTicket)) throw new RuntimeException("Invalid Carpark ID"); 
		seasonTicketDAO.registerTicket(seasonTicket);		
	}



	@Override
	public void deregisterSeasonTicket(ISeasonTicket seasonTicket) {
		seasonTicketDAO.deregisterTicket(seasonTicket);		
	}

	
	
	@Override
	public void recordSeasonTicketEntry(String ticketId) {
		ISeasonTicket ticket = seasonTicketDAO.findTicketById(ticketId);
		if (ticket == null) throw new RuntimeException("recordSeasonTicketEntry: invalid ticketId - " + ticketId);
		
		seasonTicketDAO.recordTicketEntry(ticketId);
		log(ticket.toString());
	}

	
	
	private void log(String message) {
		System.out.println("Carpark : " + message);
	}



	@Override
	public void recordAdhocTicketEntry() {
		if(capacity!=0){
		nParked++;
		notifyObservers();
		}
	}



	@Override
	public void recordAdhocTicketExit() {
		nParked--;
		if(capacity!=0){
		notifyObservers();	
		}		
	}



	@Override
	public void recordSeasonTicketExit(String ticketId) {
		ISeasonTicket ticket = seasonTicketDAO.findTicketById(ticketId);
		if (ticket == null) throw new RuntimeException("recordSeasonTicketExit: invalid ticketId - " + ticketId);
		
		seasonTicketDAO.recordTicketExit(ticketId);
		log(ticket.toString());
	}



	@Override
	public boolean isSeasonTicketInUse(String ticketId) {
		ISeasonTicket ticket = seasonTicketDAO.findTicketById(ticketId);
		if (ticket == null) throw new RuntimeException("recordSeasonTicketExit: invalid ticketId - " + ticketId);
		if(isExist(ticket) && ticket.inUse())
		{
			return true;
		} else
		{ 
			return false;
		}
			
	}

















}
