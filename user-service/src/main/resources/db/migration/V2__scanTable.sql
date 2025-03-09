CREATE TABLE `scan` (
    `scan_id` BIGINT(20) NOT NULL,
    `user_id` BIGINT(20) NOT NULL,
    `scan_type` VARCHAR(255),
    `scan_start_time` TIMESTAMP,
    PRIMARY KEY (`scan_id`),
    FOREIGN KEY (`user_id`) REFERENCES `user`(`id`) ON DELETE CASCADE
);
