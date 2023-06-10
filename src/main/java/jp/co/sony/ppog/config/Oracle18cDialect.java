package jp.co.sony.ppog.config;

import java.sql.Types;

import org.hibernate.dialect.Oracle12cDialect;
import org.hibernate.type.StandardBasicTypes;

public class Oracle18cDialect extends Oracle12cDialect {

	public Oracle18cDialect() {
		super();
		this.registerHibernateType(Types.NCHAR, StandardBasicTypes.STRING.getName());
	}
}
