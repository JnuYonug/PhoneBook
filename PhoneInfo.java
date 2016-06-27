import java.io.Serializable;
import java.math.BigInteger;

public class PhoneInfo implements Serializable {
	private transient String name;
	private transient String phoneNumber;
	private BigInteger[] encName;
	private BigInteger[] encPhoneNumber;
	
	public PhoneInfo(String name, String phoneNumber) { 
		this.name = name;
		this.phoneNumber = phoneNumber;
		
		if(this instanceof PhoneUnivInfo 
				|| this instanceof PhoneCompanyInfo) 
		{
			return;
		}

		encryptAll();
	}
	
	public String getName() { return name; }
	public String getPhone() { return phoneNumber; }
	public void setName(String name) { this.name = name; }
	public void setPhone(String phoneNumber) { this.phoneNumber = phoneNumber; }
	
	@Override
	public String toString() {
		return "¿œπ›";
	}
	public void showInfo() {
		System.out.printf("%s\t %s\t %s\t", this.toString(), name, phoneNumber);
		
		if( !(this instanceof PhoneUnivInfo) 
					&& !(this instanceof PhoneCompanyInfo) )
		{
			final String NULL = "null";
			System.out.printf(" %s\t %s\t %s", NULL, NULL, NULL);
		}
	}
	
	public void encryptAll() {
		this.encName = RSACrypto.encrypt(name);
		this.encPhoneNumber = RSACrypto.encrypt(phoneNumber);
	}
	public void decryptAll(BigInteger key1, BigInteger key2) {
		this.name = RSACrypto.decrypt(encName, key1, key2);
		this.phoneNumber = RSACrypto.decrypt(encPhoneNumber, key1, key2);
	}
}

