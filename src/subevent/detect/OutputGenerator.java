package subevent.detect;

public class OutputGenerator {

	private String start;
	private String end;
	public String getStart() {
		return start;
	}

	public String getEnd() {
		return end;
	}

	public int getCounter() {
		return counter;
	}

	private int counter;

	public OutputGenerator() {
		counter = 0;
	}

	public void setStart(String start) {
		this.start = start.substring(0, 11);
	}

	public void setEnd(String end) {
		this.end = end.substring(0, 11);
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public boolean inBoundary(int overFlowboundary) {

		if (Math.abs(overFlowboundary - counter) <= 10) {
			return true;
		}
		return false;
	}

	public String toString() {
		return String.format("%s , %s \n", start, end);
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return counter;
	}

}
