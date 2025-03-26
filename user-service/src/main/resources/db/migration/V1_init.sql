CREATE TABLE `user` (
    `id` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
);

-- Create scan table with all necessary columns and constraints
CREATE TABLE `scan` (
    `scan_id` VARCHAR(255) NOT NULL,
    `user_id` VARCHAR(255) NOT NULL,
    `scan_type` VARCHAR(255),
    `scan_start_time` TIMESTAMP,
    `scan_end_time` TIMESTAMP,
    `elapsed_time` DOUBLE(10,2),
    `active` BOOLEAN,
    `scan_ip` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`scan_id`),
    FOREIGN KEY (`user_id`) REFERENCES `user`(`id`) ON DELETE CASCADE
);