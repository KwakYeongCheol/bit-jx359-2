package kr.co.webcash.service;

import java.util.List;

import kr.co.webcash.domain.Favorite;

public interface FavoriteService {

	List<Favorite> listByLoginId(String loginId);

	void delete(Favorite favorite);

	void add(Favorite favorite);

}
