package jp.co.sony.ppog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.co.sony.ppog.entity.Language;

/**
 * Searching repository of entity Language
 *
 * @author Administrator
 */
public interface LanguageRepository extends JpaRepository<Language, String> {
}
