CREATE TABLE login_sessions(session_id BIGINT PRIMARY KEY, user_id BIGINT NOT NULL,
					session_token VARCHAR(100) UNIQUE NOT NULL, login_time DATETIME NOT NULL,
					logout_time DATETIME, is_active BOOLEAN NOT NULL,
                    CONSTRAINT fk_login_session_user foreign key(user_id)references users(user_id)
                    ON DELETE CASCADE ON UPDATE CASCADE);
