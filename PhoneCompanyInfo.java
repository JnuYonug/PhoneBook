import java.io.Serializable;
import java.math.BigInteger;

public class PhoneCompanyInfo extends PhoneInfo implements Serializable {
	private transient String company;
	private BigInteger[] encCompany;
	
	public PhoneCompanyInfo(String name, String phoneNumber, String company) {
		super(name, phoneNumber);
		this.company = company;
		
		encryptAll();
	}
	
	public String getCompany() { return company; }
	public void setCompany(String company) { this.company = company; }
	
	@Override
	public String toString() { 
		return "È¸»ç"; 
	}
	@Override
	public void showInfo() {
		super.showInfo();
		final String NULL = "null";
		System.out.printf(" %s\t %s\t %s", NULL, NULL, company);
	}

	public void encryptAll() {
		super.encryptAll();
		this.encCompany = RSACrypto.encrypt(company);
	}
	public void decryptAll(BigInteger key1, BigInteger key2) {
		super.decryptAll(key1, key2);
		this.company = RSACrypto.decrypt(encCompany, key1, key2);
	}
}
