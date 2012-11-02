package kr.co.webcash.service;

import java.util.Arrays;
import java.util.List;

import kr.co.webcash.domain.PostRevision;
import kr.co.webcash.domain.post.Post;
import kr.co.webcash.repository.PostRevisionRepository;
import kr.co.webcash.utils.PostRevisionUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import difflib.DiffUtils;
import difflib.Patch;
import difflib.PatchFailedException;

@Service
public class PostRevisionServiceImpl implements PostRevisionService{
	
	@Autowired private PostRevisionRepository postRevisionRepository;
	@Autowired private PostService postService;
	
	@Override
	public void init(PostService postService, PostRevisionRepository postRevisionRepository) {
		this.postService = postService;
		this.postRevisionRepository = postRevisionRepository;
	}

	@Override
	public boolean save(Post post) {
		PostRevision lastRevision = postRevisionRepository.findLastRevisionByPostId(post.getId());
		
		if(lastRevision == null){
			return postRevisionRepository.insert(
				new PostRevision(post, Long.valueOf(1), "")
			);
		}else{
			Post lastPost = postService.findById(post.getId());
			
			PostRevision postRevision = new PostRevision(post, lastRevision.getDisplayId(), generateDiff(post.getContents(), lastPost.getContents()));
			postRevisionRepository.update(
				postRevision
			);
			
			postRevisionRepository.insert(
				new PostRevision(post, lastRevision.getDisplayId() + 1, "")	
			);
			
			return true;
		}
	}

	@Override
	public PostRevision currentRevision(Post post) {
		return postRevisionRepository.findLastRevisionByPostId(post.getId());
	}
	
	@Override
	public String getContents(Post post, long postRevisionDisplayId) {
		PostRevision currentRevision = currentRevision(post);
		Post findPost = postService.findById(post.getId());
		
		if(currentRevision.getDisplayId() <= postRevisionDisplayId){
			return findPost.getContents();
		}
		
		return patchContents(findPost, postRevisionDisplayId, currentRevision.getDisplayId());
	}

	private String patchContents(Post post, long fromDisplayId, long toDisplayId) {
		List<PostRevision> revisionList = postRevisionRepository.findAllByPostAndFromRevisionAndToRevision(post, fromDisplayId, toDisplayId);
		
		String contents = post.getContents();
		
		for(PostRevision revision : revisionList){
			List<String> patchList = Arrays.asList(revision.getDiff().split("\n"));
			
			Patch patch = DiffUtils.parseUnifiedDiff(patchList);
			
			StringBuilder builder = new StringBuilder();
			
			boolean start = true;
			
			try {
				for(Object obj : DiffUtils.patch(Arrays.asList(contents.split("\n")), patch)){
					if(start)		start = false;
					else			builder.append("\n");
					builder.append(obj);
				}
				
				contents = builder.toString();
			} catch (PatchFailedException e) {
				e.printStackTrace();
			}
		}
		
		return contents;
	}
	
	private String generateDiff(String original, String patched) {
		List<String> originalList = Arrays.asList(original.split("\n"));
		List<String> patchedList = Arrays.asList(patched.split("\n"));
		
		final Patch patch = DiffUtils.diff(originalList, patchedList);
		
		List<String> unifiedDiff = DiffUtils.generateUnifiedDiff(null, null, originalList, patch, 0);
		
		StringBuilder builder = new StringBuilder();
		for(String line : unifiedDiff){
			builder.append(line).append("\n");
		}
		
		return builder.toString();
	}

	@Override
	public List<PostRevision> list(Post post) {
		return postRevisionRepository.findAllByPost(post);
	}

	@Override
	public PostRevision get(long postId, long postRevisionDisplayId) {
		return postRevisionRepository.findByPostIdAndDisplayId(postId, postRevisionDisplayId);
	}

	@Override
	public String getCompareHtml(Post post, long revisionId) {
		String original = getContents(post, revisionId);
		String revised = getContents(post, revisionId - 1);
		
		return PostRevisionUtils.generateDiffRows(original, revised);
	}

}
