package jp.co.sony.ppog.repository;

import java.math.BigDecimal;
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
	 * 都市の公用語を検索する
	 *
	 * @param percentage  人口比率
	 * @param countryCode 国家コード
	 * @return List<String>
	 */
	List<String> findLanguageByCity(@Param("percentage") BigDecimal percentage,
			@Param("countryCode") String countryCode);
}
