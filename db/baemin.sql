DROP TABLE IF EXISTS `store`;

CREATE TABLE `store` (
    `id` BIGINT NOT NULL,
    `category_id` BIGINT NOT NULL,
    `name` VARCHAR(20) NULL,
    `description` TEXT NULL,
    `owner` VARCHAR(20) NULL,
    `phone` VARCHAR(20) NULL,
    `address` VARCHAR(200) NULL,
    `open_time` TIME NULL,
    `close_time` TIME NULL,
    `min_order_amount` INT NULL,
    `delivery_fee` INT NULL,
    `status` TINYINT NULL,
    `created_at` DATETIME NULL
);

DROP TABLE IF EXISTS `store_image`;

CREATE TABLE `store_image` (
    `id` BIGINT NOT NULL,
    `store_id` BIGINT NOT NULL,
    `type` TINYINT NULL,
    `url` VARCHAR(500) NULL,
    `created_at` DATETIME NULL
);

DROP TABLE IF EXISTS `area`;

CREATE TABLE `area` (
    `id` BIGINT NOT NULL,
    `parent_id` BIGINT NOT NULL,
    `name` VARCHAR(20) NULL,
    `latitude` DOUBLE NULL,
    `longitude` DOUBLE NULL,
    `distance` DOUBLE NULL,
    `created_at` DATETIME NULL
);

DROP TABLE IF EXISTS `store_area`;

CREATE TABLE `store_area` (
    `id` BIGINT NOT NULL,
    `store_id` BIGINT NOT NULL,
    `area_id` BIGINT NOT NULL,
    `created_at` DATETIME NULL
);

DROP TABLE IF EXISTS `store_review`;

CREATE TABLE `store_review` (
    `id` BIGINT NOT NULL,
    `store_id` BIGINT NOT NULL,
    `order_id` BIGINT NOT NULL,
    `rating` INT NULL,
    `comment` TEXT NULL,
    `image` VARCHAR(500) NULL,
    `created_at` DATETIME NULL
);

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
    `id` BIGINT NOT NULL,
    `email` VARCHAR(500) NULL,
    `password` VARCHAR(100) NULL,
    `nickname` VARCHAR(20) NULL,
    `phone` VARCHAR(20) NULL,
    `status` TINYINT NULL,
    `created_at` DATETIME NULL
);

DROP TABLE IF EXISTS `store_category`;

CREATE TABLE `store_category` (
    `id` BIGINT NOT NULL,
    `name` VARCHAR(20) NULL,
    `created_at` DATETIME NULL
);

DROP TABLE IF EXISTS `store_review_reply`;

CREATE TABLE `store_review_reply` (
    `id` BIGINT NOT NULL,
    `review_id` BIGINT NOT NULL,
    `comment` TEXT NULL,
    `created_at` DATETIME NULL
);

DROP TABLE IF EXISTS `store_menu`;

CREATE TABLE `store_menu` (
    `id` BIGINT NOT NULL,
    `store_menu_category_id` BIGINT NOT NULL,
    `name` VARCHAR(20) NULL,
    `description` TEXT NULL,
    `price` INT NULL,
    `image` VARCHAR(500) NULL,
    `priority` INT NULL,
    `status` TINYINT NULL,
    `created_at` DATETIME NULL
);

DROP TABLE IF EXISTS `store_menu_category`;

CREATE TABLE `store_menu_category` (
    `id` BIGINT NOT NULL,
    `store_id` BIGINT NOT NULL,
    `name` VARCHAR(20) NULL,
    `description` TEXT NULL,
    `priority` INT NULL,
    `status` TINYINT NULL,
    `created_at` DATETIME NULL
);

DROP TABLE IF EXISTS `store_menu_option`;

CREATE TABLE `store_menu_option` (
    `id` BIGINT NOT NULL,
    `store_menu_id` BIGINT NOT NULL,
    `name` VARCHAR(20) NULL,
    `price` INT NULL,
    `priority` INT NULL,
    `status` TINYINT NULL,
    `created_at` DATETIME NULL
);

DROP TABLE IF EXISTS `order`;

CREATE TABLE `order` (
    `id` BIGINT NOT NULL,
    `user_id` BIGINT NOT NULL,
    `store_id` BIGINT NOT NULL,
    `area_id` BIGINT NOT NULL,
    `coupon_id` BIGINT NOT NULL,
    `address` VARCHAR(200) NULL,
    `memo` TEXT NULL,
    `delivery_fee` INT NULL,
    `status` TINYINT NULL,
    `created_at` DATETIME NULL
);

