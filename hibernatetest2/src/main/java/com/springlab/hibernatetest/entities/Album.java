package com.springlab.hibernatetest.entities;

import static jakarta.persistence.GenerationType.IDENTITY;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Version;

@Entity
@Table(name="ALBUM")
public class Album {

	@Id
	@GeneratedValue(strategy=IDENTITY)
	@Column(name="ID")
	private Long id;
	
	@Column(name="TITLE")
	private String title;
	
	@Column(name="RELEASE_DATE")
	@Temporal(TemporalType.DATE)
	private Date releaseDate;
	
	@Version
	@Column(name="VERSION")
	private int version;
	
	@ManyToOne
	@JoinColumn(name = "SINGER_ID")
	private Singer singer;

	public Album() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Album(Long id, String title, Date releaseDate, int version) {
		super();
		this.id = id;
		this.title = title;
		this.releaseDate = releaseDate;
		this.version = version;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public Singer getSinger() {
		return singer;
	}

	public void setSinger(Singer singer) {
		this.singer = singer;
	}

	@Override
	public String toString() {
		return "Album [id=" + id + ", title=" + title + ", releaseDate=" + releaseDate + ", version=" + version
				+ ", singer=" + singer + "]";
	}
	
	
	
}