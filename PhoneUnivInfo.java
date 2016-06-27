import java.io.Serializable;
import java.math.BigInteger;

public class PhoneUnivInfo extends PhoneInfo implements Serializable {
	private transient String major;
	private transient int year;
	private BigInteger[] encMajor;
	private BigInteger encYear;
	
	public PhoneUnivInfo(String name, String phoneNumber, String major, int year) {
		super(name, phoneNumber);
		this.major = major;
		this.year = year;
		
		encryptAll();
	}
	
	public String getMajor() { return major; }
	public int getYear() { return year; }
	public void setMajor(String major) { this.major = major; }
	public void setYear(int year) { this.year = year; }
	
	@Override
	public String toString() { 
		return "¥Î«–"; 
	}
	@Override
	public void showInfo() {
		super.showInfo();
		System.out.printf(" %s\t %d\t %s", major, year, "null");	 
	}

	@Override
	public void encryptAll() {
		super.encryptAll();
		this.encMajor = RSACrypto.encrypt(major);
		this.encYear = RSACrypto.encrypt(BigInteger.valueOf(year));
	}
	@Override
	public void decryptAll(BigInteger key1, BigInteger key2) {
		super.decryptAll(key1, key2);
		
		this.major = RSACrypto.decrypt(encMajor, key1, key2);
		this.year = RSACrypto.decrypt(encYear, key1, key2);
	}
}
