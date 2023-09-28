package jp.co.sony.ppog.dto;

/**
 * 都市情報データ交換クラス
 *
 * @author ArcaHozota
 * @since 3.69
 */
public record CityDto(Long id, String name, String continent, String nation, String district, Long population,
		String language) {
}
