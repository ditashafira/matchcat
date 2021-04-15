package dita.shafira.mate.model;

public class Content{
	private String accessToken;
	private int statusCode;
	private String tokenType;
	private User user;

	public String getAccessToken(){
		return accessToken;
	}

	public int getStatusCode(){
		return statusCode;
	}

	public String getTokenType(){
		return tokenType;
	}

	public User getUser(){
		return user;
	}
}
