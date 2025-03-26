ALTER TABLE `scan`
    ADD `scan_ip` VARCHAR(255) NOT NULL;
ALTER TABLE scan
    MODIFY COLUMN `elapsed_time` DOUBLE(10,2);