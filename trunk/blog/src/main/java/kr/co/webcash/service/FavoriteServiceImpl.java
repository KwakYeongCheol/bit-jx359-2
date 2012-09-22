package kr.co.webcash.service;

import java.util.List;

import kr.co.webcash.domain.Favorite;
import kr.co.webcash.repository.FavoriteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavoriteServiceImpl implements FavoriteService{

	@Autowired private FavoriteRepository favoriteRepository;

	@Override
	public List<Favorite> listByLoginId(String loginId) {
		return favoriteRepository.findAllByLoginId(loginId);
	}

	@Override
	public void delete(Favorite favorite) {
		favoriteRepository.delete(favorite);
	}

}
