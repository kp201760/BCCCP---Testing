package bcccp.tickets.adhoc;

import java.util.Date;

public class AdhocTicket implements IAdhocTicket {
	
	private String carparkId_;
	private int ticketNo_;
	private long entryDateTime;
	private long paidDateTime;
	private long exitDateTime;
	private float charge;
	private String barcode;
	private STATE state_;
	

	AdhocTicket(int ticketNo_, String barcode, String carparkId_) throws Exception{
		if(ticketNo_<=0)
			 throw new Exception("invalid ticketNo")
			this.ticketNo_=ticketNo_;
		if(carparkId_ == null)
			throw new Exception("invalid carparkId")
			this.carparkId_ = carparkId_;
		if(barcode== null)
			throw new Exception("invalid barcode")
			this.barcode = barcode;
				
	}
	

	private enum STATE { ISSUED, CURRENT, PAID, EXITED }

	
	
	public AdhocTicket(String carparkId_, int ticketNo, String barcode)throws Exception{
		if(ticketNo_<=0)
			throw new Exception("invalid ticketNo")
			this.ticketNo_=ticketNo_;
		if(carparkId_ == null)
			throw new Exception("invalid carparkId")
			this.carparkId_ = carparkId_;
		if(barcode == null)
			throw new Exception("invalid barcode")
			this.barcode = barcode;

	public AdhocTicket(String carparkId, int ticketNo, String barcode) {
		this.carparkId_ = carparkId;
		this.ticketNo_ = ticketNo;
		this.barcode = barcode;

		this.state_ = STATE.ISSUED;		
	}

	
	
	@Override
	public String getBarcode() {

		String barcode == "A" + Integer.toHexString(ticketNo) + Integer.toHexString(entryDateTime);


		return barcode;
	}


	
	@Override
	public String getCarparkId() {
		return carparkId_;
	}

	
	
	@Override
	public int getTicketNo() {
		return ticketNo_;
	}
	

	
	@Override

	public void enter(long entryDateTime) throws Exception {
		if(entryDateTime <= 0)
			throw new Exception("invalid dateTime")
			this.entryDateTime = entryDateTime;
		    this.state_ = STATE.CURRENT;		

	public void enter(long entryDateTime) {
		this.entryDateTime = entryDateTime;
		this.state_ = STATE.CURRENT;		

	}
	
	
	
	@Override
	public long getEntryDateTime() {

		if(state_ = STATE.CURRENT)
			return entryDateTime;
		else
			return 0;

		return entryDateTime;

	}

	
	
	@Override

	public void pay(long paidDateTime, float charge) throws Exception{
		if(state_ == STATE.CURRENT || state_ ==STATE.ISSUED)
			throw Exception(please make payment)
			this.paidDateTime = paidDateTime;
			this.charge = charge;
			state_ = STATE.PAID;

	public void pay(long paidDateTime, float charge) {
		this.paidDateTime = paidDateTime;
		this.charge = charge;
		state_ = STATE.PAID;

	}
	
	
	
	@Override
	public long getPaidDateTime() {

		if(state_ = STATE.PAID)
			return paidDateTime;
		else
			return 0;

		return paidDateTime;

	}



	@Override
	public float getCharge() {

		if(state_ = STATE.PAID)
			return charge;
		else
			return 0;

		return charge;

	}

	
	
	public String toString() {
		Date entryDate = new Date(entryDateTime);
		Date paidDate = new Date(paidDateTime);
		Date exitDate = new Date(exitDateTime);

		return "Carpark    : " + carparkId_ + "\n" +
		       "Ticket No  : " + ticketNo_ + "\n" +
		       "Entry Time : " + entryDate + "\n" + 
		       "Paid Time  : " + paidDate + "\n" + 
		       "Exit Time  : " + exitDate + "\n" +
		       "State      : " + state_ + "\n" +
		       "Barcode    : " + barcode;		
	}



	@Override
	public boolean isCurrent() {

		if(state_ == STATE.CURRENT || state_ != STATE.EXITED)
			return true;
		else
			return false;

		return state_ == STATE.CURRENT;

	}



	@Override
	public boolean isPaid() {

		if(state_ == STATE.PAID)
			return true;
		else
			return false;

		return state_ == STATE.PAID;

	}



	@Override
	public void exit(long dateTime) {

		if(state_ == STATE.PAID)
			return 0;
		else
			this.exitDateTime = dateTime;
			state_ = STATE.EXITED;
			return exitDateTime;

		exitDateTime = dateTime;
		state_ = STATE.EXITED;

	}



	@Override

	public long getExitDateTime() throws Exception {
		if(state_ == STATE.PAID || state_ ==STATE.CURRENT) 
			throw Exception( first do payment)
			return exitDateTime;


	public long getExitDateTime() {
		return exitDateTime;

	}



	@Override
	public boolean hasExited() {

		if(state_ == STATE.EXITED)
			return true;
		else
			return false;

		return state_ == STATE.EXITED;

	}


}
