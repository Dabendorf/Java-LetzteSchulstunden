package schulstundenCounter;

/**
 * Die Klasse Subject generiert Unterrichtsfachobjekte mit Namen und der Anzahl der Stunden als Attribute.
 * 
 * @author Lukas Schramm
 * @version 1.0
 */
public class Subject implements Comparable<Subject> {
	
	/**Name des Unterrichtsfachs*/
	private String name;
	/**Anzahl der Stunden pro Montag*/
	private int monday;
	/**Anzahl der Stunden pro Dienstag*/
	private int tuesday;
	/**Anzahl der Stunden pro Mittwoch*/
	private int wednesday;
	/**Anzahl der Stunden pro Donnerstag*/
	private int thursday;
	/**Anzahl der Stunden pro Freitag*/
	private int friday;
	/**Gesamtzahl restlicher Unterrichtsstunden*/
	private int rest = 0;
	
	public Subject(String name, int monday, int tuesday, int wednesday, int thursday, int friday) {
		this.name = name;
		this.monday = monday;
		this.tuesday = tuesday;
		this.wednesday = wednesday;
		this.thursday = thursday;
		this.friday = friday;
	}
	
	public int getRestOfDay(int numOfDay) {
		switch(numOfDay) {
		case 2:return monday;
		case 3:return tuesday;
		case 4:return wednesday;
		case 5:return thursday;
		case 6:return friday;
		default:return 0;
		}
	}

	public String getName() {
		return name;
	}

	public int getRest() {
		return rest;
	}

	public void addRest(int rest) {
		this.rest += rest;
	}

	@Override
	public int compareTo(Subject o) {
		return ((Integer)o.rest).compareTo(this.rest);
	}
}