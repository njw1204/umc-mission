UPDATE
	`order`
SET
	status = 1 -- 주문 접수
WHERE
	id = ?;

UPDATE
	order_payment
SET
	pg_payment_id = ?,
	status = 1 -- 결제 완료
WHERE
	id = ?;
