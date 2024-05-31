CREATE TABLE IF NOT EXISTS users (
                                     id BIGSERIAL PRIMARY KEY,
                                     username VARCHAR(30) NOT NULL,
                                     password VARCHAR(80),
                                     age INT,
                                     flagaccess BOOLEAN
);

CREATE TABLE IF NOT EXISTS roles (
                                     id SERIAL PRIMARY KEY,
                                     name VARCHAR(50) NOT NULL,
                                     authority VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS users_roles (
                                           user_id BIGINT NOT NULL,
                                           role_id INT NOT NULL,
                                           PRIMARY KEY (user_id, role_id),
                                           FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
                                           FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE
);

INSERT INTO roles (name, authority) VALUES
                                        ('ROLE_USER', 'ROLE_USER'),
                                        ('ROLE_ADMIN', 'ROLE_ADMIN')
ON CONFLICT DO NOTHING;

INSERT INTO users (id,username, password, age, flagaccess) VALUES
    (1,'admin', '$2a$12$CdmGYSyo8M3iHZ.oB9k29eyO9WQguoKuysQnlQAtdbtn87B9KZFF6', 30, TRUE)
ON CONFLICT DO NOTHING;

INSERT INTO users (id,username, password, age, flagaccess) VALUES
    (2,'user', '$2a$12$CdmGYSyo8M3iHZ.oB9k29eyO9WQguoKuysQnlQAtdbtn87B9KZFF6', 30, TRUE)
ON CONFLICT DO NOTHING;

INSERT INTO users_roles (user_id, role_id) VALUES
    (1, 2)
ON CONFLICT DO NOTHING;

INSERT INTO users_roles (user_id, role_id) VALUES
    (2, 1)
ON CONFLICT DO NOTHING;
