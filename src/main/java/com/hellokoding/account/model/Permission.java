package com.hellokoding.account.model;

import javax.persistence.*;

@Entity
public class Permission {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	/*
	 * @JoinColumn(name = "username", referencedColumnName = "username")
	 * 
	 * @OneToOne private User username;
	 */

	private String url;

	@Column(name = "UrlTitle")
	private String urlTitle;

	private String username;

	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	/*@JoinColumn(name = "userId", referencedColumnName = "id")
	@OneToOne
	private User userId;*/

	/*public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}*/

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/*
	 * public User getUsername() { return username; }
	 * 
	 * public void setUsername(User username) { this.username = username; }
	 */

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrlTitle() {
		return urlTitle;
	}

	public void setUrlTitle(String urlTitle) {
		this.urlTitle = urlTitle;
	}
}
