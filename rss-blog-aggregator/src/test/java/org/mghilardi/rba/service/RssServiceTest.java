package org.mghilardi.rba.service;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mghilardi.rba.entity.Item;
import org.mghilardi.rba.excpetion.RssException;

public class RssServiceTest {

	private RssService rssService;

	@Before
	public void setUp() throws Exception {
		rssService = new RssService();
	}

	@Test
	public void testGetItemsFile() throws RssException {
		final List<Item> items = rssService.getItems(new File("test-rss/rss.xml"));
		assertEquals(10, items.size());
		final Item firstItem = items.get(0);
		assertEquals("How to solve Source not found error during debug in Eclipse",firstItem.getTitle());
		assertEquals("22 06 2014 21:35:49", new SimpleDateFormat("dd MM yyyy HH:mm:ss").format(firstItem.getPublishedDate()));
	}

}
