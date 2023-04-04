package jp.co.sony.ppog.dto;

import jp.co.sony.ppog.entity.CityView;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CityInfoDto extends CityView {

	private static final long serialVersionUID = 9053927948255512241L;

	private String language;
}
