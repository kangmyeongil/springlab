package com.springlab.hibernatetest.entities;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="INSTRUMENT")
public class Instrument {

	@Id
	@Column(name="INSTRUMENT_ID")
	private String instrumentId;
	@ManyToMany
	@JoinTable(
			name="SINGER_INSTRUMENT",
	        joinColumns = {@JoinColumn(name="SINGER_ID")},
	        inverseJoinColumns = {@JoinColumn(name="INSTRUMENT_ID")})
	private Set<Singer> singer;
	
	public String getInstrumentId() {
		return instrumentId;
	}
	public void setInstrumentId(String instrumentId) {
		this.instrumentId = instrumentId;
	}
	public Set<Singer> getSinger() {
		return singer;
	}
	public void setSinger(Set<Singer> singer) {
		this.singer = singer;
	}
	@Override
	public String toString() {
		return "Instrument [instrumentId=" + instrumentId + "]";
	}
	
	
	
	
	
}
