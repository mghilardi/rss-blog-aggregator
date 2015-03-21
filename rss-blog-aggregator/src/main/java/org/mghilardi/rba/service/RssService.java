package org.mghilardi.rba.service;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.mghilardi.rba.entity.Item;
import org.mghilardi.rba.excpetion.RssException;
import org.mghilardi.rba.rss.ObjectFactory;
import org.mghilardi.rba.rss.TRss;
import org.mghilardi.rba.rss.TRssChannel;
import org.mghilardi.rba.rss.TRssItem;
import org.springframework.stereotype.Service;

/**
 * xsd to bind http://europa.eu/rapid/conf/RSS20.xsd
 * 
 * @author Mauricio
 *
 */

@Service
public class RssService {

	public List<Item> getItems(File file) throws RssException {
		return getItems(new StreamSource(file));
	}

	private List<Item> getItems(Source source) throws RssException {
		final List<Item> list = new ArrayList<Item>();
		try {
			final JAXBContext jaxbContext = JAXBContext
					.newInstance(ObjectFactory.class);
			final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			final JAXBElement<TRss> jaxbElement = unmarshaller.unmarshal(
					source, TRss.class);
			final TRss rss = jaxbElement.getValue();

			final List<TRssChannel> channels = rss.getChannel();
			for (final TRssChannel channel : channels) {
				final List<TRssItem> items = channel.getItem();
				for (final TRssItem rssItem : items) {
					final Item item = new Item();
					item.setTitle(rssItem.getTitle());
					item.setDescription(rssItem.getDescription());
					item.setLink(rssItem.getLink());
					final Date pubDate = new SimpleDateFormat(
							"EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH)
							.parse(rssItem.getPubDate());
					item.setPublishedDate(pubDate);
					list.add(item);
				}
			}
		} catch (final JAXBException e) {
			throw new RssException(e);
		} catch (final ParseException e) {
			throw new RssException(e);
		}

		return list;
	}

	public List<Item> getItems(String url) throws RssException {
		return getItems(new StreamSource(url));
	}
}
