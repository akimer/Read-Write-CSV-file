package csv;

public class Entitie {

	private int sid;
	private long timestamp;
	private int x;
	private int y;
	private int z;

	public Entitie() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Entitie(int sid, long timestamp, int x, int y, int z) {
		super();
		this.sid = sid;
		this.timestamp = timestamp;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(int timestamp) {
		this.timestamp = timestamp;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}

	@Override
	public String toString() {
		return "Entitie [sid=" + sid + ", timestamp=" + timestamp + ", x=" + x + ", y=" + y + ", z=" + z + "]";
	}

	
}
