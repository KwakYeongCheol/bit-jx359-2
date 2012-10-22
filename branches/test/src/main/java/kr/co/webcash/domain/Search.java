package kr.co.webcash.domain;

import java.util.List;

import kr.co.webcash.domain.post.Post;

public class Search {
	private List<Post> postList;

	
	public List<Post> getPostList() {
		return postList;
	}

	public void setPostList(List<Post> postList) {
		this.postList = postList;
	}
}