DROP TABLE IF EXISTS `order_menu`;

CREATE TABLE `order_menu` (
    `id` BIGINT NOT NULL,
    `order_id` BIGINT NOT NULL,
    `store_menu_id` BIGINT NOT NULL,
    `name` VARCHAR(20) NULL,
    `description` TEXT NULL,
    `price` INT NULL,
    `image` VARCHAR(500) NULL,
    `priority` INT NULL,
    `created_at` DATETIME NULL
);

DROP TABLE IF EXISTS `order_menu_option`;

CREATE TABLE `order_menu_option` (
    `id` BIGINT NOT NULL,
    `order_menu_id` BIGINT NOT NULL,
    `store_menu_option_id` BIGINT NOT NULL,
    `name` VARCHAR(20) NULL,
    `price` INT NULL,
    `priority` INT NULL,
    `created_at` DATETIME NULL
);

DROP TABLE IF EXISTS `order_payment`;

CREATE TABLE `order_payment` (
    `id` BIGINT NOT NULL,
    `order_id` BIGINT NOT NULL,
    `type` TINYINT NULL,
    `pg_payment_id` VARCHAR(100) NULL,
    `amount` INT NULL,
    `status` TINYINT NULL,
    `created_at` DATETIME NULL
);

DROP TABLE IF EXISTS `coupon`;

CREATE TABLE `coupon` (
    `id` BIGINT NOT NULL,
    `user_id` BIGINT NOT NULL,
    `coupon_info_id` BIGINT NOT NULL,
    `status` TINYINT NULL,
    `created_at` DATETIME NULL,
    `expired_at` DATETIME NULL
);

DROP TABLE IF EXISTS `coupon_info`;

CREATE TABLE `coupon_info` (
    `id` BIGINT NOT NULL,
    `name` VARCHAR(20) NULL,
    `code` VARCHAR(20) NULL,
    `type` TINYINT NULL,
    `amount` INT NULL,
    `min_order_amount` INT NULL,
    `status` TINYINT NULL,
    `duration` INT NULL,
    `created_at` DATETIME NULL
);

ALTER TABLE `store` ADD CONSTRAINT `PK_STORE` PRIMARY KEY (
    `id`
);

ALTER TABLE `store_image` ADD CONSTRAINT `PK_STORE_IMAGE` PRIMARY KEY (
    `id`
);

ALTER TABLE `area` ADD CONSTRAINT `PK_AREA` PRIMARY KEY (
    `id`
);

ALTER TABLE `store_area` ADD CONSTRAINT `PK_STORE_AREA` PRIMARY KEY (
    `id`
);

ALTER TABLE `store_review` ADD CONSTRAINT `PK_STORE_REVIEW` PRIMARY KEY (
    `id`
);

ALTER TABLE `user` ADD CONSTRAINT `PK_USER` PRIMARY KEY (
    `id`
);

ALTER TABLE `store_category` ADD CONSTRAINT `PK_STORE_CATEGORY` PRIMARY KEY (
    `id`
);

ALTER TABLE `store_review_reply` ADD CONSTRAINT `PK_STORE_REVIEW_REPLY` PRIMARY KEY (
    `id`
);

ALTER TABLE `store_menu` ADD CONSTRAINT `PK_STORE_MENU` PRIMARY KEY (
    `id`
);

ALTER TABLE `store_menu_category` ADD CONSTRAINT `PK_STORE_MENU_CATEGORY` PRIMARY KEY (
    `id`
);

ALTER TABLE `store_menu_option` ADD CONSTRAINT `PK_STORE_MENU_OPTION` PRIMARY KEY (
    `id`
);

ALTER TABLE `order` ADD CONSTRAINT `PK_ORDER` PRIMARY KEY (
    `id`
);

ALTER TABLE `order_menu` ADD CONSTRAINT `PK_ORDER_MENU` PRIMARY KEY (
    `id`
);

ALTER TABLE `order_menu_option` ADD CONSTRAINT `PK_ORDER_MENU_OPTION` PRIMARY KEY (
    `id`
);

ALTER TABLE `order_payment` ADD CONSTRAINT `PK_ORDER_PAYMENT` PRIMARY KEY (
    `id`
);

ALTER TABLE `coupon` ADD CONSTRAINT `PK_COUPON` PRIMARY KEY (
    `id`
);

ALTER TABLE `coupon_info` ADD CONSTRAINT `PK_COUPON_INFO` PRIMARY KEY (
    `id`
);

