package com.springlab.hibernatetest.entities;

import static jakarta.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Version;

@Entity
@Table(name="SINGER")
@NamedQueries({
	@NamedQuery(name=Singer.FIND_ALL, 
			query = "from Singer"),
	@NamedQuery(name=Singer.FIND_ALL_WITH_ALBUM, 
	        query = "select distinct s from Singer s " 
	              + "left JOIN fetch s.albums a "
	        	  + "left JOIN fetch s.instruments i "),
	@NamedQuery(name=Singer.FIND_BY_ID, 
    query = "select distinct s from Singer s " 
          + "left JOIN fetch s.albums a "
    	  + "left JOIN fetch s.instruments i "
          + "where s.id = :id")
})
public class Singer {
	public static final String FIND_ALL = "findAll";
	public static final String FIND_ALL_WITH_ALBUM = "findAllWithAlbum";
	public static final String FIND_BY_ID = "findById";
	
	@Id
	@GeneratedValue(strategy=IDENTITY)
	@Column(name="ID")
	private Long id;
	
	@Column(name="FIRST_NAME")
	private String firstName;
	
	@Column(name="LAST_NAME")
	private String lastName;
	
	@Temporal(TemporalType.DATE)
	@Column(name="BIRTH_DATE")
	private Date birthDate;
	
	@Version
	@Column(name="VERSION")
	private int version;
	
	@OneToMany(mappedBy = "singer", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Album> albums = new HashSet<>();
	
	@ManyToMany
	@JoinTable(
			name="SINGER_INSTRUMENT",
	        joinColumns = {@JoinColumn(name="SINGER_ID")},
	        inverseJoinColumns = {@JoinColumn(name="INSTRUMENT_ID")})
	private Set<Instrument> instruments = new HashSet<>();

	public Singer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Singer(String firstName, String lastName, Date birthDate) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Set<Album> getAlbums() {
		return albums;
	}

	public void setAlbums(Set<Album> albums) {
		this.albums = albums;
	}

	public void addAlbum(Album album) {
		album.setSinger(this);
		this.albums.add(album);
	}
	
	public void removeAlbum(Album album) {
		this.albums.remove(album);
	}
	
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public Set<Instrument> getInstruments() {
		return instruments;
	}

	public void setInstruments(Set<Instrument> instruments) {
		this.instruments = instruments;
	}
	
	public void addInstrument(Instrument instrument) {
		this.instruments.add(instrument);
	}

	@Override
	public String toString() {
		return "Singer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", birthDate=" + birthDate + ", version=" + version
				+ "]";
	}
	
	
	
	
	
	
}
