package com.librarymgmt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;



@Entity
@Table(name = "PATRON")
public class Patron {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "PATRON_SEQUENCE")
	@SequenceGenerator(name = "PATRON_SEQUENCE",sequenceName = "PATRON_SEQ",allocationSize = 0)
	@Column(name = "USER_ID")
	private String userId;
	
	@Column(name = "USER_NAME")
	private String userName;
	
	@Column(name = "DOB")
	private String dob;
	
	@Column(name = "PHN_NMBR")
	private String phnNmbr;
	
	@Column(name = "BOOK_ISSUE")
	private String bookIssue;
	
	@Column(name = "BOOK_RETURN")
	private String bookReturn;
	
	@Column(name = "STATUS")
	private String status;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getPhnNmbr() {
		return phnNmbr;
	}

	public void setPhnNmbr(String phnNmbr) {
		this.phnNmbr = phnNmbr;
	}

	public String getBookIssue() {
		return bookIssue;
	}

	public void setBookIssue(String bookIssue) {
		this.bookIssue = bookIssue;
	}

	public Patron(@NotBlank String userId, @NotBlank String userName, String dob, String phnNmbr, String bookIssue,
			String bookReturn, String status) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.dob = dob;
		this.phnNmbr = phnNmbr;
		this.bookIssue = bookIssue;
		this.bookReturn = bookReturn;
		this.status = status;
	}

	public Patron() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Patron [userId=" + userId + ", userName=" + userName + ", dob=" + dob + ", phnNmbr=" + phnNmbr + "]";
	}

	public String getBookReturn() {
		return bookReturn;
	}

	public void setBookReturn(String bookReturn) {
		this.bookReturn = bookReturn;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
