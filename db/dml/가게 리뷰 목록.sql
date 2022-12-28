SELECT
	*
FROM
	store_review
	LEFT OUTER JOIN store_review_reply
	ON
		store_review_reply.review_id = store_review.id
WHERE
	store_review.store_id = ?
ORDER BY
	store_review.id DESC
