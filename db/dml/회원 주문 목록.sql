SELECT
    *
FROM
    `order`
    INNER JOIN store
    INNER JOIN order_payment
    INNER JOIN order_menu
    ON
        store.id = `order`.store_id
        AND order_payment.order_id = `order`.id
        AND order_menu.order_id = `order`.id
    LEFT OUTER JOIN order_menu_option
    ON
        order_menu_option.order_menu_id = order_menu.id
    LEFT OUTER JOIN coupon
    ON
        coupon.id = `order`.coupon_id
    LEFT OUTER JOIN coupon_info
    ON
        coupon_info.id = coupon.coupon_info_id
WHERE
    `order`.user_id = ?
ORDER BY
    `order`.id DESC,
    order_menu.priority DESC,
    order_menu_option.priority DESC
