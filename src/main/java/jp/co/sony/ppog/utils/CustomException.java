package jp.co.sony.ppog.utils;

public class CustomException extends RuntimeException {

	private static final long serialVersionUID = 1692201139310590555L;

	public CustomException(final String message) {
		super(message);
	}
}