package jp.co.sony.ppog.dto;

import jp.co.sony.ppog.entity.City;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CityInfo extends City {

	private String continent;

	private String nation;
}
