package hash;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashCalculator {
	
	private String hash;

	public HashCalculator(String string) {
		this.hash = calculateHash(string);
	}
	
	public String getValue() {
		return hash;
	}
	
	private String calculateHash(String s) {
		StringBuffer buffer = new StringBuffer();
		// creating a hash from user login
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte digest[] = md.digest(s.getBytes("UTF-8"));
			for (byte b : digest) {
				buffer.append(Integer.toHexString(0xFF & b));
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return buffer.toString();
	}
}
