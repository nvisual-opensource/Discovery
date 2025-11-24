package cn.com.nvisual.monitor.model;

import java.io.Serializable;

public class JwtRequest implements Serializable {

	private static final long serialVersionUID = 5926468583005150707L;
	
	private String username;
	private String password;
	private String shortlink;
	private Boolean noexp ;
	private String noexppassword;


	//need default constructor for JSON Parsing
	public JwtRequest()
	{
		
	}

	public JwtRequest(String username, String password) {
		this.setUsername(username);
		this.setPassword(password);
	}

	public Boolean getNoexp() {
		return noexp;
	}

	public void setNoexp(Boolean noexp) {
		this.noexp = noexp;
	}

	public String getNoexppassword() {
		return noexppassword;
	}

	public void setNoexppassword(String noexppassword) {
		this.noexppassword = noexppassword;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public String getShortlink() {
		return shortlink;
	}

	public void setShortlink(String shortlink) {
		this.shortlink = shortlink;
	}

	@Override
	public String toString() {
		return "JwtRequest{" +
				"username='" + username + '\'' +
				", password='" + password + '\'' +
				", shortlink='" + shortlink + '\'' +
				", noexp=" + noexp +
				", noexppassword='" + noexppassword + '\'' +
				'}';
	}
}