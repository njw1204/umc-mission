SELECT
	*,
	IFNULL(AVG(store_review.rating), 0) AS store_rating,
	COUNT(`order`.id) AS order_count
FROM
	store
	INNER JOIN store_image
	INNER JOIN store_area
	ON
		store_image.store_id = store.id
		AND store_area.store_id = store.id
	LEFT OUTER JOIN `order`
	ON
		`order`.store_id = store.id
	LEFT OUTER JOIN store_review
	ON
		store_review.order_id = `order`.id
WHERE
	store.category_id = ?
	AND store_area.area_id = ?
	AND store_image.type = 1 -- 로고 이미지
GROUP BY
	store.id
HAVING
  IFNULL(AVG(store_review.rating), 0) >= ?
ORDER BY
	order_count DESC
