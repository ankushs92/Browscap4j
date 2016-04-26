package in.ankushs.browscap4j.service;

import org.apache.commons.lang3.CharUtils;
/**
 * 
 * @author Ankush Sharma
 *
 */
public final class RegexResolver {
	
	private RegexResolver(){}

	/**
	 * Takes in a name pattern from the browscap.csv file,
	 * and returns a Regex representation of it.
	 * @param namePattern the name pattern
	 * @return regex for the name pattern
	 */
	public static String toRegex(String namePattern) {
		final StringBuilder patternBuilder = new StringBuilder();
		patternBuilder.append("^");
		for (final char c : namePattern.toCharArray()) {
			switch (c) {
				case '*':
					patternBuilder.append(".*?");
					break;
				case '?':
					patternBuilder.append(".");
					break;
				default:
					if(CharUtils.isAsciiAlphanumeric(c) || c==' '){
						//The char c is either an alphabet,or a number or a whitespace,and NOT a regex wildcard.
						patternBuilder.append(c);
					}else {
						patternBuilder.append("\\").append(c);
					}
			}
		}
		patternBuilder.append("$");
		final String pattern = patternBuilder.toString().toLowerCase();
		return pattern;
	}
}
