package in.ankushs.browscap4j.utils;


/**
 * 
 * String utilities.
 * @author Ankush Sharma
 */
public class Strings {
	
	private Strings(){}
	
	/**
	 * Checks whether a String has any  text in it.
	 * @param str the string being inspected for text
	 * @return false if {@code str} is null or empty(after trimming),and true otherwise
	 */
	public static boolean hasText(final String str){
		if(str == null){
			return false;
		}
		for(final char ch : str.toCharArray()){
			if(!Character.isWhitespace(ch)){
				return true;
			}
		}
		return false;
	}
	
}
