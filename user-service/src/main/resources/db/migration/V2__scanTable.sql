CREATE TABLE `scan` (
    `scanId` BIGINT(20) NOT NULL,
    `userId` BIGINT(20) NOT NULL,
    `scanType` VARCHAR(255),
    `scanStartTime` TIMESTAMP,
    PRIMARY KEY (`scanId`),
    FOREIGN KEY (`userId`) REFERENCES `user`(`id`) ON DELETE CASCADE
);
