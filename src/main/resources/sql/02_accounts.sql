CREATE TABLE accounts(account_id BIGINT PRIMARY KEY, user_id BIGINT NOT NULL, 		
					account_number VARCHAR(20) UNIQUE NOT NULL, account_type 				
                    VARCHAR(20) NOT NULL,balance DECIMAL(15,2) NOT NULL DEFAULT 0.00, 
                    status VARCHAR(20) NOT NULL, 
                    created_at DATETIME NOT NULL, updated_at DATETIME, 
                    CONSTRAINT fk_accounts_user foreign key (user_id) 
					references users(user_id) ON DELETE CASCADE ON UPDATE CASCADE,
                    CONSTRAINT chk_account_balance CHECK(balance >=0));