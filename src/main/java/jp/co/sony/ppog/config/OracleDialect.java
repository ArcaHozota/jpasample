package jp.co.sony.ppog.config;

import java.sql.Types;

import org.hibernate.dialect.SQLServerDialect;
import org.hibernate.type.StandardBasicTypes;

public class OracleDialect extends SQLServerDialect {

	public OracleDialect() {
		super();
		this.registerHibernateType(-15, "string");
		this.registerHibernateType(Types.NCHAR, StandardBasicTypes.STRING.getName());
	}
}
