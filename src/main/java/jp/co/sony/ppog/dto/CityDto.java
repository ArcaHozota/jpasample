package jp.co.sony.ppog.dto;

import jp.co.sony.ppog.entity.City;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CityDto extends City {

	private static final long serialVersionUID = 9053927948255512241L;

	private String continent;

	private String nation;

	private String language;
}
