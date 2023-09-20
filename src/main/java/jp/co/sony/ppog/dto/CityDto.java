package jp.co.sony.ppog.dto;

import jp.co.sony.ppog.entity.City;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CityDto extends City {

	private static final long serialVersionUID = 9053927948255512241L;

	private String language;
}
