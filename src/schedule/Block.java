package src.schedule;

public class Block {
	
	private Day day;
	private int startMinute;
	private int duration;
	
	public Block(Day day, int startMinute, int duration) {
		this.day = day;
		this.startMinute = startMinute;
		this.duration = duration;
	}
	
	public Day getDay() {
		return this.day;
	}
	
	public int getStartMinute() {
		return this.startMinute;
	}
	
	public int getEndMinute() {
		return this.startMinute + this.duration;
	}
	
	public int getDuration() {
		return this.duration;
	}
	
	public void setDay(Day day) {
		this.day = day;
	}
	
	public void setStartMinute(int startMinute) {
		this.startMinute = startMinute;
	}
	
	public void setDuration(int duration) {
		this.duration = duration;
	}

}
