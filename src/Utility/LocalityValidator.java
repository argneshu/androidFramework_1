package Utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LocalityValidator {
	private Pattern pattern;
	private Matcher matcher;

	private static final String LOCALITY_WORD =    "\\d+\\s+([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]+)";

	public LocalityValidator() {
		pattern = Pattern.compile(LOCALITY_WORD);
	}
	/**
	 * Validate hex with regular expression
	 * 
	 * @param hex
	 *            hex for validation
	 * @return true valid hex, false invalid hex
	 */
	public boolean validate(final String hex) {

		matcher = pattern.matcher(hex);
		return matcher.matches();

	}

}
