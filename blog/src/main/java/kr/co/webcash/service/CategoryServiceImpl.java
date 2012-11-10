package kr.co.webcash.service;

import java.util.List;

import kr.co.webcash.domain.Blog;
import kr.co.webcash.domain.Category;
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
		Category findCategory = categoryRepository.findLast();
		
		if(findCategory == null){
			category.setId(1);
		}else{
			category.setId(findCategory.getId() + 1);
		}
		
		categoryRepository.insert(category);
	}
	
	@Override
	public List<Category> listByBlogId(String blogId) {
		return categoryRepository.findAllByBlogId(blogId);
	}

	@Override
	public long findLastDisplayIdByBlogId(String blogId) {
		Category category = categoryRepository.findLastPostByBlogId(blogId);

		return category.getDisplayId();
	}


	@Override
	public void update(Category category) {
		categoryRepository.update(category);
	}

	@Override
	public void delete(Category category) {
		if(postRepository.findLastByCategoryId(category.getId()) == null){
			long categoryCount = categoryRepository.findAllCountByBlogId(category.getBlog().getId());
			
			if(categoryCount > 1){
				System.out.println("카테고리가 삭제되었습니다.");
				categoryRepository.delete(category);
			}else{
				System.out.println("최소 한 개의 카테고리는 존재해야 합니다.");
			}
		}else{
			System.out.println("해당 카테고리에 글이 있기 때문에 삭제할 수 없습니다.");
		}
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
