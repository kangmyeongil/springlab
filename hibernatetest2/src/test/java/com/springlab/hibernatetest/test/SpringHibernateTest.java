package com.springlab.hibernatetest.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.springlab.hibernatetest.dao.SingerDao;
import com.springlab.hibernatetest.entities.Album;
import com.springlab.hibernatetest.entities.Instrument;
import com.springlab.hibernatetest.entities.Singer;

public class SpringHibernateTest {

	private static Logger logger = LoggerFactory.getLogger(SpringHibernateTest.class);

	private GenericXmlApplicationContext ctx = null;
	private SingerDao singerDao;
	
	@Before
	public void setup() {
		ctx = new GenericXmlApplicationContext("classpath:config/appConfig.xml");
		singerDao = ctx.getBean(SingerDao.class);
		assertNotNull(singerDao);
		
	}
	
	@After
	public void tearOff() {
		ctx.close();
	}
	
//	@Test
	public void testFindAll() {
		List<Singer> singers = singerDao.findAll();
		assertTrue(singers.size() == 3);
		
		listSingers(singers);
	}
	
//	@Test
	public void testFindAllWithAlbum() {
		List<Singer> singers = singerDao.findAllWithAlbum();
		assertTrue(singers.size() == 3);
		
		listSingersWithAlbum(singers);
	}
	
//	@Test
	public void testFindById() {
		Singer singer = singerDao.findById(1L);
		assertNotNull(singer);
		
		List<Singer> singers = new ArrayList<>();
		singers.add(singer);
		listSingersWithAlbum(singers);
	}
	
//	@Test
	public void testInsert() {
		Singer singer = new Singer();
		singer.setFirstName("BB");
		singer.setLastName("King");
		singer.setBirthDate((new GregorianCalendar(1940, 8, 16)).getTime());
		
		Album album = new Album();
		album.setTitle("My kind of Blues");
		album.setReleaseDate((new GregorianCalendar(1961, 7, 18)).getTime());
		singer.addAlbum(album);
		
		Instrument instrument = new Instrument();
		instrument.setInstrumentId("Piano");
		singer.addInstrument(instrument);
		
		singerDao.insert(singer);
		
		assertNotNull(singer.getId());
		
		List<Singer> singers = singerDao.findAllWithAlbum();
		assertTrue(singers.size() == 4);
		
		listSingersWithAlbum(singers);
		
	}
	// 적용되면 버전값이 1 올라감
//	@Test
	public void testUpdate() {
		Singer singer = singerDao.findById(1L);
		assertNotNull(singer);
		assertEquals(singer.getLastName(),"Mayer");
		singer.setFirstName("John Clayton");
		
		Album album = singer.getAlbums().stream().filter(a -> a.getTitle().equals("Battle Studies")).findFirst().get();
		
		singer.getAlbums().remove(album);
		singerDao.update(singer);
		
		listSingersWithAlbum(singerDao.findAllWithAlbum());
	}
	
	@Test
	public void testDelete() {
		Singer singer = new Singer();
		singer.setId(3L);
		singerDao.delete(singer);
		
		listSingersWithAlbum(singerDao.findAllWithAlbum());
	}
	
	
	
	
	
	private void listSingers(List<Singer> singers) {
		logger.info(">>> 가수 목록");
		singers.forEach( s -> logger.info(">>>" + s.toString()));
	}
	
	private void listSingersWithAlbum(List<Singer> singers) {
		logger.info(">>> 가수, 앨범 목록");
		singers.forEach( s -> {logger.info(">>>" + s.toString());
		    if(s.getAlbums() != null && !s.getAlbums().isEmpty()) {
		        s.getAlbums().forEach(a -> logger.info(">>> "+ a.toString()));
		    }
		    if(s.getInstruments() != null && !s.getInstruments().isEmpty()) {
		        s.getInstruments().forEach(i -> logger.info(">>> "+ i.toString()));
		    }
		});
	}
	
}
