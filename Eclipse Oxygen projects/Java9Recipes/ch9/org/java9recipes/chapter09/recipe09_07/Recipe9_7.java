package org.java9recipes.chapter09.recipe09_07;

/**
 * User: Freddy Modified by Juneau Recipe 9-7
 */
public class Recipe9_7 {
	public static void main(String[] args) {
		Recipe9_7 recipe = new Recipe9_7();
		recipe.start();
	}

	Object chatServer = null;

	private void start() {

		try {
			sendChat("Hello, how are you?");
		} catch (ConnectionUnavailableException e) {
			System.out.println("Caught a connection unavailable Exception!");
		}

		Thread someThread = new Thread(() -> {
			disconnectChatServer(chatServer);
		});
		someThread.setName("Some Unlucky Thread");
		someThread.start();

	}

	private void disconnectChatServer(Object chatServer) {
		if (chatServer == null)
			throw new IllegalChatServerException("Chat server is empty");
	}

	private void sendChat(String chatMessage) throws ConnectionUnavailableException {
		if (chatServer == null)
			throw new ConnectionUnavailableException("Can't find the chat server");
	}

	class ConnectionUnavailableException extends Exception {
		/**
		 * 
		 */
		private static final long serialVersionUID = 6084098318378770622L;

		ConnectionUnavailableException(String message) {
			super(message);
		}
	}

	class IllegalChatServerException extends RuntimeException {
		/**
		 * 
		 */
		private static final long serialVersionUID = -7087674105334779078L;

		IllegalChatServerException(String message) {
			super(message);
		}
	}
}
