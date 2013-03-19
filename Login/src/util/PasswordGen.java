package util;

public class PasswordGen {
	/**
	 * Generates a password with given length that is alphanumeric(a-z;A-Z;0-9)
	 * @param length
	 * @return
	 */
	public static String generatePassword(int length) {
		String ret = "";
		for (int i = 0; i < length; i++) {
			int rnd = (int) (Math.random() * 3);
			//System.out.println(rnd);
			switch (rnd) {
			case 0: {
				ret += (int)( Math.random() * 9);
				break;
			}
			case 1: {
				ret += ((char) (Math.random() * 25 + 65));
				break;
			}
			case 2: {
				ret += ((char) (Math.random() * 25 + 97));
				break;
			}
			}
		}
		return ret;
	}
	public static String generateSalt(int length){
			String ret = "";
			for (int i = 0; i < length; i++) {
				int rnd = (int) (Math.random() * 2);
				//System.out.println(rnd);
				switch (rnd) {
				case 0: {
					ret += (int)( Math.random() * 30 + 33);
					break;
				}
				case 1: {
					ret += ((char) (Math.random() * 25 + 65));
					break;
				}
				case 2: {
					ret += ((char) (Math.random() * 25 + 97));
					break;
				}
				}
			}
			return ret;
		}
}
