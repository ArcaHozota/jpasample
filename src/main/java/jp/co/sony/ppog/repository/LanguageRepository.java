package jp.co.sony.ppog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import jp.co.sony.ppog.entity.Language;

/**
 * 言語リポジトリ
 *
 * @author Administrator
 */
public interface LanguageRepository extends JpaRepository<Language, String> {

	/**
	 * 都市のすべての言語を検索する
	 *
	 * @param countryCode 国家コード
	 * @return List<String>
	 */
	List<Language> findLanguagesByCty(@Param("countryCode") String countryCode);
}
