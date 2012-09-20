package kr.co.webcash.service;

import java.util.List;

import kr.co.webcash.domain.Blog;
import kr.co.webcash.domain.Category;
import kr.co.webcash.domain.Post;
import kr.co.webcash.repository.CategoryRepository;
import kr.co.webcash.repository.PostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired private CategoryRepository categoryRepository;
	@Autowired private PostRepository postRepository;
	@Override
	public List<Category> listByBlogId(String blogId) {
		return categoryRepository.findAllByBlogId(blogId);
	}

	@Override
	public int findLastIdByBlogId(String blogId) {
		Category category = categoryRepository.findLastPostByBlogId(blogId);

		return Integer.parseInt(category.getId());
	}

	@Override
	public void save(Category category) {
		categoryRepository.insert(category);
	}

	@Override
	public void update(Category category) {
		categoryRepository.update(category);
	}

	@Override
	public void delete(Category category) {
		if(postRepository.findLastByBlogIdAndCategoryId(category.getBlog().getId(), category.getId()) == null){
			categoryRepository.delete(category);
		}
	}

	@Override
	public Category findByIdAndBlogId(String id, String blogId) {
		// TODO Auto-generated method stub
		return categoryRepository.findByIdAndBlogId(id,blogId);
	}

}
