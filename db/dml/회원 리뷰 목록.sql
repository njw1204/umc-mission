SELECT
	*
FROM
	store_review
	INNER JOIN store
	INNER JOIN `order`
	INNER JOIN user
	ON
		store.id = store_review.store_id
		AND `order`.id = store_review.order_id
		AND user.id = `order`.user_id
	LEFT OUTER JOIN store_review_reply
	ON
		store_review_reply.review_id = store_review.id
WHERE
	user.id = ?
ORDER BY
	`order`.id DESC
