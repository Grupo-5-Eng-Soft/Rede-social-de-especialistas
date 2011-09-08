package hash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashCalculator {

	public static String calculateHash(String s) {
		MessageDigest md = null;
		// creating a hash from user login
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(s.getBytes());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return md.digest().toString();
	}


}
