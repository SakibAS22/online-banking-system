CREATE TABLE transactions(transaction_id BIGINT PRIMARY KEY, account_id BIGINT NOT NULL,
					transaction_type VARCHAR(20) NOT NULL, amount DECIMAL(15,2) NOT NULL,
					description VARCHAR(255), reference_no VARCHAR(50) UNIQUE NOT NULL,
                    balance_after_transaction DECIMAL(15,2) NOT NULL, transaction_at DATETIME NOT NULL,
                    CONSTRAINT fk_transaction_account foreign key (account_id) references accounts(account_id)
                    ON DELETE RESTRICT ON UPDATE CASCADE);