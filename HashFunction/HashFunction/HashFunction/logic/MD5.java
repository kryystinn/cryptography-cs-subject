package logic;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {

	
	public static String hashPassword(String pass){
		String hashCode = "";
		
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(pass.getBytes());
			
			byte[] digest = md.digest();
			StringBuilder sb = new StringBuilder(32);
			for (byte b : digest) {
				sb.append(String.format("%02x", b & 0xff));
			}
			
			hashCode = sb.toString();
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	
		return hashCode;
	}
}
