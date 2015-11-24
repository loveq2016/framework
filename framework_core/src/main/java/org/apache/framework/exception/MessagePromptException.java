package org.apache.framework.exception;

/**
 * Runtime exception for XML handling.
 * 
 * @author carver
 * @since 1.0, Jun 12, 2007
 */
public class MessagePromptException extends RuntimeException {

	private static final long serialVersionUID = 381260478228427716L;

	public MessagePromptException() {
		super();
	}

	public MessagePromptException(String message, Throwable cause) {
		super(message, cause);
	}

	public MessagePromptException(String message) {
		super(message);
	}

	public MessagePromptException(Throwable cause) {
		super(cause);
	}

}
