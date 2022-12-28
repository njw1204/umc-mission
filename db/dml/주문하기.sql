INSERT INTO
	`order` (
		user_id, store_id, area_id, coupon_id, address, memo, delivery_fee,
		status, created_at
	)
VALUE
	(
		?, ?, ?, ?, ?, ?, ?,
		?, ?
	);

INSERT INTO
	order_menu (
		order_id, store_menu_id, name, description, price, image, priority, created_at
	)
VALUE
	(
		?, ?, ?, ?, ?, ?, ?, ?
	);

INSERT INTO
	order_menu_option (
		order_menu_id, store_order_menu_option_id, name, price, priority, created_at
	)
VALUE
	(
		?, ?, ?, ?, ?, ?
	);

INSERT INTO
	order_payment (
		order_id, type, pg_payment_id, amount, status, created_at
	)
VALUE
	(
		?, ?, ?, ?, ?, ?
	);

UPDATE
	coupon
SET
	status = 1 -- 사용 완료
WHERE
	id = ?;
