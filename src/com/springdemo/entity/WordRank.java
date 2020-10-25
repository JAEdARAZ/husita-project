package com.springdemo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="word_rank")
public class WordRank {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="word_esp")
	private String wordEsp;
	
	@Column(name="counter")
	private int counter;
	
	
	public WordRank () {}
	
	public WordRank (String wordEsp, int counter) {
		this.wordEsp = wordEsp;
		this.counter = counter;
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getWordEsp() {
		return wordEsp;
	}

	public void setWordEsp(String wordEsp) {
		this.wordEsp = wordEsp;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	@Override
	public String toString() {
		return "WordRank [id=" + id + ", wordEsp=" + wordEsp + ", counter=" + counter + "]";
	}
	
}
