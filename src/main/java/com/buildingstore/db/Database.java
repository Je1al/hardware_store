package com.buildingstore.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Доступ к встроенной базе данных H2.
 *
 * База хранится в файле ./data/buildingstore.mv.db рядом с проектом, поэтому
 * данные сохраняются между запусками. Отдельный сервер БД устанавливать не нужно.
 */
public final class Database {

    /** Файловая БД H2; AUTO_SERVER позволяет открывать её одновременно из нескольких соединений. */
    private static final String URL =
            "jdbc:h2:file:./data/buildingstore;AUTO_SERVER=TRUE;DB_CLOSE_DELAY=-1";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    static {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Драйвер H2 не найден в classpath", e);
        }
    }

    private Database() {
    }

    /** Новое соединение с базой. Вызывающий код обязан закрыть его (try-with-resources). */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    /** Создаёт таблицы, если их ещё нет. */
    public static void initSchema() {
        String categories = """
                CREATE TABLE IF NOT EXISTS categories (
                    id   BIGINT PRIMARY KEY,
                    name VARCHAR(255) NOT NULL,
                    slug VARCHAR(255) NOT NULL UNIQUE
                )""";
        String products = """
                CREATE TABLE IF NOT EXISTS products (
                    id          BIGINT PRIMARY KEY,
                    name        VARCHAR(255)  NOT NULL,
                    description VARCHAR(1000),
                    price       DECIMAL(12,2) NOT NULL,
                    stock       INT           NOT NULL,
                    unit        VARCHAR(64),
                    category_id BIGINT        NOT NULL,
                    CONSTRAINT fk_product_category FOREIGN KEY (category_id) REFERENCES categories(id)
                )""";
        try (Connection c = getConnection(); Statement st = c.createStatement()) {
            st.execute(categories);
            st.execute(products);
        } catch (SQLException e) {
            throw new IllegalStateException("Не удалось создать схему БД", e);
        }
    }
}
