package kr.co.webcash.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.Map;

import kr.co.webcash.utils.PostUtils;

import org.junit.Test;

public class PostServiceTest {
	
	@Test
	public void successCase(){
		List<String> scrapURLList = PostUtils.getScrapURLFromContents("Hello? @@kwakyc87/123## I'm posted this blog :D");
		
		assertThat(scrapURLList.size(), is(1));
		assertThat(scrapURLList.get(0), is("@@kwakyc87/123##"));
		
		scrapURLList = PostUtils.getScrapURLFromContents("Well, this is long Example maybe. @@kwakyc87/1## is great post to me. Sometimes, people told me," +
				"@kwakyc87/1## how are you doing? @@kwakyc87/2/## " +
				"hahaha But. It's fail because I expected @@ ('two at') but He have wrong spelling. and '/##'!!" +
				"so I told him, 'Hey! you have wrong spelling! @@kwakyc87/1## ! haha " +
				"but It's duplicate Import '@@kwakyc87/1##. So I expected only 1 '@@kwakyc87/1##.");
		
		assertThat(scrapURLList.size(), is(1));
		assertThat(scrapURLList.get(0), is("@@kwakyc87/1##"));
	}
	
	@Test
	public void similarCase(){
		List<String> scrapURLList = PostUtils.getScrapURLFromContents("Hello, @@fail/fail## It's failcase :(");
		assertThat(scrapURLList.size(), is(0));

		scrapURLList = PostUtils.getScrapURLFromContents("Hello, @@123fail/1## It's failcase, too :(");
		assertThat(scrapURLList.size(), is(1));

		scrapURLList = PostUtils.getScrapURLFromContents("Hello, @@fail_/1## It's failcase, too :(");
		assertThat(scrapURLList.size(), is(0));

		scrapURLList = PostUtils.getScrapURLFromContents("Hello, @@fail-/1## It's failcase, too :(");
		assertThat(scrapURLList.size(), is(0));
		
		scrapURLList = PostUtils.getScrapURLFromContents("How about this? @@## or @@kwakyc87##");
		assertThat(scrapURLList.size(), is(0));
		
		scrapURLList = PostUtils.getScrapURLFromContents("and.. @@@kwakyc87/1##23###. Can you find this?");
		assertThat(scrapURLList.size(), is(1));
		assertThat(scrapURLList.get(0), is("@@kwakyc87/1##"));
	}
	
	@Test
	public void parseToBlogIdAndPostDisplayId(){
		List<String> scrapURLList = PostUtils.getScrapURLFromContents("Hello? @@kwakyc87/123## I'm posted this blog :D");
		
		List<Map<String, String>> scrapList = PostUtils.parseToMap(scrapURLList);
		
		assertThat(scrapList.size(), is(1));
		
		Map<String, String> scrap = scrapList.get(0);
		
		assertThat(scrap.get("blogId"), is("kwakyc87"));
		assertThat(scrap.get("postDisplayId"), is("123"));
	}
}

