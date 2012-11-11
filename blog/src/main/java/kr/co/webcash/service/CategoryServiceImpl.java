package kr.co.webcash.service;

import java.util.List;

import kr.co.webcash.domain.Blog;
import kr.co.webcash.domain.Category;
import kr.co.webcash.domain.post.Post;
import kr.co.webcash.repository.CategoryRepository;
import kr.co.webcash.repository.PostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired private CategoryRepository categoryRepository;
	@Autowired private PostRepository postRepository;
	
	@Override
	public void saveDefault(Blog blog) {
		this.save(Category.defaultCategory(blog));
	}
	
	@Override
	public void save(Category category) {
		build(category);
		
		categoryRepository.insert(category);
	}
	
	private void build(Category category){
		setId(category);
		setDisplayId(category);
		setOrderValue(category);
	}
	
	private void setId(Category category){
		Category findCategory = categoryRepository.findLast();
		
		if(findCategory == null) 	category.setId(1);
		else					
			category.setId(findCategory.getId() + 1);
	}
	
	private void setDisplayId(Category category){
		category.setDisplayId(findLastDisplayIdByBlogId(category.getBlogId()) + 1);
	}
	
	private void setOrderValue(Category category){
		long orderValue = findLastOrderValueByBlog(category.getBlog());
		category.setOrderValue(Category.nextOrderValue(orderValue));
	}
	
	@Override
	public void update(Category category) {
		categoryRepository.update(category);
	}

	@Override
	public void delete(Category category) {
		if(hasPost(category)){
			System.out.println("해당 카테고리에 글이 있기 때문에 삭제할 수 없습니다.");
		}else{
			if(Category.isDefaultCategory(category)){
				System.out.println("기본 카테고리는 삭제할 수 없습니다.");
			}else{
				System.out.println("카테고리가 삭제되었습니다.");
				categoryRepository.delete(category);
			}
		}
	}
	
	private long findLastOrderValueByBlog(Blog blog) {
		Category category = categoryRepository.findLastByBlogIdAndOrderValue(blog.getId());
		if(category == null)		return 0;
		return 						category.getOrderValue();
	}

	@Override
	public List<Category> listByBlogId(String blogId) {
		return categoryRepository.findAllByBlogId(blogId);
	}

	@Override
	public long findLastDisplayIdByBlogId(String blogId) {
		Category category = categoryRepository.findLastByBlogId(blogId);
		if(category == null)	return 0;
		else					return category.getDisplayId();
	}
	
	private boolean hasPost(Category category){
		Post lastPost = postRepository.findLastByCategoryId(category.getId());
		return (lastPost != null);
	}

	@Override
	public Category findByBlogIdAndDisplayId(String blogId, long displayId) {
		return categoryRepository.findByBlogIdAndDisplayId(blogId, displayId);
	}

	@Override
	public Category findByBlogIdAndDisplayId(Category category) {
		return this.findByBlogIdAndDisplayId(category.getBlog().getId(), category.getDisplayId());
	}
}
