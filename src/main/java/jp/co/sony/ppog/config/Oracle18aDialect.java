package jp.co.sony.ppog.config;

import java.sql.Types;

import org.hibernate.dialect.Oracle12cDialect;
import org.hibernate.type.StandardBasicTypes;

@SuppressWarnings("unused")
public class Oracle18aDialect extends Oracle12cDialect {

	public Oracle18aDialect() {
		super();
		this.registerHibernateType(Types.NCHAR, StandardBasicTypes.STRING.getName());
	}
}
