package schulstundenCounter;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * Dies ist die Hauptklasse des Counterprojekts, welche ausrechnet, wie viele Reststunden im Abiturjahr 2016 pro Fach verbleiben.
 * 
 * @author Lukas Schramm
 * @version 1.0
 */
public class Counter {
	
	/**Frame zur Darstellung des Rests*/
	private JFrame frame1 = new JFrame("Restliche Schulstunden");
	/**Tabelle nach Faechern und Reststunden*/
	private JTable tabelle1 = new JTable();
	/**Das heutige Datum und seine berechneten Folgetage*/
	private Calendar today = GregorianCalendar.getInstance();
	/**Das Datum des letzten Schultags*/
	private Calendar lastDay = new GregorianCalendar();
	/**Die Liste von Unterrichtsfaechern*/
	private Subject[] subjects = new Subject[11];
	
	public Counter() {
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.setPreferredSize(new Dimension(250,246));
        frame1.setMinimumSize(new Dimension(250,246));
        frame1.setMaximumSize(new Dimension(375,369));
	    frame1.setResizable(true);
	    
		addSubjects();
		calculateRest();
		sortiere();
		
		frame1.pack();
	    frame1.setLocationRelativeTo(null);
	    tabelle1.setVisible(true);
	    frame1.getContentPane().add(new JScrollPane(tabelle1));
	    frame1.setVisible(true);
	}
	
	/**
	 * Dieses Programm generiert die Tabelle und traegt ihre Werte entsprechend ein.
	 */
	private void sortiere() {
		Vector<Object> eintraege = new Vector<Object>();
		for(Subject sub:subjects) {
			Vector<Object> zeile = new Vector<Object>();
			zeile.add(sub.getName());
			zeile.add(sub.getRest());
			eintraege.add(zeile);
		}
		Vector<Object> gesamtzeile = new Vector<Object>();
		gesamtzeile.add("Gesamt");
		gesamtzeile.add(printRest());
		eintraege.add(gesamtzeile);

		Vector<String> titel = new Vector<String>();
		titel.add("Fach");
		titel.add("Reststunden");
		tabelle1 = new JTable(eintraege, titel);
		
		tabelle1.getColumn("Fach").setPreferredWidth(40);
	    tabelle1.getColumn("Reststunden").setPreferredWidth(15);
	    tabelle1.getTableHeader().setBackground(Color.lightGray);
	    tabelle1.setEnabled(false);
	    
	    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	    centerRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
	    for(int x=0;x<tabelle1.getColumnCount();x++) {
	    	tabelle1.getColumnModel().getColumn(x).setCellRenderer(centerRenderer);
	    	tabelle1.getTableHeader().getColumnModel().getColumn(x).setCellRenderer(centerRenderer);
	    }
	    tabelle1.setDefaultRenderer(String.class, centerRenderer);
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
	private int printRest() {
		int sum = 0;
		for(Subject s:subjects) {
			sum += s.getRest();
		}
		return sum;
	}

	public static void main(String[] args) {
		new Counter();
	}
}