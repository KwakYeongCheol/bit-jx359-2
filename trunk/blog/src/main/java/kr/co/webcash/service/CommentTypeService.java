package kr.co.webcash.service;

import kr.co.webcash.domain.CommentType;

public interface CommentTypeService {

	long getTargetIdByTypeAndDisplayId(CommentType type, long targetDisplayId);

}
