package kr.co.webcash.repository;

import java.util.List;

import kr.co.webcash.domain.Favorite;

public interface FavoriteRepository {

	List<Favorite> findAllByLoginId(String loginId);

	void delete(Favorite favorite);

	void insert(Favorite favorite);
	
}
