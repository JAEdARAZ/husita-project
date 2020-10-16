package com.springdemo.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="translation")
public class Translation {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="sent_spanish")
	private String sentSpanish;
	
	@Column(name="sent_english")
	private String sentEnglish;
	
	@Column(name="date")
	private Date date;
	
	
	public Translation() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSentSpanish() {
		return sentSpanish;
	}

	public void setSentSpanish(String sentSpanish) {
		this.sentSpanish = sentSpanish;
	}

	public String getSentEnglish() {
		return sentEnglish;
	}

	public void setSentEnglish(String sentEnglish) {
		this.sentEnglish = sentEnglish;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
}
