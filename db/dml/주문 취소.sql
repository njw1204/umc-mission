UPDATE
    `order`
SET
    status = 2 -- 주문 취소
WHERE
    id = ?;

UPDATE
    coupon
SET
    status = 0 -- 사용 전
WHERE
    id = ?;

UPDATE
    order_payment
SET
    status = 2 -- 결제 취소
WHERE
    id = ?;
