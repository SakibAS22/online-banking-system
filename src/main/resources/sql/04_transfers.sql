CREATE TABLE transfers(transfer_id BIGINT PRIMARY KEY, from_account_id BIGINT NOT NULL,
					to_account_id BIGINT NOT NULL, amount DECIMAL(15,2) NOT NULL, 
                    status VARCHAR(20) NOT NULL, created_at DATETIME NOT NULL, 
                    updated_at DATETIME,
                    CONSTRAINT fk_transfer_from_account foreign key (from_account_id) refErences accounts(account_id)
                    ON DELETE RESTRICT ON UPDATE CASCADE,
                    CONSTRAINT fk_transfer_to_account foreign key(to_account_id) references accounts(account_id)
					ON DELETE RESTRICT ON UPDATE CASCADE);