package schulstundenCounter;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Dies ist die Hauptklasse des Counterprojekts, welche ausrechnet, wie viele Reststunden im Abiturjahr 2016 pro Fach verbleiben.
 * 
 * @author Lukas Schramm
 * @version 1.0
 */
public class Counter {
	
	/**Das heutige Datum und seine berechneten Folgetage*/
	private Calendar today = GregorianCalendar.getInstance();
	/**Das Datum des letzten Schultags*/
	private Calendar lastDay = new GregorianCalendar();
	/**Die Liste von Unterrichtsfaechern*/
	private Subject[] subjects = new Subject[11];
	
	public Counter() {
		addSubjects();
		calculateRest();
		printRest();
	}
	
	/**
	 * Diese Methode fuegt alle Faecher mitsamt ihrer Stundenzahl pro Tag hinzu.
	 */
	private void addSubjects() {
		subjects[0] = new Subject("Mathe",2,0,2,0,0);
		subjects[1] = new Subject("Deutsch",2,0,0,2,0);
		subjects[2] = new Subject("Englisch",0,2,0,0,2);
		subjects[3] = new Subject("Franzakisch",0,1,0,2,0);
		subjects[4] = new Subject("Chemie",0,2,0,0,2);
		subjects[5] = new Subject("Geschichte",2,2,0,0,0);
		subjects[6] = new Subject("Politik",0,0,2,0,0);
		subjects[7] = new Subject("Informatik",0,0,2,0,0);
		subjects[8] = new Subject("Kunst",0,0,0,2,0);
		subjects[9] = new Subject("Sport",0,0,1,2,0);
		subjects[10] = new Subject("Info SK",0,0,0,0,2);
	}
	
	/**
	 * Diese Methode rechnet fuer jeden Tag bis zum letzten Schultag die entsprechenden Reststunden zur Liste hinzu.
	 */
	private void calculateRest() {
		lastDay.set(2016, 3, 13, 23, 59);
		while(today.getTimeInMillis() < lastDay.getTimeInMillis()) {
			int dayOfWeek = today.get(Calendar.DAY_OF_WEEK);
			int dateOfTheMonth = today.get(Calendar.DAY_OF_MONTH);
			int dateMonth = today.get(Calendar.MONTH);
			int dateYear = today.get(Calendar.YEAR);
			if(dateYear==2015 && dateMonth==11 && dateOfTheMonth>21) {
				today.set(2016, 0, 3, 0, 0);
				continue;
			}
			if(dateYear==2016 && dateMonth==1 && dateOfTheMonth==1) {
				today.add(Calendar.DAY_OF_MONTH, 7);
				continue;
			}
			if(dateYear==2016 && dateMonth==1 && dateOfTheMonth==17) {
				today.add(Calendar.DAY_OF_MONTH, 1);
				continue;
			}
			if(dateYear==2016 && dateMonth==1 && dateOfTheMonth==19) {
				today.add(Calendar.DAY_OF_MONTH, 1);
				continue;
			}
			if(dateYear==2016 && dateMonth==2 && dateOfTheMonth==21) {
				today.add(Calendar.DAY_OF_MONTH, 14);
				continue;
			}
			//System.out.println(today.getTime());
			switch(dayOfWeek) {
		    case Calendar.MONDAY:
		    	for(Subject s:subjects) {
		    		s.addRest(s.getMonday());
		    	}
		    	today.add(Calendar.DAY_OF_MONTH, 1);
		        break;
		    case Calendar.TUESDAY:
		    	for(Subject s:subjects) {
		    		s.addRest(s.getTuesday());
		    	}
		    	today.add(Calendar.DAY_OF_MONTH, 1);
		        break;
			case Calendar.WEDNESDAY:
				for(Subject s:subjects) {
		    		s.addRest(s.getWednesday());
		    	}
				today.add(Calendar.DAY_OF_MONTH, 1);
		        break;
		    case Calendar.THURSDAY:
		    	for(Subject s:subjects) {
		    		s.addRest(s.getThursday());
		    	}
		    	today.add(Calendar.DAY_OF_MONTH, 1);
		        break;
		    case Calendar.FRIDAY:
		    	for(Subject s:subjects) {
		    		s.addRest(s.getFriday());
		    	}
		    	today.add(Calendar.DAY_OF_MONTH, 3);
		        break;
		    case Calendar.SATURDAY:
				today.add(Calendar.DAY_OF_MONTH, 2);
				break;
		    case Calendar.SUNDAY:
				today.add(Calendar.DAY_OF_MONTH, 1);
				break;
			}
		}
	}
	
	/**
	 * Diese Methode gibt die restlichen Stundenzahlen des Schuljahres aus.
	 */
	private void printRest() {
		int sum = 0;
		for(Subject s:subjects) {
			sum += s.getRest();
			System.out.println(s);
		}
		System.out.println("Gesamt: "+sum);
	}

	public static void main(String[] args) {
		new Counter();
	}
}