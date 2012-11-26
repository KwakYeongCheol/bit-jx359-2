package kr.co.webcash.service;

import org.junit.Test;


public class PostServiceTest {
	
	@Test
	public void removeHtml(){
		StringBuilder builder = new StringBuilder("   <div>         Hello Super mario?     </div><p><a href='showmethemoney'>         hi  </a>".trim());
		
		int fromIndex = -1;
		while((fromIndex = builder.indexOf("<")) != -1){
			builder.replace(0, fromIndex, builder.substring(0, fromIndex).trim());
			int toIndex = builder.indexOf(">", fromIndex);
			
			if(toIndex != -1){
				int nextToIndex = builder.indexOf("<", toIndex);
				
				if(nextToIndex != -1){
					builder.replace(toIndex+1, nextToIndex, builder.substring(toIndex+1, nextToIndex).trim());
				}
				
				builder.replace(fromIndex, toIndex+1, "");
			}
		}
	}
	
//	@Test
//	public void successCase(){
//		List<Scrap> scrapList = Scrap.convert("Hello? @@kwakyc87/123/1## I'm posted this blog :D");
//		
//		assertThat(scrapList.size(), is(1));
//		Scrap scrap = scrapList.get(0);
//		assertThat(scrap.getTargetBlogId(), is("kwakyc87"));
//		assertThat(scrap.getTargetPostDisplayId(), is(Long.valueOf("123")));
//		assertThat(scrap.getTargetPostRevisionId(), is(Long.valueOf("1")));
//		
//		scrapList = Scrap.convert("Well, this is long Example maybe. @@kwakyc87/1/1## is great post to me. Sometimes, people told me," +
//				"@kwakyc87/1## how are you doing? @@kwakyc87/2/## " +
//				"hahaha But. It's fail because I expected @@ ('two at') but He have wrong spelling. and '/##'!!" +
//				"so I told him, 'Hey! you have wrong spelling! @@kwakyc87/1## ! haha " +
//				"but It's duplicate Import '@@kwakyc87/1##. So I expected only 1 '@@kwakyc87/1##.");
//	
//		assertThat(scrapList.size(), is(1));
//		scrap = scrapList.get(0);
//		assertThat(scrap.getTargetBlogId(), is("kwakyc87"));
//		assertThat(scrap.getTargetPostDisplayId(), is(Long.valueOf("1")));
//		assertThat(scrap.getTargetPostRevisionId(), is(Long.valueOf("1")));
//		assertThat(scrap.getTag(), is("@@kwakyc87/1/1##"));
//	}
//	
//	@Test
//	public void similarCase(){
//		List<Scrap> scrapList = Scrap.convert("Hello, @@fail/fail## It's failcase :(");
//		assertThat(scrapList.size(), is(0));
//
//		scrapList = Scrap.convert("Hello, @@123fail/1/1## It's not failcase :(");
//		assertThat(scrapList.size(), is(1));
//
//		scrapList = Scrap.convert("Hello, @@fail_/1/1## It's failcase, too :(");
//		assertThat(scrapList.size(), is(0));
//
//		scrapList = Scrap.convert("Hello, @@fail-/1/1## It's failcase, too :(");
//		assertThat(scrapList.size(), is(0));
//		
//		scrapList = Scrap.convert("How about this? @@## or @@kwakyc87##");
//		assertThat(scrapList.size(), is(0));
//		
//		scrapList = Scrap.convert("and.. @@@2/1/1##23###. Can you find this?");
//		assertThat(scrapList.size(), is(1));
//		assertThat(scrapList.get(0).getTag(), is("@@2/1/1##"));
//	}
}

