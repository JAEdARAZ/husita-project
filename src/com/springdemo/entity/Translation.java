package com.springdemo.entity;

import java.sql.Timestamp;

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
	private Timestamp date;
	
	
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

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Translation [id=" + id + ", sentSpanish=" + sentSpanish + ", sentEnglish=" + sentEnglish + ", date="
				+ date.toString() + "]";
	}
	
}
