CREATE TABLE IF NOT EXISTS users (
                                     id SERIAL PRIMARY KEY,
                                     username VARCHAR(30) NOT NULL,
                                     password VARCHAR(80) NOT NULL,
                                     age INT
);
CREATE TABLE IF NOT EXISTS roles (
                                     id SERIAL PRIMARY KEY,
                                     name VARCHAR(50) NOT NULL
);
CREATE TABLE IF NOT EXISTS users_roles (
                                           user_id BIGINT  NOT NULL,
                                           role_id INT NOT NULL,
                                           PRIMARY KEY (user_id, role_id),
                                           FOREIGN KEY (user_id) REFERENCES users(id) ,
                                           FOREIGN KEY (role_id) REFERENCES roles(id)
);

INSERT INTO roles (name) VALUES
                                        ('ROLE_ADMIN'),
                                        ('ROLE_USER')
ON CONFLICT DO NOTHING;

INSERT INTO users (id,username, password, age) VALUES
    (0,'admin', '$2a$12$CdmGYSyo8M3iHZ.oB9k29eyO9WQguoKuysQnlQAtdbtn87B9KZFF6', 30)
ON CONFLICT DO NOTHING;

INSERT INTO users (id,username, password, age) VALUES
    (2,'user', '$2a$12$CdmGYSyo8M3iHZ.oB9k29eyO9WQguoKuysQnlQAtdbtn87B9KZFF6', 30, TRUE)
ON CONFLICT DO NOTHING;

INSERT INTO users_roles (user_id, role_id) VALUES
    (0, 1)
ON CONFLICT DO NOTHING;

INSERT INTO users_roles (user_id, role_id) VALUES
    (2, 1)
ON CONFLICT DO NOTHING;