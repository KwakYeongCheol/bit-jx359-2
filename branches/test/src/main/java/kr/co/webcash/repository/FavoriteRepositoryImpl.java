package kr.co.webcash.repository;

import java.util.List;

import kr.co.webcash.domain.Favorite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class FavoriteRepositoryImpl implements FavoriteRepository{
	@Autowired private SqlMapClientTemplate template;
	@Override
	public List<Favorite> findAllByLoginId(String loginId) {
		return template.queryForList("Favorite.findAllByLoginId", loginId);
	}
	@Override
	public void delete(Favorite favorite) {
		template.delete("Favorite.delete", favorite);
	}
	@Override
	public void insert(Favorite favorite) {
		template.insert("Favorite.insert", favorite);
	}

}
