package kr.co.webcash.service;

import java.util.List;

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
	public List<Category> listByBlogId(String blogId) {
		return categoryRepository.findAllByBlogId(blogId);
	}

	@Override
	public long findLastIdByBlogId(String blogId) {
		Category category = categoryRepository.findLastPostByBlogId(blogId);

		return category.getId();
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
	public Category findByIdAndBlogId(long id, String blogId) {
		return categoryRepository.findByIdAndBlogId(id,blogId);
	}

}
