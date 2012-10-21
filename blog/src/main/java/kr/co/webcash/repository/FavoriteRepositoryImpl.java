package kr.co.webcash.repository;

import java.util.List;

import kr.co.webcash.domain.Favorite;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FavoriteRepositoryImpl implements FavoriteRepository{
	@Autowired private SqlSession sqlSession;
	
	@Override
	public void insert(Favorite favorite) {
		sqlSession.insert("Favorite.insert", favorite);
	}
	
	@Override
	public void delete(Favorite favorite) {
		sqlSession.delete("Favorite.delete", favorite);
	}
	
	@Override
	public List<Favorite> findAllByLoginId(String loginId) {
		return sqlSession.selectList("Favorite.findAllByLoginId", loginId);
	}
}
