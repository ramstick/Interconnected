package objects;

public class IPAddress {
	private int[] address;
	
	public IPAddress(int [] address) {
		this.address = address;
	}
	
	public IPAddress(int a, int b, int c, int d) {
		int[] address = {a, b, c, d};
		this.address = address;
	}
	
	public String toString() {
		return address[0]+"."+address[1]+"."+address[2]+"."+address[3];
	}
	
	public int getSubaddress(int i) {
		return address[i];
	}
	
	public int [] getAddress() {
		return address;
	}
	
	public boolean equals(IPAddress b) {
		int[] b_Address = b.getAddress();
		return address[0] == b_Address[0] && address[1] == b_Address[1] && address[2] == b_Address[2] && address[3] == b_Address[3];
	}
}
