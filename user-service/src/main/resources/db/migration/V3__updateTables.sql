ALTER TABLE `scan`
DROP FOREIGN KEY scan_ibfk_1;

-- Change column type in `user` table
ALTER TABLE `user`
    MODIFY COLUMN `id` VARCHAR(255) NOT NULL;

-- Change column types in `scan` table
ALTER TABLE `scan`
    MODIFY COLUMN `scanId` VARCHAR(255) NOT NULL,
    MODIFY COLUMN `userId` VARCHAR(255) NOT NULL;

-- Drop and re-add foreign key constraint
ALTER TABLE `scan`
    ADD CONSTRAINT scan_user_fk FOREIGN KEY (`userId`) REFERENCES `user`(`id`) ON DELETE CASCADE;