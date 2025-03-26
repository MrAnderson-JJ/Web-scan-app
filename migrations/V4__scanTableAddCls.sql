ALTER TABLE `scan`
    ADD `scan_end_time` TIMESTAMP,
    ADD `elapsed_time` DOUBLE(10,10),
    ADD `active` BOOLEAN;