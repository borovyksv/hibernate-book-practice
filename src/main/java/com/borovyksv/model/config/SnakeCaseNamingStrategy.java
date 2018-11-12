package com.borovyksv.model.config;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

import static java.lang.Character.isDigit;

public class SnakeCaseNamingStrategy extends PhysicalNamingStrategyStandardImpl {
    public static final SnakeCaseNamingStrategy INSTANCE = new SnakeCaseNamingStrategy();

    @Override
    public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment context) {
        String text = getStringSnakeCase(name.getText());
        return new Identifier(text, name.isQuoted());
    }

    String getStringSnakeCase(String text) {
        StringBuilder sb = new StringBuilder();
        int length = text.length();
        if (length > 0) {
            sb.append(Character.toLowerCase(text.charAt(0)));
        }
        for (int i = 1; i < length; i++) {
            char current = text.charAt(i);
            if (Character.isUpperCase(current)) {
                sb.append('_');
                sb.append(Character.toLowerCase(current));
            } else if(isDigit(current) && !isDigit(text.charAt(i - 1))) {
                sb.append('_');
                sb.append(current);
            } else {
                sb.append(current);
            }
        }
        return sb.toString();
    }
}
