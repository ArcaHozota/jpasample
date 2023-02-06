package jp.co.sony.ppog.config;

import java.sql.Types;

import org.hibernate.dialect.Oracle12cDialect;
import org.hibernate.type.StandardBasicTypes;

public class Oracle18aDialect extends Oracle12cDialect {

	public Oracle18aDialect() {
		super();
		this.registerHibernateType(-15, "string");
		this.registerHibernateType(Types.NCHAR, StandardBasicTypes.STRING.getName());
	}
}
