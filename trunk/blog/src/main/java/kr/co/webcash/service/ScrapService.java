package kr.co.webcash.service;

import java.util.List;

import kr.co.webcash.domain.Blog;
import kr.co.webcash.domain.Scrap;

public interface ScrapService {

	List<Scrap> listAllByScrappedBlog(Blog blog);

}
