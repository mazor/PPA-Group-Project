package requirementX.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;

import requirementX.view.MainWindow;

/**
 *  Used to transform the date into Ripley Format.
 *  Checks the date validity (from date cannot exceed to date).
 *  Checks date span constraint (cannot be more than 18350 days apart) is not broken.  
 * @authors Miriam Tamara Grodeland Aarag.
 *			Florence Anyakwo.
 *			Sharon Mazor.
 *			Funke Sowole.
 */

public class DateValidation {
	
		private MainWindow mw; //represents the MainWindow from which the dates are obtained (in spanFine and orderFine)
		private SimpleDateFormat ripleyDateFormat; //used to convert a Date into the correct format for Ripley's getIncidentsInRange method
		
		private Calendar convertDate; //used when setting values and for date conversions
		private String sDate; //represents the String representation of a date in the ripley format

		private static final int MAXIMUM_DATE_RANGE = 18350;	//this is approximately 50 years in days. User cannot search greater than 50 years. 
		
		/**
		 * Takes a MainWindow and assigns it as this class' MainWindow. 
		 * The ripleyDateFormat is initialised.
		 * The convertDate calendar is initialised
		 * @param mw
		 */
		public DateValidation(MainWindow mw){
			
			this.mw = mw;
			ripleyDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	
			convertDate = Calendar.getInstance();
			
		}
		
		/**
		 * Returns the ripley date format (of type SimpleDateFormat)
		 * @return ripleyDateFormat
		 */
		public SimpleDateFormat theRipleyDateFormat(){	
			
			return ripleyDateFormat;
			
		}
		
		/**
		 * Returns the MainWindow associated with this DateValidation 
		 * @return mw
		 */
		public MainWindow getmw(){
			
			return mw;
			
		}
		
		/**
		 * Converts a date to a calendar with that date. The value for convertDate is updated and returned.
		 * One use of this is due to the fact the DateSpinnerModel only accepts date and converting to Calendar can improve ease of use. 
		 * @param date
		 * @return convertDate
		 */
		public Calendar convertDateToCalendar(Date date){	
			
			convertDate.setTime(date);
			return convertDate;
			
		}
		
		/**
		 * Converts Date to a String in an appropriate layout for Ripley
		 * @param date
		 * @return sDate: the String representation of the Date in an appropriate layout
		 */
		public String convertDateToRipleyString(Date date){
			
			//sDate formated using ripleyDateFormat
			sDate = ripleyDateFormat.format(date);
			return sDate;
			
		}
		
		/**
		 * Used to convert a String to a Calendar with a date that represents that String
		 * @param sDate: supplied date in a String format
		 * @return the calendar representation of the supplied sDate
		 */
		public Calendar convertStringToCalendar(String sDate){
		
			//date initialised as null
			Date date = null;
			//tries to assign date as the converted sDate using ripleyDateFormat
			try {   		
				
				date = (Date)ripleyDateFormat.parse(sDate); 
				Calendar cal=Calendar.getInstance();
				cal.setTime(date);
				
			} catch (ParseException e){
				
				System.out.println("Exception :"+e);
				
			}  

			return convertDateToCalendar(date);			//calls method to convert date to Calendar. 
		
		}

		/**
		 * Returns whether the from date is before or equal to the to date
		 * @return True if from date is before or equal to the to date, false if it is not
		 */
		public boolean orderFine(){
			
			//to date should not be before from date so if it is then false is returned
			if (mw.getToDate().compareTo(mw.getFromDate())<0){
				
				return false;
				
			}
			//otherwise true is returned
			return true;
			
		}

		/**
		 * Returns whether the to and from date have less than or equal to the MAXIMUM_DATE_RANGE number of days between them
		 * @return false if the date span is greater than MAXIMUM_DATE_RANGE, true if it not
		 */
		public boolean spanFine(){
			
			//difference represents the milliseconds between the to and from dates in mw
		    long difference=mw.getToDate().getTime()-mw.getFromDate().getTime();
		    
		    //diffDays represents the value of difference in days instead of milliseconds
		    long diffDays = difference / (24 * 60 * 60 * 1000);
	    
		    //if diffDays is greater than MAXIMUM_DATE_RANGE then false is returned
			if (diffDays > MAXIMUM_DATE_RANGE){
				
					return false;
					
			}
			
			//otherwise true is returned
			return true;
			
		}
		
		
		/**
		 * Sets a value for convertDate
		 * @param year
		 * @param month
		 * @param date
		 * @param hourOfDay
		 * @param minute
		 * @param second
		 */
		public void setCalendarDate(int year, int month, int date, int hourOfDay, int minute, int second){
			
			convertDate.set(year,month,date,hourOfDay,minute,second);
			
		}

}
