package jp.co.sony.ppog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;

import jp.co.sony.ppog.entity.Language;
import jp.co.sony.ppog.entity.LanguageId;

/**
 * 言語リポジトリ
 *
 * @author Administrator
 */
public interface LanguageRepository extends JpaRepository<Language, LanguageId>, JpaSpecificationExecutor<Language> {
}