ALTER TABLE `store` ADD CONSTRAINT `FK_store_category_TO_store_1` FOREIGN KEY (
    `category_id`
)
REFERENCES `store_category` (
    `id`
);

ALTER TABLE `store_image` ADD CONSTRAINT `FK_store_TO_store_image_1` FOREIGN KEY (
    `store_id`
)
REFERENCES `store` (
    `id`
);

ALTER TABLE `area` ADD CONSTRAINT `FK_area_TO_area_1` FOREIGN KEY (
    `parent_id`
)
REFERENCES `area` (
    `id`
);

ALTER TABLE `store_area` ADD CONSTRAINT `FK_store_TO_store_area_1` FOREIGN KEY (
    `store_id`
)
REFERENCES `store` (
    `id`
);

ALTER TABLE `store_area` ADD CONSTRAINT `FK_area_TO_store_area_1` FOREIGN KEY (
    `area_id`
)
REFERENCES `area` (
    `id`
);

ALTER TABLE `store_review` ADD CONSTRAINT `FK_store_TO_store_review_1` FOREIGN KEY (
    `store_id`
)
REFERENCES `store` (
    `id`
);

ALTER TABLE `store_review` ADD CONSTRAINT `FK_order_TO_store_review_1` FOREIGN KEY (
    `order_id`
)
REFERENCES `order` (
    `id`
);

ALTER TABLE `store_review_reply` ADD CONSTRAINT `FK_store_review_TO_store_review_reply_1` FOREIGN KEY (
    `review_id`
)
REFERENCES `store_review` (
    `id`
);

ALTER TABLE `store_menu` ADD CONSTRAINT `FK_store_menu_category_TO_store_menu_1` FOREIGN KEY (
    `store_menu_category_id`
)
REFERENCES `store_menu_category` (
    `id`
);

ALTER TABLE `store_menu_category` ADD CONSTRAINT `FK_store_TO_store_menu_category_1` FOREIGN KEY (
    `store_id`
)
REFERENCES `store` (
    `id`
);

ALTER TABLE `store_menu_option` ADD CONSTRAINT `FK_store_menu_TO_store_menu_option_1` FOREIGN KEY (
    `store_menu_id`
)
REFERENCES `store_menu` (
    `id`
);

ALTER TABLE `order` ADD CONSTRAINT `FK_user_TO_order_1` FOREIGN KEY (
    `user_id`
)
REFERENCES `user` (
    `id`
);

ALTER TABLE `order` ADD CONSTRAINT `FK_store_TO_order_1` FOREIGN KEY (
    `store_id`
)
REFERENCES `store` (
    `id`
);

ALTER TABLE `order` ADD CONSTRAINT `FK_area_TO_order_1` FOREIGN KEY (
    `area_id`
)
REFERENCES `area` (
    `id`
);

ALTER TABLE `order` ADD CONSTRAINT `FK_coupon_TO_order_1` FOREIGN KEY (
    `coupon_id`
)
REFERENCES `coupon` (
    `id`
);

ALTER TABLE `order_menu` ADD CONSTRAINT `FK_order_TO_order_menu_1` FOREIGN KEY (
    `order_id`
)
REFERENCES `order` (
    `id`
);

ALTER TABLE `order_menu` ADD CONSTRAINT `FK_store_menu_TO_order_menu_1` FOREIGN KEY (
    `store_menu_id`
)
REFERENCES `store_menu` (
    `id`
);

ALTER TABLE `order_menu_option` ADD CONSTRAINT `FK_order_menu_TO_order_menu_option_1` FOREIGN KEY (
    `order_menu_id`
)
REFERENCES `order_menu` (
    `id`
);

ALTER TABLE `order_menu_option` ADD CONSTRAINT `FK_store_menu_option_TO_order_menu_option_1` FOREIGN KEY (
    `store_menu_option_id`
)
REFERENCES `store_menu_option` (
    `id`
);

ALTER TABLE `order_payment` ADD CONSTRAINT `FK_order_TO_order_payment_1` FOREIGN KEY (
    `order_id`
)
REFERENCES `order` (
    `id`
);

ALTER TABLE `coupon` ADD CONSTRAINT `FK_user_TO_coupon_1` FOREIGN KEY (
    `user_id`
)
REFERENCES `user` (
    `id`
);

ALTER TABLE `coupon` ADD CONSTRAINT `FK_coupon_info_TO_coupon_1` FOREIGN KEY (
    `coupon_info_id`
)
REFERENCES `coupon_info` (
    `id`
);
